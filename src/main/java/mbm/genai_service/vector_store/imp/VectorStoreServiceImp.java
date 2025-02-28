package mbm.genai_service.vector_store.imp;

import lombok.extern.slf4j.Slf4j;
import mbm.genai_service.domain.LyricsDataDto;
import mbm.genai_service.domain.LyricsDto;
import mbm.genai_service.domain.TrackDto;
import mbm.genai_service.vector_store.VectorStoreService;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class VectorStoreServiceImp implements VectorStoreService {

    private static final String ADDING_DOCS = "Adding {} documents to the vector store.";
    private static final String DOCS_ADDED = "Documents added to the vector store.";

    private final VectorStore vectorStore;

    public VectorStoreServiceImp(final VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    // This should be per user session
    @Override
    public void addLyrics(final LyricsDataDto lyricsDataDto) {
        final List<LyricsDto> lyrics = lyricsDataDto.lyrics();
        log.info(ADDING_DOCS, lyrics.size());

        final List<Document> documents = lyrics.stream().map(this::toDocument).toList();
        vectorStore.add(documents);
        log.info(DOCS_ADDED);
    }

    private Document toDocument(final LyricsDto lyricsDto) {
        final Map<String, Object> metadata = toMetadata(lyricsDto.track());
        return Document.builder()
                .id(UUID.randomUUID().toString())
                .metadata(metadata)
                .text(lyricsDto.lyrics())
                .build();
    }

    private Map<String, Object> toMetadata(final TrackDto trackDto) {
        return Map.of("artist", trackDto.artist(), "title", trackDto.title());
    }
}

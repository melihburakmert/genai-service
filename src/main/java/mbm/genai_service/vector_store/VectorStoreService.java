package mbm.genai_service.vector_store;

import mbm.genai_service.domain.LyricsDataDto;

public interface VectorStoreService {
    void addLyrics(final LyricsDataDto lyricsDataDto);
}

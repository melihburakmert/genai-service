package mbm.genai_service.subscriber.receiver;

import lombok.extern.slf4j.Slf4j;
import mbm.genai_service.domain.LyricsDataDto;
import mbm.genai_service.session.SessionStateService;
import mbm.genai_service.serialization.SerializationService;
import mbm.genai_service.subscriber.mapper.LyricsMessageMapper;
import mbm.genai_service.subscriber.message.LyricsMessage;
import mbm.genai_service.vector_store.VectorStoreService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LyricsReceiver extends MessageReceiver<LyricsMessage> {

    private final LyricsMessageMapper mapper;
    private final VectorStoreService vectorStoreService;
    private final SessionStateService sessionStateService;

    protected LyricsReceiver(final SerializationService serializationService,
                             final LyricsMessageMapper mapper,
                             final VectorStoreService vectorStoreService,
                             final SessionStateService sessionStateService) {
        super(serializationService, LyricsMessage.class);
        this.mapper = mapper;
        this.vectorStoreService = vectorStoreService;
        this.sessionStateService = sessionStateService;
    }

    @Override
    public void consume(final LyricsMessage message) {
        final LyricsDataDto lyricsDataDto = mapper.map(message);
        log.info("Received lyrics: " + lyricsDataDto.lyrics());

        vectorStoreService.addLyrics(lyricsDataDto);
        sessionStateService.updateSessionState(message.sessionId(), "lyrics", "DONE");
    }
}

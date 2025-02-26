package mbm.genai_service.subscriber.receiver;

import lombok.extern.slf4j.Slf4j;
import mbm.genai_service.domain.LyricsDataDto;
import mbm.genai_service.serialization.SerializationService;
import mbm.genai_service.subscriber.mapper.LyricsMessageMapper;
import mbm.genai_service.subscriber.message.LyricsMessage;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LyricsReceiver extends MessageReceiver<LyricsMessage> {

    private final LyricsMessageMapper mapper;

    protected LyricsReceiver(final SerializationService serializationService, final LyricsMessageMapper mapper) {
        super(serializationService, LyricsMessage.class);
        this.mapper = mapper;
    }

    @Override
    public void consume(final LyricsMessage message) {
        final LyricsDataDto lyricsDataDto = mapper.map(message);
        log.info("Received lyrics: " + lyricsDataDto.lyrics());
    }
}

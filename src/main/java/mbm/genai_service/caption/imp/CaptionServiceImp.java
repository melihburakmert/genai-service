package mbm.genai_service.caption.imp;

import lombok.extern.slf4j.Slf4j;
import mbm.genai_service.caption.CaptionService;
import mbm.genai_service.session.SessionStateService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class CaptionServiceImp implements CaptionService {

    private static final String LYRICS = "lyrics";
    private static final String IMAGE_DESCRIPTION = "imageDescription";

    private final ChatClient captionChatClient;
    private final SessionStateService sessionStateService;

    public CaptionServiceImp(final ChatClient captionChatClient, final SessionStateService sessionStateService) {
        this.captionChatClient = captionChatClient;
        this.sessionStateService = sessionStateService;
    }

    @Override
    public String getCaption(final String sessionId) {
        if (sessionStateService.isSessionComplete(sessionId, Set.of(LYRICS, IMAGE_DESCRIPTION))) {
            log.info("Both lyrics and image description ready. Generating caption...");

            final String description = getDescriptionFromSession(sessionId);
            final String caption = generateCaption(description);

            log.info("Generated Caption: {}", caption);
            return caption;
        } else {
            log.info("Waiting for both lyrics and image processing to complete...");
            return null;
        }
    }

    private String generateCaption(final String description) {
        return captionChatClient.prompt().user(description).call().content();
    }

    private String getDescriptionFromSession(final String sessionId) {
        return (String) sessionStateService.getSessionState(sessionId).get(IMAGE_DESCRIPTION);
    }
}
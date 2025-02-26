package mbm.genai_service.image_analysis.imp;

import lombok.extern.slf4j.Slf4j;
import mbm.genai_service.image_analysis.ImageAnalysisService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageAnalysisServiceImp implements ImageAnalysisService {

    private static final String IMAGE_PROMPT = """
            Describe this image in detail, focusing on its mood, setting, and key visual elements.
            """;
    private final ChatClient imageChatClient;

    public ImageAnalysisServiceImp(final ChatClient imageChatClient) {
        this.imageChatClient = imageChatClient;
    }

    @Override
    public String getImageDescription(final MultipartFile image) {
        final ByteArrayResource resource = getResource(image);
        final Media media = new Media(MimeTypeUtils.IMAGE_JPEG, resource);

        final UserMessage userMessage = new UserMessage(IMAGE_PROMPT, media);
        final Prompt prompt = new Prompt(userMessage);
        return imageChatClient.prompt(prompt).call().content();
    }

    private ByteArrayResource getResource(final MultipartFile image) {
        try {
            return new ByteArrayResource(image.getBytes());
        } catch (final IOException e) {
            log.error("Failed to read file {}", image.getOriginalFilename());
            // TODO: Throw a custom exception
            throw new RuntimeException(e);
        }
    }
}
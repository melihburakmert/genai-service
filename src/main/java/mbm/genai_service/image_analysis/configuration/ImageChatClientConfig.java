package mbm.genai_service.image_analysis.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageChatClientConfig {

    @Bean
    public ChatClient imageChatClient(final ChatClient.Builder builder,
                           final SimpleLoggerAdvisor simpleLoggerAdvisor) {
        return builder
                .defaultAdvisors(simpleLoggerAdvisor)
                .build();
    }
}

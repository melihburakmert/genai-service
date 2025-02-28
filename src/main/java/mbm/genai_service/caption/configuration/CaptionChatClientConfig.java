package mbm.genai_service.caption.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaptionChatClientConfig {

    private static final String CAPTION_PROMPT = """
            You'll be given a photograph description.
            Find a two-liner song lyrics that can be a good caption for the photograph.
            Return the most relevant consecutive two sentences of the song and nothing else.
            """;

    @Bean
    public ChatClient captionChatClient(final ChatClient.Builder builder,
                             final SimpleLoggerAdvisor simpleLoggerAdvisor,
                             final QuestionAnswerAdvisor questionAnswerAdvisor) {
        return builder
                .defaultSystem(CAPTION_PROMPT)
                .defaultAdvisors(simpleLoggerAdvisor, questionAnswerAdvisor)
                .build();
    }
}

package mbm.genai_service.configuration;

import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdvisorConfig {

    @Bean
    SimpleLoggerAdvisor simpleLoggerAdvisor() {
        return new SimpleLoggerAdvisor();
    }

    @Bean
    QuestionAnswerAdvisor questionAnswerAdvisor(final VectorStore vectorStore,
                                                final SearchRequest searchRequest) {
        return new QuestionAnswerAdvisor(vectorStore, searchRequest);
    }

    @Bean
    SearchRequest searchRequest() {
        return SearchRequest.builder().similarityThresholdAll().topK(10).build();
    }

    @Bean
    MessageChatMemoryAdvisor messageChatMemoryAdvisor(final ChatMemory inMemoryChatMemory) {
        return new MessageChatMemoryAdvisor(inMemoryChatMemory);
    }

    // TODO: Replace with CassandraChatMemory
    @Bean
    ChatMemory inMemoryChatMemory() {
        return new InMemoryChatMemory();
    }
}

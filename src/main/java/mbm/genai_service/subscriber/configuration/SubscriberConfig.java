package mbm.genai_service.subscriber.configuration;

import mbm.genai_service.subscriber.receiver.LyricsReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class SubscriberConfig {

    @Bean
    public Consumer<Message<String>> lyricsConsumer(final LyricsReceiver receiver) {
        return receiver::receive;
    }
}
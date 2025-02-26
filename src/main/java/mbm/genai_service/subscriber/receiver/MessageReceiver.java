package mbm.genai_service.subscriber.receiver;

import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.extern.slf4j.Slf4j;
import mbm.genai_service.serialization.SerializationService;
import org.springframework.messaging.Message;

@Slf4j
abstract class MessageReceiver<T> {
  private static final String INFO_MESSAGE = "Pulled %s message: %s";
  private static final String ERROR = "An error has occurred while consuming message: %n%s%n";

  private final SerializationService serializationService;
  private final Class<T> clazz;

  protected MessageReceiver(final SerializationService serializationService, final Class<T> clazz) {
    this.serializationService = serializationService;
    this.clazz = clazz;
  }

  public void receive(final Message<String> message) {
    GcpPubSubHeaders.getOriginalMessage(message).ifPresent(BasicAcknowledgeablePubsubMessage::ack);
    final String payload = message.getPayload();
    log.info(String.format(INFO_MESSAGE, clazz.getSimpleName(), payload));
    final T deserialized = serializationService.deserialize(payload, clazz);
    consumeWithErrorHandling(payload, deserialized);
  }

  private void consumeWithErrorHandling(final String message, final T t) {
    try {
      consume(t);
    } catch (final Exception e) {
      log.error(String.format(ERROR, message), e);
    }
  }

  public abstract void consume(T message);}

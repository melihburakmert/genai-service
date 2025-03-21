package mbm.genai_service.serialization.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mbm.genai_service.serialization.SerializationService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SerializationServiceImp implements SerializationService {
  private static final String ERROR_DESERIALIZE = "Unable to deserialize %s message: %s";

  private final ObjectMapper objectMapper;

  public SerializationServiceImp(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public <T> T deserialize(final String message, final Class<T> clazz) {
    try {
      return objectMapper.readValue(message, clazz);
    } catch (final JsonProcessingException e) {
      throw new MessageMapperDeserializationException(clazz, message, e);
    }
  }

  private static class MessageMapperDeserializationException extends RuntimeException {
    public <T> MessageMapperDeserializationException(
        final Class<T> clazz, final String message, final Exception e) {
      super(String.format(ERROR_DESERIALIZE, clazz.getSimpleName(), message), e);
    }
  }
}

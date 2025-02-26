package mbm.genai_service.serialization;

public interface SerializationService {
  <T> T deserialize(String message, Class<T> clazz);
}

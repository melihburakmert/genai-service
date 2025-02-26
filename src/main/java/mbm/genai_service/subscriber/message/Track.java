package mbm.genai_service.subscriber.message;

import java.io.Serializable;

public record Track(String artist, String title) implements Serializable {
}

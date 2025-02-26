package mbm.genai_service.subscriber.message;

import java.io.Serializable;

public record Lyrics(Track track, String lyrics) implements Serializable {
}

package mbm.genai_service.subscriber.message;

import java.io.Serializable;
import java.util.List;

public record LyricsMessage(List<Lyrics> lyrics, String sessionId) implements Serializable {
}

package mbm.genai_service.session;

import java.util.Map;
import java.util.Set;

public interface SessionStateService {
    void updateSessionState(String sessionId, String key, String value);

    Map<Object, Object> getSessionState(String sessionId);

    boolean isSessionComplete(String sessionId, Set<String> requiredKeys);
}

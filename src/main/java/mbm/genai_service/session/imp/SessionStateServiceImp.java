package mbm.genai_service.session.imp;

import mbm.genai_service.session.SessionStateService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class SessionStateServiceImp implements SessionStateService {

    private static final String SESSION_PREFIX = "session:";
    private static final long EXPIRATION_TIME = 10;

    private final StringRedisTemplate redisTemplate;

    public SessionStateServiceImp(final StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void updateSessionState(final String sessionId, final String key, final String value) {
        final String sessionKey = SESSION_PREFIX + sessionId;
        redisTemplate.opsForHash().put(sessionKey, key, value);
        redisTemplate.expire(sessionKey, EXPIRATION_TIME, TimeUnit.MINUTES);
    }

    @Override
    public Map<Object, Object> getSessionState(final String sessionId) {
        return redisTemplate.opsForHash().entries(SESSION_PREFIX + sessionId);
    }

    @Override
    public boolean isSessionComplete(final String sessionId, final Set<String> requiredKeys) {
        final Map<Object, Object> session = getSessionState(sessionId);
        return requiredKeys.stream().allMatch(session::containsKey);
    }
}

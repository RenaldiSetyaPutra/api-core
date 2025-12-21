package renaldi.setya.putra.apicore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void putCache(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void putCache(String key, Object value, long ttlSeconds) {
        redisTemplate.opsForValue()
                .set(key, value, ttlSeconds, TimeUnit.SECONDS);
    }

    public Object getCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public <T> T getCache(String key, Class<T> type) {
        Object value = redisTemplate.opsForValue().get(key);
        return type.cast(value);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

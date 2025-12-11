package renaldi.setya.putra.apicore.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import renaldi.setya.putra.apicore.service.CacheService;

@Component
@RequiredArgsConstructor
public class CacheUtil {
    private final CacheService cacheService;

    public void putCache(String key, Object value) {
        cacheService.putCache(key, value);
    }

    public void putCacheWithTtl(String key, Object value, long ttl) {
        cacheService.putCache(key, value, ttl);
    }

    public Object getCache(String key) {
        return cacheService.getCache(key);
    }

    public <T> T getCache(String key, Class<T> clazz) {
        return cacheService.getCache(key, clazz);
    }

    public void removeCache(String key) {
        cacheService.delete(key);
    }
}

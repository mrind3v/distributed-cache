import java.util.Map;

public interface EvictionPolicy {
    // onGet() and onPut() methods  to put recently accessed keys to the tail
    void onGet(CacheKey key);
    void onPut(CacheKey key);
    CacheKey evict(Map<CacheKey, CacheEntry> storage);
}

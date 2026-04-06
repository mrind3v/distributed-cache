import java.util.HashMap;
import java.util.Map;

public class CacheStore {
        Map<CacheKey, CacheEntry> storage = new HashMap();
    EvictionPolicy evictionPolicy;
    long maxSizeBytes;
    long currentSizeBytes = 0;

    CacheStore(EvictionPolicy evictionPolicy, long maxSizeBytes) {
        this.evictionPolicy = evictionPolicy;
        this.maxSizeBytes = maxSizeBytes;
    }

    CacheValue get(CacheKey key) {
        CacheEntry entry = storage.get(key);
        if (entry == null) return null;

        if (entry.isExpired()) {
            storage.remove(key);
            currentSizeBytes -= entry.value.getSizeBytes();
            return null;
        }

        evictionPolicy.onGet(key);
        return entry.value;
    }

    void put(CacheKey key, CacheValue value, long ttlSeconds) {
        // evict until there's room
        while (currentSizeBytes + value.getSizeBytes() > maxSizeBytes) {
            CacheKey evicted = evictionPolicy.evict(storage);
            currentSizeBytes -= storage.get(evicted).value.getSizeBytes();
            storage.remove(evicted);
        }

        CacheEntry entry = new CacheEntry(value, ttlSeconds);
        storage.put(key, entry);
        currentSizeBytes += value.getSizeBytes();
        evictionPolicy.onPut(key);
    }

    // overload with no TTL
    void put(CacheKey key, CacheValue value) {
        put(key, value, -1);
    }

    // used by prefetcher to peek at metadata without triggering onGet
    CacheEntry getEntry(CacheKey key) {
        return storage.get(key);
    }
}

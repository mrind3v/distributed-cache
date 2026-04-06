import java.util.LinkedHashMap;
import java.util.Map;

public class LRUEvictionPolicy implements EvictionPolicy {
    LinkedHashMap<CacheKey, Boolean> accessOrder;

    LRUEvictionPolicy(int maxEntries) {
        // true = order by access, false = order by insertion
        this.accessOrder = new LinkedHashMap(maxEntries, 0.7F, true);
    }

    @Override
    public void onGet(CacheKey key) {
        accessOrder.get(key);       // moves key to tail internally
    }

    @Override
    public void onPut(CacheKey key) {
        accessOrder.put(key, true); // new key goes to tail
    }

    @Override
    public CacheKey evict(Map<CacheKey, CacheEntry> storage) {
        CacheKey lruKey = accessOrder.keySet().iterator().next();
        accessOrder.remove(lruKey);
        return lruKey;
    }

}

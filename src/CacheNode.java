public class CacheNode {
        String nodeId;
    CacheStore store;

    CacheNode(String nodeId, CacheStore store) {
        this.nodeId = nodeId;
        this.store = store;
    }

    CacheValue get(CacheKey key) {
        return store.get(key);
    }

    void put(CacheKey key, CacheValue value, long ttlSeconds) {
        store.put(key, value, ttlSeconds);
    }

    void put(CacheKey key, CacheValue value) {
        store.put(key, value);
    }
}

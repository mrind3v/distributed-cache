public class CacheClient {
        LoadBalancer loadBalancer;

    CacheClient(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    CacheValue get(CacheKey key) {
        CacheNode node = loadBalancer.getNode(key);
        return node.get(key);
    }

    void put(CacheKey key, CacheValue value) {
        CacheNode node = loadBalancer.getNode(key);
        node.put(key, value);
    }

    void put(CacheKey key, CacheValue value, long ttlSeconds) {
        CacheNode node = loadBalancer.getNode(key);
        node.put(key, value, ttlSeconds);
    }
}

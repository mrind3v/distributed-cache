public interface LoadBalancer {
    CacheNode getNode(CacheKey key);
    void addNode(CacheNode node);
    void removeNode(String nodeId);
}

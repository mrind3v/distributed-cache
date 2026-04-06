public class CacheEntry {
    CacheValue value;
    long createdAt;
    long lastUpdatedAt;
    long ttlSeconds;        // -1 means no TTL

    CacheEntry(CacheValue value, long ttlSeconds) {
        this.value = value;
        this.createdAt = System.currentTimeMillis();
        this.lastUpdatedAt = this.createdAt;
        this.ttlSeconds = ttlSeconds;
    }

    boolean isExpired() {
        if (ttlSeconds == -1) return false;
        long elapsed = System.currentTimeMillis() - lastUpdatedAt;
        return elapsed > ttlSeconds * 1000;
    }
}

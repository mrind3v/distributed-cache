import java.util.ArrayList;
import java.util.List;

public class RoundRobinBalancer implements LoadBalancer {
    List<CacheNode> nodes = new ArrayList();
    int counter = 0;

    public void addNode(CacheNode node) {
        nodes.add(node);
    }

    public void removeNode(String nodeId) {
        nodes.removeIf(n -> n.nodeId.equals(nodeId));
    }

    public CacheNode getNode(CacheKey key) {
        if (nodes.isEmpty()) try {
            throw new Exception("No nodes available");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CacheNode node = nodes.get(counter % nodes.size());
        counter++;
        return node;
    }
}

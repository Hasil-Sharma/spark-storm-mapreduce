package storm.pagerank.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StaticUndirectedGraph {
    public Map<Integer, Set<Integer>> _vertices = null;

    public StaticUndirectedGraph() {
        _vertices = new HashMap<Integer, Set<Integer>>();
    }

    public void insertEdge(int sourceNode, int targetNode){
        if(!_vertices.containsKey(sourceNode)){
            _vertices.put(sourceNode, new HashSet<Integer>());
        }
        if(!_vertices.containsKey(targetNode)){
            _vertices.put(targetNode, new HashSet<Integer>());
        }
        _vertices.get(sourceNode).add(targetNode);
        _vertices.get(targetNode).add(sourceNode);
    }
}

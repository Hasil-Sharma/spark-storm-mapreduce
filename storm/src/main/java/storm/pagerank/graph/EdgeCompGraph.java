package storm.pagerank.graph;

import java.util.HashMap;
import java.util.Map;

public class EdgeCompGraph<T>{
    public Map<Integer, Map<Integer, T>> _edges = null;

    public EdgeCompGraph() {
        _edges = new HashMap<Integer, Map<Integer, T>>();
    }

    public void setValue(int sourceNode, int targetNode, int value){
    }
}

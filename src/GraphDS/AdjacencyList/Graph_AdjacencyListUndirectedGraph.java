package GraphDS.AdjacencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph_AdjacencyListUndirectedGraph {
    List<GraphNodeAdjacencyList> nodeList;

    public Graph_AdjacencyListUndirectedGraph(List<GraphNodeAdjacencyList> nodeList) {
        this.nodeList = nodeList;
    }

    public void addUndirectedEdges(int firstIndex, int secondIndex) {
        nodeList.get(firstIndex).getNeighborsList().add(nodeList.get(secondIndex));
        nodeList.get(secondIndex).getNeighborsList().add(nodeList.get(firstIndex));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        nodeList.stream().forEach(g -> {
            s.append(g.getValue()+" : "+ g.getNeighborsList().stream().map(v -> v.getValue()).collect(Collectors.joining(" -> ")));
            s.append("\n");
        });
        return s.toString();
    }
}

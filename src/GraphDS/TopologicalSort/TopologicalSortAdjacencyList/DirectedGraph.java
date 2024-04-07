package GraphDS.TopologicalSort.TopologicalSortAdjacencyList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class DirectedGraph {
    private List<GraphNode> nodeList;

    public DirectedGraph(List<GraphNode> nodeList) {
        this.nodeList = nodeList;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        nodeList.stream().forEach(g -> {
            s.append(g.getValue()+" : "+ g.getNeighbors().stream().map(v -> v.getValue()).collect(Collectors.joining(" -> ")));
            s.append("\n");
        });
        return s.toString();
    }

    public void addEdgeOfDirectedGraph(int i, int j) {
        GraphNode firstNode = nodeList.get(i);
        GraphNode secondNode = nodeList.get(j);
        firstNode.getNeighbors().add(secondNode);
    }

    public void topologySort() {
        Stack<GraphNode> nodeStack = new Stack<>();
        for(GraphNode g: nodeList) {
            if(!g.isVisited()) {
                topologySortVisit(g, nodeStack);
            }
        }
        // print stack from bottom
        List<String> collect = nodeStack.stream().map(v -> v.getValue()).collect(Collectors.toList());
        Collections.reverse(collect);
        collect.forEach(System.out::print);
        // TODO - Try other approaches as well to print stack from bottom
    }

    private void topologySortVisit(GraphNode node, Stack<GraphNode> stack) {
        for(GraphNode neighbor : node.getNeighbors()) {
            if(!neighbor.isVisited()) {
                topologySortVisit(neighbor, stack);
            }
        }
        node.setVisited(true);
        stack.push(node);
    }
}

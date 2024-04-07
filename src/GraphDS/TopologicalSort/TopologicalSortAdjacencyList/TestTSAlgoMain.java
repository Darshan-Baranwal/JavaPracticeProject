package GraphDS.TopologicalSort.TopologicalSortAdjacencyList;

import java.util.*;

public class TestTSAlgoMain {

    public static void main(String[] args) {
        List<GraphNode> nodeList = new ArrayList<>();
        nodeList.add(new GraphNode(0,"A"));
        nodeList.add(new GraphNode(1,"B"));
        nodeList.add(new GraphNode(2,"C"));
        nodeList.add(new GraphNode(3,"D"));
        nodeList.add(new GraphNode(4,"E"));
        nodeList.add(new GraphNode(5,"F"));
        nodeList.add(new GraphNode(6,"G"));
        nodeList.add(new GraphNode(7,"H"));

        DirectedGraph myGraph = new DirectedGraph(nodeList);
        myGraph.addEdgeOfDirectedGraph(0,2);
        myGraph.addEdgeOfDirectedGraph(1,2);
        myGraph.addEdgeOfDirectedGraph(1,3);
        myGraph.addEdgeOfDirectedGraph(2,4);
        myGraph.addEdgeOfDirectedGraph(3,5);
        myGraph.addEdgeOfDirectedGraph(4,7);
        myGraph.addEdgeOfDirectedGraph(4,5);
        myGraph.addEdgeOfDirectedGraph(5,6);

        System.out.println(myGraph.toString());
        myGraph.topologySort();
    }

}

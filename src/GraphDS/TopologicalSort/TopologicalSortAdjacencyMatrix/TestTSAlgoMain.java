package GraphDS.TopologicalSort.TopologicalSortAdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;

public class TestTSAlgoMain {
    public static void main(String[] args) {
        List<GraphNodeForMatrix> nodeList = new ArrayList<>();
        nodeList.add(new GraphNodeForMatrix(0,"A"));
        nodeList.add(new GraphNodeForMatrix(1,"B"));
        nodeList.add(new GraphNodeForMatrix(2,"C"));
        nodeList.add(new GraphNodeForMatrix(3,"D"));
        nodeList.add(new GraphNodeForMatrix(4,"E"));
        nodeList.add(new GraphNodeForMatrix(5,"F"));
        nodeList.add(new GraphNodeForMatrix(6,"G"));
        nodeList.add(new GraphNodeForMatrix(7,"H"));

        DirectedGraphForMatrix myGraph = new DirectedGraphForMatrix(nodeList);
        myGraph.addEdgeOfDirectGraph(0,2);
        myGraph.addEdgeOfDirectGraph(1,2);
        myGraph.addEdgeOfDirectGraph(1,3);
        myGraph.addEdgeOfDirectGraph(2,4);
        myGraph.addEdgeOfDirectGraph(3,5);
        myGraph.addEdgeOfDirectGraph(4,7);
        myGraph.addEdgeOfDirectGraph(4,5);
        myGraph.addEdgeOfDirectGraph(5,6);

        System.out.println(myGraph.toString());
        myGraph.topologySort();
    }
}

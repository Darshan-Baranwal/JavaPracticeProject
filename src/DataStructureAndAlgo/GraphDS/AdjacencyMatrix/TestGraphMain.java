package DataStructureAndAlgo.GraphDS.AdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;

public class TestGraphMain {

    public static void main(String[] args) {
        List<GraphNodeForAdjacencyMatrix> nodeList = new ArrayList<>();
        nodeList.add(new GraphNodeForAdjacencyMatrix(0,"A"));
        nodeList.add(new GraphNodeForAdjacencyMatrix(1,"B"));
        nodeList.add(new GraphNodeForAdjacencyMatrix(2,"C"));
        nodeList.add(new GraphNodeForAdjacencyMatrix(3,"D"));
        nodeList.add(new GraphNodeForAdjacencyMatrix(4,"E"));
        Graph_AdjacencyMatrixUndirectedGraph myGraph = new Graph_AdjacencyMatrixUndirectedGraph(nodeList);

//        myGraph.addUndirectedEdges(0, Collections.unmodifiableList(Arrays.asList(1, 2, 3))); // Node/Vertex "A" is mapped to -> "B", "C", "D"
//        myGraph.addUndirectedEdges(1, Collections.unmodifiableList(Arrays.asList(0, 4))); // Node/Vertex "B" is mapped to -> "A", "E"
        // Above approach is not good approach duplicate mapping occur like "B" has to map with "A"(0) again, but for large number of node this seems good to go as manual mapping
        // seems not possible
        myGraph.addUndirectedEdges(0,1);
        myGraph.addUndirectedEdges(0,2);
        myGraph.addUndirectedEdges(0,3);
        myGraph.addUndirectedEdges(1,4);
        myGraph.addUndirectedEdges(2,3);
        myGraph.addUndirectedEdges(3,4);
//        System.out.println(myGraph.toString());
        try {
            myGraph.bfs(0);
            nodeList.forEach(v->v.setVisited(false));
            myGraph.dfs(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

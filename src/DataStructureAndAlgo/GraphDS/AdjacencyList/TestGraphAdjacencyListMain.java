package DataStructureAndAlgo.GraphDS.AdjacencyList;

import java.util.ArrayList;
import java.util.List;

public class TestGraphAdjacencyListMain {

    public static void main(String[] args) {
        List<GraphNodeAdjacencyList> nodeAdjacencyList = new ArrayList<>();
//        nodeAdjacencyList.add(new GraphNodeAdjacencyList(0,"A"));
//        nodeAdjacencyList.add(new GraphNodeAdjacencyList(1,"B"));
//        nodeAdjacencyList.add(new GraphNodeAdjacencyList(2,"C"));
//        nodeAdjacencyList.add(new GraphNodeAdjacencyList(3,"D"));
//        nodeAdjacencyList.add(new GraphNodeAdjacencyList(4,"E"));

        nodeAdjacencyList.add(new GraphNodeAdjacencyList(0,"A"));
        nodeAdjacencyList.add(new GraphNodeAdjacencyList(1,"B"));
        nodeAdjacencyList.add(new GraphNodeAdjacencyList(2,"C"));
        nodeAdjacencyList.add(new GraphNodeAdjacencyList(3,"D"));
        nodeAdjacencyList.add(new GraphNodeAdjacencyList(4,"E"));
        nodeAdjacencyList.add(new GraphNodeAdjacencyList(5,"F"));
        nodeAdjacencyList.add(new GraphNodeAdjacencyList(6,"G"));

        Graph_AdjacencyListUndirectedGraph myGraph = new Graph_AdjacencyListUndirectedGraph(nodeAdjacencyList);
//        myGraph.addUndirectedEdges(0,1);
//        myGraph.addUndirectedEdges(0,2);
//        myGraph.addUndirectedEdges(0,3);
//        myGraph.addUndirectedEdges(1,4);
//        myGraph.addUndirectedEdges(2,3);
//        myGraph.addUndirectedEdges(3,4);

        myGraph.addUndirectedEdges(0,1);
        myGraph.addUndirectedEdges(0,2);
        myGraph.addUndirectedEdges(2,3);
        myGraph.addUndirectedEdges(2,4);
        myGraph.addUndirectedEdges(1,3);
        myGraph.addUndirectedEdges(1,6);

        myGraph.addUndirectedEdges(3,5);
        myGraph.addUndirectedEdges(4,5);
        myGraph.addUndirectedEdges(5,6);

        System.out.println(myGraph.toString());

        myGraph.bfsAlgo(0);
        nodeAdjacencyList.forEach(v->v.setVisited(false));
        myGraph.dfsAlgo(0);
    }
}

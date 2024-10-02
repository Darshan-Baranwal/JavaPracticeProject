package DataStructureAndAlgo.GraphDS.TopologicalSort.TopologicalSortAdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DirectedGraphForMatrix {

    List<GraphNodeForMatrix> nodeList;
    int[][] adjacencyMatrix;

    public DirectedGraphForMatrix(List<GraphNodeForMatrix> nodeList) {
        this.nodeList = nodeList;
        adjacencyMatrix = new int[nodeList.size()][nodeList.size()];
    }

    public void addEdgeOfDirectGraph(int i, int j) {
        adjacencyMatrix[i][j]=1;
    }
    public ArrayList<GraphNodeForMatrix> getAllNeighbors(GraphNodeForMatrix node) {
        ArrayList<GraphNodeForMatrix> neighbors = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[node.getIndex()][i]==1) {
                neighbors.add(nodeList.get(i));
            }
        }
        return neighbors;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (GraphNodeForMatrix g :
                nodeList) {
            s.append("  "+g.getValue());
        }
        s.append("\n");
        for (int i = 0; i < adjacencyMatrix[0].length; i++) {
            s.append(nodeList.get(i).getValue()+" ");
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                s.append(adjacencyMatrix[i][j]+"  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public void topologySort() {
        Stack<GraphNodeForMatrix> nodeStack = new Stack<>();
        for (GraphNodeForMatrix g : nodeList) {
            if (!g.isVisited()) {
                topologySortRecursion(g, nodeStack);
            }
        }
        while(!nodeStack.isEmpty()) {
            System.out.print(nodeStack.pop().getValue() + " ");
        }
    }

    private void topologySortRecursion(GraphNodeForMatrix g, Stack<GraphNodeForMatrix> nodeStack) {
        ArrayList<GraphNodeForMatrix> neighbors = getAllNeighbors(g);
        for (GraphNodeForMatrix neighbor :
                neighbors) {
            if (!neighbor.isVisited()) {
                topologySortRecursion(neighbor, nodeStack);
            }
        }
        g.setVisited(true);
        nodeStack.add(g);
    }

}

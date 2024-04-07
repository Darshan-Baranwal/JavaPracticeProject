package GraphDS.AdjacencyMatrix;

import java.util.*;

/*
Graph DS representation requires 2*2 matrix
If Undirected Graph then Both way matrix index need to be updated
 */
public class Graph_AdjacencyMatrixUndirectedGraph {
    private int[][] matrix;
    private List<GraphNodeForAdjacencyMatrix> nodeList;

    public Graph_AdjacencyMatrixUndirectedGraph(List<GraphNodeForAdjacencyMatrix> nodeList) {
        this.nodeList = nodeList;
        this.matrix = new int[nodeList.size()][nodeList.size()];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (GraphNodeForAdjacencyMatrix g :
                nodeList) {
            s.append("  "+g.getValue());
        }
        s.append("\n");
        for (int i = 0; i < matrix[0].length; i++) {
            s.append(nodeList.get(i).getValue()+" ");
            for (int j = 0; j < matrix[0].length; j++) {
                s.append(matrix[i][j]+"  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

//    public void addUndirectedEdges(int i, List<Integer> list) {
//        list.stream().mapToInt(Integer::valueOf).forEach(j -> {
//            matrix[i][j] = 1;
//            matrix[j][i] = 1;
//        });
//    }
    public void addUndirectedEdges(int i, int j) {
            matrix[i][j] = 1;
            matrix[j][i] = 1;
    }

    public ArrayList<GraphNodeForAdjacencyMatrix> getAllNeighborsForTheNode(GraphNodeForAdjacencyMatrix currentNode) {
        ArrayList<GraphNodeForAdjacencyMatrix> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[currentNode.getIndex()][i]==1) {
                neighbors.add(nodeList.get(i));
            }
        }
        return neighbors;
    }

    /*
    LinkedList can be used as queue
     */
    public void bfs(int index) throws Exception {
        // apply for loop if unconnected graph is mentioned

//        for(GraphNodeAdjacencyList gn: nodeList) {
//            if(!gn.isVisited()) {
//                // paste below code
//            }
//        }
        if(index>nodeList.size()) throw new Exception("Index is greater than the size of the Graph");
        Queue<GraphNodeForAdjacencyMatrix> nodesQueue = new LinkedList<>();
        nodesQueue.add(nodeList.get(index));
        while(!nodesQueue.isEmpty()) {
            GraphNodeForAdjacencyMatrix currentNode = nodesQueue.poll();
            currentNode.setVisited(true);
            System.out.print(currentNode.getValue()+" ");
            ArrayList<GraphNodeForAdjacencyMatrix> neighbors = this.getAllNeighborsForTheNode(currentNode);
            for (GraphNodeForAdjacencyMatrix g : neighbors) {
                if (!g.isVisited()) {
                    nodesQueue.add(g);
                    g.setVisited(true);
                }
            }
        }
        System.out.println("\n--------------------------------");
    }

    /*
    DFS uses Stack, rest all logic is same
     */
    public void dfs(int index) throws Exception {
        // apply for loop if unconnected graph is mentioned

//        for(GraphNodeAdjacencyList gn: nodeList) {
//            if(!gn.isVisited()) {
//                // paste below code
//            }
//        }
        if(index>nodeList.size()) throw new Exception("Index is greater than the size of the Graph");
        Stack<GraphNodeForAdjacencyMatrix> nodesStack = new Stack<>();
        nodesStack.add(nodeList.get(index));
        while(!nodesStack.isEmpty()) {
            GraphNodeForAdjacencyMatrix currentNode = nodesStack.pop();
            currentNode.setVisited(true);
            System.out.print(currentNode.getValue()+" ");
            ArrayList<GraphNodeForAdjacencyMatrix> neighbors = this.getAllNeighborsForTheNode(currentNode);
            for (GraphNodeForAdjacencyMatrix g : neighbors) {
                if (!g.isVisited()) {
                    nodesStack.add(g);
                    g.setVisited(true);
                }
            }
        }
    }
}

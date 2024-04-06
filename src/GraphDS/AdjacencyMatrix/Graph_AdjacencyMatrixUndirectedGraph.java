package GraphDS.AdjacencyMatrix;

import java.util.List;

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
}

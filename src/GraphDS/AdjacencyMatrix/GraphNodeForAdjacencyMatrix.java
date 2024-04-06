package GraphDS.AdjacencyMatrix;

/*
Graph node/vertex(vertices) for Adjacency matrix should contain ID-index and value
 */
public class GraphNodeForAdjacencyMatrix {
private int index;
private String value;

GraphNodeForAdjacencyMatrix(int index, String value) {
    this.index = index;
    this.value = value;
}

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}

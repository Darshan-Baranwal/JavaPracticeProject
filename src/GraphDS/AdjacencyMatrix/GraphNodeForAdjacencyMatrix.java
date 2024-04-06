package GraphDS.AdjacencyMatrix;

import java.util.ArrayList;

/*
Graph node/vertex(vertices) for Adjacency matrix should contain ID-index and value
 */
public class GraphNodeForAdjacencyMatrix {
private int index;
private String value;
private boolean isVisited = false;

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
    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}

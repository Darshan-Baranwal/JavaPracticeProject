package DataStructureAndAlgo.GraphDS.SSSPP;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private int index;
    private String value;

    private boolean isVisited = false;
    private List<GraphNode> neighbors = new ArrayList<>();

    public GraphNode(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public List<GraphNode> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<GraphNode> neighbors) {
        this.neighbors = neighbors;
    }
}

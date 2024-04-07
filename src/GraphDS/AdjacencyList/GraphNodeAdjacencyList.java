package GraphDS.AdjacencyList;

import java.util.ArrayList;
import java.util.List;

public class GraphNodeAdjacencyList {
    private int index;
    private List<GraphNodeAdjacencyList> neighborsList = new ArrayList<>();

    private boolean isVisited = false;

    GraphNodeAdjacencyList(int index, String value) {
        this.index = index;
        this.value = value;
    }
    private String value;

    public int getIndex() {
        return index;
    }

    public List<GraphNodeAdjacencyList> getNeighborsList() {
        return neighborsList;
    }

    public String getValue() {
        return value;
    }

    public void setNeighborsList(List<GraphNodeAdjacencyList> neighborsList) {
        this.neighborsList = neighborsList;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}

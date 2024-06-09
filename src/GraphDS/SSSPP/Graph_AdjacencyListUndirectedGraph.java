package GraphDS.SSSPP;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Graph_AdjacencyListUndirectedGraph {
    List<GraphNodeAdjacencyList> nodeList;

    public Graph_AdjacencyListUndirectedGraph(List<GraphNodeAdjacencyList> nodeList) {
        this.nodeList = nodeList;
    }

    public void addUndirectedEdges(int firstIndex, int secondIndex) {
        nodeList.get(firstIndex).getNeighborsList().add(nodeList.get(secondIndex));
        nodeList.get(secondIndex).getNeighborsList().add(nodeList.get(firstIndex));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        nodeList.stream().forEach(g -> {
            s.append(g.getValue()+" : "+ g.getNeighborsList().stream().map(v -> v.getValue()).collect(Collectors.joining(" -> ")));
            s.append("\n");
        });
        return s.toString();
    }

    public void bfsAlgo(int startingIndex) {
        // apply for loop if unconnected graph is mentioned

//        for(GraphNodeAdjacencyList gn: nodeList) {
//            if(!gn.isVisited()) {
//                // paste below code
//            }
//        }
        Queue<GraphNodeAdjacencyList> nodeQueue = new LinkedList<>();
        GraphNodeAdjacencyList startingGraphNode = nodeList.get(startingIndex);
        nodeQueue.add(startingGraphNode);
        System.out.print("BFS traversal result ->  ");
        while(!nodeQueue.isEmpty()) {
            GraphNodeAdjacencyList currentGraphNode = nodeQueue.poll();
            currentGraphNode.setVisited(true);
            System.out.print(currentGraphNode.getValue()+ " ");
            for (GraphNodeAdjacencyList g : currentGraphNode.getNeighborsList()) {
                if(!g.isVisited()) {
                    g.setVisited(true);
                    nodeQueue.add(g);
                }
            }
        }
        System.out.println("\n--------------------------------");
    }


    /*
    DFS uses Stack, rest all logic is same
     */
    public void dfsAlgo(int startingIndex) {

        // apply for loop if unconnected graph is mentioned

//        for(GraphNodeAdjacencyList gn: nodeList) {
//            if(!gn.isVisited()) {
//                // paste below code
//            }
//        }

        Stack<GraphNodeAdjacencyList> nodeStack = new Stack<>();
        GraphNodeAdjacencyList startingGraphNode = nodeList.get(startingIndex);
        nodeStack.add(startingGraphNode);
        System.out.print("DFS traversal result ->  ");
        while(!nodeStack.isEmpty()) {
            GraphNodeAdjacencyList currentGraphNode = nodeStack.pop();
            currentGraphNode.setVisited(true);
            System.out.print(currentGraphNode.getValue()+ " ");
            for (GraphNodeAdjacencyList g : currentGraphNode.getNeighborsList()) {
                if(!g.isVisited()) {
                    nodeStack.add(g);
                    g.setVisited(true);
                }
            }
        }
    }

    public void getSSSP_ForNode(GraphNodeAdjacencyList startingNode) {
        // Queue can also be used
        LinkedList<GraphNodeAdjacencyList> queue = new LinkedList<>();
        queue.add(startingNode);
        while(!queue.isEmpty()) {
            GraphNodeAdjacencyList currentNode = queue.remove(0);
            currentNode.setVisited(true);
            System.out.print("Current Node "+currentNode.getValue()+" :");
            printPath(currentNode);
            System.out.println("");
            for (GraphNodeAdjacencyList neighbor :
                    currentNode.getNeighborsList()) {
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                    neighbor.setVisited(true);
                    neighbor.parentNode = currentNode;
                }
            }
        }
    }

    void printPath(GraphNodeAdjacencyList node) {
        if(node.parentNode != null) {
            printPath(node.parentNode);
        }
        System.out.print(node.getValue()+" ");
    }
}
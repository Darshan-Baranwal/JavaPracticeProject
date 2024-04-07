package GraphDS.DFS_Recursion;

import java.util.ArrayList;

/*
problem link
https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1

login with d.b email id (short form)
 */
class Solution {
    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visitors = new boolean[V];
        visitors[0] = true;
        result.add(0);
        dfs(0, visitors, adj, result);
        return result;

    }
    public static void dfs(int startingIndex, boolean visitorsBooleanArrays[], ArrayList<ArrayList<Integer>> adj,
                           ArrayList<Integer> result) {

        for(int neighbor: adj.get(startingIndex)) {
            if(!visitorsBooleanArrays[neighbor]) {
                visitorsBooleanArrays[neighbor] = true;
                result.add(neighbor);
                dfs(neighbor, visitorsBooleanArrays, adj, result);
            }
        }
    }
}
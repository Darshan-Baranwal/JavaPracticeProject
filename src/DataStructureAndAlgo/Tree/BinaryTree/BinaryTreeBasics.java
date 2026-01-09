package DataStructureAndAlgo.Tree.BinaryTree;
// Binary Tree Builder
// traversal -> Pre/In/Post Order Traversal, Level Order Traversal(Queue),
// Traversal with level printed separately

import DataStructureAndAlgo.Tree.TreeNode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeBasics {
    static int idx = -1;
    public static void main(String[] args) {
        TreeNode root = buildTreeFromPreOrderArray(new int[]{1,2,-1,-1,3,4,-1,-1,5,-1,-1});
//        preOrderTraversal(root);
//        inOrderTraversal(root);
//        postOrderTraversal(root);
//        levelOrderTraversal(root);
        levelOrderTraversalWithLevelPrintedSeparately(root);
    }

    private static void levelOrderTraversalWithLevelPrintedSeparately(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        while(!q.isEmpty()) {
            TreeNode current = q.peek();

            q.poll();
            if(current == null) {
                if(!q.isEmpty()){
                    System.out.println("\n");
                    q.offer(null);
                    continue;
                }else {
                    break;
                }
            }
            System.out.print(" "+current.data);
            if(current.left != null) {
                q.offer(current.left);
            }
            if(current.right != null){
                q.offer(current.right);
            }
        }
    }

    private static void levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            TreeNode current = q.peek();
            System.out.println(current.data);
            q.poll();
            if(current.left != null) {
                q.offer(current.left);
            }
            if(current.right != null){
                q.offer(current.right);
            }
        }
    }

    private static void postOrderTraversal(TreeNode root) {
        if(root == null){
            return;
        }

        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
        System.out.print(root.data);
    }

    private static void inOrderTraversal(TreeNode root) {
        if(root == null){
            return;
        }

        preOrderTraversal(root.left);
        System.out.print(root.data);
        preOrderTraversal(root.right);
    }

    private static void preOrderTraversal(TreeNode root) {
        if(root == null){
            return;
        }
        System.out.print(root.data);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
    // 1,-1,-1

    // 1,2,-1,-1,3,4,-1,-1,5,-1,-1
    private static TreeNode buildTreeFromPreOrderArray(int[] preOrder) {
        idx++;
        if(preOrder[idx] == -1) {
            return null;
        }
        TreeNode newNode = new TreeNode(preOrder[idx]);
        newNode.left = buildTreeFromPreOrderArray(preOrder);
        newNode.right = buildTreeFromPreOrderArray(preOrder);
        return newNode;
    }
}

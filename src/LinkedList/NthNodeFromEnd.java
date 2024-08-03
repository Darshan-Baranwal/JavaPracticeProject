package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class NthNodeFromEnd {
    static int counter=0;
    public static void main(String[] args) {
        int nodeFromLast = 3;
        SingleLinkedList sl = new SingleLinkedList();
        sl.addToLast("5");
        sl.addToLast("6");
        sl.addToLast("7");
        sl.addToLast("8");
        sl.addToLast("9");
        sl.printLinkedList(sl);
        System.out.println("-----------");
        getNthNodeFromEndUsingSlidingWindow(sl, nodeFromLast); // O(n), O(1)
        getNthNodeFromEndUsingRecursion(sl.head, nodeFromLast);// O(2n), O(1)
        getNthNodeFromLastUsingHashMap(sl, nodeFromLast); // O(n), O(n)
    }

    private static void getNthNodeFromLastUsingHashMap(SingleLinkedList sl, int nodeFromLast) {
        Map<Integer, Node> map = new HashMap<>();
        Node currentNode = sl.head;
        int i=1;
        map.put(i, currentNode);
        while(currentNode.next!= null) {
            currentNode = currentNode.next;
            map.put(++i, currentNode);
        }
        System.out.println("HashMap "+map.get(sl.length-nodeFromLast+1).value);
    }

    private static void getNthNodeFromEndUsingSlidingWindow(SingleLinkedList sl, int n) {
        Node pNthNode = sl.head;
        Node pTemp = sl.head;
        if(sl.length>n) {
            for (int i = 0; i < n-1; i++) {
                pTemp = pTemp.next;
            }
            while (pTemp.next != null) {
                pNthNode = pNthNode.next;
                pTemp = pTemp.next;
            }
            System.out.println("Two pointer "+pNthNode.value);
        }else {
            System.out.println("no value");
        }
    }
    private static Object getNthNodeFromEndUsingRecursion(Node head, int n) {
        if(head != null) {
            getNthNodeFromEndUsingRecursion(head.next, n);
            counter++;
            if(n== counter) {
                System.out.println("Recursion "+head.value);
            }
        }
        return null;
    }
}

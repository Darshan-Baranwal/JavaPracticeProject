package LLDPractice.LeastRecentlyUsedCache_LRU;
//LRUCache (Capacity c): Initialize LRU cache with positive size capacity c.
//get(key):O(1): returns the value of the key if it already exists in the cache otherwise returns -1.
//put(key, value):O(1): if the key is already present, update its value. If not present, add the key-value pair to the cache.
//If the cache reaches its capacity it should remove the key-value pair with the lowest priority(the last one).

import java.util.HashMap;
class Node {
    public int key;
    public int value;
    Node next;
    Node previous;
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

class LRUCache{
    public HashMap<Integer, Node> cache;
    public int capacity;
    public Node head;
    Node tail;
    int size;

    public LRUCache(int capacity){
        this.capacity = capacity;
        cache=  new HashMap<>();
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        this.head.next = tail;
        tail.previous = head;
        size = 0;
    }
    int getValue(int key){
        if(cache.get(key)== null){
            return -1;
        }
        Node keyNode = cache.get(key);
        removeNodeFromLL(keyNode);
        addNodeToHead(keyNode);
        return keyNode.value;
    }

    private void removeNodeFromLL(Node keyNode) {
        keyNode.previous.next = keyNode.next;
        keyNode.next.previous = keyNode.previous;
    }

    Node put(int key, int value){
        Node newNode = new Node(key, value);
        Node cacheNode = cache.get(key);
        if(cacheNode != null) {
            cacheNode.value = value;
            removeNodeFromLL(cacheNode);
            addNodeToHead(cacheNode);
            return cacheNode;
        }else {
            if (capacity == size) {
                removeNodeFromLast(); // remove recently used
                cache.remove(key);
            }
            addNodeToHead(newNode);
            cache.put(key, newNode);
            size++;
        }
        return newNode;
    }

    private Node removeNodeFromLast() {
        Node lastNode = this.tail.previous;
        cache.remove(lastNode.key);
        removeNodeFromLL(lastNode);
        return lastNode;
    }

    private void addNodeToHead(Node keyNode) {
        Node firstNode = this.head.next;
        firstNode.previous = keyNode;
        keyNode.next = firstNode;
        this.head.next = keyNode;
        keyNode.previous = head;
    }
    public void printLRU(){
        Node current = this.head.next;
        while(current.next != null) {
            System.out.print("""
                (%s,%s) -->
            """.formatted(current.key, current.value));
            current = current.next;
        }
    }

}


public class LRU {
    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,10); //[{1:10}, ]
//        lruCache.printLRU();
        lruCache.put(2,20);// [{2:20}, {1:10}]
        System.out.println(lruCache.getValue(3));
        lruCache.put(3,30);// [{3:30}, {2:20}, {1:10}]
        lruCache.put(1,100);// [{1:100}, {3:30}, {2:20}]
        lruCache.put(4,40);// [{4:40}, {1:100}, {3:30}]
        System.out.println(lruCache.getValue(3));
        System.out.println(lruCache.getValue(1));
        lruCache.printLRU();

    }
}

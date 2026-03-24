package LLDPractice.LeastFrequentlyUsed;

import java.util.HashMap;
import java.util.Map;

class Node {
    Node prev;
    Node next;
    int frequency;
    int key;
    int value;
    public Node(int key, int value){
        this.key = key;
        this.value = value;
        this.frequency = 1;
    }
}
class DoublyLL{
    Node head;
    Node tail;
    int size;
    public DoublyLL(){
        size = 0;
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void addNodeAtFront(Node cachedNode) {
        Node firstNode = this.head.next;
        firstNode.prev = cachedNode;
        cachedNode.next = firstNode;
        this.head.next = cachedNode;
        cachedNode.prev = this.head;

    }

    public void removeNode(Node cachedNode) {
        cachedNode.next.prev = cachedNode.prev;
        cachedNode.prev.next = cachedNode.next.next;
    }

    public Node removeLastNode() {
        if(size>0){
            removeNode(this.tail.prev);
        }else {
//            System.out.println("Nothing to remove");
            return null;
        }
        return this.tail.prev;
    }
    public void printLRU() {
        Node current = this.head.next;
        while (current!= null && current.next != null) {
            System.out.print("""
                        (%s,%s) -->
                    """.formatted(current.key, current.value));
            current = current.next;
        }
    }
}
public class LFUCache {
    int minFrequency;
    int capacity;
    Map<Integer, Node> cache = new HashMap<>(); // cache with Node, also indicates the current size
    Map<Integer, DoublyLL> freqMap = new HashMap<>(); // {1: [{Node1}, {Node2}]}
    public LFUCache(int capacity){
        this.capacity =capacity;
    }
    public Node getValue(int key){
        Node cachedNode = cache.get(key);
        if(cachedNode != null) {
            updateFreq(cachedNode);
            return cachedNode;
        }else {
            System.out.println("Value not found");
            return null;
        }
    }

    private void updateFreq(Node cachedNode) {
        int currentFreq = cachedNode.frequency;
        DoublyLL oldLL = freqMap.get(currentFreq);
        oldLL.removeNode(cachedNode);
        if(minFrequency==currentFreq && freqMap.get(cachedNode.frequency).size == 0){
            minFrequency++;
        }
        cachedNode.frequency++;
        DoublyLL newLL = freqMap.getOrDefault(cachedNode.frequency, new DoublyLL());
        newLL.addNodeAtFront(cachedNode);
        freqMap.put(cachedNode.frequency, newLL);
    }

    public Node put(int key, int value) {
        Node cachedNode = cache.get(key);
        Node newNode = new Node(key, value);
        if(this.cache.size() == capacity){
            // remove the lowest frequency ->  DLL last node
            DoublyLL doublyLL = freqMap.get(minFrequency);
            doublyLL.removeLastNode();

            cache.remove(doublyLL.tail.prev.key);
            freqMap.put(minFrequency, doublyLL);
        }
        if(cachedNode != null) { // key already exists
            cachedNode.value = value;
            updateFreq(cachedNode);
            return cachedNode;
        }else {
// new entry for the cache
            if(cache.size() == capacity){// remove least frequent -> least recently Node
               DoublyLL minDoublyLL = freqMap.get(minFrequency);
               Node nodeToRemove = minDoublyLL.removeLastNode();
               cache.remove(nodeToRemove.key);
            }
            minFrequency = 1;
            DoublyLL ll = freqMap.getOrDefault(minFrequency, new DoublyLL());
            ll.addNodeAtFront(newNode);
            freqMap.put(1,ll);
            cache.put(key, newNode);
        }
        return newNode;
    }


    public static void main(String[] args) {

        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,10);

        lfuCache.put(2,20);
        lfuCache.printCacheWithFreq();
        lfuCache.getValue(1);
        lfuCache.put(3,30);
        lfuCache.getValue(3);
        lfuCache.printCacheWithFreq();
    }

    private void printCacheWithFreq() {
        freqMap.forEach((k,v) -> {
            System.out.println("Frequency: "+k);
            v.printLRU();
        });
    }
}

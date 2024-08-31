package MultiThreading.PoducerConsumer;
import java.util.*;
public class SharedResourceQueue {
    Queue<Integer> buffer = new LinkedList<>();
    int bufferSize;

    public SharedResourceQueue(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) {
        while(buffer.size() == bufferSize){
            try {
                System.out.println("Inside Producer wait, Queue is full waiting for consumption");
                wait(); // release all locks
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Adding item to buffer "+item);
        buffer.add(item);
        notify();
    }

    public synchronized void consume() {
        while(buffer.size()==0) {
            try {
                System.out.println("Inside Consumer wait, Queue is empty");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Consuming data "+ buffer.peek());
        buffer.poll();
        notify();
    }
}

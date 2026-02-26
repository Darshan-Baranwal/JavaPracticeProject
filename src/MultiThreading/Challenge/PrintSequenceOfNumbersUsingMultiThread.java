package MultiThreading.Challenge;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

// counter++ -> counter%ThreadCount == 1(remainder)-> thread1
public class PrintSequenceOfNumbersUsingMultiThread {
    static AtomicInteger counter = new AtomicInteger(1);
    static int MAX_COUNT = 10;
    static int ThreadCount = 3;

    static Object lock = new Object();
    static class SequenceGenerator implements Runnable {
        private final int remainder;

        SequenceGenerator(int remainder) {
            this.remainder = remainder;
        }
        @Override
        public void run() {
            while(counter.get()<= MAX_COUNT) { // The Thread keeps running as long as the counter hasn't surpassed the
                // MAX_COUNT(10)
                synchronized (lock) { // only one thread can enter after this block, because of lock at parent class
                    while (counter.get() <= MAX_COUNT && counter.get() % ThreadCount != remainder) {
                        // while loop condition checks if remainder of current thread is same as counter remainder, if not
                        // then lock -> .wait
                        try {
                            lock.wait(); // the thread release the lock and enter a waiting state, allowing other thread
                            // to acquire lock  to enter synchronized block
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(counter.get()<=MAX_COUNT) { // this is important else if few counter gets printed like 11 in our case
                        System.out.println("Thread: "+ remainder + " is printing "+counter);
                        counter.incrementAndGet();
                    }
                    lock.notifyAll(); // this signals all other waiting threads that the counter has changed. They will
                    // wake up and try re-compete for the lock and re-evaluate if it is now their turn
                }
//                ThreadPoolExecutor()
            }
        }
    }
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(ThreadCount);
        for(int i = 0; i<ThreadCount; i++) {
            int remainder = (i+1)%ThreadCount;
            es.execute(new SequenceGenerator(remainder));
        }
        es.shutdown();
    }
}

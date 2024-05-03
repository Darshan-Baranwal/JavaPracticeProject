package MultiThreading.Livelock_StarvationProblems;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class StarvingThread {
    private static final ReentrantLock lock = new ReentrantLock(true); // true means fair lock

    public static void main(String[] args) {
        Callable<Boolean> thread = () -> {
            String threadName = Thread.currentThread().getName();
            int threadNo = Integer.parseInt(threadName.replaceAll(".*-[1-9]*-.*-", ""));
            boolean keepGoing = true;
            while (keepGoing) {
                lock.lock();
                try {
                    System.out.printf("%d acquired the lock .%n", threadNo);
                    Thread.sleep(1000);
                } catch (InterruptedException e)  {
                    System.out.printf("Shutting down %d%n", threadNo);
                    Thread.currentThread().interrupt();
                    return false; // required to close the looping after thread interruption from shutDownNow method
                }finally {
                    lock.unlock();
                }
            }
            return true;
        };
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {

            List<Future<Boolean>> futures = executorService.invokeAll(Arrays.asList(thread, thread, thread),8, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdownNow(); // cancels all the future without waiting of completion
    }
}

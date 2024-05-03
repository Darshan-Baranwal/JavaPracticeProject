package MultiThreading.SynchronizationChallenge;

import java.util.concurrent.*;

public class ExecutorsChallenge {
    public static void main(String[] args) {
        ShoeWarehouseUsingExecutors sh = new ShoeWarehouseUsingExecutors();

        // newCacheThreadPool is used so that large number of orders received using auto increasing thread count
        ExecutorService producerExecutorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(200); // now Cached thread pool will reuse some the already available threads completed their tasks.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int finalI = i;
                Callable<Order> orderTask = () -> {
                    Order newOrder = new Order("O-" + (finalI + 1),
                            ShoeWarehouse.PRODUCT_LIST[finalI % ShoeWarehouse.PRODUCT_LIST.length], 2);
                    sh.receiveOrders(newOrder);
                    return newOrder;
                };
                producerExecutorService.submit(orderTask);
            }
        } finally {
            producerExecutorService.shutdown();
            try {
                producerExecutorService.awaitTermination(6, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sh.shutdown();
        }
    }
}

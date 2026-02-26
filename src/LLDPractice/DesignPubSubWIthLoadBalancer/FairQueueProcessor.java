package LLDPractice.DesignPubSubWIthLoadBalancer;

import java.util.concurrent.*;

public class FairQueueProcessor {
    BlockingQueue<Message> globalQueue;
    int maxWorkers;
    ConcurrentHashMap<String, BlockingQueue<Message>> tenantBasedQueue = new ConcurrentHashMap<>();
    BlockingQueue<String> tenantRoundRobinQueue = new LinkedBlockingQueue<>();
    // distribution is round-robin

    public FairQueueProcessor(BlockingQueue<Message> globalQueue,int maxWorkers) throws InterruptedException {
        this.globalQueue = globalQueue;
        this.maxWorkers = maxWorkers;
        // on application up first split the message queue with tenant blocking queue
        // parallel transfer all the incoming tenant id in tenant round robin queue
        startDispatcherToSplitGlobalQueueToTenantQueue();
        startWorkers(maxWorkers);
    }


    private void startDispatcherToSplitGlobalQueueToTenantQueue() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        es.submit(() -> {
        while(true) {
            Message message = this.globalQueue.take();
            BlockingQueue<Message> blockingQueue = this.tenantBasedQueue.get(message.tenantId());
            if (blockingQueue != null) {
                blockingQueue.offer(message);
                this.tenantBasedQueue.put(message.tenantId(), blockingQueue);
            } else {
                BlockingQueue<Message> newBlockingQueue = new LinkedBlockingQueue<>();
                newBlockingQueue.offer(message);
                this.tenantBasedQueue.put(message.tenantId(), newBlockingQueue);
                this.tenantRoundRobinQueue.offer(message.tenantId());
            }
        }});
    }
    private void startWorkers(int maxWorkers) {
    }

}

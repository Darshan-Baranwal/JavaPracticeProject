package LLDPractice.DesignPubSubWIthLoadBalancer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Message> globalQueue = new LinkedBlockingQueue<>();
        int maxWorkers = 100;
        FairQueueProcessor fairQueueProcessor = new FairQueueProcessor(globalQueue, maxWorkers);

    }
}

package MultiThreading.ParallelStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VisitorList_ArrayBlockingQueueExample {
    private static final CopyOnWriteArrayList masterList;
static {
    masterList = Stream.generate(Person::new).limit(25).distinct()
            .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
}
    // ArrayBlockingQueue takes initial capacity of the elements
    private static final ArrayBlockingQueue<Person> newVisitors =
            new ArrayBlockingQueue<>(3);
    public static void main(String[] args) {
        Runnable consumer = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" Polling queue "+ newVisitors.size());
            Person visitor = newVisitors.poll();// can be timeout
            if(visitor!=null) {
                System.out.println(threadName + " "+visitor);

                if (!masterList.contains(visitor)) {
                    masterList.add(visitor);
                    System.out.println("new visitor added to master list "+visitor.toString());
                }
            }
        };

        Runnable producer = () -> {
            Person visitor = new Person(); // generate 4 persons
            System.out.println("Queuing "+ visitor.toString());
            boolean queue=false;
            try {

                // offer method with timeout will wait for timeout units if queue is already full
                // offer block the code temporarily till timeout time.
                // put(E e) blocks for indefinite time
                // for non-blocking way of adding item in queue are add(E e) and offer(E e)
                queue = newVisitors.offer(visitor, 2, TimeUnit.SECONDS);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(queue) {
//                System.out.println("Queued visitors "+newVisitors);
            }else {
                List<String> listOfAllVisitors =  new ArrayList<>();
                List<Person> timelines = new ArrayList<>();
                System.out.println("Queue is Full Not add "+visitor);
//                System.out.println("Draining Queue and writing data to file");
//                newVisitors.drainTo(timelines);
//                timelines.forEach(v -> listOfAllVisitors.add(v.toString()));
//                listOfAllVisitors.add(visitor.toString());
//                try {
//                    Files.write(Path.of("DrainedQueue.txt"), listOfAllVisitors, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
            }

        };
        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        producerExecutor.scheduleWithFixedDelay(producer, 0,1,TimeUnit.SECONDS);

        while (true) {
            try {
                if(!producerExecutor.awaitTermination(10, TimeUnit.SECONDS));{
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ScheduledExecutorService consumerExecutor = Executors.newSingleThreadScheduledExecutor();
        consumerExecutor.scheduleWithFixedDelay(consumer, 0,1,TimeUnit.SECONDS);

        while (true) {
            try {
                if(!consumerExecutor.awaitTermination(10, TimeUnit.SECONDS));{
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        producerExecutor.shutdown();
        consumerExecutor.shutdown();
    }
}

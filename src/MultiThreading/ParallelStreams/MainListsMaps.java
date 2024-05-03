package MultiThreading.ParallelStreams;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainListsMaps {
    public static void main(String[] args) {
//ConcurrentSkipListMap ->Constructs a new, empty map, sorted according to the natural ordering of the keys.
        ConcurrentSkipListMap<String, Long> threadMap = new ConcurrentSkipListMap<>();
        Person[] persons = Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .peek(p-> {
                    var threadName = Thread.currentThread().getName().replace(
                            "ForkJoinPool.commonPool-worker-", "thread_"
                    );
                    threadMap.merge(threadName, 1L, Long::sum);
                })
                .toArray(Person[]::new);
        System.out.println(persons.length);
        System.out.println(threadMap);
        System.out.println(threadMap.values().stream().mapToLong(v->v).sum()); // this will
        //tell how many threads are running the parallel processing task.
    }
    
}

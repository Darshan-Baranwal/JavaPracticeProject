package MultiThreading.ParallelStreams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record Person(String firstName, String lastName, int age) {
    private final static String[] firsts = {"Able", "Bob", "Charlie", "Eve"};
    private final static String[] lasts = {"Smith", "Rai", "oho", "Kalu"};

    private final static Random random = new Random();

    public Person() {
        this(firsts[random.nextInt(firsts.length)],
                lasts[random.nextInt(lasts.length)],
                random.nextInt(18));
    }

    @Override
    public String toString() {
        return "%s%s-(%d)".formatted(lastName,firstName,age);
    }
}
public class Main {
    public static void main(String[] args) {
        int numbersLength = 100;
        long[] numbers = new Random().longs(numbersLength, 1, numbersLength).toArray();
        long start = System.nanoTime();
        double averageSerial = Arrays.stream(numbers).average().orElseThrow();
        long elapsedSerial = System.nanoTime()-start;
        System.out.printf("Ave = %.2f, elapsed = %d nanos or %.2f ms%n",
                averageSerial, elapsedSerial, elapsedSerial/1000000.0);

        start = System.nanoTime();
        double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();
        long elapsedParallel = System.nanoTime()-start;
        System.out.printf("Ave = %.2f, elapsed = %d nanos or %.2f ms%n",
                averageParallel, elapsedParallel, elapsedParallel/1000000.0);

        Stream.generate(Person::new)
                .limit(10)
                .sorted(Comparator.comparing(Person::lastName))
                .forEach(System.out::println);
System.out.println("--------------------------------");
        Stream.generate(Person::new)
                .limit(10)
                .parallel()
                .sorted(Comparator.comparing(Person::lastName)) // will not work in parallel processing
                .forEachOrdered(System.out::println); // Ordered in which the Source stream provided the item.
        System.out.println("--------------------------------");
        System.out.println("-to apply sorting in parallel streams");
        Stream<Person> sorted = Stream.generate(Person::new)
                .limit(10).sorted(Comparator.comparing(Person::lastName));
        sorted.parallel().forEachOrdered(System.out::println);
        System.out.println("--------------------------------");
        Map<String, Long> lastNameCount = Stream.generate(Person::new)
                .limit(10).peek(v->System.out.print(v.lastName()+","))
                .parallel()
                .collect(Collectors.groupingBy(Person::lastName, Collectors.counting()));
        //If preservation of the order in which elements are presented to the downstream collector
        // is not required, using groupingByConcurrent(Function, Collector)
        // may offer better parallel performance.
        //groupingBy return type by default is HashMap
        //groupingByConcurrent return type is ConcurrentHashMap
        lastNameCount.entrySet().forEach(System.out::println);

       var lastCounts =  new TreeMap<String, Long>();
        Stream.generate(Person::new)
                .limit(100000).parallel()
                .forEach((person -> lastCounts.merge(person.lastName(), 1L, Long::sum)));

// Above code will throw exception Caused by: java.util.ConcurrentModificationException
        // try running multiple times
        // Because TreeMap iterator is fail-fast and not thread safe that's why throw ConcurrentModificationException
        // Solution ->
        // use 1. ConcurrentSkipListMap -> sorted
        // 2. Collections.synchronizeMap(new TreeMap<>()) -> Blocking (uses Locks internally), i.e. other threads have to be waiting once this tasks completes
        // 3. ConcurrentHashMap

    }
}

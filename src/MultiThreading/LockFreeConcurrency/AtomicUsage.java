package MultiThreading.LockFreeConcurrency;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

record Student(String name, int enrolledYear, int studentId) implements Comparable<Student> {
 @Override
    public int compareTo(Student o) {
        return this.studentId-o.studentId;
    }
}
class AtomicStudentId {
    private AtomicInteger atomicId = new AtomicInteger(0);
private int id;
    public int getId() {
        return id;
    }
    public int getNextId() {
        return ++id;
    }
    public AtomicInteger getAtomicId() {
        return atomicId;
    }
    public int getNextAtomicId() {
//        return ++id;
        return atomicId.getAndIncrement();
    }
}
public class AtomicUsage {
    private static final Random random = new Random();
    private static ConcurrentSkipListSet<Student> studentSet = new ConcurrentSkipListSet<>();

    public static void main(String[] args) {
        AtomicStudentId atomicStudentId = new AtomicStudentId();
        Callable<Student> studentMaker = () -> {
//            int studentId = atomicStudentId.getNextAtomicId();
            int studentId = atomicStudentId.getNextId(); // some id will get missed..because ++id is not an atomic operation it is combination of
            // increment and assigning value to the variable. Atomic operation means the task either will be completed or Abort.
            // Atomic classes guarantees that the increment decrement values must be occurred atomically.
            Thread.sleep(100);
            Student student = new Student("Darshan"+studentId, random.nextInt(2024), studentId );
            studentSet.add(student);
            return student;
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            executorService.invokeAll(Collections.nCopies(10, studentMaker));
            studentSet.forEach(System.out::println);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}

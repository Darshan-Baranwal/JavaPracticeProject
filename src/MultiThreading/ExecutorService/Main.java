package MultiThreading.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

class ColorThreadFactory implements ThreadFactory {
    private String threadName;
    private int colorValue = 1;
    public ColorThreadFactory(ThreadColor threadColor) {
        this.threadName = threadColor.name();
    }
    public ColorThreadFactory() {
    }
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = threadName;
        if (name== null) {
            name = ThreadColor.values()[colorValue].name();
        }
        if(++colorValue>(ThreadColor.values().length)) {
            colorValue=1;
        }
        thread.setName(name);
        return thread;
    }
}
public class Main {

    public static void main(String[] args) {
        int count = 3;
        ExecutorService multiExecutors = Executors.newFixedThreadPool(3, new ColorThreadFactory());
        for (int i = 0; i < 6; i++) { // check notes for the behaviour
            multiExecutors.execute(Main::countDown);
        }
        multiExecutors.shutdown();
    }
    public static void singlemain(String[] args) {
        ExecutorService blueExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_BLUE));
        blueExecutor.execute(Main::countDown);
        blueExecutor.shutdown();
        boolean isDone = false;
        try {
            // making blue first complete and then proceed to other threads, similar to join
            isDone = blueExecutor.awaitTermination(500, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(isDone) {
            System.out.println("Blue Completed, Yellow starting");
            ExecutorService yellowExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_YELLOW));
            yellowExecutor.execute(Main::countDown);
            yellowExecutor.shutdown();
        }
        // awaitTermination can be applied to other thread similarly
        ExecutorService redExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_RED));
        redExecutor.execute(Main::countDown);
        redExecutor.shutdown();
    }
    public static void notmain(String[] args) {
        Thread blue = new Thread(Main::countDown, ThreadColor.ANSI_BLUE.name());
        blue.start();
        try {
            blue.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread yellow = new Thread(Main::countDown, ThreadColor.ANSI_YELLOW.name());
        yellow.start();
        try {
            yellow.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread red = new Thread(Main::countDown, ThreadColor.ANSI_RED.name());
        red.start();
        try {
            red.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    static void countDown() {
        String threadName = Thread.currentThread().getName();
        String threadColorName = ThreadColor.ANSI_RESET.name();
        threadColorName = ThreadColor.valueOf(threadName).name();
        for (int i = 0; i < 20; i++) {
            System.out.println(
                    threadColorName.replace("ANSI_","")+ " " +i);
        }
    }
}

package MultiThreading.ThreadProcessMemory;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch(TimeUnit.SECONDS);
        Thread green = new Thread(stopwatch::countDown, ThreadColor.ANSI_GREEN.name());
        Thread purple = new Thread(() -> stopwatch.countDown(7), ThreadColor.ANSI_PURPLE.name());
        Thread red = new Thread(stopwatch::countDown, ThreadColor.ANSI_RED.name());

        green.start();
        purple.start();
        red.start();
    }
}
class Stopwatch{
    private TimeUnit timeUnit;
private int i; // the looping variable is the part of Stopwatch object which gets shared to all threads
    //thus making it available to Heap memory and all threads can change this variable
    // this produces unexpected results.
    // to fix this we can define int i in for loop itself or make the behaviour of i Atomic or make countdown
    // method synchronized
    public Stopwatch(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
    public void countDown() {
        countDown(5);
    }
    public void countDown(int unitCount) {
        String threadName = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName);
        } catch (IllegalArgumentException ignore) {

        }
        for ( i=unitCount;i>0;i--) {
            try {
                timeUnit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s %s Thread : i = %d%n", threadColor, threadName, i);
        }
    }
}

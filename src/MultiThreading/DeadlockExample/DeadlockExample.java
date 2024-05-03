package MultiThreading.DeadlockExample;

import java.io.File;

/*
One thread trying to access a resource whose lock is already taken by other thread and vice versa.
Solution -
1. Use right hierarchy of resources
2. Use explicit locks like Reentrant lock it has tryLock methods which prevent to enter taking lock infinitely
 */
public class DeadlockExample {
    static String  resourceA = "resourceA";
    static String resourceB = "resourceB";
    public static void main(String[] args) {
//        File resourceA = new File("input.csv");
//        File resourceB = new File("output.csv");
        // to produce deadlock reverse the order of locks in one thread.

        Thread threadA = new Thread(() -> {
            System.out.println("Attempting to take lock in resourceA by "+ Thread.currentThread().getName());
            synchronized (resourceA) {
                System.out.println("taking lock on resource A by Thread - "+ Thread.currentThread().getName());
                System.out.println("Attempting to take lock in resourceB by "+ Thread.currentThread().getName());

                synchronized (resourceB) {
                    System.out.println("taking lock on resource B by Thread - "+ Thread.currentThread().getName());
                }
                System.out.println("Releasing lock in resourceB by "+ Thread.currentThread().getName());
            }
            System.out.println("Releasing lock in resourceA by "+ Thread.currentThread().getName());

        }, "ThreadA");

        Thread threadB = new Thread(() -> {
            System.out.println("Attempting to take lock in resourceA by "+ Thread.currentThread().getName());

            synchronized (resourceA) {
                System.out.println("taking lock on resourceA by Thread - "+ Thread.currentThread().getName());
                System.out.println("Attempting to take lock in resourceB by "+ Thread.currentThread().getName());

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                synchronized (resourceB) {
                    System.out.println("taking lock on resourceB by Thread - "+ Thread.currentThread().getName());
                }
                System.out.println("Releasing lock in resourceB by "+ Thread.currentThread().getName());
            }
            System.out.println("Releasing lock in resourceA by "+ Thread.currentThread().getName());
        }, "ThreadB");


        // Deadlocking thread B

//        Thread threadB = new Thread(() -> {
//            System.out.println("Attempting to take lock in resourceB by "+ Thread.currentThread().getName());
//
//            synchronized (resourceB) {
//                System.out.println("taking lock on resourceB by Thread - "+ Thread.currentThread().getName());
//                System.out.println("Attempting to take lock in resourceA by "+ Thread.currentThread().getName());
//
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("taking lock on resource B by Thread - "+ Thread.currentThread().getName());
//                synchronized (resourceA) {
//                    System.out.println("taking lock on resource A by Thread - "+ Thread.currentThread().getName());
//                }
//                System.out.println("Releasing lock in resourceB by "+ Thread.currentThread().getName());
//            }
//            System.out.println("Releasing lock in resourceA by "+ Thread.currentThread().getName());
//        }, "ThreadB");
        threadB.start();
        threadA.start();
    }
}

package FailFast_FailSafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MultiThreadFailMain {
    public static void main(String[] args) {
        List<Integer> l  = new CopyOnWriteArrayList<>(Arrays.asList(1,2,3,4));
        Thread t1 = new Thread(() -> {
            l.add(1);
        });

        Thread t2 = new Thread(() -> {
            l.add(1);
        });
        l.forEach(v -> {
            t1.start();
            t2.start();
        });

        System.out.println("\n---------------");
        l.forEach(System.out::print);

    }

}

package PracticeNew;

/*
 */
public class PrintEvenOdd {
    final static int num = 10;
    boolean lock = false;
    void printEven() {
        synchronized (this) {
            for (int i = 0; i < num; i++) {
                while (lock) { // false
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (i % 2 == 0) { //
                    System.out.println(i);
                    lock = !lock; // false -> true -> false
                    notify();
                }
            }
        }
    }
    void printOdd() {
        synchronized (this) {
            for (int i = 0; i < num; i++) {
                while (!lock) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (i % 2 != 0) { //i = 1
                    System.out.println(i);
                    lock = !lock; // false
                    notify();
                }
            }
        }
    }


    public static void main(String[] args) {
        PrintEvenOdd m = new PrintEvenOdd();
        Thread t1 = new Thread(() -> {
           m.printEven();
        });
        Thread t2 = new Thread(() -> {
            m.printOdd();
        });
        t1.start();
        t2.start();

    }


}
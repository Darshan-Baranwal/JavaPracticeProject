package MultiThreading;

import java.util.concurrent.TimeUnit;

public class MultiThreadingMain {
    public static void main(String[] args) {
//        Thread thread = new Thread(new RunnableInterfaceClass());
//        thread.start();
        ThreadExtendClass th = new ThreadExtendClass();
        th.start(); // start the new thread if .run() gets called then it will be synchronous execution
        // like a method call

        Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.print(" 2 ");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException it) {
                    it.printStackTrace();
                }
            }
        };
        Thread runnableThread = new Thread(runnable);
        runnableThread.start();

        for (int i = 0; i <=3; i++) {
            System.out.print(" 0 ");
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
        }
    }
}

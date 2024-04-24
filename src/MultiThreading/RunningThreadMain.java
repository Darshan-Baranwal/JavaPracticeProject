package MultiThreading;
public class RunningThreadMain {
    public static void main(String[] args) {
        System.out.println("Main threading running");
        try {
            System.out.println("Main paused for 1 sec");
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main thread would continue here");

        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.print("$ ");
                try {
                    Thread.sleep(500);
                    System.out.println("A. State = "+Thread.currentThread().getState());
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("Whoops Thread get interrupted");
                    System.out.println("A1. State = "+Thread.currentThread().getState());
                    return;
                }
            }
        };
        Thread thread = new Thread(runnable);
        System.out.println("B. State = "+thread.getState());
        thread.start();
        System.out.println("C. State = "+thread.getState());

        long now = System.currentTimeMillis();
        while(thread.isAlive()) {
            System.out.println("\n waiting for thread to complete");
            try {
                Thread.sleep(1000);
                System.out.println("D. State = "+thread.getState());

                if(System.currentTimeMillis()-now>2000) {
                    thread.interrupt();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("E. State = "+ thread.getState());

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        thread.interrupt();
    }
}

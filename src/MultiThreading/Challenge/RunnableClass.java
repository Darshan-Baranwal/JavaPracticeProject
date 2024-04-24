package MultiThreading.Challenge;

public class RunnableClass implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <= 9; i=i+2) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Interrupt "+ Thread.currentThread().getName());
                return;
            }
            System.out.print(i+" ");
        }
    }
}

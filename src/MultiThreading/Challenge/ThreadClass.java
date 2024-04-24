package MultiThreading.Challenge;

public class ThreadClass extends Thread{
    @Override
    public void run() {
        for (int i = 2; i <= 10; i=i+2) {
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

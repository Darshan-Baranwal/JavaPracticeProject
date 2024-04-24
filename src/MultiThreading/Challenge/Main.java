package MultiThreading.Challenge;

public class Main {
    public static void main(String[] args) {
        ThreadClass threadClass = new ThreadClass();
        threadClass.setName("ThreadClass");
        threadClass.start();

        Thread runnableClass = new Thread(new RunnableClass());
        runnableClass.setName("RunnableClass");

        runnableClass.start();

        try {
            Thread.sleep(1000);
            threadClass.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

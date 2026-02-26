package MultiThreading.Challenge;

public class Main {
    public static void main(String[] args) {
        ThreadClass threadClass = new ThreadClass();// printing even numbers
        threadClass.setName("ThreadClass");
        threadClass.start();

        Thread runnableClass = new Thread(new RunnableClass());// printing odd numbers
        runnableClass.setName("RunnableClass");



        try {
            threadClass.join();
            runnableClass.start();

//            Thread.sleep(1000);
//            threadClass.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

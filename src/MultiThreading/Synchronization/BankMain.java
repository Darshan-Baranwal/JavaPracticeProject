package MultiThreading.Synchronization;

public class BankMain {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("Tom",10000);
        Thread t1 = new Thread(() -> bankAccount.withdraw(2500));
        Thread t2 = new Thread(() -> bankAccount.deposit(5000));
        Thread t4 = new Thread(() -> bankAccount.setName("Tim"));

        Thread t3 = new Thread(() -> bankAccount.withdraw(5000));

        t1.start();
        t2.start();
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        t3.start();
        t4.start();

        // call join to make sure that the thread runs first before final balance.
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final balance :" + bankAccount.getBalance());
    }
}

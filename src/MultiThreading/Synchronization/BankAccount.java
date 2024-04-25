package MultiThreading.Synchronization;

public class BankAccount {
    private double balance;

    private String name;

    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

    public BankAccount(String name, double balance) {
        this.balance = balance;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        synchronized (lockName) {
            this.name = name;
            System.out.println("updated name ="+ this.name);
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        System.out.println("Dealing with banker for deposit"); // this get called first by some thread
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (lockBalance) { // monitor lock/intrinsic lock is bankAccount instance
            double originalBalance = amount;
            balance += amount;
            System.out.printf("Starting balance: %.0f, Deposit (%.0f)" +
                    " : New Balance = %.0f%n", originalBalance, amount, balance);
            addPromoDollars(amount); // nested lock on lockBalance Object because of the Reentrant Synchronisation nested lock handled and avoid indefinite waiting.
        }
    }
    private void addPromoDollars(double amount) {
        if (amount>=5000) {
            synchronized (lockBalance) {
                System.out.println("Congratulations on promotional deposit");
                balance +=25;
            }
        }
    }


    public synchronized void withdraw(double amount) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double originalBalance = amount;
        if(balance>=amount) {
            balance -=amount;
            System.out.printf("Starting balance: %.0f, Withdraw (%.0f)"+
                    " : New Balance = %.0f%n", originalBalance, amount, balance);
        } else {
            System.out.println("Balance is less than Withdrawing amount Insufficient balance");
        }

    }
}

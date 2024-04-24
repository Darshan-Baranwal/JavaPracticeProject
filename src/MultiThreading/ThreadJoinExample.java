package MultiThreading;

public class ThreadJoinExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            String tName = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                System.out.print(". ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Whoops interrupted");
                    // Thread has to interrupt itself as interrupting from outside sometime delayed to update isInterrupted() state
                    // to solve this
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.println("\nThread 0 completed");
        });
        thread.start();
        Thread threadMonitor = new Thread(() -> {
            long now = System.currentTimeMillis();
            while(thread.isAlive()) {
                try {
                    Thread.sleep(300);
                    if(System.currentTimeMillis()-now>1000) { // change the monitor time to get successfully completion of thread
                        thread.interrupt();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadMonitor.start();
        System.out.println("Thread monitoring starting ");

        Thread installationThread = new Thread(() ->{
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(250);
                    System.out.println("Installation Step "+(i+1)+ " is Completed");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "InstallThread");

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(!thread.isInterrupted()) {
            installationThread.start();
        } else {
            System.out.println("Previous thread was interrupted, "+ installationThread.getName()+" can't run");
        }


    }


}

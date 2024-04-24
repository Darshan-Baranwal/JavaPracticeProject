package MultiThreading.VolatileExample;

public class CachedData {
    private boolean flag = false;
//    private volatile boolean flag = false;

    public void toggleFlag() {
        flag = !flag;
    }
    public boolean isReady() {
        return flag;
    }

    public static void main(String[] args) {
        CachedData example = new CachedData();
        Thread reader = new Thread(() -> {
            try {
                Thread.sleep(500);
                example.toggleFlag();
                System.out.println("Reader's flag state : " +example.isReady());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread writer = new Thread(() -> {
            while (!example.flag) {
                // the value of flag changed by reader doesn't reflect here because the changes from previous
                // thread will not be available immediately
                // solution is to make it "volatile"

            };
            System.out.println("Writer's flag state : "+example.isReady());
        });
        reader.start();
        writer.start();
    }
}

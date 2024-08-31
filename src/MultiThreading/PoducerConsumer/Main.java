package MultiThreading.PoducerConsumer;

public class Main {

    public static void main(String[] args) {
        SharedResourceQueue q = new SharedResourceQueue(5);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                q.produce(i);
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                q.consume();
            }
        });
        producer.start();
        consumer.start();
    }
}

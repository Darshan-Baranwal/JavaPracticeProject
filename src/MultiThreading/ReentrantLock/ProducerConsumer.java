package MultiThreading.ReentrantLock;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageRepository {
    private final Lock lock = new ReentrantLock();
    private String message;
    private boolean hasMessage = false;

    public String read() {
        if (lock.tryLock()) {
            try {
                while (!hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = false;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("** read blocked"+ lock);
            hasMessage = false; // some messages will be missed as state got changed even without reading
        }
        return message;
    }

    public void write(String message) {
        try {
            if(lock.tryLock(3, TimeUnit.SECONDS)) {
                while (hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = true;
                this.message = message;
            } else {
                System.out.println("** writer blocked"+ lock);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

class MessageProducer implements Runnable {
    private final String message = """
            All the best,
            for the future endeavors,""";
    private MessageRepository messageRepository;

    public MessageProducer(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void run() {
        System.out.println("Inside Producer");
        String[] messages = message.split("\n");
        for (String msg :
                messages) {
            messageRepository.write(msg);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        messageRepository.write("Message Finished");
    }
}

class MessageConsumer implements Runnable {
    private MessageRepository messageRepository;

    public MessageConsumer(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void run() {
        System.out.println("Inside Consumer");
        String message = "";
        do {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            message = messageRepository.read();
            System.out.println(message);
        } while (!Objects.equals(message, "Message Finished"));
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        MessageRepository messageRepository = new MessageRepository();
        Thread producer = new Thread(new MessageProducer(messageRepository), "Producer");
        Thread consumer = new Thread(new MessageConsumer(messageRepository), "Consumer");
        producer.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Producer had exception: " + exception);
            if (consumer.isAlive()) {
                consumer.interrupt();
            }
        });
        consumer.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Consumer had exception: " + exception);
            if (producer.isAlive()) {
                producer.interrupt();
            }
        });
        producer.start();
        consumer.start();
        // enters into a deadlock situation where 1 thread wants to change
        // the hasMessage variable but didn't get the lock as the other thread
        // enters into infinite loop and holding lock on monitor object.
        // synchronized keyword on method takes lock on class instance.
    }
}

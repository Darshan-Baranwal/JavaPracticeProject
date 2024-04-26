package MultiThreading.DeadlockExample;

import java.util.Objects;

class MessageRepository {
    private String message;
    private boolean hasMessage = false;
    public synchronized String read() {
        while(!hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = false;
        notifyAll();
        return message;
    }
    public synchronized void write(String message) {
        while (hasMessage) {
            try {
                wait();// this object lock is no release and this thread become ideal
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = true;
        notifyAll(); // notify all the thread waiting to take lock on this object.
        this.message = message;
    }
}

class MessageProducer implements Runnable {
    private MessageRepository messageRepository;
    private final String message = """
                All the best,
                for the future endeavors,""";
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
        String message="";
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
        Thread producer = new Thread(new MessageProducer(messageRepository));
        Thread consumer = new Thread(new MessageConsumer(messageRepository ));

        producer.start();
        consumer.start();

        // enters into a deadlock situation where 1 thread wants to change
        // the hasMessage variable but didn't get the lock as the other thread
        // enters into infinite loop and holding lock on monitor object.
        // synchronized keyword on method takes lock on class instance.
    }
}

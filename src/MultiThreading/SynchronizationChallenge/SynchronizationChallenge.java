package MultiThreading.SynchronizationChallenge;

record Order(String id, String type, int quantity) {

}
public class SynchronizationChallenge {
    public static void main(String[] args) {
        ShoeWarehouse sh = new ShoeWarehouse();

        Thread producer = new Thread(() ->{
            for(int i=0;i<10;i++) {
                sh.receiveOrders(new Order("O-"+(i+1), ShoeWarehouse.PRODUCT_LIST[i%ShoeWarehouse.PRODUCT_LIST.length], 2));
            }
        });

        producer.start();

        // without synchronization solution
//        try {
//            producer.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        for (int i = 0; i < 2; i++) {
            Thread consumer = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    Order item = sh.fulfillOrder();
                }
            });
            consumer.start();
        };
    }
}

package MultiThreading.SynchronizationChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouseUsingExecutors {

    private List<Order> shippingOrders;
    private ExecutorService fulfillShipmentService;
    public static final String[] PRODUCT_LIST =
            {"Heels", "Boots", "Formals", "Sports"};

    public ShoeWarehouseUsingExecutors() {
        this.shippingOrders = new ArrayList<>();
        this.fulfillShipmentService = Executors.newFixedThreadPool(3);
    }

    public synchronized void receiveOrders(Order item) {
        while (shippingOrders.size() > 20) {
            try {
//                System.out.println("Inside receive orders");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        shippingOrders.add(item);
        this.fulfillShipmentService.submit(() -> this.fulfillOrder());
        notifyAll();
        System.out.println(Thread.currentThread().getName()+" Item added :" + item);
    }

    public synchronized Order fulfillOrder() {
        while (shippingOrders.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order item = shippingOrders.remove(0);
        System.out.println(Thread.currentThread().getName() + " Fulfilled Order : " + item);
        notifyAll();
        return item;
    }

    public void shutdown() {
        this.fulfillShipmentService.shutdown();
    }
}

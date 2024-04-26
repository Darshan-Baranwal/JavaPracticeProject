package MultiThreading.SynchronizationChallenge;

import java.util.ArrayList;
import java.util.List;

public class ShoeWarehouse {

    List<Order> shippingOrders;
    public static final String[] PRODUCT_LIST =
            {"Heels", "Boots", "Formals", "Sports"};

    public ShoeWarehouse() {
        this.shippingOrders = new ArrayList<>();
    }

    public synchronized void receiveOrders(Order item) {
        while(shippingOrders.size()>20) {
            try {
//                System.out.println("Inside receive orders");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        shippingOrders.add(item);
        notifyAll();
        System.out.println("Item added :" + item);
    }

    public synchronized Order fulfillOrder() {
        while(shippingOrders.size()==0) {
            try {
//                System.out.println("Inside fulfill orders");

                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            Order item = shippingOrders.remove(0);
            System.out.println(Thread.currentThread().getName()+" Fulfilled Order : " + item);
            notifyAll();
            return item;
        }
}

package MultiThreading;

import java.util.concurrent.TimeUnit;

public class MultiThreadingMain {
    public static void main(String[] args) {
//        new Thread(new RunnableInterfaceClass()).start();
        ThreadExtendClass th = new ThreadExtendClass();
        th.start();
        for (int i = 0; i <=3; i++) {
            System.out.print(" 0 ");
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
        }
    }
}

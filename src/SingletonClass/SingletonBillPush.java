package SingletonClass;

public class SingletonBillPush {
    private SingletonBillPush(){}
    private static class SingletonInstance{
       private static final SingletonBillPush singleton_instance = new SingletonBillPush();
    }
    private static SingletonBillPush getInstance() {
        return SingletonInstance.singleton_instance;
    }
}

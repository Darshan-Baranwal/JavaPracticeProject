package DesignPatterns.Creational;

public class SingletonClass {
    private static SingletonClass instanceVariable = null;
    private SingletonClass() {

    }
    static SingletonClass getInstance() {
        if(SingletonClass.instanceVariable == null) {
            SingletonClass.instanceVariable = new SingletonClass();
        }
        return SingletonClass.instanceVariable;
    }
}
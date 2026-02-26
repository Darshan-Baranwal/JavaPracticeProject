package Polymorphism;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

class BaseService {}
class ChildService extends BaseService {}

class Base {
    public int value = 90;
    public void display(BaseService service) {
        System.out.println("In Polymorphism.Base");
    }
}
class Child extends Base {
    public int value = 45;
    public void display(ChildService service) {
        System.out.println("In Polymorphism.Child");
    }
}
public class PolymorphismProblems {
    public static void main(String[] args) {

        Base baseOrg = new Base();
        System.out.println(baseOrg.value); // 90

        Base base = new Child();
        // through base reference we can call child's overridden methods only.
        // rest primitive variable and static variable will be Base class' only
        System.out.println(base.value); // 90 ??
        base.display(new ChildService()); // In Polymorphism.Base ??

        Child child = new Child();
        System.out.println(child.value); // 45
        // child own display method get called
        child.display(new ChildService());

        // child will call base class display method as argument is BaseService,
        // and we can not assign Child reference type with base reference type
        child.display(new BaseService()); // In Polymorphism.Base ??
    }
}

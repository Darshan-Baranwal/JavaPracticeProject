package Polymorphism;

interface A {
    public void execute();
}
class B implements A {
    public void execute() {
        System.out.println("Hello World! from class B");
    }
}
class C extends B {
    public void execute() {
        System.out.println("Hello World! from class C");
    }
    public void printline(){
        System.out.println("Test print line");
    }
}
public class PolymorphismProblem3 {
    public static void main(String[] args) {
//        A a = new A(); // ?
//        a.execute(); // ?
//        B b = new C();
//        b.execute(); // ?Hello World! from class C
//        b.printline(); //? Test print lin
//
//        C c = new B();
//        c.execute(); // Hello World! from class B
//        B newB = new A();
//        newB.execute(); // compile time error
    }
}

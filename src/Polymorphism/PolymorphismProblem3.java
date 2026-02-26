package Polymorphism;

interface A {
    public void execute();
}
class B implements A {
    public void execute() {
        System.out.println("Hello World! from class B");
    }
    static void test() {
        System.out.println("B");
    }
}
class C extends B {
    public void execute() {
        System.out.println("Hello World! from class C");
    }
    public void printline(){
        System.out.println("Test print line");
    }
    static void test() {
        System.out.println("C");
    }
}
public class PolymorphismProblem3 {
    public static void main(String[] args) {
//        A a = new A(); // ? compile time error
//        a.execute(); // ? compile time error
//        B b = new C(); // works
//        b.execute(); // ?Hello World! from class C
//        b.printline(); //? compile time error
//        // How to fix this ->
//        ((C)b).printline(); // JVM got the class C reference
//        b.test(); //? No vtable for static / private / final methods
//        C c = new B(); // compile time error
//        c.execute(); // compile time error
//        B newB = new A(); // compile time error
//        newB.execute(); // compile time error
//
//        A a = new B(); // works
//        C c = (C) a; // NO Compile time error but at runtime ClassCastException..

        A a1 = new B();
        // Compiler sirf hierarchy dekhta hai (C extends A)
        //JVM actual object dekhta hai → B
        //B is NOT C

        if (a1 instanceof C) { // always false at runtime: Object B ka hai → C nahi
            C c1 = (C) a1;
        }

        A a2 = null;

        System.out.println(a2 instanceof A); // false null kisi ka instance nahi hota

        B[] arr = new C[5];
        arr[0] = new B();
        // Compile time OK
        // Runtime → ArrayStoreException
    }
}

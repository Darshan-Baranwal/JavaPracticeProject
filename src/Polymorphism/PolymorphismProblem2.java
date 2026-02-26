package Polymorphism;

class Parent {
    String n= "p";
    String m() {
        return "from p";
    }
}

class Child2 extends Parent{
    String n= "c";
    @Override
    String m() {
        return "from c";
    }
    void k() {
        System.out.println("Child2's method");
    }
}
public class PolymorphismProblem2 {
    public static void main(String[] args) {
        Parent p = new Child2(); // p can access only Child2 overridden method
        System.out.println(p.n + " "+p.m());
//        p.k();
        // k method inaccessible as object reference is Parent
    }
}

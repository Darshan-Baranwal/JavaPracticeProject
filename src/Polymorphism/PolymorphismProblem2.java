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
}
public class PolymorphismProblem2 {
    public static void main(String[] args) {
        Parent p = new Child2();
        System.out.println(p.n + " "+p.m());
    }
}

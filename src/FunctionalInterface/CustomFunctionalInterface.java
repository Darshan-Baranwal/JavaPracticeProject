package FunctionalInterface;

import java.util.List;

@FunctionalInterface
interface Operate<T> {
    T operate(T a, T b);
}
public class CustomFunctionalInterface<T> {
    public static void main(String[] args) {
        // anonymous class
        Operate<Integer> calculateSum = new Operate<Integer>() {

            @Override
            public Integer operate(Integer a, Integer b) {
                return a+b;
            }
        };
        System.out.println(calculateSum.operate(5,7));

        Operate<Integer> calculateMultiply = (a,b) -> a*b;
        System.out.println(calculateMultiply.operate(5,7));

    }
}

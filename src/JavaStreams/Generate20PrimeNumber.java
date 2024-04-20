package JavaStreams;

import java.util.stream.IntStream;

public class Generate20PrimeNumber {
    public static void main(String[] args) {
        IntStream.iterate(1, n->n+1) // get indefinite stream of data
                .filter(Generate20PrimeNumber::isPrime) // filter as per prime logic
                .limit(20) // get only first 20 of them, if input unable to produce atleast 20 numbers the loop will run for infinite
                .forEach((s) -> System.out.print(s + " "));
        System.out.println();
        getAllPrimeNumbersBelow100();
    }

    private static void getAllPrimeNumbersBelow100() {
        IntStream.iterate(1, n->n<100, n->n+1) // gives 1-> 100
                // range and rangeClosed can also be used
                .filter(Generate20PrimeNumber::isPrime) // filter as per prime logic
                .forEach((s) -> System.out.print(s + " "));
    }

    public static boolean isPrime(int wholeNumber) {
        if(wholeNumber<=2){
            return wholeNumber==2;
        }
        for (int divisor = 2;divisor < wholeNumber; divisor++) {
            if(wholeNumber%divisor==0) return false;
        }
        return true;
    }
}

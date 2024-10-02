package Practice;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Epam {
    public static void main(String[] args) {
        printStringRepeatingCharacter();
        getEvenOddSumUsingCompletingFuture();
    }

    private static void getEvenOddSumUsingCompletingFuture() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        CompletableFuture.supplyAsync(() -> {
            int evenSum = numbers.stream()
                    .filter(v->v%2==0).reduce(0, (a,b)-> a+b);
            int oddSum = numbers.stream()
                    .filter(v->v%2!=0).reduce(0, (a,b)-> a+b);
            return Map.of("evenSum", evenSum, "oddSum", oddSum);
        }).thenAccept(v -> System.out.println(v));
    }

    private static void printStringRepeatingCharacter() {
        String b="banana";
        b.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(v->v, Collectors.counting()))
                .forEach((k,v) -> System.out.println(k+" : "+v));
    }
}

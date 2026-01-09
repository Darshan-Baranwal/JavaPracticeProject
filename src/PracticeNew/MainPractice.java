package PracticeNew;

import java.util.HashMap;
import java.util.Map;

public class MainPractice {
    public static void main(String[] args) {
         int n=50;
         Map<Integer, Long> memo = new HashMap<>();
         System.out.println(factorialNumberResult(n, memo));
    }
    static long factorialNumberResult(int n, Map<Integer, Long> memo) {
        if(n==0){
            return 1;
        } else {
            return n*factorialNumberResult(n-1, memo);
        }
    }
}

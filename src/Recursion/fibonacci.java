package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fibonacci {

    public static void main(String[] args) {
        int n = 5; // 1,1,2,3,5
        List<Integer> fibonacciArray = new ArrayList<>();
        List<Integer>  result = getFibonacci(n);
        System.out.println(result);
    }

    private static List<Integer> getFibonacci(int n) {
        List<Integer> fibonacciArray = new ArrayList<>();
        if(n<2) {
            fibonacciArray.add(0);
            fibonacciArray.add(1);
            return fibonacciArray;
        }
        fibonacciArray = getFibonacci(n-1);
        int ad = fibonacciArray.get(fibonacciArray.size()-1)+fibonacciArray.get(fibonacciArray.size()-2);
        fibonacciArray.add(ad);
        return fibonacciArray;


//        if(n==1){
//            fibonacciArray.add(1);
//            return fibonacciArray;
//        }
//        int a = 0;
//        int b = 1;
//        for(int i=1;i<=n;i++) {
//            int temp = a+b;
//            fibonacciArray.add(temp);
//            a=b;
//            b=temp;
//        }
//        return fibonacciArray;
    }
}

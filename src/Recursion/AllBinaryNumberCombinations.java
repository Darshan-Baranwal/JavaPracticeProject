package Recursion;

import java.util.Arrays;

public class AllBinaryNumberCombinations {

    static int[] A;
    public AllBinaryNumberCombinations(int n){
        // n=1 -> [0], [1]
        // n=2 -> [0,0], [0,1], [1,0], [1,1]
        A = new int[n];
    }
    public static void main(String[] args) {
        AllBinaryNumberCombinations a = new AllBinaryNumberCombinations(2);
        generateBinaryNumbers(2, A);
    }

    private static void generateBinaryNumbers(int n, int[] A) {
        if(n<=0) {
            System.out.println(Arrays.toString(A));
        } else {
            A[n-1]=0;
            generateBinaryNumbers(n-1, A);
            A[n-1]=1;
            generateBinaryNumbers(n-1,A);
        }
    }
}

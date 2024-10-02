package Recursion;

public class factorial {

    public static void main(String[] args) {
        int n = 4;
        System.out.println(getFactorial(n));
    }

    private static int getFactorial(int n) {
        if(n<=1) return 1;
        return n*getFactorial(n-1);
    }
}

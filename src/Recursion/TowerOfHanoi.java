package Recursion;

public class TowerOfHanoi {
    public static void main(String[] args) {
        toh(3, 'A', 'C', 'B');
    }

    private static void toh(int n, char from, char to, char aux) {
        if(n==1) {
            System.out.println(String.format("Moving 1 disc from %s to %s", from, to));
            return;
        }
        toh(n-1, from, aux, to);

        System.out.println(String.format("Moving 1 disc from %s to %s", from, to));

        toh(n-1, aux, to, from);
    }
}

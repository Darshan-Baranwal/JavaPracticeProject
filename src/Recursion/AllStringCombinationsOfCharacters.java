package Recursion;

import java.util.Arrays;
/*
question available at Page 41 DS& Algo made easy by Narasimha Karumanchi
 */
public class AllStringCombinationsOfCharacters {
    static char[] A;
    static char[] k;
    public AllStringCombinationsOfCharacters(int n, char[] k){
        A = new char[n];
        this.k = k;
    }

    public static void main(String[] args) {
        AllStringCombinationsOfCharacters a = new AllStringCombinationsOfCharacters(3, new char[]{'A', 'B', 'C'});
        getAllStringCombinations(A.length, A, k);
    }

        private static void getAllStringCombinations(int n , char[] A, char[] k) {
            if (n<=0) {
                System.out.println(Arrays.toString(A));
            } else {
                for (int i = 0; i < k.length; i++) {
                    A[n-1] = k[i];
                    getAllStringCombinations(n-1, A, k);
                }
            }
        }
}

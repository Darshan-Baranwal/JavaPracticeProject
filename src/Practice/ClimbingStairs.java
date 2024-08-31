package Practice;

import java.util.*;

class Solution {
    public static int findKthElement(List<Integer> input, int k) {


        int output =
                input.stream()
                        .sorted((v2,v1) -> v1.compareTo(v2))
                        .toList().get(k);
        return output;
    }
}
public class ClimbingStairs {
    public static void main(String[] args)
    {
        System.out.print(ways(3, 0));
    }

    static int ways(int n, int step) {
        if(step==3){
            return 1;
        }
        if(step>3){
            return 0;
        }
        return ways(n, step+1) + ways(n, step+2);
    }
}

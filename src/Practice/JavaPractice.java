package Practice;

import java.util.*;
import java.util.stream.Collectors;

public class JavaPractice {
    public static int missingNumber(int n, int[] arr){
        int xor1 = 0, xor2 = 0;

        // XOR all array elements
        for (int i = 0; i < n - 1; i++) {
            xor2 ^= arr[i];
            System.out.println(123);
        }

        // XOR all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor1 ^= i;
        }

        // Missing number is the XOR of xor1 and xor2
        return xor1 ^ xor2;
    }
    public static int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        int count = 0;
        for(int i = 0; i < nums.length;i++) {
            if(m.containsKey(nums[i]-k)) {
                count +=m.get(nums[i]-k);
            }
            if(m.containsKey(nums[i]+k)) {
                count +=m.get(nums[i]+k);
            }
            if(null != m.get(nums[i])) {
                m.put(nums[i], m.get(nums[i])+1);
            } else {
                m.put(nums[i], 1);
            }
        }
        System.out.println(count);
        return count;
    }

    public static void main(String[] args)
    {
        Arrays.asList('X', 'D');
        int[] arr = { 1, 2, 3, 4 };
        Arrays.sort(arr);
        int n = 5;
//        System.out.println(missingNumber(n, arr));
        countKDifference(new int[]{7,7,8,3,1,2,7,2,9,5}, 6);
    }
}

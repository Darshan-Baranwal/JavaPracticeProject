package CompanyWiseQuestions.BlackRock;

import java.util.*;

/*
Goal:

Subarray length = k

All elements unique

Maximum sum

If none valid → return -1
 */
public class FindOptimalSolution {
    public static void main(String[] args) {
        int[] arr = new int[] {1,5,5,2,9,9,9};
        int k = 3;
        System.out.println(findTheMaxUniqueSubarraySum(arr, 3));
        ;
    }

    private static int findTheMaxUniqueSubarraySum(int[] arr, int k) {
        // move left pointer twice - once on duplicate and next on getting size k sub array
        int maxSum = -1;
        Set<Integer> set = new HashSet<>();
        int left = 0;
        int sum = 0;
        for (int right = 0; right < arr.length; right++) {

            while (set.contains(arr[right]) && left<arr.length){
                sum = sum - arr[left];
                set.remove(arr[left]);
                left++;
            }
            set.add(arr[right]);
            sum = sum+arr[right];
            if(right-left+1 == k) {
                maxSum = Math.max(sum, maxSum);
                sum = sum - arr[left];
                set.remove(arr[left]);
                left++;
            }
        }

        return maxSum;
    }
}

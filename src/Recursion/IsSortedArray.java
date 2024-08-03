package Recursion;

public class IsSortedArray {
    public static void main(String[] args) {
        int[] arr = {1,2,4,5,8,3};
        boolean result = arr.length>1 ? isSortedArray(arr, 1) : false;
        System.out.println(result);
    }

    private static boolean isSortedArray(int[] arr, int index) {
        if(index==arr.length) {
            return true;
        }
        if(arr[index-1]<arr[index]) {
            return isSortedArray(arr, index+1);
        } else {
            return false;
        }
    }
}

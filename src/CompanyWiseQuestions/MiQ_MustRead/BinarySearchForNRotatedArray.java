package CompanyWiseQuestions.MiQ_MustRead;

public class BinarySearchForNRotatedArray {
    //https://leetcode.com/problems/search-in-rotated-sorted-array/submissions/1936973458/
    public static void main(String[] args) {
        int[] rotatedArray = new int[] {4,5,6,1,2,3};
                //{4,5,6,7,8,1,2,3};
        for (int i : rotatedArray) {
            System.out.println(findIndexOfTheNumber(rotatedArray, i));
        }
//        System.out.println(findIndexOfTheNumber(rotatedArray, 8));
    }

    private static int findIndexOfTheNumber(int[] a, int i) {
        int result = twistedSearch(a, 0, a.length-1,i);
        return result;
    }

    private static int twistedSearch(int[] a, int left, int right, int i1) {
        // 4,5,6,7,8,1,2,3
        // 4,5,1,2,3

        while(left<=right) {
            int mid = ((left+right))/2;
            if (a[mid] == i1) {
                return mid;
            }
            if (a[right] == i1) {
                return right;
            }
            if (a[left] == i1) {
                return left;
            }
            if (left == mid && right == mid) {
                return a[mid] == i1 ? mid : -1;
            }
            if (a[mid] >= a[left]) {  // left part is sorted
                if(i1>a[left] && i1<a[mid]){ // target element is present
                    right = mid-1;
                }else {
                    left = mid + 1;
                }
            }else {// right part is sorted
                if(i1<a[right] && i1>a[mid]){ // target element is present
                    left = mid+1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

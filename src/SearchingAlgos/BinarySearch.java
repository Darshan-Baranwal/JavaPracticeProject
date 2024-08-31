package SearchingAlgos;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    // Always applied on Sorted Array
    // Binary means 0 and 1 i.e. 2 entries so divide the sorted into 2 entries every
    //time then search that recursively
    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1,2,3,4,5,9);
        int searchItem = 11;
        boolean isElementPresent = binarySearch(l, searchItem);
        System.out.println(isElementPresent);
    }

    private static boolean binarySearch(List<Integer> l, int searchItem) {
//        if(l.get(0)== searchItem)
//            return true;
//        if (l.get(l.size()-1)<searchItem || l.get(0)>searchItem)
//            return false;
        int index = binarySearchAlgo(0, l.size()-1, l, searchItem);
        System.out.println(index);
        return index > 0;
    }

    private static int binarySearchAlgo(int startingIndex, int maxIndex, List<Integer> l, int searchItem) {
        if(maxIndex>=startingIndex) {
            int mid = (startingIndex + maxIndex) / 2;
            if (l.get(mid) == searchItem) {
                return mid;
            } else if (l.get(mid) > searchItem) {
                return binarySearchAlgo(startingIndex, mid - 1, l, searchItem);
            } else {
                return binarySearchAlgo(mid + 1, maxIndex, l, searchItem);
            }
        }
        return -1;
    }
}

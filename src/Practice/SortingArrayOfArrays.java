package Practice;

import java.util.*;
import java.util.stream.Collectors;

public class SortingArrayOfArrays
{
    public static void main(String[] args) {
        int[] A = {9,7,5,1,3,1,2,4,12,19,14,13,21,22,23,12};
        List<Integer> aI = Arrays.stream(A).boxed().toList();
        int S = 4;
        int countryCount = A.length/4;
        List<List<Integer>> minRatingCompany = new ArrayList<>();
        for(int k =0; k<A.length;k = k+countryCount) {
            minRatingCompany.add(aI.subList(k,k+countryCount));
        }
        minRatingCompany.stream().sorted((v1, v2) -> {
            Integer v1Min = v1.stream().min(Comparator.naturalOrder()).get();
            Integer v2Min = v2.stream().min(Comparator.naturalOrder()).get();
            if(v1Min==v2Min) {
                List<Integer> l1 = v1.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                List<Integer> l2 = v2.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                return l1.get(1).compareTo(l2.get(1));
            } else {
                return v1Min.compareTo(v2Min);
            }
        }).collect(Collectors.toList()).forEach(System.out::print);
    }
}

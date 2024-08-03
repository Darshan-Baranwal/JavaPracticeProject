package Practice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    //String[][] grades = { {"Rohit", "85"},  {"Rahul", "80"}, {"Amit", "85"}, {"Rohit", "90"} };
    public static void main(String[] args) {
//        int[] arr1 = {2, 5, 3, 7, 9};

//        int[] arr2 = {1, 4, 5, 8, 7};
//        IntStream.concat(Arrays.stream(arr2), Arrays.stream(arr1)).sorted().distinct().forEach(System.out::println);
        // merge -> sort -> distinct

//        String s = "mango"; // apple
//        if(s.chars().distinct().count() == s.length()) {
//
//        }

//        List<Integer> i = new ArrayList<>(); // 1,2,3,4,5
//        i.stream().filter(v -> v%2==0).forEach(System.out::println);
//        i.stream().filter(v -> v%2!=0).forEach(System.out::println);

        String[][] grades = { {"Rohit", "85"},  {"Rahul", "80"}, {"Amit", "85"}, {"Rohit", "90"}, {"Rohit", "90"} };
        // {Rohit: avg mark}
        Arrays.stream(grades).collect(Collectors.groupingBy(e->e[0],
                        Collectors.averagingLong(value -> Long.valueOf(value[1]))))
                .forEach((k,v) -> System.out.println("Student : "+ k+ " Avg marks : "+ v));
//        Map<String, Float> map = new HashMap<>();
//        for(String[] s: grades) {
//            if(null != map.get(s[0])){
//                Float avg = (map.get(s[0])+Float.valueOf(s[1]))/2;
//                map.put(s[0], (float) Math.round(avg));
//            } else {
//                map.put(s[0], Float.valueOf(s[1]));
//            }
//        }
//        map.forEach((k,v) -> System.out.println("Student : "+ k+ " Avg marks : "+ v));
    }

}


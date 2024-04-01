import java.util.*;
import java.util.stream.Collectors;

public class TestMain {
    public static void main(String[] args) {
        // Separate even and odd, first odd then even
        Arrays.asList(1, 2, 3, 4).stream().collect(Collectors.partitioningBy(i -> i % 2 == 0))
                .entrySet().stream().flatMap(v -> v.getValue().stream()).collect(Collectors.toList()).forEach(System.out::println);

        // Frequency of each character in string
        // using group by similar to Sql group by takes unique value of the column or entry value and return occurrence count in the value
        String name = "darshan";
        Arrays.asList(name.split("")).stream()
                .collect(Collectors.groupingBy(s ->s, LinkedHashMap:: new, Collectors.counting()))
                .entrySet().stream().sorted((e1,e2) -> e2.getValue().compareTo(e1.getValue()))
                    .forEach((k) -> System.out.println(k.getKey() +" "+ k.getValue()));

        // sorting the list
        Arrays.asList('a', 'c', 'd', 'b').stream().sorted((e1, e2) -> e1.compareTo(e2)).forEach(System.out::println);
        // Arrays.asList('a', 'c', 'd', 'b').stream().sorted(Character::compareTo).forEach(System.out::println);
        System.out.println("----------------------");

        // short string by length
        Arrays.asList("ab", "cdef", "def", "b").stream().sorted((e1, e2) -> e2.length() - e1.length()).forEach(System.out::println);

        // Reverse integer array
        var arr = Arrays.asList(1, 2, 3, 4);
        Collections.reverse(arr);
        System.out.println(arr);

        System.out.println("----------------------");
        // remove duplicate items from an Array
        Arrays.asList('a', 'a', 'd', 'b').stream().collect(Collectors.toSet()).forEach(System.out::println); // doesn't maintain order of insertion
        System.out.println("----------------------");
        Arrays.asList('a', 'a', 'd', 'b').stream().distinct().forEach(System.out::println); // maintain order

        // get sum and avg of Int array
        System.out.println(Arrays.asList(1, 2, 3, 4).stream().mapToInt(e -> e).sum());
        System.out.println(Arrays.asList(1, 2, 3, 4).stream().mapToInt(e -> e).average().getAsDouble());

        // join list of String with Delimiter prefix and suffix
        System.out.println(Arrays.asList("ab", "cdef", "def", "b").stream().collect(Collectors.joining("++", "Leading Attachment " , " Trailing Attachment" )));

        System.out.println("----------------------");

        // Maximum and Minimum in a list
        System.out.println(Arrays.asList(1, 2, 3, 4).stream().mapToInt(e -> e).max().orElse(0));
        System.out.println("----------------------");
        System.out.println(Arrays.asList(1, 2, 3, 4).stream().mapToInt(e -> e).max().orElse(0));
        System.out.println("----------------------");

        // check anagram abcd is angram of dcab
        String a = "abcd";
        String b = "dcab";
        System.out.println(Arrays.stream(a.split("")).sorted().collect(Collectors.joining())
                .equalsIgnoreCase(Arrays.stream(b.split("")).sorted().collect(Collectors.joining())));

        System.out.println("----------------------");

        // Second Largest number in an Array
        System.out.println(Arrays.asList(1, 2, 3, 4).stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get());

        System.out.println("----------------------");
        // common elements between 2 array
        List<Integer> l = Arrays.asList(1, 5, 3, 7);
        List<Integer> k = Arrays.asList(1, 2, 3, 4);
        k.stream().filter(v ->l.contains(v)).collect(Collectors.toList()).forEach(System.out::println);


        System.out.println("----------------------");

        // Reverse each string of a list
        Arrays.asList("ab", "cdef", "def", "b").stream().map(v -> new StringBuilder(v).reverse()).forEach(System.out::println);

        System.out.println("----------------------");
        // Find string starts with a number
        Arrays.asList("0ab", "1cdef", "def", "b").stream().filter(v -> Character.isDigit(v.toCharArray()[0])).forEach(System.out::println);

        System.out.println("----------------------");

        // find duplicate elements from an array
        // set add() returns true if element not present
        Set<String> s = new HashSet<>();
        Arrays.asList('a', 'a', 'd', 'b').stream().filter(v->!s.add(String.valueOf(v))).forEach(System.out::println);

        System.out.println("----------------------");

        // check palindrome
        String p1 = "abdba";
        String r = String.valueOf(new StringBuilder(p1).reverse());
        System.out.println(r.equals(p1));
    }

}
import java.util.*;
import java.util.stream.Collectors;

/*
You have an array of Person objects with attributes name, age, and gender.
Implement a Java method that takes this array and returns a map where the keys are genders,
and the values are lists of names of persons older than 25.
Male -> Charlie
Female
String[][] persons = {
                {"Alice", "28", "Female"},
                {"Bob", "22", "Male"},
                {"Charlie", "30", "Male"},
                {"Diana", "26", "Female"},
                {"Eva", "24", "Female"}
        };
 */
class Person {
    String name;
    int age;
    String gender;
}
public class Main {
    public static void main(String[] args) {
        String[][] pr = {
                {"Hello", "World"},
                {"How", "areyou", "doing"},
                {"please", "align"},
                {"centre"}
        };

        int width = 12;
        String[] result = findtheArticle(pr, width);
//        System.out.println(Arrays.toString(result));

        extracted();


//        System.out.println(wordBreak("bb", List.of("a","b","bbb","bbbb")));

                String[][] persons = {
                        {"Alice", "28", "Female"},
                        {"Bob", "22", "Male"},
                        {"Charlie", "30", "Male"},
                        {"Diana", "26", "Female"},
                        {"Eva", "24", "Female"}
                };

                // Convert the array of arrays to a List for stream processing
                List<String[]> personsList = Arrays.asList(persons);

                // --- Java 8 Stream Logic ---
                Map<String, List<String>> m = personsList.stream().filter(k -> Integer.valueOf(k[1])>25)
                        // Group the elements by the gender (index 2)
                        .collect(Collectors.groupingBy(
                                // The Classification function (Key Mapper): returns the gender String
                                person -> person[2],
                                // The Downstream Collector (Value Mapper): specifies what to collect for each group.
                                // In this case, we map each array to the name (index 0) and collect them into a List.
                                Collectors.mapping(
                                        person -> person[0],
                                        Collectors.toList()
                                )
                        ));
                // --- End of Stream Logic ---

//                System.out.println(m);

        for (int i = 0; i < persons.length; i++) {
            if(Integer.valueOf(persons[i][1])>25) {
                if (persons[i][2] == "Female") {
                    List<String> femaleList = m.get("Female");
                    femaleList.add(persons[i][0]);
                    m.put("Female", femaleList);
                } else {
                    List<String> maleList = m.get("Male");
                    maleList.add(persons[i][0]);
                    m.put("Male", maleList);
                }
            }
        }
//        m.forEach((k,v) -> System.out.println(k + " -> "+ v.toString()));
    }

    private static void extracted() {
        int[] n = new int[]{1, 2, 3, 4, 3};
                // {300,100,200,400};
        int i = 0;
        int j = n.length-1;
        int rightSum = 0;
        int leftSum = 0;
        while(i<j) {
            if (leftSum<rightSum) {

                leftSum += n[i];
                i++;
            } else {

                rightSum += n[j];
                j--;
            }
        }
        if(i==j && leftSum == rightSum){
            System.out.println("result: "+ i);
        } else {
            System.out.println("result: "+ "No solution found");
        }
    }

    private static String[] findtheArticle(String[][] pr, int width) {

//        String[][] pr = {
//                {"Hello", "World"},
//                {"How", "areyou", "doing"},
//                {"please", "align"},
//                {"centre"}
//        };

        List<String> l = new ArrayList<>();
        String f = "";
        for (int i = 0; i < width + 2; i++) {
            f = f+"*";
        }
        l.add(f);
        String re = "";
        for(int i = 0;i<pr.length;i++) {
            int t = 0;

            while(t<pr[i].length){
                String s2 = re+" "+pr[i][t];
                if(s2.length()>width) {
                    l.add(formatString(re, width));
                    re  = pr[i][t];
                }else {
                    re = s2;

                    if(t==pr[i].length-1)
                    {
                        l.add(formatString(re, width));
                        re = "";
                    }

                }
                t++;
            }
        }

        String last = "";
        for (int i = 0; i < width + 2; i++) {
            last = last+"*";
        }
        l.add(last);
        String[] res = new String[l.size()];
        for(int k = 0;k<l.size();k++) {
            res[k] = l.get(k);
        }
        return res;
    }

    private static String formatString(String s, int width) {
        String result = "*";
        int gap = width - s.length();
        if(gap %2 == 0) {

            if(gap !=0) {
                for (int i = 0; i < gap / 2; i++) {
                    result += " ";
                }
                result +=s;
                for (int i = 0; i < gap / 2; i++) {
                    result += " ";
                }
            }else {
                result = result + s;
            }
            result += "*";
        } else {
            if(gap !=0) {
                for (int i = 0; i < gap / 2; i++) {
                    result += " ";
                }
                result +=s;
                for (int i = 0; i < ((gap / 2)+1); i++) {
                    result += " ";
                }
            }else {
                result = result + s;
            }
            result += "*";
        }
        return result;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Integer> w = new HashMap<>();
        for(String k : wordDict) {
            w.putIfAbsent(k,1);
        }
        int i;
        int j=0;
        int count = 0;
        for(i=0;i<s.length();i++) {
            while (j<=s.length()) {
                if (w.get(s.substring(i, j)) != null) {
                    count++;
                    i = j;
                }
                j++;
            }
        }
        if(count >= wordDict.size()){
            return true;
        }
        return false;
    }
}
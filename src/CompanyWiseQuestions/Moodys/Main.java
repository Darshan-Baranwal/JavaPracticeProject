package CompanyWiseQuestions.Moodys;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /*
    List<Integer> -> 1,2,3; List<Integer> -> 9,8,7,6,5;
    Result -> 1,9,2,8,3,7,  6,5

    // darshan -> first non repeating char -> LinkedHashMap
     */
    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
//        l1.add(1);
//        l1.add(null);
        List<Integer> l2 = null;
//        mergeTwoList(l1, l2).forEach(v -> System.out.println(v));
/*
Empty string --> map.size() == 0
Null input --> map.size() == 0
All characters repeat --> map
Single character -> map
String with only spaces "    "
Mixed case: "AaBb" --> A
 */
        // CHECK below code is working
        "AaBb".chars().mapToObj(v -> (char) v)
                .collect(Collectors.groupingBy(v-> v,LinkedHashMap::new, Collectors.counting()));
        String s = "    ";
        LinkedHashMap<Character, Integer> r = firstNonRepeatingCharacter(s);
        if(r.isEmpty()){
//            System.out.println("No Character present");
        } else {
//            System.out.println(r.entrySet().stream().findFirst().get().getKey());
//            System.out.println(r.entrySet().stream().findFirst().get().getValue());
        }
        /*
        Input: "()" → Output: true
Input: "()[]{}" → Output: true
Input: "(]" → Output: false
Input: "([)]" → Output: false
Input: "{[()]}" → Output: true
// []
         */
        String b = "()[]{}";
        //"{[()]}";
        // "([)]";
        boolean br = validateTheBrackets(b);
        System.out.println(br);


    }

    private static boolean validateTheBrackets(String b) {
        boolean result = false;
        if (b == null || b.length() ==0 || b.trim().length()==0){
            return result;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        map.put('{', '}');
        map.put('(', ')');
        map.put('[', ']');
        Stack<Character> characters = new Stack<>();
        LinkedList<Character> ll = new LinkedList<>();
        for(char c: b.toCharArray()){
            characters.add(c);
        }
        //"" --> []
        // "" ->> []
        // "()[]{}"

        ll.add(characters.pop());
        while (!characters.empty()){
            char i = characters.pop();
            if(ll.size() != 0 && i == map.get(ll.get(ll.size()-1))){
                ll.remove(ll.size()-1);
            }else {
                ll.add(i);
            }
        }
        return ll.size() == 0 ? true: false;

    }

    private static LinkedHashMap<Character, Integer> firstNonRepeatingCharacter(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        if(s == null || s.length() ==0 || s.trim().length()==0){
            return map;
        }

        for(char c: s.toCharArray()){
            if(map.get(c) != null){
                int count = map.get(c) + 1;
                map.put(c, count);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }

    private static List<Integer> mergeTwoList(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<>(); // [] size()
        if(l2 == null){
            return l1;
        }
        if(l1 == null){
            return l2;
        }
        int loopCount = Math.min(l1.size(), l2.size());
        for (int i = 0; i < loopCount; i++) {
            result.add(l1.get(i));
            result.add(l2.get(i));
        }
        if(l2.size()>l1.size()){
            result.addAll(l2.subList(loopCount, l2.size()));
        }else {
            result.addAll(l1.subList(loopCount, l1.size()));
        }
        return result;
    }
}

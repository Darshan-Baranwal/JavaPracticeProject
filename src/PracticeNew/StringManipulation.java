package PracticeNew;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StringManipulation {
    public static void main(String[] args) {
        String input = "sdq21asdaswqeqwdadasdasdsadasdas";
        input.chars().mapToObj(v -> (char) v)
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue))
                .entrySet().stream().max(Map.Entry.comparingByKey())
                .ifPresent(maxEntry -> maxEntry.getValue().forEach(value -> {
                    System.out.println(value.getKey() +" : "+value.getValue());
                }));
        ;

//        Long highestFreq = collect.values().stream().findFirst().get();
//
//        collect.entrySet().stream().filter(e1 -> e1.getValue().equals(highestFreq))
//                .collect(Collectors.toMap(v -> v.getKey(), v -> v.getValue()))
//                .forEach((k,v) -> {
//            System.out.println(k +": "+v);
//        });

        Map<Character, Long> freqMap = new HashMap<>();
        for(char c: input.toCharArray()) {
            if(freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c)+1);
            }else {
                freqMap.put(c, 1L);
            }
        }
        freqMap = freqMap.entrySet().stream().sorted((e1,e2) -> e2.getValue().compareTo(e1.getValue())).collect(Collectors.toMap(v->v.getKey(), v->v.getValue()));
        Long highestFreq1 = freqMap.values().stream().findFirst().get();
//        freqMap.entrySet().stream().filter(e1 -> e1.getValue().equals(highestFreq1))
//                .collect(Collectors.toMap(v -> v.getKey(), v -> v.getValue()))
//                .forEach((k,v) -> {
//                    System.out.println(k +": "+v);
//                });
    }
}

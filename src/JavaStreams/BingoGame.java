package JavaStreams;

import java.util.Arrays;
import java.util.*;

public class BingoGame {

    static int bingoIndex = 1;
    static Map<Character, int[]> bingoCharacterNumberRange = new HashMap<>();

    public static void main(String[] args) {
        bingoNumberGenerator();
    }
    public static void bingoNumberGenerator() {
        for (char c :
                "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNumber = bingoIndex;
            Arrays.setAll(numbers, i->i+labelNumber);
            bingoCharacterNumberRange.put(c, numbers);
            bingoIndex +=15;
        }
        // looping map
//        bingoCharacterNumberRange.forEach((k,v) -> System.out.println(k+" has range: "+v[0]+" -> "+v[v.length-1]));
//        bingoCharacterNumberRange.entrySet().stream().map(e -> e.getKey()+" has range: "+e.getValue()[0]+" -> "+e.getValue()[e.getValue().length-1])
//                .forEach(System.out::println);
        bingoCharacterNumberRange.entrySet()
                .stream()
                .map(e-> ""+e.getKey()+e.getValue()[0]+" - "+e.getKey()+e.getValue()[e.getValue().length-1])
                .forEach(System.out::println);
    }
}

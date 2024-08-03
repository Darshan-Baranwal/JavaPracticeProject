package Practice;

import java.util.*;

public class GeminiInterview {
    public static void main(String[] args) {
        // "ABCDEFGABEFGHIJK"
        String input = "ABCDEFGABEF"; // ABCDEFG -7, BCDEFGA -7, CDEFGAB - 7
        char[] c = input.toCharArray();
        int l = 0;
        List<String> result = new ArrayList<>();
        Set<Character> s = new LinkedHashSet<>();
        for(int i = 0; i<c.length;i++) {
            s.add(c[i]);
            for(int j =i+1; j<c.length; j++) {
                if(!s.add(c[j])) {
                    if(s.size()>=l) {
                        result.add(s.toString());
                    }
                    l = Math.max(l,s.size());
                    break;
                }
            }
            s.clear();
        }
        result.forEach( v-> System.out.println(v));

    }
}

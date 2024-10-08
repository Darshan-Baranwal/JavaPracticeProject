package PracticeNew;
/*
Given a string IP, return “IPv4” if IP is a valid IPv4 address,
“IPv6” if IP is a valid IPv6 address or “Neither” if IP is not a correct IP of any type.

A valid IPv4 address is an IP in the form “x1.x2.x3.x4” where 0 <= xi <= 255 and xi cannot contain leading zeros.
For example, “192.168.1.1” and “192.168.1.0” are valid IPv4 addresses but “192.168.01.1”,
while “192.168.1.00” and “192.168@1.1“ are invalid IPv4 addresses.

A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:

1 <= xi.length <= 4 xi is a hexadecimal string which may contain digits,
lower-case English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
Leading zeros are allowed in xi.
For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses,
while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.

Example 1:

Input: IP = "172.16.254.1" Output: "IPv4" Explanation: This is a valid IPv4 address, return "IPv4".

Example 2:

Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334" Output: "IPv6" Explanation: This is a valid IPv6 address, return "IPv6".

Example 3:

Input: IP = "256.256.256.256" Output: "Neither" Explanation: This is neither a IPv4 address nor a IPv6 address.

Example 4:

Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334:" Output: "Neither"

Example 5:

Input: IP = "1e1.4.5.6" Output: "Ne
 */
import java.util.*;
import java.util.stream.Collectors;
public class MyMain {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList("192.168.1.1","192.168.1.0","192.168.1.00", "192.168@1.1",
                "2001:0db8:85a3:0000:0000:8a2e:0370:7334","2001:db8:85a3:0:0:8A2E:0370:7334","2001:0db8:85a3::8A2E:037j:7334","02001:0db8:85a3:0000:0000:8a2e:0370:7334"));
        List<Boolean> result = new ArrayList<>();
        for (String s: input){
            if(s.split("\\.").length==4){
                result.add(checkForValidIPv4(s));
            } else if (s.split(":").length==8) {
                result.add(checkForValidIPv6(s));
            } else {
                result.add(false);
            }
        }
        System.out.println(result);

    }

    private static boolean checkForValidIPv6(String st) {

        String[] sA = st.split(":");
        for(String s: sA) {
            if(s.length()>4){
                return false;
            } else {
                if(!isLetterCorrect(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isLetterCorrect(String s) {
        String aTof = "abcdef";
        String AtoF = "ABCDEF";
        for(char c: s.toCharArray()){
            if(Character.isLetter(c) && !(aTof.contains(c+"") || AtoF.contains(c+""))){
                return false;
            }
        }
        return true;
    }

    private static boolean checkForValidIPv4(String st) {
        for(String s: st.split("\\.")) {
            if(s.length() > 3) {
                return false;
            } else {
                if(!withNumberRange(s) || isHavingLeadingZeros(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isHavingLeadingZeros(String s) {
        if(s.length()>=2 && s.charAt(s.length()-1)=='0' && s.charAt(s.length()-2)=='0') {
            return true;
        }
        return false;
    }

    private static boolean withNumberRange(String s) {
        if(Integer.valueOf(s)<=255){
            return true;
        }
        return false;
    }

}

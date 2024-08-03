package Practice;


import java.util.*;
public class MyMain {
    public static void main(String[] args) {
        // unsorted int[] ->  -> 7
        // 10,
        Set<Integer> s = new HashSet<>();
        s.add(1);
        List<Integer> li = new ArrayList<>(s);
//        li.get()
        int[] l =  {10,1,7,13,2,5};
        int max = l[0];
        int sH = Integer.MIN_VALUE;
        int tH = Integer.MIN_VALUE;
        for(int i=1;i<l.length;i++) {
            if(l[i]>max){
                tH = sH;
                sH  = max;
                max = l[i];
            }else if(l[i]>sH) {
                tH = sH;
                sH=l[i];
            } else if(l[i]>tH) {
                tH=l[i];
            }
        }
System.out.print(tH);
    }
}

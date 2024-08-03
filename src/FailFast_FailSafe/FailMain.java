package FailFast_FailSafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FailMain {
    public static void main(String[] args) {
        List<Integer> l  = new ArrayList<>(Arrays.asList(1,2,3,4));
        l.forEach(System.out::print);

        System.out.println("--------------");

//        l.forEach(v -> {
//            l.remove(2); // throws ConcurrentModificationException
//        });
//        for (Integer i :
//                l) {
//            l.remove(1);// throws ConcurrentModificationException
//        }
//        for (int i = 0; i < l.size(); i++) {
//            if(i==1)
//                l.remove(1); // will run fine
//        }
//        l.forEach(System.out::print);
        Iterator<Integer> itr = l.iterator();
        while (itr.hasNext()) {
            if(itr.next()==3)
                itr.remove(); // will run fine
        }
        l.forEach(System.out::print);
     }
}

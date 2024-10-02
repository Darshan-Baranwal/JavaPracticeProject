package Practice;
import java.util.*;
public class bpTest {
    public static void main(String[] args) {

        List<String> l = new ArrayList<>();
        l.add("a->b");
        l.add("r->s");
        l.add("b->c");
        l.add("x->c");
        l.add("q->r");
        l.add("y->x");
        l.add("w->z");
        l.add("a,q,w");
        l.add("a,c,r");
        for (int i = 0; i < l.size(); i++) {
            if(l.get(i).contains("a->b")){
                l.add("123");
            }
        }

        System.out.println(l);

        l.forEach(v -> {
            if(v.contains("a->b")){
                l.add("123");
            }
        });

//        Map<String, String> m = new HashMap<>();
//        for(String s: l){
//            if(s.contains("->")) {
//
//            } else {
//
//            }
//        }


    }
}

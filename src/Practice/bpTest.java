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

        Map<String, String> m = new HashMap<>();
        for(String s: l){
            if(s.contains("->")) {

            } else {

            }
        }


    }
}

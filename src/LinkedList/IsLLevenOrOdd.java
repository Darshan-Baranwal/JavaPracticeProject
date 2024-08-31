package LinkedList;

public class IsLLevenOrOdd {

    public static void main(String[] args) {
        SingleLinkedList sl1 = new SingleLinkedList();
        sl1.addToLast("1");
        sl1.addToLast("2");
        sl1.addToLast("3");
        sl1.addToLast("4");
        sl1.addToLast("5");

        SingleLinkedList sl2 = new SingleLinkedList();
        sl2.addToLast("3");
        sl2.addToLast("4");
        sl2.addToLast("5");
        sl2.addToLast("6");
       boolean result = checkForEvenLL(sl2);
       System.out.println(result);
    }

    private static boolean checkForEvenLL(SingleLinkedList sl) {
        Node f = sl.head;
        while (f!=null && f.next!=null) {
            f = f.next.next;
        }
        if (f==null) return true;
        return false;
    }
}

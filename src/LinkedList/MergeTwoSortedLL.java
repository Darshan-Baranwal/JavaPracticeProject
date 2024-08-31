package LinkedList;

public class MergeTwoSortedLL {

    public static void main(String[] args) {
        SingleLinkedList sl1 = new SingleLinkedList();
        sl1.addToLast("1");
        sl1.addToLast("5");
        sl1.addToLast("7");
        SingleLinkedList sl2 = new SingleLinkedList();
        sl2.addToLast("2");
        sl1.addToLast("5");
//        Node h = mergeSortedLL(sl1.head, sl2.head);
        Node h = mergeSortedLLUsingRecursion(sl1.head, sl2.head);
        while(h!= null) {
            System.out.print(h.value+" ");
            h=h.next;
        }
    }

    private static Node mergeSortedLLUsingRecursion(Node head1, Node head2) {
        Node head = new Node("0");

        return head;
    }

    private static Node mergeSortedLL(Node l, Node b) {
        Node h = new Node("0");
        Node curr = h;
        while (true) {
            if(l == null){
                curr.next = b;
                break;
            }
            if (b == null) {
                curr.next = l;
                break;
            }
            int lValue = Integer.parseInt(l.value);
            int bValue = Integer.parseInt(b.value);

            if(lValue<=bValue) {
                curr.next = l;
                l = l.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        return h.next;
    }

}

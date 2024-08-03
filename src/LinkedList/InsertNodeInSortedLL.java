package LinkedList;

public class InsertNodeInSortedLL {
    public static void main(String[] args) {
        SingleLinkedList s = new SingleLinkedList();
        s.addToLast("5");
        s.addToLast("6");
        s.addToLast("9");
        s.addToLast("10");
        insertNodeInSortedLL("8", s);
        insertNodeInSortedLL("11", s);
        insertNodeInSortedLL("4", s);
        s.printLinkedList(s);
    }
    private static void insertNodeInSortedLL(String number, SingleLinkedList s) {
        Node newNode = new Node(number);
        Node prev = null;
        Node current = s.head;
        if(s.head == null) {
            s.head = newNode;
            return;
        }
        while(current != null && Integer.valueOf(current.value)<Integer.valueOf(newNode.value)) {
            prev = current; // 6
            current = current.next; //9
        }
        newNode.next = current;
        if(prev != null) {
            prev.next = newNode;
        } else {
            s.head = newNode;
        }
    }
}

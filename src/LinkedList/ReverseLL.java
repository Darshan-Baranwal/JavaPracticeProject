package LinkedList;

public class ReverseLL {
    Node head;
    int length;
    public ReverseLL(){
        head=null;
        length = 0;
    }
    public static void main(String[] args) {
        ReverseLL sl = new ReverseLL();
        sl.addToLast("5");
        sl.addToLast("6");
        sl.addToLast("7");
//        sl.addToLast("8");
//        sl.addToLast("9");
//        sl.printLinkedList(sl);
//        sl.addAtIndex(2,"11");
//        sl.printLinkedList(sl);
        sl.reverseLLUsingRecursion(); // O(n), O(n)
        sl.printLinkedList(sl);
        System.out.println("\n");
        sl.reverseLL();

        sl.printLinkedList(sl);
    }

    private void reverseLL() {
        Node current = this.head;
        Node previous = null;
        while(current!=null) {
            Node temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }
        this.head = previous;
    }

    private void reverseLLUsingRecursion() {
        Node[] headArr = new Node[1];
        reverseLinkedList(this.head, headArr);
    }

    private void reverseLinkedList(Node current, Node[] headArr) {
        Node next = current.next;
        if(next == null) {
            headArr[0] = current; // storing new head
            return;
        }
        reverseLinkedList(next, headArr);
        next.next = current;
        current.next = null;
        this.head = headArr[0];
    }


    public void addToLast(String number) {
        Node newNode = new Node(number);
        if(this.head==null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while(currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            newNode.next = null;
        }
        length++;
    }
    void addAtIndex(int index, String number) {
        Node newNode = new Node(number);
        if(this.head==null) {
            head = newNode;
        }else {
            int i = 0;
            Node currentNode = head;
            while(i<index-1) {
                currentNode = currentNode.next;
                i++;
            }
            Node temp = currentNode.next;
            currentNode.next = newNode;
            newNode.next = temp;
        }
    }
    void printLinkedList(ReverseLL sl) {
        Node currentNode = this.head;
        System.out.print(currentNode.value + " ");
        while(currentNode.next != null) {
            currentNode = currentNode.next;
            System.out.print(currentNode.value + " ");
        }

    }
}

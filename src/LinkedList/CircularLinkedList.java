package LinkedList;

class Node{
    String value;
    Node next;
    public Node(String data) {
        this.value = data;
        next = null;
    }
    @Override
    public boolean equals(Object n) {
        return ((Node)n).value.compareTo(this.value)==0;
    }
}
public class CircularLinkedList {
    int length;
    // tail Node is just before Head
    Node tail;
    // head from where the Circular Linked List starts
    Node head;
     public CircularLinkedList() {
         tail = null;
         head = null;
         length = 0;
     }
     public void insertAtBeginning(Node newNode) {
         if(head==null) {
             head = newNode;
             tail = newNode;
             newNode.next = head;
         } else {
             newNode.next = head;
             head.next = newNode;
             head = newNode;
         }
         length++;
     }
    public void insertAtLast(Node newNode) {
         if(head == null) {
             head = newNode;
             tail = newNode;
             newNode.next = head;
         } else {
             tail.next = newNode;
             newNode.next = head;
             tail = newNode;
         }
         length++;
    }
     public int insertAfterGivenNode(Node newNode, Node positionNode) throws Exception {
         Node currentNode = head;
         while(!currentNode.next.value.equals(positionNode.value)) {
             if(currentNode.next == head){
                 return -1;
             }
             currentNode = currentNode.next;
         }
         if(head.value.equals(positionNode.value)) {
            head = newNode;
         }
         newNode.next=currentNode.next;
         currentNode.next = newNode;
         length++;
         return 1;
     }

     public boolean containsGivenData(String data) {
         Node currentNode = head;
         boolean result = false;
         while(!currentNode.value.equals(data)) {
             if(currentNode.next == head){
                 return false;
             }
             currentNode = currentNode.next;
         }
         return true;
     }

     public void deleteAtBeginning() {
         if(head==null){
             System.out.println("Nothing to delete");
             return;
         }
         if(head == tail) {
             // only 1 entry
             head = tail = null;
             length = 0;
         }else {
             tail.next = head.next;
             head = tail.next;
             length--;
         }
     }

     public void deleteAtLast() {
         if (head == null) {
             System.out.println("Nothing to delete");
             return;
         }
         if (head == tail) {
             // only 1 entry
             head = tail = null;
             length = 0;
         } else {
             Node currentNode = tail;
             while (currentNode.next != tail) {
                 currentNode = currentNode.next;
             }
             currentNode.next = tail.next;
             tail = currentNode;
             length--;
         }
     }

     public int deleteANode(Node nodeToBeDeleted) {
         if(nodeToBeDeleted.value.equals(head.value)) {
             deleteAtBeginning();
             return 1;
         } else if(nodeToBeDeleted.value.equals(tail.value)) {
             deleteAtLast();
             return 1;
         } else {
             Node currentNode = head;
             while (!currentNode.next.value.equals(nodeToBeDeleted.value)) {
                 if (currentNode.next == head) {
                     return -1;
                 }
                 currentNode = currentNode.next;
             }
             Node furtherNode = currentNode.next.next;
             currentNode.next = furtherNode;
             return 1;
         }
     }

     public boolean isEmpty() {
         return tail == null;
     }
     public void clearLinkedList() {
         tail = head = null;
         length = 0;
     }

     public int getLength() {
         return length;
     }
      public String toString() {
         String result = "[";
         if(head == null) {
             return result+"]";
         } else {
             result = result+" "+head.value;
             Node currentNode = head.next;
             while(currentNode != head) {
                 result = result+" "+currentNode.value;
                 currentNode = currentNode.next;
             }
             return result+" ]";
         }
      }

    public static void main(String[] args) throws Exception {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        circularLinkedList.insertAtBeginning(new Node("A"));
        circularLinkedList.insertAtBeginning(new Node("C"));
        circularLinkedList.insertAtLast(new Node("D"));
        circularLinkedList.insertAtLast(new Node("B"));
        int result = circularLinkedList.insertAfterGivenNode(new Node("E"), new Node("A"));
        System.out.println(circularLinkedList.containsGivenData("D"));

        System.out.println(circularLinkedList.toString());
        circularLinkedList.deleteAtBeginning();
        System.out.println(circularLinkedList.toString());
        circularLinkedList.deleteAtLast();
        System.out.println(circularLinkedList.toString());
        circularLinkedList.deleteANode(new Node("A"));
        System.out.println(circularLinkedList.toString());
    }
}

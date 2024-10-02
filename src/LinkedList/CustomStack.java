package LinkedList;

public class CustomStack {
    int[] stack;
    int top=-1;
    int initialCapacity;
    CustomStack(int initialCapacity) {
        stack = new int[initialCapacity];
        this.initialCapacity = initialCapacity;
    }

    void push(int item) throws Exception {
        if((initialCapacity)<= top) {
            throw new Exception("Stack is full");
        } else {
            stack[++top] = item;
        }
    }
    int peek() {
        return stack[top];
    }
    int pop() throws Exception {
        if(top <0) {
            throw new Exception("Stack is empty");
        }
        int result = stack[top--];
        return result;
    }
    public static void main(String[] args) {
        CustomStack stack = new CustomStack(2);
        try{
            stack.push(1);
            stack.push(2);
            System.out.println(stack.peek());
            System.out.println(stack.pop());
            System.out.println(stack.peek());
            System.out.println(stack.pop());
            stack.pop();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}



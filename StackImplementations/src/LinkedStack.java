/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: 4. Stack Implementations
 * Date: 9/14/2016
 */
public class LinkedStack<T> implements StackInterface<T> {

    private Node topNode;

    public LinkedStack() { topNode = null; }

    public T peek2() {

        T anEntry;

        if(topNode.getNext() == null)
            throw new StackOverflowError("The stack has less than two entries.");
        else
            anEntry = topNode.getNext().getData();

        return anEntry;
    }

    public void remove(int n) {
        int i = 0;
        while (!isEmpty() && i < n){
            pop();
            i++;
        }
    }

    public void filter(T value){
        StackInterface<T> tempStack = new LinkedStack<>();
        while(!tempStack.isEmpty()){
            T element = tempStack.pop();
            if(!element.equals(value))
                tempStack.push(element);
        }
        while(!tempStack.isEmpty()){
            this.push(tempStack.pop());
        }
    }

    public void reverse(){
        Node reversedPart = null;
        Node current = topNode;
        while(current != null){
            Node next = current.next;
            current.next = reversedPart;
            reversedPart = current;
            current = next;
        }
        topNode = reversedPart;
    }

    public void pushAll(T[] a) {
        int i = 0;
        for(T array: a){
            push(a[i++]);
        }
    }

    public T peek() {

        T anEntry;

        if(isEmpty())
            throw new StackOverflowError("The stack is empty.");
        else
            anEntry = topNode.getData();

        return anEntry;
    }

    public T pop() {
        T top = peek();
        if(!isEmpty())
            topNode = topNode.getNext();

        return top;
    }

    public void doubleStack(){
        Node current = topNode;
        while(current != null){
            Node next = current.next;
            Node newNode = new Node(current.data, current.next);
            current.next = newNode;
            current = next;
        }
    }

    public void push(T newEntry) {
        topNode = new Node(newEntry, topNode);
    }

    public boolean isEmpty() {
        return topNode == null;
    }

    public void clear() {
        topNode = null;
    }

    public String toString(){
        Node currentNode = topNode;
        String output = "";
        while(currentNode != null){
            output += "'" + currentNode.getData() + "' ";
            currentNode = currentNode.getNext();
        }
        return output;
    }

    private class Node {

        private Node next;
        private T data;

        public Node(T data, Node next){
            this.data = data;
            this.next = next;
        }

        public Node getNext(){ return next; }

        public T getData() { return data; }

        public void setNext(Node next) { this.next = next; }

        public void setData(T anEntry) { this.data = data; }
    }
}

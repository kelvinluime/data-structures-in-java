/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:
 * Date:
 */
public class DoublyLinkedQueue<T>{

    private Node firstNode;
    private Node lastNode;

    public DoublyLinkedQueue(){
        firstNode = null;
        lastNode = null;
    }

    public void addToBack(T newEntry){
        Node newNode = new Node(lastNode, newEntry, null);
        if(isEmpty())
            firstNode = newNode;
        else
            lastNode.next = newNode;

        lastNode = newNode;
    }

    public void addToFront(T newEntry){
        Node newNode = new Node(null, newEntry, firstNode);
        if(isEmpty())
            lastNode = newNode;
        else
            firstNode.prev = newNode;

        firstNode = newNode;
    }

    public T removeFront(){
        T front = firstNode.data; //might throw EmptyQueueException
        assert firstNode != null;
        firstNode = firstNode.next;
        if(firstNode == null)
            lastNode = null;
        else
            firstNode.prev = null;
        return front;

    }

    public T removeBack(){
        T back = lastNode.data;
        assert lastNode != null;
        lastNode = lastNode.prev;
        if(lastNode == null)
            firstNode = null;
        else
            lastNode.next = null;
        return back;
    }

    public T getFront() throws EmptyQueueException {
        return firstNode.data;
    }

    public void splice(QueueInterface<T> q) {
        while(!q.isEmpty())
            addToBack(q.dequeue());
    }

    public boolean isEmpty() {
        return firstNode == null && lastNode == null;
    }

    public boolean isFull() {
        return false;
    }

    public void clear() {
        firstNode = null;
        lastNode = null;
    }

    private class Node {

        Node next;
        T data;
        Node prev;

        public Node(Node prev, T data, Node next){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

    }
}

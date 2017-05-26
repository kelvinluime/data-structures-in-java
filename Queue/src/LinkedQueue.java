/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Two Queues
 * Date: 10/5/2016
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    private Node queueNode;
    private Node freeNode;

    public LinkedQueue(){
        freeNode = new Node(null, null);
        freeNode.setNext(freeNode);
        queueNode = freeNode;
    }

    public void splice(QueueInterface<T> anotherQ){
        while(!anotherQ.isEmpty()){
            this.enqueue(anotherQ.dequeue());
        }
    }

    public void splice2(LinkedQueue<T> anotherQ){
        if(anotherQ.isEmpty()){
            throw new EmptyQueueException();
        } else {
            freeNode.data = anotherQ.queueNode.data;
            freeNode.next = anotherQ.queueNode.next;
            freeNode = anotherQ.freeNode;
            freeNode.next = queueNode;
        }
    }

    public void dequeueUpTo(T anEntry){
        if(isEmpty())
            throw new EmptyQueueException();
        else{
            boolean done = false;
            while(!isEmpty() && !done){
                if(getFront().equals(anEntry))
                    done = true;
                else
                    dequeue();
            }
        }
    }

    public void enqueue(T entry) {
        freeNode.setData(entry);
        if(isFull()){
            Node newNode = new Node(null, freeNode.getNext());
            freeNode.setNext(newNode);
        }
        freeNode = freeNode.getNext();
    }

    public boolean isFull(){
        return freeNode.getNext() == queueNode;
    }

    public T dequeue() throws EmptyQueueException{
        if(isEmpty())
            throw new EmptyQueueException();
        T front = queueNode.getData();
        queueNode.setData(null);
        queueNode = queueNode.getNext();
        return front;
    }

    public T getFront() throws EmptyQueueException{
        T front = null;
        try{
            front = queueNode.getData();
        }
        catch(EmptyQueueException e){
            e.printStackTrace();
        }

        return front;
    }

    public boolean isEmpty() {
        return queueNode == freeNode;
    }

    public void clear() {
        freeNode.setNext(freeNode);
        queueNode = freeNode;
    }

    public class Node{

        private Node next;
        private T data;

        public Node(T data, Node next){
            this.next = next;
            this.data = data;
        }

        public Node(T data){
            this(data, null);
        }

        public void setNext(Node next){
            this.next = next;
        }

        public void setData(T data){
            this.data = data;
        }

        public Node getNext(){
            return next;
        }

        public T getData(){
            return data;
        }

    }
}

import java.util.Arrays;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Two Queues
 * Date: 10/5/2016
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] q;
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_INITIAL_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayQueue(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayQueue(int capacity){
        if(capacity > MAX_CAPACITY)
            capacity = DEFAULT_INITIAL_CAPACITY;
        @SuppressWarnings("unchecked")
        T[] temp = (T[])new Object[capacity + 1];
        q = temp;
        frontIndex = 0;
        backIndex = capacity;
    }

    public void enqueue(T entry) {
        ensureCapacity();
        backIndex = (backIndex + 1) % q.length;
        q[backIndex] = entry;

    }

    public T dequeue() throws EmptyQueueException{
        if(isEmpty())
            throw new EmptyQueueException();
        else{
            T front = getFront();
            q[frontIndex] = null;
            frontIndex = (frontIndex + 1) % q.length;
            return front;
        }
    }

    public T getFront() throws EmptyQueueException{
        T front = null;
        try{
            front = q[frontIndex];
        }
        catch(EmptyQueueException e){
            e.printStackTrace();
        }
        return front;
    }

    public void splice(QueueInterface<T> anotherQ) throws EmptyQueueException{
        if(anotherQ.isEmpty())
            throw new EmptyQueueException();
        while (!anotherQ.isEmpty())
            this.enqueue(anotherQ.dequeue());
    }

    public void splice2(ArrayQueue<T> anotherQ) {
        if (anotherQ.isEmpty())
            throw new EmptyQueueException();
        else {
            while ((anotherQ.backIndex + 1) % anotherQ.q.length != anotherQ.frontIndex) {
                if ((backIndex + 1) % q.length == frontIndex)
                    q = Arrays.copyOf(q, q.length * 2);
                backIndex = (backIndex + 1) % q.length;
                q[backIndex] = anotherQ.q[anotherQ.frontIndex];
                anotherQ.q[anotherQ.frontIndex] = null;
                anotherQ.frontIndex = (anotherQ.frontIndex + 1) % anotherQ.q.length;
            }
        }
    }

    public boolean isEmpty() {
        return (backIndex + 1) %  q.length == frontIndex;
    }

    public boolean isFull() {
        return frontIndex == (backIndex + 2) % q.length;
    }

    public void clear() {
        while(!isEmpty())
            dequeue();
    }

    private void ensureCapacity(){
        if(isFull()) {
            T[] temp = (T[]) new Object[q.length * 2];
            System.arraycopy(q, 0, temp, 0, q.length);
            q = temp;
        }
    }
}

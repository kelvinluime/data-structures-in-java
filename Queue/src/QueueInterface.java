/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Two Queues
 * Date: 10/5/2016
 */
public interface QueueInterface<T> {

    void enqueue(T entry);

    T dequeue() throws EmptyQueueException;

    T getFront() throws EmptyQueueException;

    void splice(QueueInterface<T> q);

    boolean isEmpty();

    void clear();

}

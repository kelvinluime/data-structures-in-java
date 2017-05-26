/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Max Heap
 * Date: 11/10/2016
 */
public interface MaxHeapInterface<T extends Comparable<? super T>> {

    public void add(T newEntry);

    public T removeMax();

    public T getMax();

    public boolean isEmpty();

    public int getSize();

    public void clear();

}

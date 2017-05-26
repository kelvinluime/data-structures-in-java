/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: 4. Stack Implementations
 * Date: 9/14/2016
 */
public interface StackInterface<T> {

    public T peek2();

    public String toString();

    public void remove(int n);

    public void pushAll(T[] a);

    public T peek();

    public  T pop();

    public void push(T newEntry);

    public boolean isEmpty();

    public void clear();

    public void filter(T value);

    public void reverse();

    public void doubleStack();

}

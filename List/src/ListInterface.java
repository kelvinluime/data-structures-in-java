/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:  Two List Implementations
 * Date:    10/12/2016
 */
public interface ListInterface<T> {

    void add(T entry);

    void add(int position, T entry);

    T remove(int position);

    void clear();

    boolean replace(int position, T entry);

    T getEntry(int position);

    boolean contains(T entry);

    int getLength();

    boolean isEmpty();

    T[] toArray();

    int getPosition(T entry);

    boolean remove(T entry);

    void moveToEnd();

    void addAll(T[] items);

    ListInterface<T> getAllLessThan(Comparable<T> anObject);

}

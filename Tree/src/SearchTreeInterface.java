/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:
 * Date:
 */
import java.util.Iterator;
public interface SearchTreeInterface<T extends Comparable<? super T>> extends TreeInterface<T>{

    boolean contains(T entry);

    T getEntry(T entry);

    T add(T newEntry);

    T remove(T entry);

    Iterator<T> iterator();

    Iterator<T> getLeftIterator();

}

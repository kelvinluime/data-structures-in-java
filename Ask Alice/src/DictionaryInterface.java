import java.util.Iterator;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Ask Alice
 * Date: 10/26/2016
 */
public interface DictionaryInterface<K, V>{

    V add(K key, V value);

    V remove(K key, V value);

    V getValue(K key);

    boolean contains(K key);

    Iterator<K> getKeyIterator();

    Iterator<V> getValueIterator();

    boolean isEmpty();

    int getSize();

    void clear();

}

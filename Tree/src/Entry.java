/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:
 * Date:
 */
public class Entry<K extends Comparable<? super K>, V> implements Comparable<Entry<K, V>>{

    private K key;
    private V value;

    public Entry(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){return key;}

    public V getValue(){return value;}

    public int compareTo(Entry<K, V> other) {
        return key.compareTo(other.key);
    }
}

import java.util.Iterator;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:
 * Date:
 */
public class Dictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>{

    private SearchTreeInterface<Entry<K, V>> bst;

    public Dictionary(){
        bst = new BinarySearchTree<>();
    }

    public V add(K key, V value) {
        Entry<K, V> newEntry = new Entry<>(key, value);
        Entry<K, V> returnEntry = bst.add(newEntry);
        V result = null;
        if(returnEntry != null)
            result = returnEntry.getValue();
        return result;
    }

    public V getValue(K key) {
        Entry<K, V> findEntry = new Entry<>(key, null);
        Entry<K, V> returnEntry = bst.getEntry(findEntry);
        V result = null;
        if(returnEntry != null)
            result = returnEntry.getValue();
        return result;
    }

    public V remove(K key) {
        Entry<K, V> findEntry = new Entry<>(key, null);
        Entry<K, V> returnEntry = bst.remove(findEntry);
        V result = null;
        if(returnEntry != null)
            result = returnEntry.getValue();
        return result;
    }

    public boolean contains(K key) {
        Entry<K, V> findEntry = new Entry<>(key, null);
        Entry<K, V> returnEntry = bst.getEntry(findEntry);
        return returnEntry != null;
    }

    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public int getSize() {
        return bst.getNumberOfNodes();
    }

    public void clear() {
        bst.clear();
    }

    private class KeyIterator implements Iterator<K>{

        private Iterator<Entry<K, V>> localIterator;

        public KeyIterator(){
            localIterator = bst.iterator();
        }

        public boolean hasNext() {
            return localIterator.hasNext();
        }

        public K next() {
            Entry<K, V> nextEntry = localIterator.next();
            return nextEntry.getKey();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterator implements Iterator<V>{

        private Iterator<Entry<K, V>> localIterator;

        public ValueIterator(){
            localIterator = bst.iterator();
        }

        public boolean hasNext() {
            return localIterator.hasNext();
        }

        public V next() {
            Entry<K, V> nextEntry = localIterator.next();
            return nextEntry.getValue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

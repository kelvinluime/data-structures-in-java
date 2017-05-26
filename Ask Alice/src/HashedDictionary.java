import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Ask Alice
 * Date: 10/26/2016
 */
public class HashedDictionary<K, V> implements DictionaryInterface<K, V>{

    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 20000;
    private static final int MAX_CAPACITY = 100000;

    private TableEntry<K, V>[] hashTable;
    private int tableSize;
    private static final int MAX_SIZE = 2 * MAX_CAPACITY;
    private boolean initialized = false;
    private static final double MAX_LOAD_FACTOR = 0.5;

    public HashedDictionary(){
        this(DEFAULT_CAPACITY);
    }

    public HashedDictionary(int initialCapacity){
        checkCapacity(initialCapacity);
        numberOfEntries = 0;

        tableSize = getNextPrime(initialCapacity);
        checkSize(tableSize);

        @SuppressWarnings("unchecked")
        TableEntry<K, V>[] temp = (TableEntry<K, V>[])new TableEntry[tableSize];
        hashTable = temp;
        initialized = true;

    }

    public V add(K key, V value) {
        checkInitialization();
        if(key == null || value == null)
            throw new IllegalArgumentException();

        V oldValue;
        int index = getHashIndex(key);
        index = quadraticProbe(index, key);      //find available location in the array to avoid collision

        assert index >= 0 && index < hashTable.length;
        if(hashTable[index] == null || hashTable[index].isRemoved()){
            hashTable[index] = new TableEntry<>(key, value);
            numberOfEntries++;
            oldValue = null;
        }
        else {
            oldValue = hashTable[index].getValue();
            hashTable[index].setValue(value);
        }

        if(isHashTableTooFull())
            enlargeHashTable();

        return oldValue;
    }

    public V remove(K key, V value) {
        checkInitialization();
        V removedValue = null;
        int index = getHashIndex(key);
        index = locate(index, key);
        if(index != -1){
            removedValue = hashTable[index].getValue();
            hashTable[index].setToRemoved();
            numberOfEntries--;
        }
        return removedValue;
    }

    public V getValue(K key) {
        checkInitialization();
        V result = null;
        int index = getHashIndex(key);
        index = locate(index, key);
        if(index != -1)
            result = hashTable[index].getValue();
        return result;
    }

    public boolean contains(K key) {
        return false;
    }

    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public int getSize() {
        checkInitialization();
        return numberOfEntries;
    }

    public void clear() {
        numberOfEntries = 0;

        tableSize = getNextPrime(DEFAULT_CAPACITY);
        checkSize(tableSize);

        @SuppressWarnings("unchecked")
        TableEntry<K, V>[] temp = (TableEntry<K, V>[])new TableEntry[tableSize];
        hashTable = temp;
    }

    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("capacity of dictionary exceeds allowed maximum of " + MAX_CAPACITY);
    }

    private int getNextPrime(int number){
        int primeNumber;
        if(isPrime(number))
            primeNumber = number;
        else
            primeNumber = getNextPrime(number + 1);
        return primeNumber;
    }

    private boolean isPrime(int number){
        boolean isPrime = false;
        assert number > 0;
        if(number == 2){
            isPrime = true;
        } else if(number > 2 && number % 2 != 0){
            int i = 3;
            boolean isDividable = false;    //check if number is dividable by other odd numbers
            while(!isDividable && i <= Math.sqrt(number)){
                if(number % i == 0){
                    isDividable = true;
                }
                i = i + 2;
            }
            if(!isDividable)            // if it is not dividable except by 1 and itself, then it is prime number
                isPrime = true;
        }

        return isPrime;
    }

    private void checkSize(int size){
        if(size > MAX_SIZE)
            throw new IllegalStateException("capacity of hash table exceeds maximum of " + MAX_SIZE);
    }

    private int getHashIndex(K key){
        int hashIndex = key.hashCode() % hashTable.length;
        if(hashIndex < 0){
            hashIndex += hashTable.length;
        }
        return hashIndex;
    }

    private int locate(int index, K key){
        boolean found = false;
        while(!found && (hashTable[index] != null)){
            if(hashTable[index].isIn() && key.equals(hashTable[index].getKey()))
                found = true;
            else
                index = (index + 1) % hashTable.length; //linear probing
        }

        int result = -1;
        if(found)
            result = index;

        return result;
    }

    private int linearProbe(int index, K key){
        boolean found = false;
        int removedStateIndex = -1;
        while(!found && hashTable[index] != null){
            if(hashTable[index].isIn()){
                if(key.equals(hashTable[index].getKey()))
                    found = true;
                else
                    index = (index + 1) % hashTable.length;
            }
            else {
                if(removedStateIndex == -1)
                    removedStateIndex = index;
                index = (index + 1) % hashTable.length;
            }
        }
        if(found || removedStateIndex == -1)
            return index;
        else
            return removedStateIndex;
    }

    private int quadraticProbe(int index, K key){
        int j = 0;
        boolean found = false;
        int removedStateIndex = -1;
        while(!found && hashTable[index] != null) {
            if (hashTable[index].isIn()) {
                if (key.equals(hashTable[index].getKey()))
                    found = true;
                else {
                    index = (index + j * j) % hashTable.length;
                    j++;
                }
            } else {
                if (removedStateIndex == -1)
                    removedStateIndex = index;
                index = (index + j * j) % hashTable.length;
                j++;
            }
        }
            if(found || removedStateIndex == -1)
                return index;
            else
                return removedStateIndex;
    }

    private boolean isHashTableTooFull(){
        boolean isTooFull;
        double loadFactor = numberOfEntries / hashTable.length;
        if(loadFactor > MAX_LOAD_FACTOR)
            isTooFull = true;
        else
            isTooFull = false;
        return isTooFull;
    }

    private void enlargeHashTable(){
        TableEntry<K, V>[] oldTable = hashTable;
        int oldSize = hashTable.length;
        int newSize = getNextPrime(oldSize + oldSize);

        @SuppressWarnings("unchecked")
        TableEntry<K, V>[] temp = (TableEntry<K, V>[])new TableEntry[newSize];
        hashTable = temp;
        numberOfEntries = 0;
        for(int index = 0; index < oldSize; index++){
            if(oldTable[index] != null && oldTable[index].isIn())
                add(oldTable[index].getKey(), oldTable[index].getValue());
        }
    }

    private class TableEntry<K, V>{
        private K key;
        private V value;
        private States state;

        private TableEntry(K key, V value){
            this.key = key;
            this.value = value;
            state = state.CURRENT;
        }

        public boolean isIn() {
            return state == States.CURRENT;
        }

        public boolean isRemoved() {
            return state == States.REMOVED;
        }

        public void setToIn() {
            state = States.CURRENT;
        }

        public void setToRemoved() {
            state = States.REMOVED;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private class KeyIterator implements Iterator<K>{

        private int currentIndex;
        private int numberLeft;

        public KeyIterator(){
            currentIndex = 0;
            numberLeft = numberOfEntries;
        }

        public boolean hasNext() {
            return numberLeft > 0;
        }

        public K next() {
            K result = null;
            if(hasNext()){
                while(hashTable[currentIndex] == null || hashTable[currentIndex].isRemoved()){
                    currentIndex++;
                }
                result = hashTable[currentIndex].getKey();
                numberLeft--;
                currentIndex++;
            }
            else
                throw new NoSuchElementException();
            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterator implements Iterator<V>{

        private int numberLeft;
        private int currentIndex;

        public ValueIterator(){
            numberLeft = numberOfEntries;
            currentIndex = 0;
        }

        public boolean hasNext() {
            return numberLeft > 0;
        }

        public V next() {
            V result;
            if(hasNext()){
                while(hashTable[currentIndex] == null || hashTable[currentIndex].isRemoved()){
                    currentIndex++;
                }
                result = hashTable[currentIndex].value;
                numberLeft--;
                currentIndex++;
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }

        public void remove() {

        }
    }

    private void checkInitialization(){
        if(!initialized)
            throw new SecurityException("class is not initialized");
    }

    private enum States {CURRENT, REMOVED}  //enum class to flag the hash location's status

}

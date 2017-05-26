import java.util.Arrays;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:  Two List Implementations
 * Date:    10/12/2016
 */

public class ArrayBasedList<T extends Comparable<? super T>> implements ListInterface<T> {

    private int numberOfEntries;
    private T[] list;
    private boolean initialized = false;
    private static final int INITIAL_CAPACITY = 1000000;

    public ArrayBasedList(){
        this(INITIAL_CAPACITY);
    }

    public ArrayBasedList(int capacity){
        if(capacity <= INITIAL_CAPACITY) {
            capacity = INITIAL_CAPACITY;
        }
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[capacity + 1];
        list = temp;
        numberOfEntries = 0;
        initialized = true;
    }

    public ArrayBasedList(T[] array){
        assert array != null;
        int capacity = array.length;
        if(capacity < INITIAL_CAPACITY)
            capacity = INITIAL_CAPACITY;
        @SuppressWarnings("unchecked")
        T[] temp = (T[])new Comparable[capacity + 1];
        list = temp;
        numberOfEntries = array.length;
        for(int i = 0; i < numberOfEntries; i++){
            list[i + 1] = array[i];
        }
        initialized = true;
    }

    public T getMin(){
        T min = null;
        for(int i = 1; i < numberOfEntries; i++){
            if(list[i].compareTo(list[i + 1]) < 0)
                min = list[i];
            else
                min = list[i + 1];
        }
        return min;
    }

    public boolean equals(ListInterface<T> anotherList){
        boolean isEqual = true;
        if(anotherList.getLength() != getLength())
            isEqual = false;
        else {
            for (int i = 1; i < getLength() + 1; i++){
                if(!getEntry(i).equals(anotherList.getEntry(i)))
                    isEqual = false;
            }
        }
        return isEqual;
    }

    public T removeMin(){
        T min = getMin();
        remove(min);
        return min;
    }

    public void moveToEnd() {
        add(remove(1));
    }

    public boolean remove(T entry) {
        boolean found = false;
        int counter = 1;
        while(!found && counter < numberOfEntries + 1){
            if(list[counter].equals(entry)) {
                remove(counter);
                found = true;
            }
            counter++;
        }
        return found;
    }

    public void addAll(T[] items) {

    }

    public ListInterface<T> getAllLessThan(Comparable<T> anObject) {
        return null;
    }

    public void add(T entry) {
        add(numberOfEntries + 1, entry);
    }

    public void add(int position, T entry) {
        checkInitialization();
        if(position >= 1 && position <= numberOfEntries + 1){
            makeRoom(position);
            list[position] = entry;
            numberOfEntries++;
            ensureCapacity();
        }
        else
            throw new IndexOutOfBoundsException();
    }

    public int getPosition(T entry){
        int counter = 1;
        int position = 0;
        while(counter < list.length + 1){
            if(list[counter].equals(entry))
                position = counter;
            counter++;
        }
        if(position == 0)
            throw new IllegalArgumentException("Entry is not in the list.");

        return position;
    }

    public T remove(int position) {
        checkInitialization();
        T result = null;
        if(position >= 1 && position <= numberOfEntries){
            deleteGap(position);
            list[numberOfEntries--] = null;
        }
        else
            throw new IndexOutOfBoundsException();

        if(isTooBig())
            reduceArray();

        return result;
    }

    public void clear() {
        @SuppressWarnings("unchecked")
        T[] temp = (T[])new Object[list.length];
        list = temp;
        numberOfEntries = 0;
        temp = null;

        if(isTooBig())
            reduceArray();
    }

    public boolean replace(int position, T entry) {
        checkInitialization();
        boolean isSuccessful = false;
        if(position >= 1 && position <= numberOfEntries){
            list[position] = entry;
            isSuccessful = true;
        }
        else{
            throw new IndexOutOfBoundsException();
        }
        return isSuccessful;
    }

    public T getEntry(int position) {
        checkInitialization();
        return list[position];
    }

    public boolean contains(T entry) {
        checkInitialization();
        boolean found = false;
        int i = 1;
        if(!isEmpty()) {
            while(!found && i <= numberOfEntries){
                if(entry.equals(list[i++]))
                    found = true;
            }
        }

        return found;
    }

    public int getLength() {
        return numberOfEntries;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Comparable[numberOfEntries + 1];
        for(int i = 1; i < numberOfEntries + 1; i++){
            result[i] = list[i];
        }
        return result;
    }

    private void ensureCapacity(){
        if(numberOfEntries + 1 == list.length){
            list = Arrays.copyOf(list, list.length * 2);
        }
    }

    private void makeRoom(int position){
        assert position >= 1 && position <= numberOfEntries + 1;
        int newIndex = position;
        int lastIndex = numberOfEntries + 1;
        for(int i = lastIndex; i >= newIndex; i--){
            list[i] = list[i - 1];
        }
    }

    private boolean isTooBig(){
        return list.length > 20;
    }

    private void reduceArray(){
        @SuppressWarnings("unchecked")
        T[] temp = (T[])new Comparable[list.length * 3 / 4];
        int counter = 1;
        while(!isEmpty()){
            temp[counter++] = removeMin();
        }
    }

    private void checkInitialization(){
        if(!initialized)
            throw new SecurityException("Class was not initialized.");
    }

    private void deleteGap(int position){
        checkInitialization();
        if(position >= 1 && position < numberOfEntries){
            for(int i = position; i < numberOfEntries; i++){
                list[i] = list[i + 1];
            }
        }
    }

}

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: ResizableArrayBag
 * Date: 9/7/2016
 */
public interface BagInterface<T> {

    public int getCurrentSize();

    public boolean isEmpty();

    public boolean add(T newEntry);

    public T remove();

    public boolean remove(T anEntry);

    public void clear();

    public int getFrequencyOf(T anEntry);

    public boolean contains(T anEntry);

    public T[] toArray();

    public boolean isArrayFull();

    public int getCapacity();

    public boolean hasDuplicateEntries();

    public boolean equals(BagInterface<T> aBag);

    boolean hasUniqueItem();
}

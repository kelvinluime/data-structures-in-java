/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: 2. ResizableArrayBag
 * Date: 8/31/2016
 */
public class ResizableArrayBag<T> implements BagInterface<T> {

    private T[] bag;
    private int numberOfEntries;
    private int capacity;
    private boolean initialized = false;
    private boolean result = false;
    private int frequency;
    private T tempEntry;
    private static final int DEFAULT_SIZE = 20;

    public ResizableArrayBag(int capacity) {

        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[capacity];
        bag = tempBag;
        numberOfEntries = 0;
        this.capacity = capacity;
        initialized = true;

    }

    public ResizableArrayBag(){                 //Default size bag
        this(DEFAULT_SIZE);
    }

    public int getCurrentSize() {

        return numberOfEntries;

    }

    public boolean isEmpty() {

        return numberOfEntries == 0;
    }

    public boolean add(T newEntry) {

        checkInitialization();

        if (isArrayFull())                          //if the bag is full, expands it before adding
            expandArray();

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        System.out.println("Entry '" + newEntry + "' was added into the bag."); //For testing purpose.

        return true;                        //since the bag is expandable, it always returns true
    }

    public T remove() {

        checkInitialization();

        tempEntry = null;

        if(!isEmpty()) {
            tempEntry = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        if(isTooBig())
           reduceArray();

        return tempEntry;
    }

    public boolean remove(T anEntry) {                  //remove a specific object once.

        checkInitialization();

        boolean entryFound = false;           //these variables are used to control the while loop below.
        int i = 0;

        if (contains(anEntry)){               //checks if the bag contains the object.
            while(!entryFound){
                if(bag[i].equals(anEntry)) {
                    bag[i] = bag[numberOfEntries - 1];          //removes an entry, then assign the last entry to
                    bag[numberOfEntries - 1] = null;            //fill up its space
                    entryFound = true;
                }
                i++;
            }
            numberOfEntries--;
            if(isTooBig())
               reduceArray();
            result = true;

        } else
            result = false;

        return result;
    }

    public void clear() {

        checkInitialization();

        for(int i = numberOfEntries; i > 0; i--){
            remove();
        }

    }

    public int getFrequencyOf(Object anEntry) {
        frequency = 0;
        for(int i = 0; i < numberOfEntries; i++){
            if(bag[i].equals(anEntry))
                frequency++;
        }
        return frequency;
    }

    public boolean contains(Object anEntry) {

        return getFrequencyOf(anEntry) > 0;

    }

    public boolean isArrayFull() {

        return numberOfEntries == capacity;
    }

    private void checkInitialization(){                             //Just a practice, it adds security to the code.
        if(!initialized)
            throw new SecurityException ("Class was not initialized");
    }

    private boolean isTooBig(){

        if(numberOfEntries < capacity / 2 && capacity > 20)
            result = true;
        else
            result = false;

        return result;
    }

    private void reduceArray(){

        checkInitialization();

        capacity = capacity * 3 / 4;

        @SuppressWarnings("unchecked")                          //Tells the compiler the casting is safe.
        T[] tempBag = (T[])new Object[capacity];                //Creates a temporary bag with three-forth of the
                                                                //capacity of the original bag, and copies the objects
        for(int i = 0; i < numberOfEntries; i++)                //from the original bag.
            tempBag[i] = bag[i];

        bag = tempBag;
        tempBag = null;                                     //Discards the tempBag;

    }

    private void expandArray(){

        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[capacity * 5 / 4];            //expands the array for a quarter
        for(int i = 0; i < numberOfEntries; i++)
            tempBag[i] = bag[i];
        bag = tempBag;
        capacity = capacity * 5 / 4;

        System.out.println("The bag has been expanded.");

    }

    public int getCapacity(){                          //For testing purpose.
        return capacity;
    }           //For testing purpose.

    public T[] toArray() {              //Needs fix

        checkInitialization();

        T[] tempBag = (T[]) new Object[capacity];
        for(int i = 0; i < numberOfEntries; i++)
            tempBag[i] = bag[i];

        return tempBag;
    }
}



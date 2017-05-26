import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: 3. Linked bag
 * Date: 9/7/2016
 */
public class LinkedBag<T> implements BagInterface<T> {

    private Node firstNode;
    private Node currentNode;
    private int numberOfEntries;
    private boolean result;
    private T anEntry;
    private final int MAX_SIZE = 50;

    public LinkedBag(){

        firstNode = null;
        numberOfEntries = 0;

    }

    public int getCurrentSize() {
        return numberOfEntries;
    }

    public boolean isEmpty() {

        if(numberOfEntries==0)
            result = true;
        else
            result = false;

        return result;
    }

    public boolean add(T newEntry){

        //The new node will become the first node, and it will refer to the original first node, and the original
        //first node get the data from the new node.
        if(numberOfEntries < MAX_SIZE) {
            Node newNode = new Node(newEntry);

            newNode.next = firstNode;
            firstNode = newNode;

            numberOfEntries++;

            System.out.println("Entry '" + newEntry + "' was added to the bag: " + getCurrentSize());

            result = true;
        }
        else
            result = false;

        return result;
    }

    public T remove() {
        anEntry = null;
        if(firstNode != null){
            anEntry = firstNode.data;
            firstNode = firstNode.next;                           //Last in first out
            numberOfEntries--;
        }
        return anEntry;
    }

    public boolean remove(T anEntry) {
        Node nodeN = getReferenceTo(anEntry);
        if(nodeN != null){
            nodeN.data = firstNode.data;                        //Finds where the node is located, replaces the node
                                                                //with the first node, and removes the first node
            remove();
            result = true;
        }
        return result;
    }

    public void clear() {
        while(!isEmpty())
            remove();
    }

    public int getFrequencyOf(T anEntry) {
        int i = 0;
        int frequency = 0;
        currentNode = firstNode;
        while(i < numberOfEntries && currentNode != null){      //currentNode != null can make sure there is no mistake
            if(currentNode.data.equals(anEntry)){               //in my code
                frequency++;
            }
            i++;
            currentNode = currentNode.next;
        }
        return frequency;
    }

    public boolean contains(T anEntry) {
        return getFrequencyOf(anEntry) > 0;
    }

    public T[] toArray() {

        int i = 0;

        @SuppressWarnings("unchecked")
        T[] displayArray = (T[])new Object[numberOfEntries];

        Node currentNode = firstNode;

        while(currentNode != null && i < numberOfEntries){
            displayArray[i] = currentNode.data;
            i++;
            currentNode = currentNode.next;
        }
        return displayArray;
    }

    public T removeFirst(){
        T result = null;
        if(numberOfEntries == 1) {
            clear();
        }
        else if (numberOfEntries > 1) {
            result = firstNode.data;
            firstNode = firstNode.next;
        }
        if(result != null)
            numberOfEntries--;
        return result;
    }

    public T removeLast(){
        Node current = firstNode;
        T result = null;
        for(int i = 0; i < numberOfEntries - 2; i++) {
            current = current.next;
        }
        result = current.next.data;
        current.next = null;
        if(result != null)
            numberOfEntries--;
        return result;
    }

    public void reverse(){
        Queue

    }

    public String toString(){
        Node current = firstNode;
        String result = "";
        while(current != null){
            result += "(" + current.data + ")";
            current = current.next;
            if(current != null)
                result += "->";
        }
        return result;
    }

    public boolean isArrayFull() {
        return numberOfEntries == MAX_SIZE;
    }

    public int getCapacity() {
        return MAX_SIZE;
    }

    public Node getReferenceTo(T anEntry){
        boolean found = false;
        Node currentNode = firstNode;

        while(!found && currentNode != null){
            if(anEntry.equals(currentNode.data))
                found = true;
            else
                currentNode = currentNode.next;
        }

        return currentNode;
    }

    public boolean hasDuplicateEntries() {              //Makes an array with all the entries of the link list,
                                                        //tests the entry to see if they have two or more duplicates
        int i = 0;
        result = false;
        T[] tempArray = toArray();

        while(i < numberOfEntries && tempArray[i] != null && result == false){
            if(getFrequencyOf(tempArray[i]) >= 2){
                result = true;
            }
            else
                i++;
        }

        return result;
    }

    public boolean hasUniqueItem(){
        int i = 0;
        result = false;
        T[] tempArray = toArray();
        while(i < numberOfEntries && tempArray[i] != null && result == false){
            if(getFrequencyOf(tempArray[i]) == 1){
                result = true;
            }
            else
                i++;
        }
        return result;

    }

   public boolean equals(BagInterface<T> otherBag){

       T[] tempArray = toArray();
       int i = 0;
       result = true;

       if(otherBag.getCurrentSize() == this.getCurrentSize()){
           while(i < numberOfEntries && result){
               if(this.getFrequencyOf(tempArray[i]) == otherBag.getFrequencyOf(tempArray[i]))
                   i++;
               else
                   result = false;
           }
       }
       else
           result = false;

       return result;
    }

    public boolean equals(LinkedBag<T> otherBag){

       Node thisCurrent = this.firstNode;
       Node otherCurrent = otherBag.firstNode;
       Boolean result = true;

       while(thisCurrent != null && result){
           if(otherCurrent == null){
               result = false;
           }
           else if(!thisCurrent.data.equals(otherCurrent.data)){
               result = false;
           }
           thisCurrent = thisCurrent.next;
           otherCurrent = otherCurrent.next;
       }
        return result;
    }

    private class Node{

        private T data;                                 //A node has both its data and the reference to the next link.
        private Node next;

        public Node(T data){                            //When the client initializes a Node without reference, it will
            this(data, null);                           //point to null, which equals to the last node
        }

        public Node(T data, Node next){
            this.data = data;
            this.next = next;
        }
    }
}

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by schatz on 10/21/15.
 */
/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Derived Linked List with Iterator
 * Date: 10/19/2016
 */
public class LinkedChainList<T> extends LinkedChainBase<T> implements ListInterface<T> {

    public LinkedChainList() {
        super();
    }

    // Here is an example of how LinkedChainList's methods can be implemented
    // using methods inherited from LinkedChainBase
    public T remove(int givenPosition) {
        // Make sure we have a good index
        if (givenPosition < 1 || givenPosition > getLength()) {
            throw new IndexOutOfBoundsException("index: " + givenPosition);
        }

        if (givenPosition == 1) {
            return removeFirstNode();
        } else {
            Node nodeBefore = traverseToNodeAt(givenPosition - 1);
            return removeAfterNode(nodeBefore);
        }
    }

    // Stub
    public void add(T newEntry) {
        add(getLength() + 1, newEntry);
    }

    public void removeValuesThatFollow(T target){
        Node currentNode = getFirstNode();
        while(currentNode != null){
            if(currentNode.getData().equals(target)){
                removeAfterNode(currentNode);
            }
            currentNode = currentNode.getNextNode();
        }
    }

    // Stub
    public void add(int newPosition, T newEntry) {

        if(newPosition < 1 || newPosition > getLength() + 1){
            throw new IndexOutOfBoundsException("index: " + newPosition);
        }

        Node newNode = new Node(newEntry);
        if(newPosition == 1){
            addFirstNode(newNode);
        } else {
            addAfterNode(traverseToNodeAt(newPosition - 1), newNode);
        }
    }

    // Stub
    public T replace(int givenPosition, T newEntry) {

        if(givenPosition < 1 || givenPosition > getLength()){
            throw new IndexOutOfBoundsException("index: " + givenPosition);
        }

        Node foundNode = traverseToNodeAt(givenPosition);
        T originalEntry = foundNode.getData();
        foundNode.setData(newEntry);
        return originalEntry;

    }

    // Stub
    public T getEntry(int givenPosition) {
        if(givenPosition < 1 || givenPosition > getLength()){
            throw new IndexOutOfBoundsException("index: " + givenPosition);
        }
        return traverseToNodeAt(givenPosition).getData();
    }

    // Stub
    public boolean contains(T anEntry) {
        boolean result = false;
        Node current = getFirstNode();
        while(current != null && !result){
            if(current.getData().equals(anEntry)){
                result = true;
            }
            current = current.getNextNode();
        }
        return result;
    }
    
    // Stub
    public Iterator<T> iterator() {
        return new IteratorForLinkedList();
    }

    private class IteratorForLinkedList implements Iterator<T> {

        private Node current;

        public IteratorForLinkedList(){
            current = getFirstNode();
        }

        public boolean hasNext() {
            return current.getNextNode() != null;
        }

        public T next() {
            if(hasNext()){
                T data = current.getData();
                current = current.getNextNode();
                return data;
            } else {
                throw new NoSuchElementException("Illegal call to next(); " +
                                                 "iterator is after end of list.");
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

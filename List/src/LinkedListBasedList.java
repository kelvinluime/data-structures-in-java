/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:  Two List Implementations
 * Date:    10/12/2016
 */
public class LinkedListBasedList<T extends Comparable<? super T>> implements ListInterface<T>{

    private int numberOfEntries;
    private Node firstNode;
    private Node lastNode;

    public LinkedListBasedList(){
        initializeDataFields();
    }

    public void add(T entry) {
        add(numberOfEntries + 1, entry);
    }

    public boolean remove(T entry) {
        boolean found = false;
        Node current = firstNode;
        int position = 1;
        while(current != null){
            if(current.data.equals(entry)) {
                found = true;
                remove(position);
            }
            current = current.next;
            position++;
        }
        return found;
    }

    public void addAll(T[] items){
        for(int i = 0; i < items.length; i++){
            add(items[i]);
        }
    }

    public void moveToEnd() {
        add(remove(1));
    }

    public void add(int position, T entry) {
        if(position >= 1 && position <= numberOfEntries + 1){
            Node newNode = new Node(entry);
            if(isEmpty()){
                firstNode = newNode;
                lastNode = newNode;
            }
            else if(position == numberOfEntries + 1){
                lastNode.next = newNode;
                lastNode = newNode;
            }
            else if(position == 1){
                newNode.next = firstNode;
                firstNode = newNode;
            }
            else{
                Node nodeBefore = getNodeAt(position - 1);
                Node next = nodeBefore.next;
                newNode.next = next;
                nodeBefore.next = newNode;
            }
            numberOfEntries++;
        }
        else
            throw new IndexOutOfBoundsException();
    }

    public int getPosition(T entry) {
        Node current = firstNode;
        int position = 1;
        boolean found = false;
        while(current != null && !found){
            if(entry.equals(current.data));
                found = true;
            current = current.next;
            position++;
        }
        return position;
    }

    public boolean equals(LinkedListBasedList<T> anotherList){
        assert anotherList != null;
        boolean isEqual = true;
        if(this.getLength() != anotherList.getLength())
            isEqual = false;
        else {
            Node current = firstNode;
            Node anotherCurrent = anotherList.firstNode;
            while(current != null && anotherCurrent != null && isEqual){
                if(!current.data.equals(anotherCurrent.data))
                    isEqual = false;
                current = current.next;
                anotherCurrent = anotherCurrent.next;
            }
        }
        return isEqual;
    }

    public ListInterface<T> getAllLessThan(Comparable<T> anObject){
        ListInterface<T> resultList = new LinkedListBasedList<>();
        Node current = firstNode;
        while(current != null){
            if(current.data.compareTo((T)anObject) < 0)
                resultList.add(current.data);
            current = current.next;
        }
        return resultList;
    }

    public T remove(int position) {
        T result = null;
        if(position >= 1 && position <= numberOfEntries){
            assert !isEmpty();
            if(numberOfEntries == 1) {
                result = firstNode.data;
                firstNode = null;
                lastNode = null;
            }
            else if(position == 1){
                firstNode = firstNode.next;
            }
            else if(position == numberOfEntries){
                lastNode = getNodeAt(numberOfEntries - 1);
            }
            else{
                Node nodeBefore = getNodeAt(position - 1);
                Node nodeAfter = getNodeAt(position + 1);
                nodeBefore.next = nodeAfter;
            }
            numberOfEntries--;
        }
        else
            throw new IndexOutOfBoundsException();

        return result;
    }

    public void clear() {
        initializeDataFields();
    }

    public boolean replace(int position, T entry) {
        Boolean success = false;
        if(position >= 1 && position <= numberOfEntries) {
            getNodeAt(position).data = entry;
            success = true;
        }
        return success;
    }

    public T getEntry(int position) {
        return getNodeAt(position).data;
    }

    public boolean contains(T entry) {
        Node current = firstNode;
        boolean result = false;
        while(current != null){
            if(current.data.equals(entry))
                result = true;
            current = current.next;
        }
        return result;
    }

    public int getLength() {
        return numberOfEntries;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Comparable[numberOfEntries + 1];
        Node current = firstNode;
        int i = 1;
        while(i <= numberOfEntries && current != null){
            result[i++] = current.data;
            current = current.next;
        }
        return result;
    }

    private void initializeDataFields(){
        numberOfEntries = 0;
        firstNode = null;
        lastNode = null;
    }

    private Node getNodeAt(int position){
        Node current = firstNode;
        int i = 1;
        if(position >= 1 && position <= numberOfEntries){
            while(i < position){
                i++;
                current = current.next;
            }
        }
        else
            throw new IndexOutOfBoundsException();
        return current;
    }

    private class Node {

        private Node next;
        private T data;

        public Node(T data){
            this(data, null);
        }

        public Node(T data, Node next){
            this.next = next;
            this.data = data;
        }
    }

}

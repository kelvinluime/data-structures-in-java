/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: 2. ResizableArrayBag
 * Date: 9/7/2016
 */
public class Tester {

    public static void main(String[] args){
    /**
        BagInterface<String> linkedBag = new LinkedBag<>();
        BagInterface<String> otherBag = new LinkedBag<>();

        testIsEmpty(linkedBag);
        space();
        testAdd(linkedBag);
        space();
        testAdd(otherBag);
        space();
        testIsArrayFull(linkedBag);
        space();
        testEquals(linkedBag, otherBag);
        space();
        testCurrentSize(linkedBag);
        displayBag(linkedBag);
        space();
        testGetFrequencyOf(linkedBag, "Apple");
        testContains(linkedBag, "Honda Fit");
        space();
        testRemove(linkedBag);
        testRemove(linkedBag);
        testRemove(linkedBag, "Apple");
        displayBag(linkedBag);
        space();
        testHasDuplicateEntries(linkedBag);
        System.out.println("Has Unique Item:" + linkedBag.hasUniqueItem());
        space();
        testClear(linkedBag);
        displayBag(linkedBag);
        System.out.println(linkedBag.hasUniqueItem());
    */

        LinkedBag<String> bag1 = new LinkedBag<>();
        LinkedBag<String> bag2 = new LinkedBag<>();
        bag1.add("A");
        bag1.add("B");
        bag1.add("C");
        bag1.add("D");
        bag2.add("A");
        bag2.add("B");
        bag2.add("C");
        bag2.add("D");
        testLocalEquals(bag1, bag2);
        System.out.println(bag1);
        bag1.removeLast();
        System.out.println(bag1);
        bag1.reverse();
        System.out.println(bag1);

    }

    private static void testLocalEquals(LinkedBag<String> bag1, LinkedBag<String> bag2){
        System.out.println("Are bag1 and bag2 equal: " + bag1.equals(bag2));
    }

    private static void testAdd(BagInterface<String> aBag){

        aBag.add("Apple");
        aBag.add("Orange");
        aBag.add("Pear");
        aBag.add("Carrot");
        aBag.add("Pineapple");
        aBag.add("Watermelon");
        aBag.add("Strawberry");
        aBag.add("Apple");
        aBag.add("Avocado");
        aBag.add("Grapes");
        aBag.add("Orange");
        aBag.add("Orange");
        aBag.add("Apple");
        aBag.add("Apple");
        aBag.add("Potato");
        aBag.add("Apple");
        aBag.add("Apple");
        aBag.add("Apple");
        aBag.add("Potato");
        aBag.add("Cheery");
        aBag.add("Blueberry");

    }

    private static void testCurrentSize(BagInterface<String> aBag){
        System.out.println("Number of entries in the bag: " + aBag.getCurrentSize());
    }

    private static void displayBag(BagInterface<String> aBag){
        Object[] tempBag = aBag.toArray();
        System.out.print("Entries in the bag(Maximum Capacity = " + aBag.getCapacity() + "): '");
        for(int i = 0; i < aBag.getCurrentSize(); i++)
            System.out.print(tempBag[i] + "' ");
        System.out.println("");
    }

    private static void testGetFrequencyOf(BagInterface<String> aBag, String anEntry){
        System.out.println("Frequency of '" + anEntry + "': " + aBag.getFrequencyOf(anEntry));
    }

    private static void space(){
        System.out.println("");
    }

    private static void testIsArrayFull(BagInterface<String> aBag){
        System.out.println("Is array full: " + aBag.isArrayFull());
    }

    private static void testContains(BagInterface<String> aBag, String anEntry){
        System.out.println("Bag contains '" + anEntry + "': " + aBag.contains(anEntry));
    }

    private static void testRemove(BagInterface<String> aBag){
        System.out.println("The first entry has been removed: " + aBag.remove());
    }

    private static void testRemove(BagInterface<String> aBag, String anEntry){
        System.out.println("Remove '" + anEntry + "': " + aBag.remove(anEntry));
    }

    private static void testClear(BagInterface<String> aBag){
        aBag.clear();
        System.out.println("The bag has been cleared.");
    }

    private static void testIsEmpty(BagInterface<String> aBag){
        System.out.print("Is the bag empty: " + aBag.isEmpty() + "\n");
    }

    private static void testHasDuplicateEntries(BagInterface<String> aBag){
        System.out.println("The bag has duplicate entries: " + aBag.hasDuplicateEntries());
    }

    private static void testEquals(BagInterface<String> aBag, BagInterface<String> otherBag){
        System.out.println("The bag equals to the bag 'otherBag': " + aBag.equals(otherBag));
    }
}

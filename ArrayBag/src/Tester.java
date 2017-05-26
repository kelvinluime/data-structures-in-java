/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: 2. ResizableArrayBag
 * Date: 8/31/2016
 */
public class Tester {

    public static void main(String[] args){

        BagInterface<String> bag = new ResizableArrayBag<>(40);

        testAdd(bag);
        testCurrentSize(bag);
        displayBag(bag);
        space();
        testGetFrequencyOf(bag, "Apple");
        testGetFrequencyOf(bag, "Potato");
        testGetFrequencyOf(bag, "Strawberry");
        space();
        testIsArrayFull(bag);
        testContains(bag, "Fish");
        testContains(bag, "Apple");
        space();
        testRemove(bag);
        testCurrentSize(bag);
        displayBag(bag);
        space();
        testRemove(bag, "Apple");
        testGetFrequencyOf(bag, "Apple");
        testRemove(bag, "Potato");
        testGetFrequencyOf(bag, "Potato");
        displayBag(bag);
        space();
        testClear(bag);
        displayBag(bag);
        testCurrentSize(bag);
        testIsEmpty(bag);
        space();
        testAdd(bag);
        testAdd(bag);
        displayBag(bag);

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
        System.out.print("Entries in the bag(Capacity = " + aBag.getCapacity() + "): ");
        for(int i = 0; i < aBag.getCurrentSize(); i++)
            System.out.print(tempBag[i] + " ");
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
        System.out.println("The last entry has been removed: " + aBag.remove());
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
}

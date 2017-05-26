/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:
 * Date:
 */
import java.util.*;
public class Tester{
    public static void main(String[] args) {

        DictionaryInterface<String, Integer> d = new Dictionary<>();

        testAdd(d, "Apple", 1);
        testAdd(d, "Orange", 2);
        testAdd(d, "Pear", 3);
        testAdd(d, "Banana", 4);
        testNumberOfEntries(d);
        display(d);

        SearchTreeInterface<Integer> bst = new BinarySearchTree<>();
        bst.add(10);
        bst.add(3);
        bst.add(7);
        bst.add(5);
        bst.add(6);

        Iterator<Integer> iterator = bst.getLeftIterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());

    }

    private static void testNumberOfEntries(DictionaryInterface<String, Integer> d){
        System.out.println("Numbers in dictionary: " + d.getSize());
    }

    private static void testAdd(DictionaryInterface<String, Integer> d, String key, int value){
        System.out.println("Dictionary add: (" + key + ", " + value + ")");
        d.add(key, value);
    }

    private static void display(DictionaryInterface<String, Integer> d){
        Iterator<String> keyIterator = d.getKeyIterator();
        Iterator<Integer> valueIterator = d.getValueIterator();
        System.out.println("Entries in the dictionary: ");
        while(keyIterator.hasNext() && valueIterator.hasNext())
            System.out.println("(" + keyIterator.next() + ", " + valueIterator.next() + ")");
    }

}

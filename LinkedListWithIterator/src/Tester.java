import java.util.Iterator;
/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Two List Implementations
 * Date: 10/12/2016
 */
public class Tester{

    public static void main(String[] args) {
        LinkedChainList<String> list = new LinkedChainList<>();
        Iterator<String> iterator = list.iterator();

        testAdd(list, "A");
        testAdd(list, "B");
        testAdd(list, "C");
        testAdd(list, "D");
        testAdd(list, 3, "A");
        testAdd(list, 2, "Y");
        testAdd(list, 6, "Z");
        displayList(list);
        list.removeValuesThatFollow("A");
        displayList(list);

    }

    static void testAdd(ListInterface<String> list, int position, String entry){
        list.add(position, entry);
        System.out.println("\"" + entry + "\" has been added into the list: " + position);
    }

    static void testAdd(ListInterface<String> list, String entry){
        list.add(entry);
        System.out.println("\"" + entry + "\" has been added into the list: 1");
    }

    static void testRemove(ListInterface<String> list, int position){
        System.out.println("Entry in position '" + position + "' has been removed: \"" + list.remove(position) + "\"");
    }

    static void testReplace(ListInterface<String> list, int position, String entry){
        list.replace(position, entry);
        System.out.println("Entry in positon '" + position + "' has been replaced by \"" + entry + "\"");
    }

    static void testContains(ListInterface<String> list, String entry){
        System.out.println("List contains entry \"" + entry + "\": " + list.contains(entry));
    }

    static void displayList(ListInterface<String> list){
        Object[] listArray = list.toArray();
        System.out.print("List(" + list.getLength() + "): ");
        for(Object element: listArray){
            System.out.print("\"" + element + "\" ");
        }
        System.out.println();
    }

    static void testIterator(Iterator<String> iterator){
        System.out.println("Iterator has next: " + iterator.hasNext());
        System.out.println("Iterator next: " + iterator.next());
        System.out.println("Iterator next: " + iterator.next());
        System.out.println("Iterator next: " + iterator.next());
    }
}

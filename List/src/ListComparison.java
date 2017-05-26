import java.util.Date;
import java.util.Random;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Two List Implementations
 * Date: 10/12/2016
 */
public class ListComparison{

    private static long elapsedTime;
    private static TypeOfImplementation type = null;

    public static void main(String[] args){

        ListInterface<String> list = new LinkedListBasedList<>();
        list.add("Bee");
        list.add("Lion");
        list.add("Bear");
        list.add("Fish");

        ArrayBasedList<String> arrayList = new ArrayBasedList<>();
        arrayList.add("Bee");
        arrayList.add("Lion");
        arrayList.add("Bear");
        arrayList.add("Fish");

        System.out.println(arrayList.equals(list));

        ListInterface<String> list2 = new LinkedListBasedList<>();
        String[] array = {"a", "c", "d"};
        list2.addAll(array);
        ListInterface<String> list3 = list2.getAllLessThan("d");
        display(list2);
        display(list3);

    }

    private static void test(){

        switch(type){
            case ARRAY:
                ListInterface<String> arrayList1 = new ArrayBasedList<>();
                ListInterface<String> arrayList2 = new ArrayBasedList<>();
                for(int i = 0; i < 10000; i++) //1000000 is too much for my computer
                    arrayList1.add("");
                for(int i = 0; i < 10000; i++)
                    arrayList2.add(generateRandomPositionToAdd(arrayList2.getLength()), "");
                for(int i = 0; i < 10000; i++)
                    arrayList1.remove(generateRandomPositionToRemoveOrReplace(arrayList1.getLength()));
                for(int i = 0; i < 10000; i++)
                    arrayList2.replace(generateRandomPositionToRemoveOrReplace(arrayList2.getLength()), "");
                break;
            case LINKED_LIST:
                ListInterface<String> linkedList1 = new LinkedListBasedList<>();
                ListInterface<String> linkedList2 = new LinkedListBasedList<>();
                for(int i = 0; i < 10000; i++)
                    linkedList1.add("");
                for(int i = 0; i < 10000; i++)
                    linkedList2.add(generateRandomPositionToAdd(linkedList2.getLength()), "");
                for(int i = 0; i < 10000; i++)
                    linkedList1.remove(generateRandomPositionToRemoveOrReplace(linkedList1.getLength()));
                for(int i = 0; i < 10000; i++)
                    linkedList2.replace(generateRandomPositionToRemoveOrReplace(linkedList2.getLength()), "");
                break;
        }                 
    }

    private static int generateRandomPositionToAdd(int max){
        return (int)(Math.random() * max) + 1;
    }

    private static int generateRandomPositionToRemoveOrReplace(int max){
        return (int)(Math.random() * (max - 1) + 1);
    }

    private static void display(ListInterface<String> list) {
        Object[] array = list.toArray();
        System.out.println("Entries in the list: ");
        for (int i = 1; i < array.length; i++) {
            System.out.print(i + "." + array[i] + " ");
        }
        System.out.println();
    }

    private static void startTimer(){
        Date start = new Date();
        elapsedTime = -start.getTime();
    }

    private static void stopTimer(){
        Date end = new Date();
        elapsedTime += end.getTime();
    }

    private static void displayTimerTime(){
        System.out.println("Total running time of " + type + ": " + elapsedTime + "ms");
    }

    private static void resetTimer(){
        elapsedTime = 0;
        System.out.println();
    }

    private enum TypeOfImplementation {
        ARRAY, LINKED_LIST;

        public String toString(){
            String result = "";
            switch(this){
                case ARRAY:
                    result = "Array Based List";
                    break;
                case LINKED_LIST:
                    result = "Linked List Based List";
                    break;
            }
            return result;
        }

    }

}

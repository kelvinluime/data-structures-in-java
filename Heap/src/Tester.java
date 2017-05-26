/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: MaxHeap
 * Date: 11/10/2016
 */
public class Tester {

    public static void main(String[] args){
        int[] array = new int[25];
        for(int i = 0; i < 25; i++){
            int num = (int)(Math.random() * 99) + 1;
            array[i] = num;
            System.out.println(num);

        }
        System.out.println("The 24th element in the array: " + kthElement(array, 24));

    }

    public static int kthElement(int[] a, int k){
        MaxHeapInterface<Integer> heap = new MaxHeap<>();
        for(int i = 0; i < a.length; i++)
            heap.add(a[i]);
        for(int i = 0; i < a.length - k; i++){
            heap.removeMax();
        }

        return heap.getMax();
    }

}

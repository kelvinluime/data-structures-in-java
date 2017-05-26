/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: 4. Sort Implementations
 * Date: 21 September 2016
 */
public class Sorts{
    public static int count;
    public static void main(String[] args){

        int length = 100;
        for(int i = 0; i < 3; i++) {
            Integer[] randomArray = generateRandomArrayWithLength(length);
            Integer[] selectionArray = copyOfArray(randomArray);
            Integer[] shellArray = copyOfArray(randomArray);
            Integer[] insertionArray = copyOfArray(randomArray);
            Integer[] mergeArray = copyOfArray(randomArray);
            int numberOfComparisonInSelectionSort = numberOfComparisonInSelectionSort(selectionArray);
            int numberOfComparisonInInsertionSort = numberOfComparisonInInsertionSort(insertionArray);
            int numberOfComparisonInShellSort = numberOfComparisonInShellSort(shellArray);
            int numberOfComparisonInMergeSort = numberOfComparisonInMergeSort(mergeArray, 0, mergeArray.length - 1);
            displayLength(length);
            displayArray(randomArray);
            displayNumberOfComparisonInSelectionSort(numberOfComparisonInSelectionSort);
            displayNumberOfComparisonInInsertionSort(numberOfComparisonInInsertionSort);
            displayNumberOfComparisonInShellSort(numberOfComparisonInShellSort);
            displayNumberOfComparisonInMergeSort(numberOfComparisonInShellSort);
            displayRatio(numberOfComparisonInSelectionSort, numberOfComparisonInInsertionSort, numberOfComparisonInShellSort,
                            numberOfComparisonInMergeSort);
            System.out.println("Sorted List: ");
            displayArray(selectionArray);
            displayArray(shellArray);
            displayArray(insertionArray);
            displayArray(mergeArray);
            System.out.println("");
            length *= 10;
        }
    }

    private static <T extends Comparable<? super T>> int numberOfComparisonInMergeSort(T[] anArray, int first, int last){
        count = 0;
        @SuppressWarnings("unchecked")
        T[] temp = (T[])new Comparable[anArray.length];
        if(first < last){
            int mid = last / 2;
            int beginLeft = first;
            int endLeft = mid;
            int beginRight = mid + 1;
            int endRight = last;
            int index = 0;
            for(int i = 0; i < temp.length; i++){
                temp[i] = anArray[i];
            }
            numberOfComparisonInMergeSort(anArray, first, mid);
            numberOfComparisonInMergeSort(anArray, mid + 1, last);
            while(beginLeft <= endLeft && beginRight <= endRight){
                if(anArray[beginLeft].compareTo(anArray[beginRight]) < 0){
                    anArray[index] = temp[beginLeft];
                    beginLeft++;
                }
                else{
                    anArray[index] = temp[beginRight];
                    beginRight++;
                }
                index++;
                count++;
            }
            while(beginLeft <= mid || beginRight <= last){
                if(beginLeft <= mid) {
                    anArray[index] = temp[beginLeft];
                    beginLeft++;
                }
                else{
                    anArray[index] = temp[beginRight];
                    beginRight++;
                }
                index ++;
            }

        }
        return count;
    }

    public static <T extends Comparable<? super T>> int numberOfComparisonInInsertionSort(T[] anArray) {
        T key;                                          //i is the end of the sorted list,
        int i;                                          //j is the beginning of the unsorted list
        count = 0;
        for(int j = 1; j < anArray.length; j++){
            key = anArray[j];
            i = j - 1;
            while(i > -1 && anArray[i].compareTo(key) > 0){
                anArray[i + 1] = anArray[i];            //Creates space for insertion
                i--;
                count++;
            }
            if(i > -1) {
                count++;
            }
            anArray[i + 1] = key;
        }

        return count;
    }

    public static <T extends Comparable<? super T>> int numberOfComparisonInShellSort(T[] anArray){
        int i;
        T key;
        int h = anArray.length / 2;
        if(h % 2 > 0){
            h++;
        }
        count = 0;
        while(h > 0){
            for(int j = h; j < anArray.length; j++){
                key = anArray[j];
                i = j - h;
                while(i > -1 && key.compareTo(anArray[i]) < 0){
                    anArray[i + h] = anArray[i];
                    i -= h;
                    count++;
                }
                if(i > -1) {
                    count++;
                }
                anArray[i + h] = key;
            }
            h /= 2;
        }

        return count;
    }

    public static Integer[] generateRandomArrayWithLength(int length){
        Integer[] anArray = new Integer[length];
        for(int i = 0; i < length; i++){
            anArray[i] = (int)(Math.random() * 100);
        }
        return anArray;
    }

    public static <T extends Comparable <? super T>> int numberOfComparisonInSelectionSort(T[] anArray){
        count = 0;
        for(int i = 0; i < anArray.length; i++){
            for(int j = i + 1; j < anArray.length; j++){
                if(anArray[j].compareTo(anArray[i]) < 0){
                    swap(anArray, i, j);
                }
                count++;
            }
        }
        return count;
    }

    public static void swap(Object[] anArray, int i, int j){
        Object temp = anArray[i];
        anArray[i] = anArray[j];
        anArray[j] = temp;
    }

    public static void displayArray(Integer[] anArray){
        System.out.print("List: ");
        for(Integer array : anArray){
            System.out.print(array + " ");
        }
        System.out.println("");
    }

    public static void displayNumberOfComparisonInSelectionSort(int num){
        System.out.println("The number of comparison in selection sort is: " + num);
    }

    public static void displayNumberOfComparisonInShellSort(int num){
        System.out.println("The number of comparison in shell sort is: " + num);
    }

    public static void displayNumberOfComparisonInInsertionSort(int num){
        System.out.println("The number of comparison in Insertion sort is: " + num);
    }

    public static Integer[] copyOfArray(Integer[] anArray){
        Integer[] result = new Integer[anArray.length];
        for(int i = 0; i < anArray.length; i++){
            result[i] = anArray[i];
        }
        return result;
    }

    public static void displayRatio(int num1, int num2, int num3, int num4){
        int key, min;
        int ratio1, ratio2, ratio3, ratio4;
        Integer[] array = {num1, num2, num3, num4};
        for(int j = 1; j < 4; j++){
            key = array[j];
            int i = j - 1;
            while(i > -1 && key < array[i]){
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
        }
        min = array[0];
        ratio1 = num1 / min;
        ratio2 = num2 / min;
        ratio3 = num3 / min;
        ratio4 = num4 / min;
        System.out.println("Ratio: " + ratio1 + " : " + ratio2 + " : " + ratio3 + " : " + ratio4);
    }

    public static void displayLength(int num){
        System.out.println("The length of the list is: " + num);
    }

    public static void displayNumberOfComparisonInMergeSort(int num){
        System.out.println("The number of comparison in Insertion sort is: " + num);
    }

}

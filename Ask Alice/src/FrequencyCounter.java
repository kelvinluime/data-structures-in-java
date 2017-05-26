import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Ask Alice
 * Date: 10/26/2016
 */
public class FrequencyCounter {

    private DictionaryInterface<String, Integer> wordTable;

    public FrequencyCounter(){
        wordTable = new HashedDictionary<>();
    }

    public void readFile(Scanner data){
        data.useDelimiter("\\W+");
        while(data.hasNext()) {
            String nextWord = data.next();
            nextWord = nextWord.toLowerCase();
            Integer frequency = wordTable.getValue(nextWord);
            if(frequency == null){
                wordTable.add(nextWord, new Integer(1));
            }
            else {
                frequency++;
                wordTable.add(nextWord, frequency);
            }
        }
        data.close();
    }

    public void display(){
        int maxFrequency = 0;
        int nextValue;
        String mostOftenWord = null;
        String nextKey;
        Iterator<String> keyIterator = wordTable.getKeyIterator();
        Iterator<Integer> valueIterator = wordTable.getValueIterator();
        while(keyIterator.hasNext()){
            nextValue = valueIterator.next();
            nextKey = keyIterator.next();
            if(nextValue > maxFrequency) {
                maxFrequency = nextValue;
                mostOftenWord = nextKey;
            }
            System.out.println(nextKey + " " + nextValue);
        }
        System.out.println("Word shows up most often: " + mostOftenWord + " " + maxFrequency);
    }

    public void displayFrequencyOf(String word){
        System.out.println("Frequency of \"" + word + "\": " + wordTable.getValue(word.toLowerCase()));
    }

}

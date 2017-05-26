import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Ask Alice
 * Date: 10/26/2016
 */
public class Driver {

    public static void main(String[] args){

        String filePath = "src/Alice.txt";
        FrequencyCounter wordCounter = new FrequencyCounter();

        try {
            System.out.println("Text path: " + new File(filePath).getAbsolutePath()); //for your convenience
            Scanner data = new Scanner(new File(filePath));
            wordCounter.readFile(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        wordCounter.display();  //display frequencies
        wordCounter.displayFrequencyOf("Alice");
        wordCounter.displayFrequencyOf("Rabbit");
        wordCounter.displayFrequencyOf("Cheshire");
        wordCounter.displayFrequencyOf("Mad");
        wordCounter.displayFrequencyOf("Hatter");

    }

}

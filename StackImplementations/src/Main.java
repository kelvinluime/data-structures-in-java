import java.util.EmptyStackException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: 4. Stack Implementations
 * Date: 9/14/2016
 */
public class Main {

    public static void main(String[] args){
/*
        String[] anArray = {"Pineapple", "Orange", "Apple", "Fish", "Strawberry", "Apple", "Pear", "Apple"};

        StackInterface<String> linkedStack = new LinkedStack<>();
        linkedStackIntro();
        testPush(linkedStack, "Apple");
        testPush(linkedStack, "Orange");
        testPush(linkedStack, "Banana");
        testPush(linkedStack, "Cherry");
        testPush(linkedStack, "Watermelon");
        testPush(linkedStack, "Avocado");
        testPush(linkedStack, "Honda Civic");
        testPush(linkedStack, "Honda Fit");
        testPush(linkedStack, "Honda Accord");
        displayStack(linkedStack);
        testPop(linkedStack);
        testPop(linkedStack);
        testPop(linkedStack);
        testPeek(linkedStack);
        testPeek2(linkedStack);
        testRemove(linkedStack, 2);
        testClear(linkedStack);
        testPushAll(linkedStack, anArray);
        testFilter(linkedStack, "Apple");
        testReverse(linkedStack);
        linkedStack.doubleStack();
        displayStack(linkedStack);
        space();
        space();
        space();
        space();
        StackInterface<String> arrayStack = new ArrayStack<>();
        arrayStackIntro();
        testPush(arrayStack, "Apple");
        testPush(arrayStack, "Orange");
        testPush(arrayStack, "Banana");
        testPush(arrayStack, "Cherry");
        testPush(arrayStack, "Watermelon");
        testPush(arrayStack, "Avocado");
        testPush(arrayStack, "Honda Civic");
        testPush(arrayStack, "Honda Fit");
        testPush(arrayStack, "Honda Accord");
        displayStack(arrayStack);
        testPop(arrayStack);
        testPop(arrayStack);
        testPop(arrayStack);
        testPeek(arrayStack);
        testPeek2(arrayStack);
        testRemove(arrayStack, 2);
        testClear(arrayStack);
        testPushAll(arrayStack, anArray);
        testFilter(arrayStack, "Apple");
        testReverse(arrayStack);

        StackInterface<Integer> test = new ArrayStack<>();
        Integer[] intArray = {5,1,2,3,8,3,4,6};
        test.pushAll(intArray);
        System.out.println(largestValue(test));
        */
        /*
        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.push("E");
        stack.push("F");
        stack.push("G");
        stack.filter("A");

        displayStack(stack);
        */
        System.out.println(balanced("[{}<>]"));

    }

    private static StackInterface<String> partitionStack(StackInterface<String> originalStack, String value){
        if(originalStack.isEmpty())
            throw new EmptyStackException();
        else {
            StackInterface<String> tempStack = new LinkedStack<>();
            StackInterface<String> resultStack = new LinkedStack<>();
            boolean found = false;

            while(!found && !originalStack.isEmpty()){
                String nextEntry = originalStack.pop();
                if(nextEntry.equals(value))
                    found = true;

                tempStack.push(nextEntry);
            }

            while(!tempStack.isEmpty())
                resultStack.push(tempStack.pop());
            return resultStack;
        }
    }

    private static void testPop(StackInterface<String> stack){
        System.out.println("'" + stack.pop() + "' was popped from the stack.");

    }

    private static void testPush(StackInterface<String> stack, String anEntry){

        stack.push(anEntry);
        System.out.println("'" + anEntry + "' was pushed onto the stack.");

    }

    private static void testPeek(StackInterface<String> stack){

        System.out.println("The top entry on the stack is: '" + stack.peek() + "'.");

    }

    private static void testPeek2(StackInterface<String> stack){

        System.out.println("The second entry from the top on the stack is: '" + stack.peek2() + "'.");

    }

    /*private static void testClear(StackInterface<String> stack){

        stack.clear();
        System.out.println("********************The stack has been cleared.********************");
        displayStack(stack);

    }
    */
    private static void displayStack(StackInterface<Character> stack){
        System.out.println("Entries in the stack (ranked from top to the bottom): " + stack);
    }
    /*
    private static void testRemove(StackInterface<String> stack, int n){
        stack.remove(n);
        System.out.println("The top " + n + " entries have been removed from the stack.");
        displayStack(stack);
    }
    */
    private static boolean isPalindrome(String word){
        boolean result = false;
        StackInterface<Character> wordStack = new LinkedStack<>();
        StackInterface<Character> temp = new LinkedStack<>();
        StackInterface<Character> reverse = new LinkedStack<>();
        for(int i = 0; i < word.length(); i++){
            wordStack.push(word.charAt(i));
        }
        while(!wordStack.isEmpty()){
            char c = wordStack.pop();
            reverse.push(c);
            temp.push(c);
        }
        while(!temp.isEmpty()){
            wordStack.push(temp.pop());
        }
        if(reverse.equals(wordStack)){
            return true;
        }
        return result;
    }
    public static boolean balanced(String s){
        boolean result = true;
        StackInterface<Character> charStack = new LinkedStack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(!Character.isLetterOrDigit(c)){
                switch(c){
                    case '{':
                        charStack.push('}');
                        break;
                    case '[':
                        charStack.push(']');
                        break;
                    case '(':
                        charStack.push(')');
                        break;
                    case '<':
                        charStack.push('>');
                        break;
                }
                if(c == '}' || c == '>' || c == ')' || c == ']'){
                    if(charStack.pop() != c){
                        result = false;
                    }
                }
            }
        }
        return result;
    }
    /*
    private static void testPushAll(StackInterface<String> stack, String[] anArray){
        String message = "An array containing ";
        int i = 0;
        for(String s: anArray){
            message += "'" + anArray[i++] + "' ";
        }
        message += "was pushed onto the stack.";
        System.out.println(message);
        stack.pushAll(anArray);
        displayStack(stack);
    }
    */
    private static void space(){
        System.out.println("");
    }

    private static void arrayStackIntro(){
        System.out.println("The following activities are based on \"array stack\" implementation...");
    }

    private static void linkedStackIntro(){
        System.out.println("The following activities are based on \"linked stack\" implementation...");
    }
    /*
    private static void testFilter(StackInterface<String> stack, String value){
        System.out.println("'" + value + "' is filtered out from the stack.");
        stack.filter(value);
        displayStack(stack);
    }
    */
    /*
    private static void testReverse(StackInterface<String> stack){
        System.out.println("The stack has been reversed.");
        stack.reverse();
        displayStack(stack);
    }
    */
    public static int largestValue(StackInterface<Integer> s){
        int max = s.peek();
        while(!s.isEmpty()){
            if(s.peek() > max){
                max = s.peek();
            }
            s.pop();
        }

        return max;
    }
}

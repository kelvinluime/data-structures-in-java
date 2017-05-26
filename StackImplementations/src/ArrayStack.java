import java.util.Arrays;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:  4. Stack Implementations
 * Date: 9/14/2016
 */

public class ArrayStack<T> implements StackInterface<T> {

    private T[] stack;
    private int topIndex;
    private static final int DEFAULT_INITIAL_CAPACITY = 5;

    public ArrayStack(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayStack(int initialCapacity){
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
    }

    public void doubleStack(){}

    public T peek2() {
        T top = null;
        if(topIndex != 0){
            top = stack[topIndex - 1];
        }
        else
            throw new StackOverflowError("The stack has less than two entries.");
        return top;
    }

    public void remove(int n) {
        for (int i = 0; i < n; i++) {
            pop();
        }
    }

    public void filter(T value) {
        for (int i = 0; i < topIndex + 1; i++) {
            if (stack[i] != null && stack[i].equals(value)) {
                for (int j = i; j < topIndex; j++) {
                    stack[j] = stack[j + 1];
                }
                stack[topIndex--] = null;
            }
        }
    }

    public void pushAll(T[] a) {
        for(int i = 0; i < a.length; i++){
            push(a[i]);
        }
    }

    public void push(T newEntry) {
        ensureCapacity();
        topIndex++;
        stack[topIndex] = newEntry;
    }

    public T peek() {
        T top = null;
        if(!isEmpty()){
            top = stack[topIndex];
        }
        else throw new StackOverflowError("");
        return top;
    }

    public T pop() {
        T top = peek();
        if(!isEmpty()){
            stack[topIndex] = null;
            topIndex--;
        }
        return top;
    }

    public boolean isEmpty() {
        return topIndex < 0;
    }

    public void clear() {
        for(int i = 0; i < topIndex; i++)
            stack[i++] = null;
        topIndex = -1;
    }

    public void reverse() {
        T temp;
        for(int i = 0; i < topIndex; i++){
            if(i < topIndex - i) {
                temp = stack[i];
                stack[i] = stack[topIndex - i];
                stack[topIndex - i] = temp;
            }
        }
    }

    public String toString(){
        String message = "";
        for(int i = topIndex; i >= 0; i--){
            if(stack[i] != null)
                message += "'" + stack[i] + "' ";
        }
        return message;
    }

    private void ensureCapacity(){
        if(topIndex == stack.length - 1)
            stack = Arrays.copyOf(stack, stack.length * 2);
    }
}

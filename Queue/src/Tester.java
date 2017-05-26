import java.util.Random;

/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment: Two Queues
 * Date: 10/5/2016
 */
public class Tester {
/*
    public static void main(String[] args){
        QueueInterface<String> linkedQ = new LinkedQueue<>();

        testIsEmpty(linkedQ);
        testEnqueue(linkedQ, "Peter");
        testEnqueue(linkedQ, "Ben");
        testEnqueue(linkedQ, "Henry");
        testEnqueue(linkedQ, "Kelvin");
        testEnqueue(linkedQ, "Florence");
        testEnqueue(linkedQ, "Mary");
        testEnqueue(linkedQ, "May");
        testSplice(linkedQ);
        displayQueue(linkedQ);
        testSplice2LinkedList();
        displayQueue(linkedQ);

        QueueInterface<String> arrayQ = new ArrayQueue<>();

        testIsEmpty(arrayQ);
        testEnqueue(arrayQ, "Peter");
        testEnqueue(arrayQ, "Ben");
        testEnqueue(arrayQ, "Henry");
        testEnqueue(arrayQ, "Kelvin");
        testEnqueue(arrayQ, "Florence");
        testEnqueue(arrayQ, "Mary");
        testEnqueue(arrayQ, "May");
        testSplice(arrayQ);
        displayQueue(arrayQ);
        testDequeue(arrayQ);
        testDequeue(arrayQ);
        testIsFull(arrayQ);
        testGetFront(arrayQ);
        testClear(arrayQ);
        testIsEmpty(arrayQ);
        displayQueue(arrayQ);
        testSplice2Array();


        DoublyLinkedQueue<String> doubleQ = new DoublyLinkedQueue<>();
        doubleQ.addToBack("Bear");
        doubleQ.addToBack("Lion");
        doubleQ.addToBack("Fish");
        doubleQ.addToBack("Bird");
        doubleQ.addToFront("Human");
        while(!doubleQ.isEmpty())
            System.out.println(doubleQ.removeFront());


        LinkedQueue<String> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");
        linkedQueue.enqueue("D");
        linkedQueue.enqueue("E");
        linkedQueue.enqueue("F");

        displayQueue(linkedQueue);

        linkedQueue.dequeueUpTo("D");

        displayQueue(linkedQueue);


    }

    public static void testClear(QueueInterface<String> q){
        System.out.println("The queue has been cleared.");
        q.clear();
    }

    public static void testSplice2LinkedList(){
        LinkedQueue<String> q = new LinkedQueue<>();
        LinkedQueue<String> anotherQ = new LinkedQueue();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        anotherQ.enqueue("d");
        anotherQ.enqueue("e");
        anotherQ.enqueue("f");
        displayQueue(q);
        q.splice2(anotherQ);
        displayQueue(q);
        q.enqueue("z");
        displayQueue(q);
        System.out.println("");
    }

    public static void testSplice2Array(){
        ArrayQueue<String> q = new ArrayQueue<>();
        ArrayQueue<String> anotherQ = new ArrayQueue();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        anotherQ.enqueue("d");
        anotherQ.enqueue("e");
        anotherQ.enqueue("f");
        displayQueue(q);
        q.splice2(anotherQ);
        displayQueue(q);
    }

    public static void testSplice(QueueInterface<String> q){
        System.out.println("Another queue has been spliced into the queue.");
        QueueInterface<String> anotherQ = new LinkedQueue<>();
        anotherQ.enqueue("Jesus");
        anotherQ.enqueue("Adam");
        anotherQ.enqueue("Eva");
        anotherQ.enqueue("Paul");
        q.splice(anotherQ);

    }

    public static void testEnqueue(QueueInterface<String> q, String entry){
        q.enqueue(entry);
        System.out.println("\'" + entry + "\' joined the queue.");

    }

    public static void testIsEmpty(QueueInterface<String> q){
        System.out.println("Is the queue empty: " + q.isEmpty());
    }

    public static void testDequeue(QueueInterface<String> q){
        System.out.println("\'" + q.dequeue() + "\' left the queue.");
    }

    public static void testGetFront(QueueInterface<String> q){
        System.out.println("The front of the line is: " + q.getFront());
    }

    public static void testIsFull(QueueInterface<String> q){
        System.out.println("Is the chain full: " + q.isFull());
    }

    public static void displayQueue(QueueInterface<String> q){
        QueueInterface<String> temp = new LinkedQueue<>();
        System.out.print("In the queue: ");
        while(!q.isEmpty()){
            if(q.getFront() != null)
                System.out.print("\'" + q.getFront() + "\' ");
            temp.enqueue(q.dequeue());
        }
        System.out.println("");
        while(!temp.isEmpty()){
            q.enqueue(temp.dequeue());
        }
    }

    public static void displayQueue(LinkedQueue<String> q){
        QueueInterface<String> temp = new LinkedQueue<>();
        System.out.print("In the queue: ");
        while(!q.isEmpty()){
            if(q.getFront() != null)
                System.out.print("\'" + q.getFront() + "\' ");
            temp.enqueue(q.dequeue());
        }
        System.out.println("");
        while(!temp.isEmpty()){
            q.enqueue(temp.dequeue());
        }
    }

    public static void sentToBack(QueueInterface<String> q, int num) {
        for (int i = 0; i < num; i++)
            q.enqueue(q.dequeue());
    }
*/


}

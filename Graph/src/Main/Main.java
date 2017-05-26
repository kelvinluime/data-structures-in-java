package Main;

import GraphPackage.*;
import ADTPackage.*;

import java.util.Iterator;
import java.util.Queue;

/**
 A driver that demonstrates the class UndirectedGraph
 and a weighted graph.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */
public class Main // Undirected, weighted
{
    private static UndirectedGraph<String> myGraph = new UndirectedGraph<String>();
    private static StackInterface<String> path = new LinkedStack<String>();
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String F = "F";
    private static final String G = "G";
    private static final String H = "H";
    private static final String I = "I";

    public static void main(String[] args)
    {

        DirectedGraph<String> graph = new DirectedGraph<>();

        //First, it tests a graph consisting of only edges between A, B, C and D (A and D are not directly connected)
        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);
        graph.addVertex(H);
        graph.addVertex(I);
        graph.addEdge(A, B, 5);
        graph.addEdge(B, E, 3);
        graph.addEdge(D, A, 10);
        graph.addEdge(A, C, 9);
        graph.addEdge(C, D, 6);
        graph.addEdge(E, C, 5);
        graph.addEdge(E, F, 7);
        graph.addEdge(F, H,8);
        graph.addEdge(H, G,11);
        graph.addEdge(G, C,15);
        graph.addEdge(D, I,1);


        //check longestPath
 //       QueueInterface<String> longestPath = graph.findLongestPath(A, I);
   //     while(!longestPath.isEmpty())
     //       System.out.println(longestPath.dequeue());

        testHeaviestEdge(graph);



        //this is for the graph code assignment
        /*
        //Part I starts here
        System.out.print("Part I: ");
        setGraphFig28_18a();
        testDFS(A);
        System.out.println("A B C F E H G D I Expected");
        System.out.println("-------------------------------------------------------");

        //Part II starts here
        System.out.println("Part II: \n");
        UndirectedGraph<String> bartStations = new UndirectedGraph<>();

        //Add vertices
        bartStations.addVertex("Pittsburg");
        bartStations.addVertex("MacArthur");
        bartStations.addVertex("19th St");
        bartStations.addVertex("12th St");
        bartStations.addVertex("Richmond");
        bartStations.addVertex("Embarcadero");
        bartStations.addVertex("24th St");
        bartStations.addVertex("Millbrae");
        bartStations.addVertex("Freemont");
        bartStations.addVertex("Dublin");
        bartStations.addVertex("Bay Fair");
        bartStations.addVertex("Coliseum");
        bartStations.addVertex("Oakland Airport");
        bartStations.addVertex("Balboa Park");

        //Add edges
        bartStations.addEdge("Pittsburg", "MacArthur");
        bartStations.addEdge("MacArthur", "19th St");
        bartStations.addEdge("19th St", "12th St");
        bartStations.addEdge("12th St", "Embracadero");
        bartStations.addEdge("Richmond", "MacArthur");
        bartStations.addEdge("Embarcadero", "12th St");
        bartStations.addEdge("Embarcadero", "24th St");
        bartStations.addEdge("Balboa Park", "24th St");
        bartStations.addEdge("Balboa Park", "Millbrae");
        bartStations.addEdge("Bay Fair", "Dublin");
        bartStations.addEdge("Bay Fair", "Coliseum");
        bartStations.addEdge("12th St", "Coliseum");
        bartStations.addEdge("Coliseum", "Oakland Airport");
        bartStations.addEdge("Bay Fair", "Freemont");

        //Create 3 empty stacks to hold the paths
        StackInterface<String> path1 = new LinkedStack<>();
        StackInterface<String> path2 = new LinkedStack<>();
        StackInterface<String> path3 = new LinkedStack<>();

        //push the paths onto the stacks
        bartStations.getShortestPath("Millbrae", "Pittsburg", path1);
        bartStations.getShortestPath("Richmond", "Dublin", path2);
        bartStations.getShortestPath("Freemont", "Oakland Airport", path3);

        System.out.println("Millbrae to Pittsburg: ");
        displayStack(path1);
        System.out.println("");
        System.out.println("Richmond to Dublin: ");
        displayStack(path2);
        System.out.println("");
        System.out.println("Freemont to Oakland Airport: ");
        displayStack(path3);
        */


    }

    public static void testHeaviestEdge(DirectedGraph<String> graph){
        ListInterface<String> vertexList = graph.heaviestEdge();
        while(!vertexList.isEmpty())
            System.out.println(vertexList.remove(1));
    }

    public static void displayList(ListInterface<String> list){
        for(int i = 1; i <= list.getLength(); i++)
            System.out.println(list.getEntry(i));
    }

    public static void checkAcyclic(DirectedGraph<String> graph){
        System.out.println("Is the graph acyclic: " + graph.isAcyclic());
    }

    public static void setGraphFig28_18a()
    {
        setVerticesFig28_18a();       // Graph cleared before setting vertices
        setEdgesFig28_18aUndirected();
    } // end setGraphFig28_18a

    public static void checkVertexAndEdgeCount(int numberOfVertices, int numberOfEdges)
    {
        System.out.println("\nNumber of vertices = " + myGraph.getNumberOfVertices() +
                " (should be " + numberOfVertices + ")");
        System.out.println("Number of edges = " + myGraph.getNumberOfEdges() +
                " (should be " + numberOfEdges + ")");
    } // end checkVertexAndEdgeCount

    public static void testEdgesFig28_18a()
    {
        // Check existing edges
        boolean ok = true;
        ok = checkEdge(A, B, ok);
        ok = checkEdge(A, D, ok);
        ok = checkEdge(A, E, ok);
        ok = checkEdge(B, E, ok);
        ok = checkEdge(C, B, ok);
        ok = checkEdge(D, G, ok);
        ok = checkEdge(E, F, ok);
        ok = checkEdge(E, H, ok);
        ok = checkEdge(F, C, ok);
        ok = checkEdge(F, H, ok);
        ok = checkEdge(G, H, ok);
        ok = checkEdge(H, I, ok);

        // Check edges in opposite direction
        ok = checkEdge(B, A, ok);
        ok = checkEdge(D, A, ok);
        ok = checkEdge(E, A, ok);
        ok = checkEdge(E, B, ok);
        ok = checkEdge(B, C, ok);
        ok = checkEdge(G, D, ok);
        ok = checkEdge(F, E, ok);
        ok = checkEdge(H, E, ok);
        ok = checkEdge(C, F, ok);
        ok = checkEdge(H, F, ok);
        ok = checkEdge(H, G, ok);
        ok = checkEdge(I, H, ok);

        // Check some non-existing edges
        ok = checkNoEdge(A, I, ok);
        ok = checkNoEdge(C, E, ok);
        ok = checkNoEdge(C, A, ok);

        if (ok)
            System.out.println("Edges are OK.");
    } // end testEdgesFig28_18a

    private static boolean checkEdge(String vertex1, String vertex2, boolean ok)
    {
        boolean check = ok;
        if (!myGraph.hasEdge(vertex1, vertex2))
        {
            System.out.println("hasEdge error " + vertex1 + " " + vertex2);
            check = false;
        } // end if

        return check;
    } // end checkEdge

    private static boolean checkNoEdge(String vertex1, String vertex2, boolean ok)
    {
        boolean check = ok;
        if (myGraph.hasEdge(vertex1, vertex2))
        {
            System.out.println("hasEdge error " + vertex1 + " " + vertex2);
            check = false;
        } // end if

        return check;
    } // end checkNoEdge

    public static void testBFS(String v)
    {
        System.out.println("\n\nBreadth-First Traversal beginning at vertex " + v + ": ");
        QueueInterface<String> bfs = myGraph.getBreadthFirstTraversal(v);

        displayQueue(bfs);
        System.out.println(" Actual");
    } // end testBFS

    public static void testDFS(String v)
    {
        System.out.println("\n\nDepth-First Traversal beginning at vertex " + v + ": ");
        QueueInterface<String> dfs = myGraph.getDepthFirstTraversal(v);

        displayQueue(dfs);
        System.out.println(" Actual");
    } // end testDFS

    public static void testCheapestPath()
    {
        showPath(A, B, "A B", 2);
        showPath(A, C, "A B C", 5);
        showPath(A, D, "A D", 5);
        showPath(A, E, "A B E", 3);
        showPath(A, F, "A B E F", 6);
        showPath(A, G, "A D G", 7);
        showPath(A, H, "A D G H", 8);
        showPath(A, I, "A B E F I", 7);
    } // end testCheapestPath

    private static void showPath(String vertex1, String vertex2, String expectedPath, int expectedCost)
    {
        System.out.print("\nThe cheapest path from " + vertex1 + " to " + vertex2 + " is ");
        double cost = myGraph.getCheapestPath(vertex1, vertex2, path);
        displayStack(path);
        System.out.println("and has a cost of " + cost + ".");
        System.out.println("Expected path:                   " + expectedPath + " " +
                "                  " + expectedCost);
    } // end showPath

    private static void setVerticesFig28_18a()
    {
        myGraph.clear();

        myGraph.addVertex(A);
        myGraph.addVertex(B);
        myGraph.addVertex(C);
        myGraph.addVertex(D);
        myGraph.addVertex(E);
        myGraph.addVertex(F);
        myGraph.addVertex(G);
        myGraph.addVertex(H);
        myGraph.addVertex(I);
    } // end setVerticesFig28_18a

    private static void setEdgesFig28_18aUndirected()
    {
        myGraph.addEdge(A, B, 2);
        myGraph.addEdge(A, D, 5);
        myGraph.addEdge(A, E, 4);

        myGraph.addEdge(B, C, 3);
        myGraph.addEdge(B, E, 1);

        myGraph.addEdge(C, F, 4);

        myGraph.addEdge(D, G, 2);

        myGraph.addEdge(E, F, 3);
        myGraph.addEdge(E, H, 6);

        myGraph.addEdge(F, H, 3);
        myGraph.addEdge(F, I, 1);

        myGraph.addEdge(G, H, 1);

        myGraph.addEdge(H, I, 1);
      
      /* Since additions are made to the end of each edge list,
       these lists appear as follows:
       A: B -> D -> E
       B: A -> C -> E
       C: B -> F
       D: A -> G
       E: A -> B -> F -> H
       F: C -> E -> H -> I
       G: D -> H
       H: E -> F -> G -> I
       I: F -> H
       We can predict the BFS and DFS traversals knowing this. */
    } // end setEdgesFig28_18aUndirected

    public static void testExample28_28()
    {
        UndirectedGraph<String> roadMap = new UndirectedGraph<String>();

        roadMap.addVertex("Provincetown");
        roadMap.addVertex("Truro");
        roadMap.addVertex("Orleans");
        roadMap.addVertex("Chatham");
        roadMap.addVertex("Barnstable");
        roadMap.addVertex("Hyannis");
        roadMap.addVertex("Sandwich");
        roadMap.addVertex("Falmouth");

        roadMap.addEdge("Provincetown", "Truro", 10);
        roadMap.addEdge("Truro", "Orleans", 17);
        roadMap.addEdge("Orleans", "Chatham", 9);
        roadMap.addEdge("Chatham", "Hyannis", 19);
        roadMap.addEdge("Hyannis", "Barnstable", 4);
        roadMap.addEdge("Barnstable", "Sandwich", 12);
        roadMap.addEdge("Barnstable", "Orleans", 19);
        roadMap.addEdge("Hyannis", "Falmouth", 20);

        roadMap.displayEdges();

        StackInterface<String> bestRoute = new LinkedStack<String>();
        double distance = roadMap.getCheapestPath("Truro", "Falmouth", bestRoute);
        System.out.println("\nThe shortest route from Truro to Falmouth is " +
                distance + " miles long and " +
                "passes through the following towns:");
        while (!bestRoute.isEmpty())
            System.out.println(bestRoute.pop());
        System.out.println();
    } // end testExample28_28

    public static void displayStack(StackInterface<String> s)
    {
        while (!s.isEmpty()) {
            System.out.print(s.pop() + "-->");
        }

        assert(s.isEmpty());
        System.out.println();
    } // end displayStack

    public static void displayQueue(QueueInterface<String> q)
    {
        while (!q.isEmpty())
            System.out.print(q.dequeue() + " ");
    } // end displayQueue
}  // end DriverUW

/*
 Testing a weighted graph like the one in Figure 28-18a, but without directions on its edges:
 
 Edges exist from the first vertex in each line to the other vertices in the line.
 (Edge weights are given; weights are zero for unweighted graphs):
 
 I F 1.0 H 1.0
 H E 6.0 F 3.0 G 1.0 I 1.0
 G D 2.0 H 1.0
 F C 4.0 E 3.0 H 3.0 I 1.0
 E A 4.0 B 1.0 F 3.0 H 6.0
 D A 5.0 G 2.0
 C B 3.0 F 4.0
 B A 2.0 C 3.0 E 1.0
 A B 2.0 D 5.0 E 4.0
 
 Number of vertices = 9 (should be 9)
 Number of edges = 13 (should be 13)
 Edges are OK.
 -------------------------------------------------------
 
 
 Breadth-First Traversal beginning at vertex A:
 A B D E C G F H I  Actual
 A B D E C G F H I  Expected
 -------------------------------------------------------
 
 
 Depth-First Traversal beginning at vertex A:
 A B C F E H G D I  Actual
 A B C F E H G D I Expected
 -------------------------------------------------------
 
 
 Finding the cheapest paths from vertex A:
 
 
 The cheapest path from A to B is A B and has a cost of 2.0.
 Expected path:                   A B                   2
 
 The cheapest path from A to C is A B C and has a cost of 5.0.
 Expected path:                   A B C                   5
 
 The cheapest path from A to D is A D and has a cost of 5.0.
 Expected path:                   A D                   5
 
 The cheapest path from A to E is A B E and has a cost of 3.0.
 Expected path:                   A B E                   3
 
 The cheapest path from A to F is A B E F and has a cost of 6.0.
 Expected path:                   A B E F                   6
 
 The cheapest path from A to G is A D G and has a cost of 7.0.
 Expected path:                   A D G                   7
 
 The cheapest path from A to H is A D G H and has a cost of 8.0.
 Expected path:                   A D G H                   8
 
 The cheapest path from A to I is A B E F I and has a cost of 7.0.
 Expected path:                   A B E F I                   7
 -------------------------------------------------------
 
 
 Testing Example 28.28 using the graph in Figure 28-3:
 
 
 Edges exist from the first vertex in each line to the other vertices in the line.
 (Edge weights are given; weights are zero for unweighted graphs):
 
 Falmouth Hyannis 20.0
 Sandwich Barnstable 12.0
 Hyannis Chatham 19.0 Barnstable 4.0 Falmouth 20.0
 Barnstable Hyannis 4.0 Sandwich 12.0 Orleans 19.0
 Chatham Orleans 9.0 Hyannis 19.0
 Orleans Truro 17.0 Chatham 9.0 Barnstable 19.0
 Truro Provincetown 10.0 Orleans 17.0
 Provincetown Truro 10.0
 
 The shortest route from Truro to Falmouth is 60.0 miles long and passes through the following towns:
 Truro
 Orleans
 Barnstable
 Hyannis
 Falmouth
 
 Done.
*/
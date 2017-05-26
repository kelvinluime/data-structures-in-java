package GraphPackage;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import ADTPackage.*; // Classes that implement various ADTs
/**
   A class that implements the ADT directed graph.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class DirectedGraph<T> implements GraphInterface<T>
{
	private DictionaryInterface<T, VertexInterface<T>> vertices;
	private int edgeCount;
	
	public DirectedGraph()
	{
		vertices = new LinkedDictionary<T, VertexInterface<T>>();
		edgeCount = 0;
	} // end default constructor

	public boolean addVertex(T vertexLabel)
	{
	  VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<T>(vertexLabel));
	  return addOutcome == null; // Was addition to dictionary successful?
	} // end addVertex

	public boolean addEdge(T begin, T end, double edgeWeight)
	{
	  boolean result = false;
	  
	  VertexInterface<T> beginVertex = vertices.getValue(begin);
	  VertexInterface<T> endVertex = vertices.getValue(end);
	  
	  if ( (beginVertex != null) && (endVertex != null) )
	    result = beginVertex.connect(endVertex, edgeWeight);
	    
	  if (result)
	    edgeCount++;
	    
	  return result;
	} // end addEdge

	public boolean addEdge(T begin, T end)
	{
	  return addEdge(begin, end, 0);
	} // end addEdge

	public boolean hasEdge(T begin, T end)
	{
	  boolean found = false;
	  
	  VertexInterface<T> beginVertex = vertices.getValue(begin);
	  VertexInterface<T> endVertex = vertices.getValue(end);
	  
	  if ( (beginVertex != null) && (endVertex != null) )
	  {
	     Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
	     while (!found && neighbors.hasNext())
	     {
	        VertexInterface<T> nextNeighbor = neighbors.next();
	        if (endVertex.equals(nextNeighbor))
	           found = true;
	     } // end while
	   } // end if
	  
	   return found;
	} // end hasEdge

	public boolean isEmpty()
	{
	  return vertices.isEmpty();
	} // end isEmpty

	public void clear()
	{
	  vertices.clear();
	  edgeCount = 0;
	}

	public int getNumberOfVertices()
	{
	  return vertices.getSize();
	} // end getNumberOfVertices

	public int getNumberOfEdges()
	{
	  return edgeCount;
	} // end getNumberOfEdges

	protected void resetVertices()
	{
	   Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
	   while (vertexIterator.hasNext())
	   {
	      VertexInterface<T> nextVertex = vertexIterator.next();
	      nextVertex.unvisit();
	      nextVertex.setCost(0);
	      nextVertex.setPredecessor(null);
	   } // end while
	} // end resetVertices

	public ListInterface<T> heaviestEdge(){
		assert getNumberOfEdges() >= 1;

		ListInterface<T> resultList = new LinkedListWithIterator<>();
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		double heaviestWeight = 0;

		while(vertexIterator.hasNext()){
			VertexInterface<T> frontVertex = vertexIterator.next();
			Iterator<Double> weightIterator = frontVertex.getWeightIterator();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			while(weightIterator.hasNext() && neighbors.hasNext()){
				double nextWeight = weightIterator.next();
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(nextWeight > heaviestWeight){
					heaviestWeight = nextWeight;
					resultList.clear();
					resultList.add(1, frontVertex.getLabel());
					resultList.add(2, nextNeighbor.getLabel());
				}
			}
		}
		return resultList;
	}

	public QueueInterface<T> getBreadthFirstTraversal(T origin)
	{
	   resetVertices();
	   QueueInterface<T> traversalOrder = new LinkedQueue<>();
	   QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
     
	   VertexInterface<T> originVertex = vertices.getValue(origin);
	   originVertex.visit();
	   traversalOrder.enqueue(origin);    // Enqueue vertex label
	   vertexQueue.enqueue(originVertex); // Enqueue vertex
	  
	   while (!vertexQueue.isEmpty())
	   {
	      VertexInterface<T> frontVertex = vertexQueue.dequeue();
	    
	      Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
	      while (neighbors.hasNext())
	      {
	         VertexInterface<T> nextNeighbor = neighbors.next();
	         if (!nextNeighbor.isVisited())
	         {
	            nextNeighbor.visit();
	            traversalOrder.enqueue(nextNeighbor.getLabel());
	            vertexQueue.enqueue(nextNeighbor);
	         } // end if
	      } // end while
	   } // end while
	  
	   return traversalOrder;
	} // end getBreadthFirstTraversal

   // Exercise 10, Chapter 29
	public QueueInterface<T> getDepthFirstTraversal(T origin) {		//Here is my method for part I
		resetVertices();
		QueueInterface<T> traversalOrder = new LinkedQueue<>();
		StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();

		VertexInterface<T> originVertex = vertices.getValue(origin);
		originVertex.visit();
		traversalOrder.enqueue(origin);
		vertexStack.push(originVertex);

		while(!vertexStack.isEmpty()) {
			VertexInterface<T> frontVertex = vertexStack.peek();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			if(neighbors.hasNext()){
				VertexInterface<T> nextNeighbor = neighbors.next();
				//This while loop skips the visited vertices until either an unvisited vertex is found or
				//there is no next neighbor
				while(nextNeighbor.isVisited() && neighbors.hasNext()){
					nextNeighbor = neighbors.next();
				}
				//if there is an unvisited neighbor, adds it into the queue and stack;
				//if there is none, pops the top vertex in the stack to go back
				if(!nextNeighbor.isVisited()){
					nextNeighbor.visit();
					traversalOrder.enqueue(nextNeighbor.getLabel());
					vertexStack.push(nextNeighbor);
				} else
					vertexStack.pop();


			}
		}
		return traversalOrder;
    }

	public int getShortestPath(T begin, T end, StackInterface<T> path)
	{
		resetVertices();
		boolean done = false;
		QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<VertexInterface<T>>();

		VertexInterface<T> originVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		originVertex.visit();
		// Assertion: resetVertices() has executed setCost(0)
		// and setPredecessor(null) for originVertex

		vertexQueue.enqueue(originVertex);

		while (!done && !vertexQueue.isEmpty())
		{
			VertexInterface<T> frontVertex = vertexQueue.dequeue();

			Iterator<VertexInterface<T>> neighbors =
					frontVertex.getNeighborIterator();
			while (!done && neighbors.hasNext())
			{
				VertexInterface<T> nextNeighbor = neighbors.next();

				if (!nextNeighbor.isVisited())
				{
					nextNeighbor.visit();
					nextNeighbor.setCost(1 + frontVertex.getCost());
					nextNeighbor.setPredecessor(frontVertex);
					vertexQueue.enqueue(nextNeighbor);
				} // end if

				if (nextNeighbor.equals(endVertex))
					done = true;
			} // end while
		} // end while

		// Traversal ends; construct shortest path
		int pathLength = (int)endVertex.getCost();
		path.push(endVertex.getLabel());

		VertexInterface<T> vertex = endVertex;
		while (vertex.hasPredecessor())
		{
			vertex = vertex.getPredecessor();
			path.push(vertex.getLabel());
		} // end while

		return pathLength;
	} // end getShortestPath

	//Extra credit methods start here
    public boolean isAcyclic(){
		boolean result = true;
		try{
			StackInterface<T> topologicalOrder = getTopologicalOrder();
		} catch (NullPointerException e){
			result = false;
		}
		return result;
	}

	public boolean isBipartite(){
    	if(isEmpty() || getNumberOfVertices() == 1)
    		return false;

    	resetVertices();
    	boolean result = true;
    	VertexInterface<T> originVertex;
    	QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
    	Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();

    	originVertex = vertexIterator.next();
		originVertex.visit();
		vertexQueue.enqueue(originVertex);
		originVertex.setCost(1);

		while(!vertexQueue.isEmpty() && result){
			VertexInterface<T> frontVertex = vertexQueue.dequeue();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			while(neighbors.hasNext()){
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(!nextNeighbor.isVisited()){
					nextNeighbor.visit();
					nextNeighbor.setCost(frontVertex.getCost() == 1 ? 0 : 1);
					vertexQueue.enqueue(nextNeighbor);
				} else {
					if (nextNeighbor.getCost() == frontVertex.getCost())
						result = false;
				}
			}
		}

    	return result;
	}

	public QueueInterface<T> findLongestPath(T origin, T end){
		assert vertices.getValue(origin) != null;
		assert vertices.getValue(end) != null;

		resetVertices();
		QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
		VertexInterface<T> originVertex = vertices.getValue(origin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		originVertex.visit();
		originVertex.setCost(1);
		vertexQueue.enqueue(originVertex);

		while(!vertexQueue.isEmpty()){
			VertexInterface<T> frontVertex = vertexQueue.dequeue();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			while(neighbors.hasNext()){
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(!nextNeighbor.isVisited()){
					nextNeighbor.visit();
					nextNeighbor.setPredecessor(frontVertex);
					nextNeighbor.setCost(1 + frontVertex.getCost());
					vertexQueue.enqueue(nextNeighbor);
				} else {
					boolean isGoingBack = false;
					VertexInterface<T> vertex = frontVertex;
					while(vertex.hasPredecessor()){
						vertex = vertex.getPredecessor();
						if(vertex.getLabel().equals(nextNeighbor.getLabel()))
							isGoingBack = true;

					}
 					if(frontVertex.getCost() + 1 > nextNeighbor.getCost() && !isGoingBack){
						nextNeighbor.setPredecessor(frontVertex);
						nextNeighbor.setCost(1 + frontVertex.getCost());
					}
				}
			}
		}

		VertexInterface<T> vertex = endVertex;
		StackInterface<T> tempStack = new LinkedStack<>();
		QueueInterface<T> longestPath = new LinkedQueue<>();

		tempStack.push(vertex.getLabel());
		while(vertex.hasPredecessor()){
			vertex = vertex.getPredecessor();
			tempStack.push(vertex.getLabel());
		}

		while(!tempStack.isEmpty())
			longestPath.enqueue(tempStack.pop());

		return longestPath;
	}

	//Extra credit methods end

	public StackInterface<T> getTopologicalOrder() {
			resetVertices();

			StackInterface<T> vertexStack = new LinkedStack<T>();
			int numberOfVertices = getNumberOfVertices();
			for (int counter = 1; counter <= numberOfVertices; counter++) {
				VertexInterface<T> nextVertex = findTerminal();
				nextVertex.visit();
				vertexStack.push(nextVertex.getLabel());
			} // end for

			return vertexStack;
		}

   // Exercise 11, Chapter 29
	/** Precondition: path is an empty stack (NOT null) */
	public double getCheapestPath(T begin, T end, StackInterface<T> path) // STUDENT EXERCISE
	{
		resetVertices();
		boolean done = false;

		// Use EntryPQ instead of Vertex because multiple entries contain
		// the same vertex but different costs - cost of path to vertex is EntryPQ's priority value
		PriorityQueueInterface<EntryPQ> priorityQueue = new HeapPriorityQueue<EntryPQ>();
		
		VertexInterface<T> originVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		priorityQueue.add(new EntryPQ(originVertex, 0, null));
	
		while (!done && !priorityQueue.isEmpty())
		{
			EntryPQ frontEntry = priorityQueue.remove();
			VertexInterface<T> frontVertex = frontEntry.getVertex();
			
			if (!frontVertex.isVisited())
			{
				frontVertex.visit();
				frontVertex.setCost(frontEntry.getCost());
				frontVertex.setPredecessor(frontEntry.getPredecessor());
				
				if (frontVertex.equals(endVertex))
					done = true;
				else 
				{
					Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
					Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
					while (neighbors.hasNext())
					{
						VertexInterface<T> nextNeighbor = neighbors.next();
						Double weightOfEdgeToNeighbor = edgeWeights.next();
						
						if (!nextNeighbor.isVisited())
						{
							double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
							priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
						} // end if
					} // end while
				} // end if
			} // end if
		} // end while

		// Traversal ends, construct cheapest path
		double pathCost = endVertex.getCost();
		path.push(endVertex.getLabel());
		
		VertexInterface<T> vertex = endVertex;
		while (vertex.hasPredecessor())
		{
			vertex = vertex.getPredecessor();
			path.push(vertex.getLabel());
		} // end while

		return pathCost;
	} // end getCheapestPath

	protected VertexInterface<T> findTerminal()
	{
		boolean found = false;
		VertexInterface<T> result = null;

		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();

		while (!found && vertexIterator.hasNext())
		{
			VertexInterface<T> nextVertex = vertexIterator.next();
			
			// If nextVertex is unvisited AND has only visited neighbors)
			if (!nextVertex.isVisited())
			{ 
				if (nextVertex.getUnvisitedNeighbor() == null )
				{ 
					found = true;
					result = nextVertex;
				} // end if
			} // end if
		} // end while

		return result;
	} // end findTerminal

	// Used for testing
	public void displayEdges()
	{
		System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
		System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		while (vertexIterator.hasNext())
		{
			((Vertex<T>)(vertexIterator.next())).display();
		} // end while
	} // end displayEdges 
	
	private class EntryPQ implements Comparable<EntryPQ>
	{
		private VertexInterface<T> vertex; 	
		private VertexInterface<T> previousVertex; 
		private double cost; // cost to nextVertex
		
		private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex)
		{
			this.vertex = vertex;
			this.previousVertex = previousVertex;
			this.cost = cost;
		} // end constructor
		
		public VertexInterface<T> getVertex()
		{
			return vertex;
		} // end getVertex
		
		public VertexInterface<T> getPredecessor()
		{
			return previousVertex;
		} // end getPredecessor

		public double getCost()
		{
			return cost;
		} // end getCost
		
		public int compareTo(EntryPQ otherEntry)
		{
			// Using opposite of reality since our priority queue uses a maxHeap;
			// could revise using a minheap
			return (int)Math.signum(otherEntry.cost - cost);
		} // end compareTo
		
		public String toString()
		{
			return vertex.toString() + " " + cost;
		} // end toString 
	} // end EntryPQ
} // end DirectedGraph
import java.util.Iterator;
import java.util.Arrays; 
import java.util.NoSuchElementException;

public class Graph implements GraphADT{
	private GraphNode[] Verticies; //vertices of the matrix 
	private GraphEdge[][] adjMatrix ; //Adjacent matrix for the graph
	private int numNodes; //number of nodes needed 
	
	public Graph(int n){ //creates an empty graph with n nodes and no edges
		this.numNodes = n; //initialize numNodes; 
		this.Verticies = new GraphNode[n]; //initialize vertices of the graph 
		this.adjMatrix = new GraphEdge[n][n]; //initialize edges of the graph 
		for(int i = 0; i<n;i++) { // looping through the vertices to name the nodes 0, 1, ... , n-1 
			this.Verticies[i] = new GraphNode(i);
		}
		
	}

	@Override
	public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {//adds to the graph an edge connecting nodes u and v
		// TODO Auto-generated method stub
		try {
			adjMatrix[nodeu.getName()][nodev.getName()] = new GraphEdge(nodeu, nodev, type, label);  
			adjMatrix[nodev.getName()][nodeu.getName()] = new GraphEdge(nodeu, nodev, type, label);  

			
		}
		catch(Exception e) {// This method throws a GraphException if either node does not exist or if there is already an edge connecting the given nodes
			throw new GraphException("node does not exist or if there is already an edge connecting the given nodes"); 
		}
	}

	@Override
	public GraphNode getNode(int name) throws GraphException { // returns the node with the specified name
		// TODO Auto-generated method stub
		try {
			return Verticies[name]; 

		} 
		catch(Exception e) { //If no node with this name exists, the method should throw a GraphException
			throw new GraphException("no node with this name exists"); 
		}
	}

	@Override
	public Iterator incidentEdges(GraphNode u) throws GraphException { //returns a Java Iterator storing all the edges
		// TODO Auto-generated method stub
		try {
			GraphEdge[] edges = adjMatrix[u.getName()]; 
			return new Iterator<GraphEdge>() {  
	            private int currentIndex = 0;

	            @Override
	            public boolean hasNext() { //makes sure the iterator doesn't include null 
	                while (currentIndex < edges.length && edges[currentIndex] == null) {
	                    currentIndex++;
	                }
	                return currentIndex < edges.length;
	            }

	            @Override
	            public GraphEdge next() {
	                if (!hasNext()) {
	                    throw new NoSuchElementException();
	                }
	                return edges[currentIndex++];
	            }
	        };
		}
		catch(Exception e) { // throw exception if u is not a node 
			throw new GraphException("u is not a node of the graph"); 
		}
	}
	

	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {//: returns the edge connecting nodes u and v
		// TODO Auto-generated method stub
		try{
			return adjMatrix[u.getName()][v.getName()]; 
		}
		catch(Exception e) { //throws a GraphException if there is no edge between u and v or if u or v are not nodes of the graph
			throw new GraphException(" no edge between u and v or if u or v are not nodes of the graph");
		}
		
	}

	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException { //returns true if nodes u and v are adjacent
		// TODO Auto-generated method stub
		try {
			if(adjMatrix[u.getName()][v.getName()] != null) return true; 
			else return false; //returns false if nor adjacent 
		}
		catch(Exception e) {
			throw new GraphException("u or v are not nodes of the graph");//throws a GraphException if u or v are not nodes of the graph
		}
	}

}

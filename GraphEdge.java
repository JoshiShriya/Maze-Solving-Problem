
public class GraphEdge {
	private GraphNode u; //first end point 
	private GraphNode v; //second end point 
	private int type; //type of edge 
	private String label; //label of edge
	
	public GraphEdge(GraphNode u, GraphNode v, int type, String label){ //Constructor of the class 
		this.u = u; //initialize first end point 
		this.v = v; //initialize second end point 
		this.type = type; //initialize type of edge 
		this.label = label; //initialize label of edge 
	}
	public GraphNode firstEndpoint() { // returns the first end point of the edge
		return u; 
	}
	public GraphNode secondEndpoint() {//returns the second end point of the edge
		return v; 
	}
	public int getType() {// returns the type of the edge
		return type; 
	}
	public void setType(int newType){//sets the type of the edge to the specified value
		this.type = newType; 
	}
	public String getLabel() {//returns the label of the edge
		return label; 
	}
	public void setLabel(String newLabel){//sets the label of the edge to the specified value
		this.label = newLabel; 
	}
}

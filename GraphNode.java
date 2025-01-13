
public class GraphNode {
	private int name; 
	boolean marked; 
	GraphNode(int name){ //constructor of the class 
		this.name = name; //initialize the variable name; 
		this.marked = false; //initialize marked to false 
	}
	void mark(boolean mark){ //marks the node with the specified value
		this.marked = mark; 
	}
	boolean isMarked() {// returns the value with which the node has been marked
		return marked; 
	}
	int getName() {//returns the name of the node
		return name; 
	}
}

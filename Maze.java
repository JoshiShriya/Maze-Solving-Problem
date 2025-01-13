import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
	//variables needed 
	private Graph graph;
	private int start, end;
	private Stack<GraphNode> way;
	private int width, length, numCoins;

	public Maze(String inputFile) throws MazeException {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line = br.readLine();

			width = Integer.valueOf(line);
			length = Integer.valueOf(line);
			numCoins = Integer.valueOf(line);

			graph = new Graph(width * length);

			while (line != null) {
				
				int numNodes = 0;
				//checking the graph 
				for (int i = 0; i < width + 1; i++) {
					char[] m = br.readLine().toCharArray();
					if (i % 2 == 1) { //checking no node rows 
						int passedNodes = 0;
						for (int j = 0; j < m.length + 1; j++) {
							char character = m[j];
							if (j % 2 == 1) { //
								if (character != 'w')
									throw new MazeException("Format for maze is incorrect");
							} else if (j % 2 == 0) { 
								if (Character.isDigit(character) && character != 'c' && character != 'w') { //checking for doors 
									graph.insertEdge(graph.getNode(numNodes + passedNodes - width),
											graph.getNode(numNodes + passedNodes), Integer.valueOf(character), "door");
								} else if (character == 'c') { //checking for corridors 
									graph.insertEdge(graph.getNode(numNodes + passedNodes - width),
											graph.getNode(numNodes + passedNodes), 0, "corridor");

								} else {
									throw new MazeException("Format of maze is incorrect");

								}

								passedNodes++;
							}
						}
					} else if (i % 2 == 0) { //reading rows with nodes 
						for (int j = 0; j < m.length + 1; j++) {
							char character = m[j];
							if (j % 2 == 0) {
								if (character == 's') { //checking for size 
									start = numNodes; 
								} else if (character == 'o') { //checking for room 
									end = numNodes; 
								}
								numNodes++; 
							}
							else if(j%2 == 1) { 
								//checking for difference connections 
								if (Character.isDigit(character) && character != 'c' && character != 'w') {
									graph.insertEdge(graph.getNode(numNodes - 1),
											graph.getNode(numNodes), Integer.valueOf(character), "door");
								} else if (character == 'c') {
									graph.insertEdge(graph.getNode(numNodes -1 ),
											graph.getNode(numNodes), 0, "corridor");

								} else {
									throw new MazeException("Format of maze is incorrect");

								}
 
							}

						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new MazeException("graph is null");
		}
	}

	public Graph getGraph() throws MazeException { //returns a reference to the Graph object representing the maze
		if (graph == null)
			throw new MazeException("Graph is null"); //Throws a MazeException if the graph is null.
		return graph;

	}

	public Iterator solve() {
		GraphNode start = null, end;
		int coins;
		try {
			start.mark(true);
			way.push(start);

			while (way != null) {
				Iterator<GraphEdge> itr = graph.incidentEdges(start);
				LinkedList<GraphEdge> edg = new LinkedList<>(); 
				while (itr.hasNext()) {
					GraphEdge edge = itr.next();
					GraphNode next = edge.secondEndpoint();
					coins = numCoins;

					if (next == start) {
						next = edge.firstEndpoint();
					}
					if (!next.isMarked() && edge.getType() >= 1) {
						edg.add(edge); 
					}
					
				}

			}
			start.mark(false);
			way.pop(); 
			
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return way.iterator();

	}

	
}
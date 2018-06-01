package Maze;
/*
 * Team Memebers:
 * My Vu 
 * Vinh Vu
 * Stefan 
 */
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;




public class Maze {
	
	private Random generator;			// randomly generated maze
	private Vertex listVertex[];		// the vertex list
	private Vertex adjMatrix[][];		// adjacency matrix of the maze
	private int matrixSize;				// size of matrix
		
	// construct of the Maze
	public Maze(int size)
	{
		generator = new Random();	// seed is 0
		listVertex = new Vertex[size*size];	
		adjMatrix = new Vertex[size][size];
		matrixSize = adjMatrix.length - 1;
		int count = 0;
		// Run the Matrix
		for(int i = 0; i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				Vertex vertex = new Vertex(i,j); // create new Vertex with i and j
				adjMatrix[i][j] = vertex;
				listVertex[count] = vertex;
				count++;
			}
		}
		
		createMaze();
		
	}
	
	// return random number
	double randomGen()
	{
		return generator.nextDouble();
	}
	
	// Create a maze
	public void createMaze()
	{
	
		Stack<Vertex> stackVertex = new Stack<Vertex>();	
		Vertex currentVertex = adjMatrix[0][0];
		int visitedVertex = 1;
		
		 
		
		while(visitedVertex<listVertex.length)
		{
			List<Vertex> neighbor = findNeighbors(currentVertex);
			Vertex keyVertex = currentVertex;
			
			// continues to the next Vertex
			if(neighbor.size()>0)
			{
				stackVertex.push(currentVertex);
				int randomValue = (int)(randomGen()*neighbor.size());
				
				currentVertex = neighbor.get(randomValue);
				deletedWall(currentVertex,keyVertex);
				currentVertex.list.add(keyVertex);
				keyVertex.list.add(currentVertex);
				visitedVertex++;
			}
			else {
					// check empty stack
					if(!stackVertex.isEmpty())
					{
						currentVertex = stackVertex.pop();
					}
			}
		
		
		}
		adjMatrix[0][0].North = false;
		adjMatrix[matrixSize][matrixSize].South=false;
	}
	
	public boolean checkVisited(Vertex gVertex)
	{
		return gVertex.North&& gVertex.South&& gVertex.West&& gVertex.East;
	}
	
	// Find neighbors method
	public List<Vertex> findNeighbors(Vertex givenVertex)
	{
		 List<Vertex> neighbors = new ArrayList<Vertex>();	// create a arrayList
	        int matrixSize = this.matrixSize;

	        //check and add visited vertices
	        if (givenVertex.j < matrixSize && checkVisited(adjMatrix[givenVertex.i][givenVertex.j + 1])) {
	            neighbors.add(adjMatrix[givenVertex.i][givenVertex.j + 1]);
	        }

	        if (givenVertex.i < matrixSize && checkVisited(adjMatrix[givenVertex.i + 1][givenVertex.j])) {
	            neighbors.add((adjMatrix[givenVertex.i + 1][givenVertex.j]));
	        }

	        if (givenVertex.j > 0 && checkVisited(adjMatrix[givenVertex.i][givenVertex.j - 1])) {
	            neighbors.add((adjMatrix[givenVertex.i][givenVertex.j - 1]));
	        }

	        if (givenVertex.i > 0 && checkVisited(adjMatrix[givenVertex.i - 1][givenVertex.j])) {
	            neighbors.add((adjMatrix[givenVertex.i - 1][givenVertex.j]));
	        }

	        return neighbors;
	}
	
	// Delete the Wall
	public void deletedWall(Vertex ver1, Vertex ver2)
	{
		if (ver1.i < matrixSize && adjMatrix[ver1.i + 1][ver1.j].equals(ver2)) {
            ver1.East = false;
            ver2.West = false;
        }

        if (ver1.j < matrixSize && adjMatrix[ver1.i][ver1.j + 1].equals(ver2)) {
            ver1.South = false;
            ver2.North = false;
        }

        if (ver1.i > 0 && adjMatrix[ver1.i - 1][ver1.j].equals(ver2)) {
            ver1.West = false;
            ver2.East = false;
        }

        if (ver1.j > 0 && adjMatrix[ver1.i][ver1.j - 1].equals(ver2)) {
            ver1.North = false;
            ver2.South = false;
        }
		
		
		
	}
	
	// Solves the maze
	public String solveMaze(){
		 String maze = "";
	        int sizeAdjMatrix = adjMatrix.length;

	        //top of the maze
	        for (int i = 0; i < sizeAdjMatrix; i++){
	            maze += (i == 0) ? "+#" : "+-";
	        }
	        maze += "+\n";

	        // side and bottom of maze
	        for (int i = 0; i < sizeAdjMatrix; i++){
	            maze += "|";
	            for (int j = 0; j < sizeAdjMatrix - 1; j++){
	                if (adjMatrix[j][i].East) {
	                    maze += (adjMatrix[j][i].valueVertex.equals("#")) ? "#|" : " |";
	                }
	                else {
	                    if (adjMatrix[j][i].valueVertex.equals("#")) {
	                        maze += "#";
	                        maze += (adjMatrix[j + 1][i].valueVertex.equals("#")) ? "#" : " ";
	                    }
	                    else maze += "  ";
	                }
	            }

	            maze += adjMatrix[sizeAdjMatrix - 1][i].valueVertex.equals("#") ? "#|\n+" : " |\n+";
	            if (i < sizeAdjMatrix - 1) {
	                for (int j = 0; j < sizeAdjMatrix; j++) {
	                    if (adjMatrix[j][i].South) maze += "-";
	                    else maze += (adjMatrix[j][i].valueVertex.equals("#")) ? "#" :" ";
	                    maze += "+";
	                }
	                maze += "\n";
	            }
	        }

	        for (int i = 0; i < sizeAdjMatrix; i++) {
	            maze += (!adjMatrix[i][sizeAdjMatrix-1].South) ? "#":"-+";
	        }

	        maze += "+";
	        return maze;
	}
	
	// print out Maze
	public String printMaze() {
		 String maze = "";
	        int size = adjMatrix.length;
	        System.out.println(size);

	        for (int i = 0; i < size; i++) {
	            maze += (i == 0) ? "+ " : "+-";
	        }
	        maze += "+\n";

	        for (int i = 0; i < size; i++) {
	            maze += "|";
	            for (int j = 0; j < size - 1; j++){
	                maze += (adjMatrix[j][i].step > -1) ? adjMatrix[j][i].step % 10 : " ";
	                maze += (adjMatrix[j][i].East) ?  "|" : " ";
	            }
	            maze += (adjMatrix[size - 1][i].step > -1) ? adjMatrix[size-1][i].step % 10 + "|\n+" : " |\n+";
	            if (i < size - 1) {
	                for (int j = 0; j < size; j++) {
	                    maze += (adjMatrix[j][i].South) ? "-+" :" +";
	                }
	                maze += "\n";
	            }
	        }

	        for (int i = 0; i < size; i++) {
	            maze += (!adjMatrix[i][size-1].South) ? " ":"-+";
	        }

	        maze += "+";
	        return maze;
	}

	// reset Maze method	
	private void resetMaze() {
		for (int i = 0; i < listVertex.length; i++) {
            listVertex[i].color = VertexColor.WHITE;
            listVertex[i].step = -1;
            listVertex[i].prev = null;
        }
	}
	
	
	// BFS method
	public String BFS()
	{
		resetMaze();
		Vertex currentVertex = listVertex[0];
		Queue<Vertex> vertexQueue = new LinkedList<Vertex>();
		vertexQueue.add(currentVertex);
		int step = 0;
		
		//check empty stack and then change color
		while(!vertexQueue.isEmpty() && !currentVertex.equals(listVertex[listVertex.length-1]))
		{
			currentVertex = vertexQueue.remove();
			currentVertex.color = VertexColor.BLACK;
			currentVertex.step = step;
			step ++;
			
			for(Vertex v:currentVertex.list)
			{
				if(v.color == VertexColor.WHITE)
				{
					v.color = VertexColor.GRAY;
					v.prev = currentVertex;
					vertexQueue.add(v);
				}
			}
		}
		
		while(currentVertex != listVertex[0])
		{
			currentVertex.valueVertex= "#";
			currentVertex = currentVertex.prev;
		}
		
		currentVertex.valueVertex = "#";
		
		// print out BFS
		System.out.println("BFS:");
		System.out.println(printMaze());
		System.out.println();
		return solveMaze();
	}
	
	public String DFS() {

		resetMaze(); //first it resets the maze

        // Stack is added with currentVertex
        Vertex currentVertex = listVertex[0];
        Stack<Vertex> verticesStack = new Stack<>();
        verticesStack.push(currentVertex);
        int step = 0;

        // check stack empty then write to stack
        while (!verticesStack.isEmpty() && !currentVertex.equals(listVertex[listVertex.length - 1])) {
 
            currentVertex = verticesStack.pop();
            currentVertex.color = VertexColor.BLACK;
            currentVertex.step = step;
            step++;

            // change color for vertex
            for (Vertex v: currentVertex.list) {
                if (v.color == VertexColor.WHITE) {
                    v.color = VertexColor.GRAY;
                    v.prev = currentVertex;
                    verticesStack.add(v);
                }
            }
        }

        // check currentVertex
        while(currentVertex != listVertex[0]) {
            currentVertex.valueVertex = "#";
            currentVertex = currentVertex.prev;
        }
        currentVertex.valueVertex = "#";

        //print DFS
        System.out.println("\n\nDFS:");
        System.out.println(printMaze());
        System.out.println();
        return solveMaze();
	}
	
	
// Main method	
	public static void main(String[] args) 
	{
		
		Maze m = new Maze(4); 
		System.out.println("4x4");
		System.out.println(m.printMaze());
		System.out.print(m.BFS());
		System.out.print(m.DFS());
		System.out.println();
		
		System.out.println("-------------------------------");
		
		Maze m1 = new Maze(6);
		System.out.println("6x6");
		System.out.println(m1.printMaze());
		System.out.print(m1.BFS());
		System.out.print(m1.DFS());
	}
}

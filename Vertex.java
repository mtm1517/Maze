package Maze;
import java.util.LinkedList;

enum VertexColor
{
	WHITE,GRAY,BLACK;
}

public class Vertex {
	boolean North = true;
	boolean South = true;
	boolean West = true;
	boolean East = true;
	
	int step;
	int i;
	int j;
	
	VertexColor color = VertexColor.WHITE;
	LinkedList<Vertex> list;
	
	String valueVertex;
	Vertex prev;
	
	// Vertex constructor
	public Vertex(int i, int j)
	{
	
		this.i = i;
		this.j = j;
		this.valueVertex = "";
	
		
		list = new LinkedList<>();
		this.prev = null;
		this.step = -1;
	}

}

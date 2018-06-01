package Maze;

import static org.junit.Assert.*;

import org.junit.Test;

public class MazeTest {

	@Test
	 public void graph4x4() {
        System.out.println("4x4 Graph");
        Maze test = new Maze(4);
        System.out.print(test.printMaze());
        System.out.print(test.DFS());
        System.out.print(test.BFS());
        assertEquals(test.BFS(), test.DFS());
    }
	
	public void graph5x5() {
        System.out.println("\n 5x5 Graph");
        Maze test1 = new Maze(5);
        System.out.print(test1.printMaze());
        System.out.print(test1.DFS());
        System.out.print(test1.BFS());
        assertEquals(test1.BFS(), test1.DFS());
    }
	
	public void graph6x6() {
	    System.out.println("\n 6x6 Graph");
	    Maze test2 = new Maze(6);
	    System.out.print(test2.printMaze());
	    System.out.print(test2.DFS());
	    System.out.print(test2.BFS());
	    assertEquals(test2.BFS(), test2.DFS());
	}
	
	 public void graph10x10() {
	    System.out.println("\n 10x10 Graph");
	    Maze test3 = new Maze(10);
	    System.out.print(test3.printMaze());
	    System.out.print(test3.DFS());
	    System.out.print(test3.BFS());
	    assertEquals(test3.BFS(), test3.DFS());
	 }
	 
	 public void graph17x17() {
	    System.out.println("\n 17x17 Graph");
	    Maze test4 = new Maze(17);
	    System.out.print(test4.printMaze());
	    System.out.print(test4.DFS());
	    System.out.print(test4.BFS());
	    assertEquals(test4.BFS(), test4.DFS());
	  }

}

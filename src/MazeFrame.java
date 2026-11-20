import java.util.*;
import javax.swing.*;

public class MazeFrame extends JFrame{

	Cell [][] grid;
	MazeCanvas canvas;
	private int delay = 15;
	
	private void pause() {
		canvas.repaint();
		try {
			Thread.sleep(delay);
		}
		catch(InterruptedException e) {
			//ignore
		}
	}
	public MazeFrame(int rows, int cols) {
		setTitle("My Perfect Maze!");
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grid = new Cell[rows][cols];
		initGrid(); 
		canvas = new MazeCanvas(grid);
		add(canvas);
	}//constructor
	public void runMaze() {
		genMazeRandomisedDFS();
		resetVisitedCells();
		bfsSolver();
	}
	private void genMazeRandomisedDFS() {
		//using random dfs to tear dows correct walls to gen a perfect maze
		Cell curr = grid[0][0];
		curr.visited = true;
		StackReferenceBased stack = new StackReferenceBased();
		stack.push(curr);
		while(!stack.isEmpty()) {
			curr = (Cell) stack.peek();
			ArrayList<Cell> list;
			list = unvisitedNeighbours(curr);
			if(list.size() >=1) {
				int index = (int) (Math.random() * list.size());
				Cell next = list.get(index);
				removeWall(curr, next);
				next.visited = true;
				stack.push(next);
				pause();
			}
			else {
				stack.pop();
				pause();
			}
			
		}
	}
	private ArrayList<Cell> unvisitedNeighbours(Cell c) {
		int row = c.row;
		int col = c.col;
		ArrayList<Cell> list = new ArrayList<Cell>();
		if((row-1>=0) && (!grid[row-1][col].visited)) {
			list.add(grid[row-1][col]);
		}
		if((row+1<grid.length) && (!grid[row+1][col].visited)) {
			list.add(grid[row+1][col]);
		}
		if((col-1>=0) && (!grid[row][col-1].visited)) {
			list.add(grid[row][col-1]);
		}
		if((col+1 < grid[0].length) && (!grid[row][col+1].visited)) {
			list.add(grid[row][col+1]);
		}
		return list;
	}
	private void removeWall(Cell curr, Cell next) {
		if(next.row == curr.row-1) {
			curr.top = false;
			next.bottom = false;
		}
		else if(next.row == curr.row+1) {
			curr.bottom = false;
			next.top = false;
		}
		else if(next.col == curr.col-1) {
			curr.left = false;
			next.right = false;
		}
		else if(next.col == curr.col+1) {
			curr.right = false;
			next.left = false;
		}
	}
	
	private void resetVisitedCells() {
		for (int i = 0; i< grid.length; i++) {
			for (int j = 0; j< grid[0].length; j++) {
				Cell c = grid[i][j];
				c.visited = false;
				pause();
			}
		}
	}
	
	private void bfsSolver() {
		Cell curr = grid[0][0];
		curr.visited = true;
		Cell exit = grid[grid.length - 1][grid[0].length - 1];
		QueueArrayBased queue = new QueueArrayBased(grid.length * grid[0].length);
		queue.enqueue(curr);
		while(!queue.isEmpty()) {
			curr = (Cell) queue.dequeue();
			if (curr == exit) {
				break;
			}

					if(curr.top == false && grid[curr.row - 1][curr.col].visited == false) {
						Cell goUp = grid[curr.row - 1][curr.col];
						goUp.visited = true;
						goUp.parent = curr;
						queue.enqueue(goUp);
						pause();
						
					}
					if(curr.bottom == false && grid[curr.row+1][curr.col].visited == false) {
						Cell goDown = grid[curr.row + 1][curr.col];
						goDown.visited = true;
						goDown.parent = curr;
						queue.enqueue(goDown);
						pause();
					}
					if(curr.left == false && grid[curr.row][curr.col-1].visited == false) {
						Cell goLeft = grid[curr.row][curr.col-1];
						goLeft.visited = true;
						goLeft.parent = curr;
						queue.enqueue(goLeft);
						pause();
					}
					if(curr.right == false && grid[curr.row][curr.col+1].visited == false) {
						Cell goRight = grid[curr.row][curr.col+1];
						goRight.visited = true;
						goRight.parent = curr;
						queue.enqueue(goRight);
						pause();
					}
		}//end of while loop
		
		Cell step = exit;
		while(step != null) {
			step.path = true;
			pause();
			step = step.parent;
		}
		
	}//end of bfs solver

	private void initGrid() {
		for (int i = 0; i< grid.length; i++) {
			for (int j = 0; j< grid[0].length; j++) {
				grid[i][j] = new Cell(i,j);
			}
		}
	}
	public static void main(String[] args) {
	MazeFrame f = new MazeFrame(10,10);
	f.setVisible(true);
	f.runMaze();
	

	}//end of main
	

}

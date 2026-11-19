import java.util.ArrayList;
import javax.swing.*;

public class MazeFrame extends JFrame{

	int M = 10; int N = 10;
	Cell [][] grid = new Cell [M][N];
	
	public MazeFrame() {
		setTitle("My Perfect Maze!");
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGrid(); 
		add(new MazeCanvas(grid));
	}
	
	private void genMazeRandomisedDFS() {
		//using random dfs to tear dows correct walls to gen a perfect maze
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
	
	private void initGrid() {
		for (int i = 0; i< grid.length; i++) {
			for (int j = 0; j< grid[0].length; j++) {
				grid[i][j] = new Cell(i,j);
			}
		}
	}
	public static void main(String[] args) {
	new MazeFrame().setVisible(true);
	

	}//end of main
	

}

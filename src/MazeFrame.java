import javax.swing.*;
public class MazeFrame extends JFrame{
	
	int M = 10; int N = 10;
	Cell [][] grid = new Cell [M][N];
	
	public MazeFrame() {
		setTitle("My Perfect Maze!");
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGrid(); 
		//grid[0][0].right = false; */
		//grid[0][1].left = false;
		//grid[5][5].bottom = false;
		//grid[6][5].top = false;
		add(new MazeCanvas(grid));
	}
	
	private void genMazeRandomisedDFS() {
		//using random dfs to tear dows correct walls to gen a perfect maze
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
		

	}

}

import java.awt.*;
import javax.swing.*;

public class MazeCanvas extends JPanel{
	
	Cell[][] grid;
	
	public MazeCanvas(Cell [][] grid_arg) {
		grid = grid_arg;
	}
	
	
	private void drawCell(Graphics g, Cell c, int top, int left, int cell_dim) {

		if (c.top)
			g.drawLine(left, top, left+cell_dim, top);//draw top line
		if (c.left)
			g.drawLine(left, top, left, top+cell_dim);//draw left line
		if (c.bottom)
			g.drawLine(left, top+cell_dim, left+cell_dim, top+cell_dim);//draw bottom line
		if (c.right)
			g.drawLine(left+cell_dim, top, left+cell_dim, top+cell_dim);//draw right line
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);//clear the panel
		int w = getWidth();
		int h = getHeight();
		
		int cell_dim;//the size of cell in pixels when drawn on screen
		if(w<h) {
			cell_dim = w/grid.length;
		}
		else {
			cell_dim = h/grid.length;
		}
		for(int i =0; i< grid.length; i++) {//rows
			for (int j=0; j< grid[0].length; j++){//cols
				drawCell(g,grid[i][j], i*cell_dim, j*cell_dim, cell_dim);
			}
		}
	}
}

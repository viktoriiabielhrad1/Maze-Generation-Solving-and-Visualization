
public class Cell {
	public boolean visited;
	int row;
	int col;
	public boolean left,right,top,bottom;
	public Cell parent = null;
	public boolean path = false;
	
	public Cell (int r, int c) {
		visited = false;
		row = r;
		col = c;
		left = right= top = bottom = true;
		
	}
	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
}

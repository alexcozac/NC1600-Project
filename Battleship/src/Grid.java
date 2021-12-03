import java.util.Iterator;

public class Grid {
	
	private char ship = 's';
	private char hit = 'h';
	private char miss = 'm';
	private char grid[][];
	
	
	//this object creates the grid
	//create grid controller obj that will inherit this class and act "referee"
	
	public Grid() {
		this.grid = new char[][] 
				{{'g','g','g','g','g','g','g','g','g','g'},
				{'g','g','g','g','g','g','g','g','g','g'},
				{'g','g','g','g','g','g','g','g','g','g'},
				{'g','g','g','g','g','g','g','g','g','g'},
				{'g','g','g','g','g','g','g','g','g','g'},
				{'g','g','g','g','g','g','g','g','g','g'},
				{'g','g','g','g','g','g','g','g','g','g'},
				{'g','g','g','g','g','g','g','g','g','g'},
				{'g','g','g','g','g','g','g','g','g','g'},
				{'g','g','g','g','g','g','g','g','g','g'}};
				
	}
		
	
		
	public char[][] getGrid() {
		return this.grid;
	}
	
	public void setGrid(int x, int y) {
		this.grid[x][y] = 'Z';

	}
	public void printGrid() {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				
				if (x==5 && y==5) {
					grid[x][y] = 's';
					grid[x][y+1] = 's';
					grid[x][y+2] = 's';
					grid[x][y+3] = 's';
					grid[x][y+4] = 's';
				}
				
				
			}
		}
		int i = 0;
		int j = 0;
		for (i = 0; i < 10; i++) {
			System.out.println("");
			for (j = 0; j < 10; j++) {
				System.out.print(grid[i][j]);
				
			}
			
		}

	}
	
	
	

}

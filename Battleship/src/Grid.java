

public class Grid {
		
	private char grid[][];
	private String player;
	
	//this object creates the grid
	//create grid controller obj that will inherit this class and act "referee"
	//player obj will ask the referee to fire (referee will maintain the grid)
	
	
	public Grid(String player) {
		this.player = player;
		
		grid = new char[10][10];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid [i][j] = 'g';
			}
		}			
	}
		
	
		
	public char[][] getGrid() {
		return grid;
	}
	
	public void setGrid(int x, int y, char z) {
		grid[x][y] = z;

	}
	public void printGrid() {
		
		for (int i = 0; i < 10; i++) {
			System.out.println("");
			for (int j = 0; j < 10; j++) {
				System.out.print(grid[i][j]);
				
			}
			
		}

	}
	
	
	

}

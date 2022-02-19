
public class Grid {

	private char grid[][];

	public Grid() {

		grid = new char[10][10];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = 'g';
			}
		}
	}

	public char[][] getGrid() {
		return grid;
	}

	// Will be used to gain advantage over Computer Player if it becomes unbeatable (During LVL2 or LVL3 AI)
	// By creating odd shaped ships
	public void setGrid(int x, int y, char z) {
		grid[x][y] = z;

	}

	// True for enemy grid (invisible ships) False for user grid
	public void printGrid(boolean fog) {

		for (int i = 0; i < grid.length; i++) {
			System.out.println("");
			for (int j = 0; j < grid.length; j++) {
				if (fog == true) {
					if (grid[i][j] == 'a' || grid[i][j] == 'b' || grid[i][j] == 's' || grid[i][j] == 'd'
							|| grid[i][j] == 'p') {
						System.out.print("g");
					} else {
						System.out.print(grid[i][j]);
					}
				} else {
					System.out.print(grid[i][j]);
				}

			}

		}
		System.out.println("");

	}

}

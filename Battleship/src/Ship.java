
public class Ship {

	private String shipName;
	private int shipSize;
	private char shipChar;
	private int shipHP;
	private boolean destroyed = false;
	private boolean announced = false;

	public Ship(String shipName, int shipSize, char shipChar) {
		this.shipName = shipName;
		this.shipSize = shipSize;
		this.shipHP = 0;
		this.shipChar = shipChar;
	}

	public String getShipName() {
		return shipName;
	}

	public int getShipSize() {
		return shipSize;
	}

	public char getShipChar() {
		return shipChar;
	}

	public boolean isShipDed() {
		return destroyed;
	}

	public int getShipHP(char[][] grid) {
		shipHP = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (grid[i][j] == shipChar) {
					shipHP += 1;
				}
			}
		}
		if (shipHP == 0 && announced == false) {
			System.out.println("You sank my " + shipName);
			destroyed = true;
			announced = true;
		} else {
			destroyed = false;
		}
		return shipHP;
	}

}

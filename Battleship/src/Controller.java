import java.util.Random;

public class Controller {

	protected final static char hit = 'h';
	protected final static char miss = 'm';
	public int score = 0;
	private static boolean turn = true;
	public static Random rand = new Random();
	private static boolean collision;
	private static boolean offbounds;
	private static boolean gameStarted;

	public static boolean userTurn() {
		return turn;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}

	public static void spawnShip(char[][] location, int vAxis, int hAxis, int direction, int shipSize, char shipChar) {

		// checker makes sure the coast is clear before starting to spawn the ship (if
		// not in use, ship will start spawning and stop on collision or out of bounds,
		// leaving part of the ship incomplete)
		collision = false;
		offbounds = true;
		int checker_vAxis = vAxis;
		int checker_hAxis = hAxis;

		if (direction == 0 && vAxis + shipSize <= location.length) {
			offbounds = false;
		} else if (direction == 1 && hAxis + shipSize <= location.length) {
			offbounds = false;
		}

		for (int i = 0; i < shipSize; i++) {
			if (location[checker_vAxis][checker_hAxis] == 'g') {
				if (direction == 0 && offbounds == false) {
					checker_vAxis++;
				} else if (direction == 1 && offbounds == false) {
					checker_hAxis++;
				}
			} else {
				collision = true;
			}
		}

		// spawner
		for (int i = 0; i < shipSize; i++) {
			if (offbounds == false && collision == false) {
				if (direction == 0) {
					location[vAxis][hAxis] = shipChar;
					vAxis++;
				} else if (direction == 1) {
					location[vAxis][hAxis] = shipChar;
					hAxis++;
				}
			} else if (offbounds == true) {
				System.out.println("Ship out of bounds!");
				break;
			} else if (collision == true) {
				System.out.println("Ship collision detected!");
				break;
			}
		}

	}
	
	public static boolean gameStarted() {
		return gameStarted;
	}
	
	public static void startGame(boolean command) {
		gameStarted = command;
	}

	public static void randomSpawn(char[][] location, int shipSize, char shipChar) {
		int vAxis = rand.nextInt(10);
		int hAxis = rand.nextInt(10);
		int direction = rand.nextInt(2);
		int checker_vAxis = vAxis;
		int checker_hAxis = hAxis;
		boolean clearance = false;

		// Depending on direction, checks if there are no collisions or out of bound
		// spawns and gives clearance.
		while (clearance == false) {
			for (int i = 0; i < shipSize; i++) {
				switch (direction) {
				case 0:
					if (location[checker_vAxis][checker_hAxis] == 'g' && vAxis + shipSize <= location.length) {
						checker_vAxis++;
						clearance = true;
						break;
					} else {
						vAxis = rand.nextInt(10);
						hAxis = rand.nextInt(10);
						checker_vAxis = vAxis;
						checker_hAxis = hAxis;
						i = i - 1;
						clearance = false;
						break;
					}
				case 1:
					if (location[checker_vAxis][checker_hAxis] == 'g' && hAxis + shipSize <= location.length) {
						checker_hAxis++;
						clearance = true;
						break;
					} else {
						vAxis = rand.nextInt(10);
						hAxis = rand.nextInt(10);
						checker_vAxis = vAxis;
						checker_hAxis = hAxis;
						i = i - 1;
						clearance = false;
						break;
					}
				}
			}
		}

		// spawns the ship
		for (int i = 0; i < shipSize; i++) {
			if (clearance = true && direction == 0) {
				location[vAxis][hAxis] = shipChar;
				vAxis++;
			} else if (clearance = true && direction == 1) {
				location[vAxis][hAxis] = shipChar;
				hAxis++;
			}
		}
	}
	/**
	 * it shoots ofc
	 * 
	 * @param location
	 * @param vAxis
	 * @param hAxis
	 * @return
	 */
	public char shoot(char[][] location, int vAxis, int hAxis) {
		if (location[vAxis][hAxis] == 'a' || location[vAxis][hAxis] == 'b' || location[vAxis][hAxis] == 's'
				|| location[vAxis][hAxis] == 'd' || location[vAxis][hAxis] == 'p') {
			location[vAxis][hAxis] = hit;
			System.out.println("My ship was hit!");
			score += 1;
			if (turn == true) {
				turn = false;
			} else if (turn == false) {
				turn = true;
			}
			return hit;
		} else if (location[vAxis][hAxis] == 'g') {
			location[vAxis][hAxis] = miss;
			System.out.println("You missed!");
			if (score > 0) {
				score -= 1;
			}
			if (turn == true) {
				turn = false;
			} else if (turn == false) {
				turn = true;
			}
			return miss;
		} else if (location[vAxis][hAxis] == hit || location[vAxis][hAxis] == miss) {
			if (turn == true) {
				System.out.println("You have already shot at these coordinates!");
			} else {
				cpuBrainz.countFails(1);
			}
			return 0;
		}
		return 0;

	}
}

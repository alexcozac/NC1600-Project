package application;
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

	public void setScore(int score, char outcome) {
		switch (outcome) {
		case hit:
			this.score = score + 1;
			break;

		case miss:
			if (score > 0)
				this.score = score - 1;
			break;
		}
	}

	public int getScore() {
		return score;
	}
	
	public void bonusScore(int score, int shipSize) {
		this.score = score + (shipSize * 2);
	}

	public void nextTurn() {
		if (turn == true) {
			turn = false;
		} else if (turn == false) {
			turn = true;
		}
	}

	public String spawnShip(char[][] location, int vAxis, int hAxis, int direction, int shipSize, char shipChar) {

		/*
		 *  checker makes sure the coast is clear before starting to spawn the ship (if
		 *  not in use, ship will start spawning and stop on collision or out of bounds,
		 *  leaving part of the ship incomplete)
		*/ 
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
				return "Ship out of bounds!";
			} else if (collision == true) {
				return "Ship collision detected!";
			}
		}
		return "Ship spawn successful!";
	}

	public static boolean gameStarted() {
		return gameStarted;
	}

	public static void startGame(boolean command) {
		gameStarted = command;
	}

	public String randomSpawn(char[][] location, int shipSize, char shipChar) {
		int vAxis = rand.nextInt(10);
		int hAxis = rand.nextInt(10);
		int direction = rand.nextInt(2);
		int checker_vAxis = vAxis;
		int checker_hAxis = hAxis;
		boolean clearance = false;
		String result;

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
		if (clearance == true) {
			result = vAxis + "" + hAxis + "" + direction;
		}
		else {
			result = vAxis + "" + hAxis + "" + 2;
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
		return result;
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
//			System.out.println("My ship was hit!");
			setScore(getScore(), hit);
			nextTurn();
			return hit;
		} else if (location[vAxis][hAxis] == 'g') {
			location[vAxis][hAxis] = miss;
//			System.out.println("You missed!");
			setScore(getScore(), miss);
			nextTurn();
			return miss;
		} else if (location[vAxis][hAxis] == hit || location[vAxis][hAxis] == miss) {
				cpuBrainz.countFails(1);
			return 0;
		}
		return 0;

	}
}

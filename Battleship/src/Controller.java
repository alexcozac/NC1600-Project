import java.util.Random;

public class Controller {

	private static char hit = 'h';
	private static char miss = 'm';
	private static int score = 0;
	private static String destroyed;
	private static boolean turn = true;
	private static Random rand = new Random();
	private static boolean collision;
	private static boolean offbounds;
	
	public static boolean turn() {
		return turn;
	}
	
	public static int score() {
		return score;
	}
	
	public static void spawnShip(char[][] location, int vAxis, int hAxis, int direction, int shipSize, char shipChar) {	
		
		//checker makes sure the coast is clear before starting to spawn the ship (if not in use, ship will start spawning and stop on collision or out of bounds, leaving part of the ship incomplete)
		collision = false;
		offbounds = true;
		int checker_vAxis = vAxis;
		int checker_hAxis = hAxis;
		
		
		if (direction == 0 && vAxis + shipSize <= location.length) {
			offbounds = false;
		}
		else if (direction == 1 && hAxis + shipSize <= location.length) {
			offbounds = false;
		}

		
		for (int i = 0; i < shipSize; i++) {
			if (location[checker_vAxis][checker_hAxis] == 'g') {
				if (direction == 0 && offbounds == false) {
					checker_vAxis++;
				}
				else if (direction == 1 && offbounds == false) {
					checker_hAxis++;
				}
			}
			else {
				collision = true;
			}
		}
		
		//first checks collision then checks bounds then spawns
			for (int i = 0; i < shipSize; i++) {
				if (offbounds == false && collision == false) {
					if (direction == 0) {
						location[vAxis][hAxis] = shipChar;
						vAxis++;
					}
					else if (direction == 1) {
						location[vAxis][hAxis] = shipChar;
						hAxis++;
						}
				}
				else if (offbounds == true) {
					System.out.println("Ship out of bounds!");
					break;
				}
				else if (collision == true) {
					System.out.println("Ship collision detected!");
					break;
				}
			}
			
	}
	
	
	public static void randomSpawn(char[][] location, int shipSize, char shipChar) { 
		int vAxis = rand.nextInt(10);
		int hAxis = rand.nextInt(10);
		int direction = rand.nextInt(2);
		int checker_vAxis = vAxis;
		int checker_hAxis = hAxis;
		boolean clearance = false;
		
		//Depending on direction, checks if there are no collisions or out of bound spawns and gives clearance.
		while (clearance == false) {
			for (int i = 0; i < shipSize; i++) {
				switch (direction) {
				case 0:
					if (location[checker_vAxis][checker_hAxis] == 'g' && vAxis + shipSize <= location.length) {
						checker_vAxis++;
						clearance = true;
						break;
					}
					else {
						vAxis = rand.nextInt(10);
						hAxis = rand.nextInt(10);
						checker_vAxis = vAxis;
						checker_hAxis = hAxis;
						i=i-1;
						clearance = false;
						break;
					}
				case 1:
					if (location[checker_vAxis][checker_hAxis] == 'g' && hAxis + shipSize <= location.length) {
						checker_hAxis++;
						clearance = true;
						break;
					}
					else {
						vAxis = rand.nextInt(10);
						hAxis = rand.nextInt(10);
						checker_vAxis = vAxis;
						checker_hAxis = hAxis;
						i=i-1;
						clearance = false;
						break;
					}
				}
			}
		}
		
		//spawns the ship
		for (int i = 0; i < shipSize; i++) {
			if (clearance = true && direction == 0) {
				location[vAxis][hAxis] = shipChar;
				vAxis++;
			}
			else if (clearance = true && direction == 1) {
				location[vAxis][hAxis] = shipChar;
				hAxis++;
			}
		}
	}
	
	
	public static void shoot(char[][] location, int vAxis, int hAxis) {
		if (location[vAxis][hAxis] == 'a' ||
				location[vAxis][hAxis] == 'b' ||
				location[vAxis][hAxis] == 's' ||
				location[vAxis][hAxis] == 'd' ||
				location[vAxis][hAxis] == 'p') {
			location[vAxis][hAxis] = hit;
			score += 1;
			if (turn == true) {
				turn = false;
			}
			else {
				turn = true;
			}
		}
		else if (location[vAxis][hAxis] == 'g') {
			location[vAxis][hAxis] = miss;
			if (score > 0) {
			score -= 1;
			}
			if (turn == true) {
				turn = false;
			}
			else {
				turn = true;
			}
		}
		else if (location[vAxis][hAxis] == 'h' || location[vAxis][hAxis] == 'm') {
			System.out.println("You have already shot at these coordinates!");
		}
		
	}
}

	
	
	
	
	
	
	
	
	


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleRunClass {

	public static void main(String[] args) throws InterruptedException {

		boolean stop = false;
		Scanner myObj = new Scanner(System.in);
		String command;
		
		while (stop == false) {

			
			Grid uGrid = new Grid();
			Grid cpuGrid = new Grid();

			Controller uPlayer = new Controller();
			Controller cpuPlayer = new Controller();

			Items uRadar = new Items(4);
			Items cpuRadar = new Items(4);

			Ship uAircraftC = new Ship("Aircraft Carrier", 5, 'a');
			Ship uBattleship = new Ship("Battleship", 4, 'b');
			Ship uSubmarine = new Ship("Submarine", 3, 's');
			Ship uDestroyer = new Ship("Destroyer", 3, 'd');
			Ship uPatrol = new Ship("Patrol Boat", 2, 'p');

			Ship cpuAircraftC = new Ship("Aircraft Carrier", 5, 'a');
			Ship cpuBattleship = new Ship("Battleship", 4, 'b');
			Ship cpuSubmarine = new Ship("Submarine", 3, 's');
			Ship cpuDestroyer = new Ship("Destroyer", 3, 'd');
			Ship cpuPatrol = new Ship("Patrol Boat", 2, 'p');

			/*
			Controller.randomSpawn(uGrid.getGrid(), uAircraftC.getShipSize(), uAircraftC.getShipChar());
			Controller.randomSpawn(uGrid.getGrid(), uBattleship.getShipSize(), uBattleship.getShipChar());
			Controller.randomSpawn(uGrid.getGrid(), uSubmarine.getShipSize(), uSubmarine.getShipChar());
			Controller.randomSpawn(uGrid.getGrid(), uDestroyer.getShipSize(), uDestroyer.getShipChar());
			Controller.randomSpawn(uGrid.getGrid(), uPatrol.getShipSize(), uPatrol.getShipChar());
			*/

			Controller.randomSpawn(cpuGrid.getGrid(), cpuAircraftC.getShipSize(), cpuAircraftC.getShipChar());
			Controller.randomSpawn(cpuGrid.getGrid(), cpuBattleship.getShipSize(), cpuBattleship.getShipChar());
			Controller.randomSpawn(cpuGrid.getGrid(), cpuSubmarine.getShipSize(), cpuSubmarine.getShipChar());
			Controller.randomSpawn(cpuGrid.getGrid(), cpuDestroyer.getShipSize(), cpuDestroyer.getShipChar());
			Controller.randomSpawn(cpuGrid.getGrid(), cpuPatrol.getShipSize(), cpuPatrol.getShipChar());
	

			while (true) {
				if (Controller.userTurn() == true) {
					System.out.println("Would you like to automaticly spawn your ships? y/n");
					command = myObj.nextLine();
					if (command.equalsIgnoreCase("y")){
						Controller.randomSpawn(uGrid.getGrid(), uAircraftC.getShipSize(), uAircraftC.getShipChar());
						Controller.randomSpawn(uGrid.getGrid(), uBattleship.getShipSize(), uBattleship.getShipChar());
						Controller.randomSpawn(uGrid.getGrid(), uSubmarine.getShipSize(), uSubmarine.getShipChar());
						Controller.randomSpawn(uGrid.getGrid(), uDestroyer.getShipSize(), uDestroyer.getShipChar());
						Controller.randomSpawn(uGrid.getGrid(), uPatrol.getShipSize(), uPatrol.getShipChar());
						Controller.startGame(true);
					}
					
					while(Controller.gameStarted() == false) {
						if (uAircraftC.getShipHP(uGrid.getGrid()) == 0) {
							System.out.println("Call in the " + uAircraftC.getShipName());
							System.out.println("Enter vertical coordinates: ");
							int vAxis = myObj.nextInt();
							System.out.println("Enter horizontal coordinates: ");		
							int hAxis = myObj.nextInt();
							System.out.println("Enter direction: (0) Vertical, (1) Horizontal");
							int direction = myObj.nextInt();
							Controller.spawnShip(uGrid.getGrid(), vAxis, hAxis, direction, uAircraftC.getShipSize(), uAircraftC.getShipChar());
							uGrid.printGrid(false);
						}
						
						if (uBattleship.getShipHP(uGrid.getGrid()) == 0) {
							System.out.println("Call in the " + uBattleship.getShipName());
							System.out.println("Enter vertical coordinates: ");
							int vAxis = myObj.nextInt();
							System.out.println("Enter horizontal coordinates: ");		
							int hAxis = myObj.nextInt();
							System.out.println("Enter direction: (0) Vertical, (1) Horizontal");
							int direction = myObj.nextInt();
							Controller.spawnShip(uGrid.getGrid(), vAxis, hAxis, direction, uBattleship.getShipSize(), uBattleship.getShipChar());
							uGrid.printGrid(false);
						}
						
						if (uSubmarine.getShipHP(uGrid.getGrid()) == 0) {
							System.out.println("Call in the " + uSubmarine.getShipName());
							System.out.println("Enter vertical coordinates: ");
							int vAxis = myObj.nextInt();
							System.out.println("Enter horizontal coordinates: ");		
							int hAxis = myObj.nextInt();
							System.out.println("Enter direction: (0) Vertical, (1) Horizontal");
							int direction = myObj.nextInt();
							Controller.spawnShip(uGrid.getGrid(), vAxis, hAxis, direction, uSubmarine.getShipSize(), uSubmarine.getShipChar());
							uGrid.printGrid(false);
						}
						
						if (uDestroyer.getShipHP(uGrid.getGrid()) == 0) {
							System.out.println("Call in the " + uDestroyer.getShipName());
							System.out.println("Enter vertical coordinates: ");
							int vAxis = myObj.nextInt();
							System.out.println("Enter horizontal coordinates: ");		
							int hAxis = myObj.nextInt();
							System.out.println("Enter direction: (0) Vertical, (1) Horizontal");
							int direction = myObj.nextInt();
							Controller.spawnShip(uGrid.getGrid(), vAxis, hAxis, direction, uDestroyer.getShipSize(), uDestroyer.getShipChar());
							uGrid.printGrid(false);
						}
						
						if (uPatrol.getShipHP(uGrid.getGrid()) == 0) {
							System.out.println("Call in the " + uPatrol.getShipName());
							System.out.println("Enter vertical coordinates: ");
							int vAxis = myObj.nextInt();
							System.out.println("Enter horizontal coordinates: ");		
							int hAxis = myObj.nextInt();
							System.out.println("Enter direction: (0) Vertical, (1) Horizontal");
							int direction = myObj.nextInt();
							Controller.spawnShip(uGrid.getGrid(), vAxis, hAxis, direction, uPatrol.getShipSize(), uPatrol.getShipChar());
							uGrid.printGrid(false);
						}
						
						if (uAircraftC.getShipHP(uGrid.getGrid()) == uAircraftC.getShipSize() && uBattleship.getShipHP(uGrid.getGrid()) == uBattleship.getShipSize()
								&& uSubmarine.getShipHP(uGrid.getGrid()) == uSubmarine.getShipSize() && uDestroyer.getShipHP(uGrid.getGrid()) == uDestroyer.getShipSize()
								&& uPatrol.getShipHP(uGrid.getGrid()) == uPatrol.getShipSize()) {
							Controller.startGame(true);
							System.out.println("Battle started!");
						}
					}
					
					cpuGrid.printGrid(true);
					command = myObj.nextLine();
					
					
					System.out.print("Enter command shoot/scan/quit: ");
					command = myObj.nextLine();

					if (command.equalsIgnoreCase("quit")) {
						System.out.println("Your score is: " + uPlayer.getScore());
						System.out.println("CPU Score is: " + cpuPlayer.getScore());
						System.out.println("Do you want to play again? y/n");
						command = myObj.nextLine();
						if (command.equalsIgnoreCase("y")) {
							Controller.startGame(false);
							break;
						}
						if (command.equalsIgnoreCase("n")) {
							stop = true;
							break;
						}
					}
					System.out.print("Enter vertical coordinates: ");
					int vAxis = myObj.nextInt();
					System.out.print("Enter horizontal coordinates: ");
					int hAxis = myObj.nextInt();

					switch (command) {
					case "shoot":
						uPlayer.shoot(cpuGrid.getGrid(), vAxis, hAxis);
						break;

					case "scan":
						uRadar.scan(cpuGrid.getGrid(), vAxis, hAxis);
						break;

					}

					cpuGrid.printGrid(true);
					Thread.sleep(2000);

					System.out.println(uAircraftC.getShipName() + " HP = " + uAircraftC.getShipHP(uGrid.getGrid()));
					System.out.println(uBattleship.getShipName() + " HP = " + uBattleship.getShipHP(uGrid.getGrid()));
					System.out.println(uSubmarine.getShipName() + " HP = " + uSubmarine.getShipHP(uGrid.getGrid()));
					System.out.println(uDestroyer.getShipName() + " HP = " + uDestroyer.getShipHP(uGrid.getGrid()));
					System.out.println(uPatrol.getShipName() + " HP = " + uPatrol.getShipHP(uGrid.getGrid()));
					
					if (cpuAircraftC.getShipHP(cpuGrid.getGrid()) == 0 && cpuBattleship.getShipHP(cpuGrid.getGrid()) == 0
							&& cpuSubmarine.getShipHP(cpuGrid.getGrid()) == 0 && cpuDestroyer.getShipHP(cpuGrid.getGrid()) == 0
							&& cpuPatrol.getShipHP(cpuGrid.getGrid()) == 0) {
						System.out.println("You Won!\nThe score is " + uPlayer.getScore() + " / " + cpuPlayer.getScore());
						System.out.println("Do you want to play again? y/n");
						command = myObj.nextLine();
						if (command.equalsIgnoreCase("y")) {
							Controller.startGame(false);
							break;
						}
						if (command.equalsIgnoreCase("n")) {
							stop = true;
							break;
						}
					}
					
					
					System.out.println("-------------");
					System.out.println("User Radar Ammo = " + uRadar.getRadarAmmo());
					System.out.println("CP Radar Ammo = " + cpuRadar.getRadarAmmo());
					System.out.println("-------------");
					Thread.sleep(1500);

					// Temporary Score bonus fix
					if (cpuAircraftC.isShipDed() == true) {
						uPlayer.setScore(uPlayer.getScore() + cpuAircraftC.getShipSize() * 2);
					}
					if (cpuBattleship.isShipDed() == true) {
						uPlayer.setScore(uPlayer.getScore() + cpuBattleship.getShipSize() * 2);
					}
					if (cpuSubmarine.isShipDed() == true) {
						uPlayer.setScore(uPlayer.getScore() + cpuSubmarine.getShipSize() * 2);
					}
					if (cpuDestroyer.isShipDed() == true) {
						uPlayer.setScore(uPlayer.getScore() + cpuDestroyer.getShipSize() * 2);
					}
					if (cpuPatrol.isShipDed() == true) {
						uPlayer.setScore(uPlayer.getScore() + cpuPatrol.getShipSize() * 2);
					}

					

					System.out.println("CPU Score is " + cpuPlayer.getScore());
					System.out.println("User Score is " + uPlayer.getScore());
					System.out.println("-------------");
					Thread.sleep(1500);
				}

				else if (Controller.userTurn() == false) {
					// Thread.sleep(500);
					System.out.println("CPU's Turn!");
					if (cpuBrainz.getState() == 0) {
						
						cpuBrainz.stateSwitch(cpuBrainz.getState(), cpuBrainz.initial_vCoordinates(),
								cpuBrainz.initial_hCoordinates(),
								cpuPlayer.shoot(uGrid.getGrid(), cpuBrainz.vCoordinates(), cpuBrainz.hCoordinates()));
								

						// prototype run
						/*
						  cpuBrainz.stateSwitch(cpuBrainz.getState(), 4, 5,
						  cpuPlayer.shoot(uGrid.getGrid(), cpuBrainz.vCoordinates(),
						 cpuBrainz.hCoordinates()));
						 
						System.out.println("cpuBrainz state is: " + cpuBrainz.getState());
						 */
						

					}

					else {
						cpuBrainz.stateSwitch(cpuBrainz.getState(), cpuBrainz.vCoordinates(), cpuBrainz.hCoordinates(),
								cpuPlayer.shoot(uGrid.getGrid(), cpuBrainz.vCoordinates(), cpuBrainz.hCoordinates()));
						System.out.println("cpuBrainz state is: " + cpuBrainz.getState());
					}
					System.out.println(cpuBrainz.getState());
					uGrid.printGrid(false);
					System.out.println("-------------");
					
					
					uAircraftC.getShipHP(uGrid.getGrid());
					uBattleship.getShipHP(uGrid.getGrid());
					uSubmarine.getShipHP(uGrid.getGrid());
					uDestroyer.getShipHP(uGrid.getGrid());
					uPatrol.getShipHP(uGrid.getGrid());
					
					if (uAircraftC.getShipHP(uGrid.getGrid()) == 0 && uBattleship.getShipHP(uGrid.getGrid()) == 0
							&& uSubmarine.getShipHP(uGrid.getGrid()) == 0 && uDestroyer.getShipHP(uGrid.getGrid()) == 0
							&& uPatrol.getShipHP(uGrid.getGrid()) == 0) {
						System.out.println("You Lost!\nThe score is " + cpuPlayer.getScore() + " / " + uPlayer.getScore());
						System.out.println("Do you want to play again? y/n");
						
						command = myObj.nextLine();
						if (command.equalsIgnoreCase("y")) {
							Controller.startGame(false);
							break;
						}
						if (command.equalsIgnoreCase("n")) {
							stop = true;
							break;
						}
					}
					
					// Reset state upon destroying a ship and add score bonus
					if (uAircraftC.isShipDed() == true) {
						cpuPlayer.setScore(cpuPlayer.getScore() + uAircraftC.getShipSize() * 2);
						cpuBrainz.setState(0);
					}
					if (uBattleship.isShipDed() == true) {
						cpuPlayer.setScore(cpuPlayer.getScore() + uBattleship.getShipSize() * 2);
						cpuBrainz.setState(0);
					}
					if (uSubmarine.isShipDed() == true) {
						cpuPlayer.setScore(cpuPlayer.getScore() + uSubmarine.getShipSize() * 2);
						cpuBrainz.setState(0);
					}
					if (uDestroyer.isShipDed() == true) {
						cpuPlayer.setScore(cpuPlayer.getScore() + uDestroyer.getShipSize() * 2);
						cpuBrainz.setState(0);
					}
					if (uPatrol.isShipDed() == true) {
						cpuPlayer.setScore(cpuPlayer.getScore() + uPatrol.getShipSize() * 2);
						cpuBrainz.setState(0);
					}
				}
			}
		}
	}
}

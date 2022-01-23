import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws InterruptedException {

		boolean stop = false;

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

			Controller.randomSpawn(cpuGrid.getGrid(), cpuAircraftC.getShipSize(), cpuAircraftC.getShipChar());
			Controller.randomSpawn(cpuGrid.getGrid(), cpuBattleship.getShipSize(), cpuBattleship.getShipChar());
			Controller.randomSpawn(cpuGrid.getGrid(), cpuSubmarine.getShipSize(), cpuSubmarine.getShipChar());
			Controller.randomSpawn(cpuGrid.getGrid(), cpuDestroyer.getShipSize(), cpuDestroyer.getShipChar());
			Controller.randomSpawn(cpuGrid.getGrid(), cpuPatrol.getShipSize(), cpuPatrol.getShipChar());
			*/

			 Controller.spawnShip(uGrid.getGrid(), 4, 0, 0, uAircraftC.getShipSize(),uAircraftC.getShipChar());

			while (true) {
				if (stop == true) { // Controller.turn() == true
					Scanner myObj = new Scanner(System.in);
					System.out.print("Enter command and coordinates (v)(h): ");
					String command = myObj.nextLine();

					if (command.equalsIgnoreCase("quit")) {
						System.out.println("Your score is: " + uPlayer.getScore());
						System.out.println("CPU Score is: " + cpuPlayer.getScore());
						System.out.println("Do you want to play again? y/n");
						command = myObj.nextLine();
						if (command.equalsIgnoreCase("y")) {
							break;
						}
						if (command.equalsIgnoreCase("n")) {
							stop = true;
							break;
						}
					}

					int vAxis = myObj.nextInt();
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

					System.out
							.println(cpuAircraftC.getShipName() + " HP = " + cpuAircraftC.getShipHP(cpuGrid.getGrid()));
					System.out.println(
							cpuBattleship.getShipName() + " HP = " + cpuBattleship.getShipHP(cpuGrid.getGrid()));
					System.out
							.println(cpuSubmarine.getShipName() + " HP = " + cpuSubmarine.getShipHP(cpuGrid.getGrid()));
					System.out
							.println(cpuDestroyer.getShipName() + " HP = " + cpuDestroyer.getShipHP(cpuGrid.getGrid()));
					System.out.println(cpuPatrol.getShipName() + " HP = " + cpuPatrol.getShipHP(cpuGrid.getGrid()));
					System.out.println("-------------");
					System.out.println("Radar ammo = " + uRadar.getRadarAmmo());
					System.out.println("Radar ammo = " + cpuRadar.getRadarAmmo());
					System.out.println("-------------");

					// Temporary Score bonus fix
					if (cpuAircraftC.getShipStatus() == true) {
						uPlayer.score += (cpuAircraftC.getShipSize() * 2);
					}
					if (cpuBattleship.getShipStatus() == true) {
						uPlayer.score += (cpuBattleship.getShipSize() * 2);
					}
					if (cpuSubmarine.getShipStatus() == true) {
						uPlayer.score += (cpuSubmarine.getShipSize() * 2);
					}
					if (cpuDestroyer.getShipStatus() == true) {
						uPlayer.score += (cpuDestroyer.getShipSize() * 2);
					}
					if (cpuPatrol.getShipStatus() == true) {
						uPlayer.score += (cpuPatrol.getShipSize() * 2);
					}

					// Reset state upon destroying a ship
					if (uAircraftC.getShipStatus() == true) {
						cpuPlayer.score += (uAircraftC.getShipSize() * 2);
						cpuBrainz.setState(0);
					}
					if (uBattleship.getShipStatus() == true) {
						cpuPlayer.score += (uBattleship.getShipSize() * 2);
						cpuBrainz.setState(0);
					}
					if (uSubmarine.getShipStatus() == true) {
						cpuPlayer.score += (uSubmarine.getShipSize() * 2);
						cpuBrainz.setState(0);
					}
					if (uDestroyer.getShipStatus() == true) {
						cpuPlayer.score += (uDestroyer.getShipSize() * 2);
						cpuBrainz.setState(0);
					}
					if (uPatrol.getShipStatus() == true) {
						cpuPlayer.score += (uPatrol.getShipSize() * 2);
						cpuBrainz.setState(0);
					}

					System.out.println("CPU Score is " + cpuPlayer.getScore());
					System.out.println("User Score is " + uPlayer.getScore());
					System.out.println("-------------");
				}

				else if (true) { // Controller.turn() == false
					Thread.sleep(1000);
					System.out.println("CPU's Turn!");
					System.out.println("previous" + cpuBrainz.vCoordinates() + cpuBrainz.hCoordinates());
					if (cpuBrainz.getState() == 0) {
						/*
						cpuBrainz.stateSwitch(cpuBrainz.getState(), cpuBrainz.initial_vCoordinates(),
								cpuBrainz.initial_hCoordinates(),
								cpuPlayer.shoot(uGrid.getGrid(), cpuBrainz.vCoordinates(), cpuBrainz.hCoordinates()));
								*/

						// prototype run
						  cpuBrainz.stateSwitch(cpuBrainz.getState(), 7, 0,
						  cpuPlayer.shoot(uGrid.getGrid(), cpuBrainz.vCoordinates(),
						 cpuBrainz.hCoordinates()));
						 
						System.out.println(cpuBrainz.getState());

					}

					else {
						cpuBrainz.stateSwitch(cpuBrainz.getState(), cpuBrainz.vCoordinates(), cpuBrainz.hCoordinates(),
								cpuPlayer.shoot(uGrid.getGrid(), cpuBrainz.vCoordinates(), cpuBrainz.hCoordinates()));
						System.out.println(cpuBrainz.getState());
					}

					uGrid.printGrid(false);
					System.out.println("------------------");

				}

			}

		}

	}
}

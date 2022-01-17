import java.util.Scanner;

public class Test  {

	public static void main(String[] args) {

		Grid uGrid = new Grid("user");
		Grid cpuGrid = new Grid("cpu");
		
		Controller uPlayer = new Controller();
		Controller cpuPlayer = new Controller();
		
		Items uRadar = new Items(2);
		Items cpuRadar = new Items(2);

		
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
		
		//figure out how to set score
		Controller.spawnShip(cpuGrid.getGrid(), 5, 0, 0, cpuAircraftC.getShipSize(), cpuAircraftC.getShipChar());
		/*
		Controller.randomSpawn(cpuGrid.getGrid(), cpuBattleship.getShipSize(), cpuBattleship.getShipChar());
		Controller.randomSpawn(cpuGrid.getGrid(), cpuSubmarine.getShipSize(), cpuSubmarine.getShipChar());
		Controller.randomSpawn(cpuGrid.getGrid(), cpuDestroyer.getShipSize(), cpuDestroyer.getShipChar());
		Controller.randomSpawn(cpuGrid.getGrid(), cpuPatrol.getShipSize(), cpuPatrol.getShipChar());
		*/
		
		
		while(true) {
		Scanner myObj = new Scanner(System.in);
		System.out.print("Enter command and coordinates (v)(h): ");
		String command = myObj.nextLine();
		int vAxis = myObj.nextInt();
		int hAxis = myObj.nextInt();
		
		switch(command) {
		case "shoot":
		uPlayer.shoot(cpuGrid.getGrid(), vAxis, hAxis);
			break;
			
		case "scan":
			uRadar.scan(cpuGrid.getGrid(), vAxis, hAxis);
			break;
		}
		
		
		
		
		cpuGrid.printGrid();
			
		System.out.println("\nCPU Score is " + cpuPlayer.getScore());
		System.out.println("User Score is " + uPlayer.getScore());

		
		System.out.println(cpuAircraftC.getShipName() + " HP = " + cpuAircraftC.getShipHP(cpuGrid.getGrid()));
		System.out.println(cpuBattleship.getShipName() + " HP = " + cpuBattleship.getShipHP(cpuGrid.getGrid()));
		System.out.println(cpuSubmarine.getShipName() + " HP = " + cpuSubmarine.getShipHP(cpuGrid.getGrid()));
		System.out.println(cpuDestroyer.getShipName() + " HP = " + cpuDestroyer.getShipHP(cpuGrid.getGrid()));
		System.out.println(cpuPatrol.getShipName() + " HP = " + cpuPatrol.getShipHP(cpuGrid.getGrid()));
		System.out.println("Radar ammo = " + uRadar.getRadarAmmo());
		System.out.println("Radar ammo = " + cpuRadar.getRadarAmmo());
		}
	}
}

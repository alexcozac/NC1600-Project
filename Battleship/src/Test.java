public class Test  {

	public static void main(String[] args) {

		Grid uGrid = new Grid("user");
		Grid cpuGrid = new Grid("cpu");

		
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
		
		
		Controller.randomSpawn(uGrid.getGrid(), uAircraftC.getShipSize(), uAircraftC.getShipChar());
		Controller.randomSpawn(uGrid.getGrid(), uBattleship.getShipSize(), uBattleship.getShipChar());
		Controller.randomSpawn(uGrid.getGrid(), uSubmarine.getShipSize(), uSubmarine.getShipChar());
		Controller.randomSpawn(uGrid.getGrid(), uDestroyer.getShipSize(), uDestroyer.getShipChar());
		Controller.randomSpawn(uGrid.getGrid(), uPatrol.getShipSize(), uPatrol.getShipChar());

		
		Controller.shoot(uGrid.getGrid(), 5, 0);
		Controller.shoot(uGrid.getGrid(), 6, 0);
		
		uGrid.printGrid();
			
		System.out.println("\nScore is " + Controller.score());
		
		System.out.println(uAircraftC.getShipName() + " HP = " + uAircraftC.getShipHP(uGrid.getGrid()));
		System.out.println(uBattleship.getShipName() + " HP = " + uBattleship.getShipHP(uGrid.getGrid()));
		System.out.println(uSubmarine.getShipName() + " HP = " + uSubmarine.getShipHP(uGrid.getGrid()));
		System.out.println(uDestroyer.getShipName() + " HP = " + uDestroyer.getShipHP(uGrid.getGrid()));
		System.out.println(uPatrol.getShipName() + " HP = " + uPatrol.getShipHP(uGrid.getGrid()));
		
		
	}

}

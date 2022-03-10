package application;

public class BattleshipLogic {

	Grid uGrid = new Grid();
	Grid cpuGrid = new Grid();

	Controller uPlayer = new Controller();
	Controller cpuPlayer = new Controller();

	Radar uRadar = new Radar(4);

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
	
	
}

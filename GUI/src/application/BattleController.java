package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class BattleController extends SpawnController implements Initializable, EventHandler {

	@FXML
	AnchorPane battlescreen = new AnchorPane();
	@FXML
	MediaView battleBG = new MediaView();
	@FXML
	ImageView battleFrame = new ImageView();

	@FXML
	Group Grid = new Group();
	@FXML
	AnchorPane SmallGrid = new AnchorPane();

	@FXML
	ImageView shootBtn = new ImageView();
	@FXML
	ImageView scanBtn = new ImageView();
	@FXML
	ImageView backBtn = new ImageView();

	@FXML
	ImageView aircraftHPbar = new ImageView();
	@FXML
	ImageView battleshipHPbar = new ImageView();
	@FXML
	ImageView submarineHPbar = new ImageView();
	@FXML
	ImageView destroyerHPbar = new ImageView();
	@FXML
	ImageView patrolHPbar = new ImageView();

	@FXML
	ImageView vAircraftSmall = new ImageView();
	@FXML
	ImageView hAircraftSmall = new ImageView();
	@FXML
	ImageView vBattleshipSmall = new ImageView();
	@FXML
	ImageView hBattleshipSmall = new ImageView();
	@FXML
	ImageView vSubmarineSmall = new ImageView();
	@FXML
	ImageView hSubmarineSmall = new ImageView();
	@FXML
	ImageView vDestroyerSmall = new ImageView();
	@FXML
	ImageView hDestroyerSmall = new ImageView();
	@FXML
	ImageView vPatrolSmall = new ImageView();
	@FXML
	ImageView hPatrolSmall = new ImageView();

	static int vAircraftIndex = 100, hAircraftIndex = 100, vBattleshipIndex = 100, hBattleshipIndex = 100,
			vSubmarineIndex = 100, hSubmarineIndex = 100, vDestroyerIndex = 100, hDestroyerIndex = 100,
			vPatrolIndex = 100, hPatrolIndex = 100;

	Image HP5, HP5_1, HP5_2, HP5_3, HP5_4, HP5Destroyed;
	Image HP4, HP4_1, HP4_2, HP4_3, HP4Destroyed;
	Image HP3, HP3_1, HP3_2, HP3Destroyed;
	Image HP2, HP2_1, HP2Destroyed;

	Image hitSquare, missSquare, greenSquare;
	ImageView tempsource = new ImageView();

	Image scan, scanClick, scanHover;
	Image shoot, shootClick, shootHover;

	boolean shootselected = true;
	boolean scanselected = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// spawn Enemy Ships
		battleshipLogic.cpuPlayer.randomSpawn(battleshipLogic.cpuGrid.getGrid(),
				battleshipLogic.cpuAircraftC.getShipSize(), battleshipLogic.cpuAircraftC.getShipChar());
		battleshipLogic.cpuPlayer.randomSpawn(battleshipLogic.cpuGrid.getGrid(),
				battleshipLogic.cpuBattleship.getShipSize(), battleshipLogic.cpuBattleship.getShipChar());
		battleshipLogic.cpuPlayer.randomSpawn(battleshipLogic.cpuGrid.getGrid(),
				battleshipLogic.cpuSubmarine.getShipSize(), battleshipLogic.cpuSubmarine.getShipChar());
		battleshipLogic.cpuPlayer.randomSpawn(battleshipLogic.cpuGrid.getGrid(),
				battleshipLogic.cpuDestroyer.getShipSize(), battleshipLogic.cpuDestroyer.getShipChar());
		battleshipLogic.cpuPlayer.randomSpawn(battleshipLogic.cpuGrid.getGrid(),
				battleshipLogic.cpuPatrol.getShipSize(), battleshipLogic.cpuPatrol.getShipChar());

		// Ships
		vAircraftSmall.setImage(aircraft);
		hAircraftSmall.setImage(aircrafth);

		vBattleshipSmall.setImage(battleship);
		hBattleshipSmall.setImage(battleshiph);

		vSubmarineSmall.setImage(submarine);
		hSubmarineSmall.setImage(submarineh);

		vDestroyerSmall.setImage(destroyer);
		hDestroyerSmall.setImage(destroyerh);

		vPatrolSmall.setImage(patrol);
		hPatrolSmall.setImage(patrolh);

		vAircraftSmall.setVisible(false);
		hAircraftSmall.setVisible(false);
		vBattleshipSmall.setVisible(false);
		hBattleshipSmall.setVisible(false);
		vSubmarineSmall.setVisible(false);
		hSubmarineSmall.setVisible(false);
		vDestroyerSmall.setVisible(false);
		hDestroyerSmall.setVisible(false);
		vPatrolSmall.setVisible(false);
		hPatrolSmall.setVisible(false);

		battleBG.setMediaPlayer(gameBG);
		gameBG.setAutoPlay(true);
		gameBG.setOnEndOfMedia(new Runnable() {
			public void run() {
				gameBG.seek(Duration.ZERO);
			}
		});
		// Enemy Grid
		for (int i = 0; i < Grid.getChildren().size(); i++) {
			ImageView temp = (ImageView) Grid.getChildren().get(i);
			temp.setImage(gridsquare);
		}
		// User Grid
		for (int i = 0; i < SmallGrid.getChildren().size(); i++) {
			ImageView temp = (ImageView) SmallGrid.getChildren().get(i);
			temp.setImage(gridsquare);
		}
		Grid.setVisible(true);

		shoot = new Image(path + "\\Battle\\Shoot.png");
		shootHover = new Image(path + "\\Battle\\ShootHover.png");
		shootClick = new Image(path + "\\Battle\\ShootClick.png");

		hitSquare = new Image(path + "\\Battle\\HitSquare.png");
		missSquare = new Image(path + "\\Battle\\MissSquare.png");
		greenSquare = new Image(path + "\\Battle\\GreenSquare.png");

		scan = new Image(path + "\\Battle\\Scan.png");
		scanHover = new Image(path + "\\Battle\\ScanHover.png");
		scanClick = new Image(path + "\\Battle\\ScanClick.png");

		shootBtn.setImage(shootClick);
		scanBtn.setImage(scan);

		HP5 = new Image(path + "\\Battle\\5HP.png");
		HP5_1 = new Image(path + "\\Battle\\5HP-1.png");
		HP5_2 = new Image(path + "\\Battle\\5HP-2.png");
		HP5_3 = new Image(path + "\\Battle\\5HP-3.png");
		HP5_4 = new Image(path + "\\Battle\\5HP-4.png");
		HP5Destroyed = new Image(path + "\\Battle\\5HPDestroyed.png");

		HP4 = new Image(path + "\\Battle\\4HP.png");
		HP4_1 = new Image(path + "\\Battle\\4HP-1.png");
		HP4_2 = new Image(path + "\\Battle\\4HP-2.png");
		HP4_3 = new Image(path + "\\Battle\\4HP-3.png");
		HP4Destroyed = new Image(path + "\\Battle\\4HPDestroyed.png");

		HP3 = new Image(path + "\\Battle\\3HP.png");
		HP3_1 = new Image(path + "\\Battle\\3HP-1.png");
		HP3_2 = new Image(path + "\\Battle\\3HP-2.png");
		HP3Destroyed = new Image(path + "\\Battle\\3HPDestroyed.png");

		HP2 = new Image(path + "\\Battle\\2HP.png");
		HP2_1 = new Image(path + "\\Battle\\2HP-1.png");
		HP2Destroyed = new Image(path + "\\Battle\\2HPDestroyed.png");

		aircraftHPbar.setImage(HP5);
		battleshipHPbar.setImage(HP4);
		submarineHPbar.setImage(HP3);
		destroyerHPbar.setImage(HP3);
		patrolHPbar.setImage(HP2);

		teleportShipsInSmallGrid();
		
		Controller.startGame(true);

	}

	@SuppressWarnings("static-access")
	public void handleGrid(Event event) {
		String type = event.getEventType().toString();
		ImageView source = (ImageView) event.getSource();
		int index = Grid.getChildren().indexOf(source);
		int hAxis = index % 10;
		int vAxis = index / 10;

		switch (type) {

		case "MOUSE_ENTERED":
			if (shootselected) {
				tempsource.setImage(source.getImage());
				source.setImage(selectsquare);

			} else {
				if (hAxis > 0 && hAxis < 9 && vAxis > 0 && vAxis < 9) {
					int center = Grid.getChildren().indexOf(event.getSource());
					int rowSel = -20;

					for (int i = 0; i < 3; i++) {
						rowSel += 10;
						for (int j = 0; j < 3; j++) {
							ImageView temp = (ImageView) Grid.getChildren().get(center + rowSel + j - 1);
							if (temp.isDisable() == false && temp.getImage() != greenSquare) {
								temp.setImage(selectsquare);
							}

						}
					}
				}
			}
			break;

		case "MOUSE_EXITED":
			if (shootselected) {
				if (tempsource.getImage() != greenSquare) {
					source.setImage(gridsquare);
				} else if (tempsource.getImage() == greenSquare) {
					source.setImage(greenSquare);
				}

			} else {
				if (hAxis > 0 && hAxis < 9 && vAxis > 0 && vAxis < 9) {
					int center = Grid.getChildren().indexOf(event.getSource());
					int rowSel = -20;

					for (int i = 0; i < 3; i++) {
						rowSel += 10;
						for (int j = 0; j < 3; j++) {
							ImageView temp = (ImageView) Grid.getChildren().get(center + rowSel + j - 1);
							if (temp.isDisable() == false && temp.getImage() != greenSquare) {
								temp.setImage(gridsquare);
							}
						}
					}
				}
			}
			break;

		case "MOUSE_PRESSED":
			if (shootselected) {
				if (battleshipLogic.uPlayer.shoot(battleshipLogic.cpuGrid.getGrid(), vAxis, hAxis) == 'h') {
					source.setImage(hitSquare);
					source.setOnMousePressed(null);
					source.setOnMouseExited(null);
					source.setOnMouseEntered(null);

				} else {
					source.setImage(missSquare);
					source.setOnMousePressed(null);
					source.setOnMouseExited(null);
					source.setOnMouseEntered(null);
				}
			} else if (scanselected) {
				if (hAxis > 0 && hAxis < 9 && vAxis > 0 && vAxis < 9) {
					if (battleshipLogic.uRadar.scan(battleshipLogic.cpuGrid.getGrid(), vAxis, hAxis)) {
						int center = Grid.getChildren().indexOf(event.getSource());
						int rowSel = -20;

						for (int i = 0; i < 3; i++) {
							rowSel += 10;
							for (int j = 0; j < 3; j++) {
								ImageView temp = (ImageView) Grid.getChildren().get(center + rowSel + j - 1);
								if (temp.isDisable() == false) {
									temp.setImage(greenSquare);
								}
							}
						}
					} else {
						int center = Grid.getChildren().indexOf(event.getSource());
						int rowSel = -20;

						for (int i = 0; i < 3; i++) {
							rowSel += 10;
							for (int j = 0; j < 3; j++) {
								ImageView temp = (ImageView) Grid.getChildren().get(center + rowSel + j - 1);
								if (temp.isDisable() == false) {
									temp.setImage(missSquare);
									temp.setOnMousePressed(null);
									temp.setOnMouseExited(null);
									temp.setOnMouseEntered(null);
									temp.setDisable(true);
								}
							}
						}
					}
				}
			}
			break;

		case "MOUSE_RELEASED":

			// TODO: On mouse release cpu will start his turn

			if (battleshipLogic.uPlayer.userTurn() == false) {

				System.out.println("cpuBrainz state is: " + cpuBrainz.getState());
				if (cpuBrainz.getState() == 0) {
					if (cpuBrainz.stateSwitch(cpuBrainz.getState(), cpuBrainz.initial_vCoordinates(),
							cpuBrainz.initial_hCoordinates(),
							battleshipLogic.cpuPlayer.shoot(battleshipLogic.uGrid.getGrid(), cpuBrainz.vCoordinates(),
									cpuBrainz.hCoordinates())) == 'h') {
						String result = cpuBrainz.getShotvAxis() + "" + cpuBrainz.getShothAxis();
						int resultIndex = Integer.parseInt(result);
						ImageView temp = (ImageView) SmallGrid.getChildren().get(resultIndex);
						temp.setImage(hitSquare);
					} else {
						String result = cpuBrainz.getShotvAxis() + "" + cpuBrainz.getShothAxis();
						int resultIndex = Integer.parseInt(result);
						ImageView temp = (ImageView) SmallGrid.getChildren().get(resultIndex);
						temp.setImage(missSquare);
					}
				}

				else {

					if (cpuBrainz.stateSwitch(cpuBrainz.getState(), cpuBrainz.vCoordinates(), cpuBrainz.hCoordinates(),
							battleshipLogic.cpuPlayer.shoot(battleshipLogic.uGrid.getGrid(), cpuBrainz.vCoordinates(),
									cpuBrainz.hCoordinates())) == 'h') {
						String result = cpuBrainz.getShotvAxis() + "" + cpuBrainz.getShothAxis();
						int resultIndex = Integer.parseInt(result);
						ImageView temp = (ImageView) SmallGrid.getChildren().get(resultIndex);
						temp.setImage(hitSquare);
					} else {
						String result = cpuBrainz.getShotvAxis() + "" + cpuBrainz.getShothAxis();
						int resultIndex = Integer.parseInt(result);
						ImageView temp = (ImageView) SmallGrid.getChildren().get(resultIndex);
						temp.setImage(missSquare);
					}
				}

			}
			if (shootselected == true) {
				source.setDisable(true);
			}
			//TODO: Status Checks
			
			
			
			
			
			
			break;
		}

	}

	@Override
	public void handle(Event event) {
		String type = event.getEventType().toString();
		ImageView source = (ImageView) event.getSource();
		String ID = source.getId();
		switch (type) {

		case "MOUSE_ENTERED":
			switch (ID) {
			case "backBtn":

				break;

			case "shootBtn":
				if (scanselected) {
					shootBtn.setImage(shootHover);
				}
				break;

			case "scanBtn":
				if (shootselected) {
					scanBtn.setImage(scanHover);
				}
				break;

			}

			break;
		case "MOUSE_EXITED":
			switch (ID) {
			case "backBtn":

				break;

			case "shootBtn":
				if (scanselected) {
					shootBtn.setImage(shoot);
				}
				break;

			case "scanBtn":
				if (shootselected) {
					scanBtn.setImage(scan);
				}
				break;

			}
			break;
		case "MOUSE_PRESSED":
			switch (ID) {
			case "backBtn":

				break;

			case "shootBtn":
				if (scanselected) {
					scanselected = false;
					shootselected = true;
					shootBtn.setImage(shootClick);
					scanBtn.setImage(scan);
				}
				break;

			case "scanBtn":
				if (shootselected) {
					shootselected = false;
					scanselected = true;
					scanBtn.setImage(scanClick);
					shootBtn.setImage(shoot);
				}
				break;

			}
			break;
		case "MOUSE_RELEASED":
			switch (ID) {
			case "backBtn":

				break;

			case "shootBtn":

				break;

			case "scanBtn":

				break;
			}
			break;
		}

	}

	public static void sendLayouts(ImageView ship, int index) {
		String name = ship.getId();
		switch (name) {
		case "vAircraft":
			vAircraftIndex = index;
			break;
		case "hAircraft":
			hAircraftIndex = index;
			break;
		case "vBattleship":
			vBattleshipIndex = index;
			break;
		case "hBattleship":
			hBattleshipIndex = index;
			break;
		case "vSubmarine":
			vSubmarineIndex = index;
			break;
		case "hSubmarine":
			hSubmarineIndex = index;
			break;
		case "vDestroyer":
			vDestroyerIndex = index;
			break;
		case "hDestroyer":
			hDestroyerIndex = index;
			break;
		case "vPatrol":
			vPatrolIndex = index;
			break;
		case "hPatrol":
			hPatrolIndex = index;
			break;
		}
	}

	public void teleportShipsInSmallGrid() {
		if (vAircraftIndex != 100) {
			vAircraftSmall
					.setLayoutX(SmallGrid.getChildren().get(vAircraftIndex).getLayoutX() + SmallGrid.getLayoutX());
			vAircraftSmall
					.setLayoutY(SmallGrid.getChildren().get(vAircraftIndex).getLayoutY() + SmallGrid.getLayoutY());
			vAircraftSmall.setVisible(true);
		}
		if (hAircraftIndex != 100) {
			hAircraftSmall
					.setLayoutX(SmallGrid.getChildren().get(hAircraftIndex).getLayoutX() + SmallGrid.getLayoutX());
			hAircraftSmall
					.setLayoutY(SmallGrid.getChildren().get(hAircraftIndex).getLayoutY() + SmallGrid.getLayoutY());
			hAircraftSmall.setVisible(true);
		}
		if (vBattleshipIndex != 100) {
			vBattleshipSmall
					.setLayoutX(SmallGrid.getChildren().get(vBattleshipIndex).getLayoutX() + SmallGrid.getLayoutX());
			vBattleshipSmall
					.setLayoutY(SmallGrid.getChildren().get(vBattleshipIndex).getLayoutY() + SmallGrid.getLayoutY());
			vBattleshipSmall.setVisible(true);
		}
		if (hBattleshipIndex != 100) {
			hBattleshipSmall
					.setLayoutX(SmallGrid.getChildren().get(hBattleshipIndex).getLayoutX() + SmallGrid.getLayoutX());
			hBattleshipSmall
					.setLayoutY(SmallGrid.getChildren().get(hBattleshipIndex).getLayoutY() + SmallGrid.getLayoutY());
			hBattleshipSmall.setVisible(true);
		}
		if (vSubmarineIndex != 100) {
			vSubmarineSmall
					.setLayoutX(SmallGrid.getChildren().get(vSubmarineIndex).getLayoutX() + SmallGrid.getLayoutX());
			vSubmarineSmall
					.setLayoutY(SmallGrid.getChildren().get(vSubmarineIndex).getLayoutY() + SmallGrid.getLayoutY());
			vSubmarineSmall.setVisible(true);
		}
		if (hSubmarineIndex != 100) {
			hSubmarineSmall
					.setLayoutX(SmallGrid.getChildren().get(hSubmarineIndex).getLayoutX() + SmallGrid.getLayoutX());
			hSubmarineSmall
					.setLayoutY(SmallGrid.getChildren().get(hSubmarineIndex).getLayoutY() + SmallGrid.getLayoutY());
			hSubmarineSmall.setVisible(true);
		}
		if (vDestroyerIndex != 100) {
			vDestroyerSmall
					.setLayoutX(SmallGrid.getChildren().get(vDestroyerIndex).getLayoutX() + SmallGrid.getLayoutX());
			vDestroyerSmall
					.setLayoutY(SmallGrid.getChildren().get(vDestroyerIndex).getLayoutY() + SmallGrid.getLayoutY());
			vDestroyerSmall.setVisible(true);
		}
		if (hDestroyerIndex != 100) {
			hDestroyerSmall
					.setLayoutX(SmallGrid.getChildren().get(hDestroyerIndex).getLayoutX() + SmallGrid.getLayoutX());
			hDestroyerSmall
					.setLayoutY(SmallGrid.getChildren().get(hDestroyerIndex).getLayoutY() + SmallGrid.getLayoutY());
			hDestroyerSmall.setVisible(true);
		}
		if (vPatrolIndex != 100) {
			vPatrolSmall.setLayoutX(SmallGrid.getChildren().get(vPatrolIndex).getLayoutX() + SmallGrid.getLayoutX());
			vPatrolSmall.setLayoutY(SmallGrid.getChildren().get(vPatrolIndex).getLayoutY() + SmallGrid.getLayoutY());
			vPatrolSmall.setVisible(true);
		}
		if (hPatrolIndex != 100) {
			hPatrolSmall.setLayoutX(SmallGrid.getChildren().get(hPatrolIndex).getLayoutX() + SmallGrid.getLayoutX());
			hPatrolSmall.setLayoutY(SmallGrid.getChildren().get(hPatrolIndex).getLayoutY() + SmallGrid.getLayoutY());
			hPatrolSmall.setVisible(true);
		}
	}

}

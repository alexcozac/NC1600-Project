package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class BattleController extends SpawnController implements Initializable, EventHandler {

	@FXML
	AnchorPane battlescreen = new AnchorPane();
	@FXML
	AnchorPane AlertScreen = new AnchorPane();
	@FXML
	MediaView battleBG = new MediaView();
	@FXML
	ImageView battleFrame = new ImageView();

	@FXML
	ImageView AlertMessage = new ImageView();
	@FXML
	ImageView yesBtn = new ImageView();
	@FXML
	ImageView noBtn = new ImageView();

	@FXML
	Group Grid = new Group();
	@FXML
	AnchorPane SmallGrid = new AnchorPane();

	@FXML
	ImageView StatsDisplay = new ImageView();
	@FXML
	ImageView radarAmmo = new ImageView();
	@FXML
	ImageView userScoreNr = new ImageView();
	@FXML
	ImageView userScoreNrDecimal = new ImageView();
	@FXML
	ImageView cpuScoreNr = new ImageView();
	@FXML
	ImageView cpuScoreNrDecimal = new ImageView();

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

	Image statsDisplay;
	Image nr0, nr1, nr2, nr3, nr4, nr5, nr6, nr7, nr8, nr9;

	Image hitSquare, missSquare, greenSquare;
	ImageView tempsource = new ImageView();

	Image scan, scanClick, scanHover;
	Image shoot, shootClick, shootHover;

	Image yes, yesHover, yesClick;
	Image no, noHover, noClick;
	Image won, lost;

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
		BattleAudioSound.setAutoPlay(true);
		BattleAudioSound.setOnEndOfMedia(new Runnable() {
			public void run() {
				BattleAudioSound.seek(Duration.ZERO);
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

		statsDisplay = new Image(path + "\\Battle\\StatsDisplay.png");
		nr0 = new Image(path + "\\Battle\\0.png");
		nr1 = new Image(path + "\\Battle\\1.png");
		nr2 = new Image(path + "\\Battle\\2.png");
		nr3 = new Image(path + "\\Battle\\3.png");
		nr4 = new Image(path + "\\Battle\\4.png");
		nr5 = new Image(path + "\\Battle\\5.png");
		nr6 = new Image(path + "\\Battle\\6.png");
		nr7 = new Image(path + "\\Battle\\7.png");
		nr8 = new Image(path + "\\Battle\\8.png");
		nr9 = new Image(path + "\\Battle\\9.png");

		won = new Image(path + "\\Battle\\YouWon.png");
		lost = new Image(path + "\\Battle\\YouLost.png");
		yes = new Image(path + "\\Battle\\Yes.png");
		yesHover = new Image(path + "\\Battle\\YesHover.png");
		yesClick = new Image(path + "\\Battle\\YesClick.png");
		no = new Image(path + "\\Battle\\No.png");
		noHover = new Image(path + "\\Battle\\NoHover.png");
		noClick = new Image(path + "\\Battle\\NoClick.png");

		StatsDisplay.setImage(statsDisplay);
		radarAmmo.setImage(nr4);
		userScoreNr.setImage(nr0);
		userScoreNrDecimal.setImage(nr0);
		cpuScoreNr.setImage(nr0);
		cpuScoreNrDecimal.setImage(nr0);

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
			GridClickSound.play();
			GridClickSound.seek(Duration.ZERO);
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
			// TODO: Give bonus if ship destroyed
			battleshipLogic.cpuAircraftC.getShipHP(battleshipLogic.cpuGrid.getGrid());
			battleshipLogic.cpuBattleship.getShipHP(battleshipLogic.cpuGrid.getGrid());
			battleshipLogic.cpuSubmarine.getShipHP(battleshipLogic.cpuGrid.getGrid());
			battleshipLogic.cpuDestroyer.getShipHP(battleshipLogic.cpuGrid.getGrid());
			battleshipLogic.cpuPatrol.getShipHP(battleshipLogic.cpuGrid.getGrid());
			if (battleshipLogic.cpuAircraftC.isShipDed() == true) {
				battleshipLogic.uPlayer.bonusScore(battleshipLogic.uPlayer.getScore(),
						battleshipLogic.cpuAircraftC.getShipSize());
			}
			if (battleshipLogic.cpuBattleship.isShipDed() == true) {
				battleshipLogic.uPlayer.bonusScore(battleshipLogic.uPlayer.getScore(),
						battleshipLogic.cpuBattleship.getShipSize());
			}
			if (battleshipLogic.cpuSubmarine.isShipDed() == true) {
				battleshipLogic.uPlayer.bonusScore(battleshipLogic.uPlayer.getScore(),
						battleshipLogic.cpuSubmarine.getShipSize());
			}
			if (battleshipLogic.cpuDestroyer.isShipDed() == true) {
				battleshipLogic.uPlayer.bonusScore(battleshipLogic.uPlayer.getScore(),
						battleshipLogic.cpuDestroyer.getShipSize());
			}
			if (battleshipLogic.cpuPatrol.isShipDed() == true) {
				battleshipLogic.uPlayer.bonusScore(battleshipLogic.uPlayer.getScore(),
						battleshipLogic.cpuPatrol.getShipSize());
			}
			// TODO: Stop game if user Won
			if (battleshipLogic.cpuAircraftC.getShipHP(battleshipLogic.cpuGrid.getGrid()) == 0
					&& battleshipLogic.cpuBattleship.getShipHP(battleshipLogic.cpuGrid.getGrid()) == 0
					&& battleshipLogic.cpuSubmarine.getShipHP(battleshipLogic.cpuGrid.getGrid()) == 0
					&& battleshipLogic.cpuDestroyer.getShipHP(battleshipLogic.cpuGrid.getGrid()) == 0
					&& battleshipLogic.cpuPatrol.getShipHP(battleshipLogic.cpuGrid.getGrid()) == 0) {
				AlertScreen.toFront();
				AlertMessage.setImage(won);

			}

			// TODO: On mouse release cpu will start his turn
			while (battleshipLogic.uPlayer.userTurn() == false) {
				if (cpuBrainz.getState() == 0) {
					char outcome = cpuBrainz.stateSwitch(cpuBrainz.getState(), cpuBrainz.initial_vCoordinates(),
							cpuBrainz.initial_hCoordinates(),
							battleshipLogic.cpuPlayer.shoot(battleshipLogic.uGrid.getGrid(), cpuBrainz.vCoordinates(),
									cpuBrainz.hCoordinates()));
					if (outcome == 'h') {
						String result = cpuBrainz.getShotvAxis() + "" + cpuBrainz.getShothAxis();
						int resultIndex = Integer.parseInt(result);
						ImageView temp = (ImageView) SmallGrid.getChildren().get(resultIndex);
						temp.setImage(hitSquare);
					} else if (outcome == 'm') {
						String result = cpuBrainz.getShotvAxis() + "" + cpuBrainz.getShothAxis();
						int resultIndex = Integer.parseInt(result);
						ImageView temp = (ImageView) SmallGrid.getChildren().get(resultIndex);
						temp.setImage(missSquare);
					} else if(outcome == 'x') {
						System.out.println("cpuBrainz got stuck in an endless loop");
						window.close();
						break;
					}
				}

				else {
					char outcome = cpuBrainz.stateSwitch(cpuBrainz.getState(), cpuBrainz.vCoordinates(),
							cpuBrainz.hCoordinates(), battleshipLogic.cpuPlayer.shoot(battleshipLogic.uGrid.getGrid(),
									cpuBrainz.vCoordinates(), cpuBrainz.hCoordinates()));
					if (outcome == 'h') {
						String result = cpuBrainz.getShotvAxis() + "" + cpuBrainz.getShothAxis();
						int resultIndex = Integer.parseInt(result);
						ImageView temp = (ImageView) SmallGrid.getChildren().get(resultIndex);
						temp.setImage(hitSquare);
					} else if (outcome == 'm') {
						String result = cpuBrainz.getShotvAxis() + "" + cpuBrainz.getShothAxis();
						int resultIndex = Integer.parseInt(result);
						ImageView temp = (ImageView) SmallGrid.getChildren().get(resultIndex);
						temp.setImage(missSquare);
					} else if(outcome == 'x') {
						System.out.println("cpuBrainz got stuck in an endless loop");
						window.close();
						break;
					}
				}

			}
			cpuBrainz.resetCount();
			if (shootselected == true) {
				source.setDisable(true);
			}
			// TODO: Status Checks
			int aircraftHP = battleshipLogic.uAircraftC.getShipHP(battleshipLogic.uGrid.getGrid());
			if (aircraftHP == 5)
				aircraftHPbar.setImage(HP5);
			else if (aircraftHP == 4)
				aircraftHPbar.setImage(HP5_1);
			else if (aircraftHP == 3)
				aircraftHPbar.setImage(HP5_2);
			else if (aircraftHP == 2)
				aircraftHPbar.setImage(HP5_3);
			else if (aircraftHP == 1)
				aircraftHPbar.setImage(HP5_4);
			else if (aircraftHP == 0) {
				aircraftHPbar.setImage(HP5Destroyed);
				if (battleshipLogic.uAircraftC.isShipDed() == true) {
					battleshipLogic.cpuPlayer.bonusScore(battleshipLogic.cpuPlayer.getScore(),
							battleshipLogic.uAircraftC.getShipSize());
					if (cpuBrainz.secondShipFound() == false) {
						cpuBrainz.setState(0);
					}
				}
			}
			// -------------------------------------------------------------
			int battleshipHP = battleshipLogic.uBattleship.getShipHP(battleshipLogic.uGrid.getGrid());
			if (battleshipHP == 4)
				battleshipHPbar.setImage(HP4);
			else if (battleshipHP == 3)
				battleshipHPbar.setImage(HP4_1);
			else if (battleshipHP == 2)
				battleshipHPbar.setImage(HP4_2);
			else if (battleshipHP == 1)
				battleshipHPbar.setImage(HP4_3);
			else if (battleshipHP == 0) {
				battleshipHPbar.setImage(HP4Destroyed);
				if (battleshipLogic.uBattleship.isShipDed() == true) {
					battleshipLogic.cpuPlayer.bonusScore(battleshipLogic.cpuPlayer.getScore(),
							battleshipLogic.uBattleship.getShipSize());
					if (cpuBrainz.secondShipFound() == false) {
						cpuBrainz.setState(0);
					}
				}
			}
			// -------------------------------------------------------------
			int submarineHP = battleshipLogic.uSubmarine.getShipHP(battleshipLogic.uGrid.getGrid());
			if (submarineHP == 3)
				submarineHPbar.setImage(HP3);
			else if (submarineHP == 2)
				submarineHPbar.setImage(HP3_1);
			else if (submarineHP == 1)
				submarineHPbar.setImage(HP3_2);
			else if (submarineHP == 0) {
				submarineHPbar.setImage(HP3Destroyed);
				if (battleshipLogic.uSubmarine.isShipDed() == true) {
					battleshipLogic.cpuPlayer.bonusScore(battleshipLogic.cpuPlayer.getScore(),
							battleshipLogic.uSubmarine.getShipSize());
					if (cpuBrainz.secondShipFound() == false) {
						cpuBrainz.setState(0);
					}
				}
			}
			// -------------------------------------------------------------
			int destroyerHP = battleshipLogic.uDestroyer.getShipHP(battleshipLogic.uGrid.getGrid());
			if (destroyerHP == 3)
				destroyerHPbar.setImage(HP3);
			else if (destroyerHP == 2)
				destroyerHPbar.setImage(HP3_1);
			else if (destroyerHP == 1)
				destroyerHPbar.setImage(HP3_2);
			else if (destroyerHP == 0) {
				destroyerHPbar.setImage(HP3Destroyed);
				if (battleshipLogic.uDestroyer.isShipDed() == true) {
					battleshipLogic.cpuPlayer.bonusScore(battleshipLogic.cpuPlayer.getScore(),
							battleshipLogic.uDestroyer.getShipSize());
					if (cpuBrainz.secondShipFound() == false) {
						cpuBrainz.setState(0);
					}
				}
			}
			// -------------------------------------------------------------
			int patrolHP = battleshipLogic.uPatrol.getShipHP(battleshipLogic.uGrid.getGrid());
			if (patrolHP == 2)
				patrolHPbar.setImage(HP2);
			else if (patrolHP == 1)
				patrolHPbar.setImage(HP2_1);
			else if (patrolHP == 0) {
				patrolHPbar.setImage(HP2Destroyed);
				if (battleshipLogic.uPatrol.isShipDed() == true) {
					battleshipLogic.cpuPlayer.bonusScore(battleshipLogic.cpuPlayer.getScore(),
							battleshipLogic.uPatrol.getShipSize());
					if (cpuBrainz.secondShipFound() == false) {
						cpuBrainz.setState(0);
					}
				}
			}
			// -------------------------------------------------------------
			// Radar Ammo
			if (battleshipLogic.uRadar.getRadarAmmo() == 4)
				radarAmmo.setImage(nr4);
			else if (battleshipLogic.uRadar.getRadarAmmo() == 3)
				radarAmmo.setImage(nr3);
			else if (battleshipLogic.uRadar.getRadarAmmo() == 2)
				radarAmmo.setImage(nr2);
			else if (battleshipLogic.uRadar.getRadarAmmo() == 1)
				radarAmmo.setImage(nr1);
			else if (battleshipLogic.uRadar.getRadarAmmo() == 0) {
				radarAmmo.setImage(nr0);
				scanselected = false;
				shootselected = true;
				shootBtn.setImage(shootClick);
				scanBtn.setImage(scan);
				scanBtn.setDisable(true);
				scanBtn.setOpacity(0.5);
			}

			setUserScore();
			setCpuScore();
			if (battleshipLogic.uAircraftC.getShipHP(battleshipLogic.uGrid.getGrid()) == 0
					&& battleshipLogic.uBattleship.getShipHP(battleshipLogic.uGrid.getGrid()) == 0
					&& battleshipLogic.uSubmarine.getShipHP(battleshipLogic.uGrid.getGrid()) == 0
					&& battleshipLogic.uDestroyer.getShipHP(battleshipLogic.uGrid.getGrid()) == 0
					&& battleshipLogic.uPatrol.getShipHP(battleshipLogic.uGrid.getGrid()) == 0) {
				AlertScreen.toFront();
				AlertMessage.setImage(lost);
			}

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
			btnHoverSound.play();
			btnHoverSound.seek(Duration.ZERO);
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
			case "yesBtn":
				yesBtn.setImage(yesHover);
				break;
			case "noBtn":
				noBtn.setImage(noHover);
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

			case "yesBtn":
				yesBtn.setImage(yes);
				break;
			case "noBtn":
				noBtn.setImage(no);
				break;
			}

			break;
		case "MOUSE_PRESSED":
			btnClickSound.play();
			btnClickSound.seek(Duration.ZERO);
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

			case "yesBtn":
				yesBtn.setImage(yesClick);
				break;
			case "noBtn":
				noBtn.setImage(noClick);
				break;
			}
			break;
		case "MOUSE_RELEASED":
			switch (ID) {
			case "backBtn":
				audioplayer.play();
				BattleAudioSound.stop();
				vAircraftIndex = 100;
				hAircraftIndex = 100;
				vBattleshipIndex = 100;
				hBattleshipIndex = 100;
				vSubmarineIndex = 100;
				hSubmarineIndex = 100;
				vDestroyerIndex = 100;
				hDestroyerIndex = 100;
				vPatrolIndex = 100;
				hPatrolIndex = 100;
				try {
					BorderPane spawn = (BorderPane) FXMLLoader.load(getClass().getResource("SpawnScreen.fxml"));
					spawnScreen = new Scene(spawn, 1024, 768);
					spawnScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

				} catch (Exception e) {
				}
				battleshipLogic.uGrid.resetGrid();
				battleshipLogic.cpuGrid.resetGrid();
				window.setScene(spawnScreen);
				break;

			case "shootBtn":

				break;

			case "scanBtn":

				break;

			case "yesBtn":
				yesBtn.setImage(yesHover);
				audioplayer.play();
				BattleAudioSound.stop();
				vAircraftIndex = 100;
				hAircraftIndex = 100;
				vBattleshipIndex = 100;
				hBattleshipIndex = 100;
				vSubmarineIndex = 100;
				hSubmarineIndex = 100;
				vDestroyerIndex = 100;
				hDestroyerIndex = 100;
				vPatrolIndex = 100;
				hPatrolIndex = 100;
				try {
					BorderPane spawn = (BorderPane) FXMLLoader.load(getClass().getResource("SpawnScreen.fxml"));
					spawnScreen = new Scene(spawn, 1024, 768);
					spawnScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

				} catch (Exception e) {
				}
				battleshipLogic.uGrid.resetGrid();
				battleshipLogic.cpuGrid.resetGrid();
				window.setScene(spawnScreen);
				break;
			case "noBtn":
				noBtn.setImage(noHover);
				window.close();
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

	public void setUserScore() {
		int nr = battleshipLogic.uPlayer.getScore() % 10;
		int nrDec = battleshipLogic.uPlayer.getScore() / 10;
		switch (nr) {
		case 0:
			userScoreNr.setImage(nr0);
			break;
		case 1:
			userScoreNr.setImage(nr1);
			break;
		case 2:
			userScoreNr.setImage(nr2);
			break;
		case 3:
			userScoreNr.setImage(nr3);
			break;
		case 4:
			userScoreNr.setImage(nr4);
			break;
		case 5:
			userScoreNr.setImage(nr5);
			break;
		case 6:
			userScoreNr.setImage(nr6);
			break;
		case 7:
			userScoreNr.setImage(nr7);
			break;
		case 8:
			userScoreNr.setImage(nr8);
			break;
		case 9:
			userScoreNr.setImage(nr9);
			break;
		}
		switch (nrDec) {
		case 0:
			userScoreNrDecimal.setImage(nr0);
			break;
		case 1:
			userScoreNrDecimal.setImage(nr1);
			break;
		case 2:
			userScoreNrDecimal.setImage(nr2);
			break;
		case 3:
			userScoreNrDecimal.setImage(nr3);
			break;
		case 4:
			userScoreNrDecimal.setImage(nr4);
			break;
		case 5:
			userScoreNrDecimal.setImage(nr5);
			break;
		case 6:
			userScoreNrDecimal.setImage(nr6);
			break;
		case 7:
			userScoreNrDecimal.setImage(nr7);
			break;
		case 8:
			userScoreNrDecimal.setImage(nr8);
			break;
		case 9:
			userScoreNrDecimal.setImage(nr9);
			break;
		}
	}

	public void setCpuScore() {
		int nr = battleshipLogic.cpuPlayer.getScore() % 10;
		int nrDec = battleshipLogic.cpuPlayer.getScore() / 10;
		switch (nr) {
		case 0:
			cpuScoreNr.setImage(nr0);
			break;
		case 1:
			cpuScoreNr.setImage(nr1);
			break;
		case 2:
			cpuScoreNr.setImage(nr2);
			break;
		case 3:
			cpuScoreNr.setImage(nr3);
			break;
		case 4:
			cpuScoreNr.setImage(nr4);
			break;
		case 5:
			cpuScoreNr.setImage(nr5);
			break;
		case 6:
			cpuScoreNr.setImage(nr6);
			break;
		case 7:
			cpuScoreNr.setImage(nr7);
			break;
		case 8:
			cpuScoreNr.setImage(nr8);
			break;
		case 9:
			cpuScoreNr.setImage(nr9);
			break;
		}
		switch (nrDec) {
		case 0:
			cpuScoreNrDecimal.setImage(nr0);
			break;
		case 1:
			cpuScoreNrDecimal.setImage(nr1);
			break;
		case 2:
			cpuScoreNrDecimal.setImage(nr2);
			break;
		case 3:
			cpuScoreNrDecimal.setImage(nr3);
			break;
		case 4:
			cpuScoreNrDecimal.setImage(nr4);
			break;
		case 5:
			cpuScoreNrDecimal.setImage(nr5);
			break;
		case 6:
			cpuScoreNrDecimal.setImage(nr6);
			break;
		case 7:
			cpuScoreNrDecimal.setImage(nr7);
			break;
		case 8:
			cpuScoreNrDecimal.setImage(nr8);
			break;
		case 9:
			cpuScoreNrDecimal.setImage(nr9);
			break;
		}
	}

}

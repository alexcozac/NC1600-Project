package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class SpawnController extends MenuController implements Initializable, EventHandler {
	static BattleshipLogic battleshipLogic;
	// Page1
	@FXML
	MediaView spawnBG = new MediaView();
	@FXML
	ImageView randomBtn = new ImageView();
	@FXML
	ImageView manualBtn = new ImageView();
	@FXML
	ImageView backBtn = new ImageView();
	@FXML
	ImageView spawnQuestion = new ImageView();

	// Page2
	@FXML
	AnchorPane spawnManual = new AnchorPane();
	@FXML
	Group Grid = new Group();
	@FXML
	Group Ships = new Group();
	@FXML
	ImageView spawnFrame = new ImageView();
	@FXML
	ImageView confirmBtn = new ImageView();
	@FXML
	ImageView rotateBtn = new ImageView();
	@FXML
	ImageView spawnAircraftBtn = new ImageView();
	@FXML
	ImageView spawnBattleshipBtn = new ImageView();
	@FXML
	ImageView spawnSubmarineBtn = new ImageView();
	@FXML
	ImageView spawnDestroyerBtn = new ImageView();
	@FXML
	ImageView spawnPatrolBtn = new ImageView();
	@FXML
	ImageView textField = new ImageView();
	@FXML
	ImageView vAircraft = new ImageView();
	@FXML
	ImageView vBattleship = new ImageView();
	@FXML
	ImageView vSubmarine = new ImageView();
	@FXML
	ImageView vDestroyer = new ImageView();
	@FXML
	ImageView vPatrol = new ImageView();
	@FXML
	ImageView hAircraft = new ImageView();
	@FXML
	ImageView hBattleship = new ImageView();
	@FXML
	ImageView hSubmarine = new ImageView();
	@FXML
	ImageView hDestroyer = new ImageView();
	@FXML
	ImageView hPatrol = new ImageView();

	// Page1
	Image back, backhover, backclick;
	Image spawnq;
	Image random, randomhover, randomclick;
	Image manual, manualhover, manualclick;
	// Page2
	static Image gridsquare, selectsquare;
	Image spawnframe;
	Image confirm, confirmhover, confirmclick;
	Image rotateh, rotatehhover, rotatev, rotatevhover;
	Image spawn, spawnhover, spawnclick;
	Image collisiondetected, outofboundsdetected;
	FadeTransition fadeText;
	// Ships
	static Image aircraft, battleship, submarine, destroyer, patrol;
	static Image aircrafth, battleshiph, submarineh, destroyerh, patrolh;

	// Page2 clicked
	boolean aircraftselected, battleshipselected, submarineselected, destroyerselected, patrolselected = false;
	boolean spawnSelected = false;
	boolean manualSelected = false;

	boolean rotate = false;
	// Coordinates

	Media gameVideo = new Media(new File(path + "\\Spawn\\GameBG.mp4").toURI().toString());
	MediaPlayer gameBG = new MediaPlayer(gameVideo);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		battleshipLogic= new BattleshipLogic();
		// Page1
		spawnq = new Image(path + "\\Spawn\\spawnQuestion.png");

		back = new Image(path + "\\Spawn\\Back.png");
		backhover = new Image(path + "\\Spawn\\BackHover.png");
		backclick = new Image(path + "\\Spawn\\BackClick.png");

		random = new Image(path + "\\Spawn\\Random.png");
		randomhover = new Image(path + "\\Spawn\\RandomHover.png");
		randomclick = new Image(path + "\\Spawn\\RandomClick.png");
		manual = new Image(path + "\\Spawn\\Manual.png");
		manualhover = new Image(path + "\\Spawn\\ManualHover.png");
		manualclick = new Image(path + "\\Spawn\\ManualClick.png");

		// Page2
		gridsquare = new Image(path + "\\Spawn\\GridSquare.png");
		selectsquare = new Image(path + "\\Spawn\\SelectSquare.png");

		spawnframe = new Image(path + "\\Spawn\\SpawnFrame.png");

		rotatev = new Image(path + "\\Spawn\\RotateH.png");
		rotatevhover = new Image(path + "\\Spawn\\RotateHHover.png");
		rotateh = new Image(path + "\\Spawn\\RotateV.png");
		rotatehhover = new Image(path + "\\Spawn\\RotateVHover.png");

		confirm = new Image(path + "\\Spawn\\Confirm.png");
		confirmhover = new Image(path + "\\Spawn\\ConfirmHover.png");
		confirmclick = new Image(path + "\\Spawn\\ConfirmClick.png");

		spawn = new Image(path + "\\Spawn\\Spawn.png");
		spawnhover = new Image(path + "\\Spawn\\SpawnHover.png");
		spawnclick = new Image(path + "\\Spawn\\SpawnClick.png");

		collisiondetected = new Image(path + "\\Spawn\\CollisionDetected.png");
		outofboundsdetected = new Image(path + "\\Spawn\\OutOfBounds.png");

		fadeText = new FadeTransition(Duration.seconds(5), textField);

		aircraft = new Image(path + "\\Spawn\\VAircraftBig.png");
		battleship = new Image(path + "\\Spawn\\VBattleshipBig.png");
		submarine = new Image(path + "\\Spawn\\VSubmarineBig.png");
		destroyer = new Image(path + "\\Spawn\\VDestroyerBig.png");
		patrol = new Image(path + "\\Spawn\\VPatrolBig.png");
		aircrafth = new Image(path + "\\Spawn\\HAircraftBig.png");
		battleshiph = new Image(path + "\\Spawn\\HBattleshipBig.png");
		submarineh = new Image(path + "\\Spawn\\HSubmarineBig.png");
		destroyerh = new Image(path + "\\Spawn\\HDestroyerBig.png");
		patrolh = new Image(path + "\\Spawn\\HPatrolBig.png");

		spawnBG.setMediaPlayer(gameBG);
		gameBG.setAutoPlay(true);
		gameBG.setOnEndOfMedia(new Runnable() {
			public void run() {
				gameBG.seek(Duration.ZERO);
			}
		});
		// Random or Manual Page
		spawnQuestion.setImage(spawnq);
		backBtn.setImage(back);
		randomBtn.setImage(random);
		manualBtn.setImage(manual);

		// Manual Spawning Page
		for (int i = 0; i < Grid.getChildren().size(); i++) {
			ImageView temp = (ImageView) Grid.getChildren().get(i);
			temp.setImage(gridsquare);
		}
		spawnFrame.setImage(spawnframe);
		confirmBtn.setImage(confirm);
		confirmBtn.setDisable(true);
		confirmBtn.setOpacity(0.5);
		rotateBtn.setImage(rotateh);
		spawnAircraftBtn.setImage(spawn);
		spawnBattleshipBtn.setImage(spawn);
		spawnSubmarineBtn.setImage(spawn);
		spawnDestroyerBtn.setImage(spawn);
		spawnPatrolBtn.setImage(spawn);
		fadeText.setFromValue(1.0);
		fadeText.setToValue(0.0);

		// Ships
		vAircraft.setImage(aircraft);
		hAircraft.setImage(aircrafth);

		vBattleship.setImage(battleship);
		hBattleship.setImage(battleshiph);

		vSubmarine.setImage(submarine);
		hSubmarine.setImage(submarineh);

		vDestroyer.setImage(destroyer);
		hDestroyer.setImage(destroyerh);

		vPatrol.setImage(patrol);
		hPatrol.setImage(patrolh);

		vAircraft.setVisible(false);
		hAircraft.setVisible(false);
		vBattleship.setVisible(false);
		hBattleship.setVisible(false);
		vSubmarine.setVisible(false);
		hSubmarine.setVisible(false);
		vDestroyer.setVisible(false);
		hDestroyer.setVisible(false);
		vPatrol.setVisible(false);
		hPatrol.setVisible(false);

		Grid.setVisible(true);
		Ships.setVisible(true);
		spawnManual.setVisible(false);

	}

	public void handleGrid(Event event) {
		String type = event.getEventType().toString();
		ImageView source = (ImageView) event.getSource();

		int index = Grid.getChildren().indexOf(source);
		int hAxis = index % 10;
		int vAxis = index / 10;

		switch (type) {

		case "MOUSE_ENTERED":

			try {
				if (aircraftselected == true) {
					if (10 >= battleshipLogic.uAircraftC.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uAircraftC.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 1;
						}
					}
				}

				if (battleshipselected == true) {
					if (10 >= battleshipLogic.uBattleship.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uBattleship.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 1;
						}
					}
				}

				if (submarineselected == true) {
					if (10 >= battleshipLogic.uSubmarine.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uSubmarine.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 1;
						}
					}
				}

				if (destroyerselected == true) {
					if (10 >= battleshipLogic.uDestroyer.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uDestroyer.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 1;
						}
					}
				}

				if (patrolselected == true) {
					if (10 >= battleshipLogic.uPatrol.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uPatrol.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uPatrol.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uPatrol.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 1;
						}
					}
				}

			} catch (Exception e) {
			}
			break;

		case "MOUSE_EXITED":
			try {
				if (aircraftselected == true) {
					if (10 >= battleshipLogic.uAircraftC.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uAircraftC.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
					}
				}

				if (battleshipselected == true) {
					if (10 >= battleshipLogic.uBattleship.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uBattleship.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
					}
				}

				if (submarineselected == true) {
					if (10 >= battleshipLogic.uSubmarine.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uSubmarine.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
					}
				}

				if (destroyerselected == true) {
					if (10 >= battleshipLogic.uDestroyer.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uDestroyer.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
					}
				}

				if (patrolselected == true) {
					if (10 >= battleshipLogic.uPatrol.getShipSize() + vAxis && rotate == false) {
						for (int i = 0; i < battleshipLogic.uPatrol.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uPatrol.getShipSize() + hAxis && rotate == true) {
						for (int i = 0; i < battleshipLogic.uPatrol.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
					}
				}

			} catch (Exception e) {
			}
			break;

		case "MOUSE_PRESSED":
			GridClickSound.play();
			GridClickSound.seek(Duration.ZERO);
			// ----------------------------------------------------
			if (aircraftselected) {
				if (rotate == false) {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 0,
							battleshipLogic.uAircraftC.getShipSize(), battleshipLogic.uAircraftC.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
						aircraftselected = false;
						spawnAircraftBtn.setVisible(false);

						spawnBattleshipBtn.setDisable(false);
						spawnSubmarineBtn.setDisable(false);
						spawnDestroyerBtn.setDisable(false);
						spawnPatrolBtn.setDisable(false);

						vAircraft.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						vAircraft.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						vAircraft.setVisible(true);
						BattleController.sendLayouts(vAircraft, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}

				} else {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 1,
							battleshipLogic.uAircraftC.getShipSize(), battleshipLogic.uAircraftC.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
						aircraftselected = false;
						spawnAircraftBtn.setVisible(false);

						spawnBattleshipBtn.setDisable(false);
						spawnSubmarineBtn.setDisable(false);
						spawnDestroyerBtn.setDisable(false);
						spawnPatrolBtn.setDisable(false);

						hAircraft.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						hAircraft.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						hAircraft.setVisible(true);
						BattleController.sendLayouts(hAircraft, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}
				}
				// ----------------------------------------------------
			} else if (battleshipselected) {
				if (rotate == false) {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 0,
							battleshipLogic.uBattleship.getShipSize(), battleshipLogic.uBattleship.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
						battleshipselected = false;
						spawnBattleshipBtn.setVisible(false);

						spawnAircraftBtn.setDisable(false);
						spawnSubmarineBtn.setDisable(false);
						spawnDestroyerBtn.setDisable(false);
						spawnPatrolBtn.setDisable(false);

						vBattleship.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						vBattleship.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						vBattleship.setVisible(true);
						BattleController.sendLayouts(vBattleship, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}

				} else {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 1,
							battleshipLogic.uBattleship.getShipSize(), battleshipLogic.uBattleship.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
						battleshipselected = false;
						spawnBattleshipBtn.setVisible(false);

						spawnAircraftBtn.setDisable(false);
						spawnSubmarineBtn.setDisable(false);
						spawnDestroyerBtn.setDisable(false);
						spawnPatrolBtn.setDisable(false);

						hBattleship.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						hBattleship.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						hBattleship.setVisible(true);
						BattleController.sendLayouts(hBattleship, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}
				}
				// ----------------------------------------------------
			} else if (submarineselected) {
				if (rotate == false) {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 0,
							battleshipLogic.uSubmarine.getShipSize(), battleshipLogic.uSubmarine.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
						submarineselected = false;
						spawnSubmarineBtn.setVisible(false);

						spawnAircraftBtn.setDisable(false);
						spawnBattleshipBtn.setDisable(false);
						spawnDestroyerBtn.setDisable(false);
						spawnPatrolBtn.setDisable(false);

						vSubmarine.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						vSubmarine.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						vSubmarine.setVisible(true);
						BattleController.sendLayouts(vSubmarine, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}

				} else {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 1,
							battleshipLogic.uSubmarine.getShipSize(), battleshipLogic.uSubmarine.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
						submarineselected = false;
						spawnSubmarineBtn.setVisible(false);

						spawnAircraftBtn.setDisable(false);
						spawnBattleshipBtn.setDisable(false);
						spawnDestroyerBtn.setDisable(false);
						spawnPatrolBtn.setDisable(false);

						hSubmarine.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						hSubmarine.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						hSubmarine.setVisible(true);
						BattleController.sendLayouts(hSubmarine, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}

				}
				// ----------------------------------------------------
			} else if (destroyerselected) {
				if (rotate == false) {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 0,
							battleshipLogic.uDestroyer.getShipSize(), battleshipLogic.uDestroyer.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
						destroyerselected = false;
						spawnDestroyerBtn.setVisible(false);

						spawnAircraftBtn.setDisable(false);
						spawnBattleshipBtn.setDisable(false);
						spawnSubmarineBtn.setDisable(false);
						spawnPatrolBtn.setDisable(false);

						vDestroyer.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						vDestroyer.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						vDestroyer.setVisible(true);
						BattleController.sendLayouts(vDestroyer, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}

				} else {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 1,
							battleshipLogic.uDestroyer.getShipSize(), battleshipLogic.uDestroyer.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
						destroyerselected = false;
						spawnDestroyerBtn.setVisible(false);

						spawnAircraftBtn.setDisable(false);
						spawnBattleshipBtn.setDisable(false);
						spawnSubmarineBtn.setDisable(false);
						spawnPatrolBtn.setDisable(false);

						hDestroyer.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						hDestroyer.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						hDestroyer.setVisible(true);
						BattleController.sendLayouts(hDestroyer, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}

				}
				// ----------------------------------------------------
			} else if (patrolselected) {
				if (rotate == false) {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 0,
							battleshipLogic.uPatrol.getShipSize(), battleshipLogic.uPatrol.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uPatrol.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
						patrolselected = false;
						spawnPatrolBtn.setVisible(false);

						spawnAircraftBtn.setDisable(false);
						spawnBattleshipBtn.setDisable(false);
						spawnSubmarineBtn.setDisable(false);
						spawnDestroyerBtn.setDisable(false);

						vPatrol.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						vPatrol.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						vPatrol.setVisible(true);
						BattleController.sendLayouts(vPatrol, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}

				} else {
					String outcome = battleshipLogic.uPlayer.spawnShip(battleshipLogic.uGrid.getGrid(), vAxis, hAxis, 1,
							battleshipLogic.uPatrol.getShipSize(), battleshipLogic.uPatrol.getShipChar());

					if (outcome.equals("Ship spawn successful!")) {
						for (int i = 0; i < battleshipLogic.uPatrol.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
						patrolselected = false;
						spawnPatrolBtn.setVisible(false);

						spawnAircraftBtn.setDisable(false);
						spawnBattleshipBtn.setDisable(false);
						spawnSubmarineBtn.setDisable(false);
						spawnDestroyerBtn.setDisable(false);

						hPatrol.setLayoutX(source.getLayoutX() + Grid.getLayoutX());
						hPatrol.setLayoutY(source.getLayoutY() + Grid.getLayoutY());
						hPatrol.setVisible(true);
						BattleController.sendLayouts(hPatrol, Grid.getChildren().indexOf(source));

					} else if (outcome.equals("Ship collision detected!")) {
						textField.setImage(collisiondetected);
						fadeText.play();

					} else if (outcome.equals("Ship out of bounds!")) {
						textField.setImage(outofboundsdetected);
						fadeText.play();
					}

				}
			}

			break;

		case "MOUSE_RELEASED":

			if (spawnAircraftBtn.isVisible() == false && spawnBattleshipBtn.isVisible() == false
					&& spawnSubmarineBtn.isVisible() == false && spawnDestroyerBtn.isVisible() == false
					&& spawnPatrolBtn.isVisible() == false) {
				confirmBtn.setDisable(false);
				confirmBtn.setOpacity(1);
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
				backBtn.setImage(backhover);
				break;

			case "randomBtn":
				randomBtn.setImage(randomhover);
				break;

			case "manualBtn":
				manualBtn.setImage(manualhover);
				break;

			case "confirmBtn":
				confirmBtn.setImage(confirmhover);
				break;

			case "rotateBtn":
				if (rotate == false) {
					rotateBtn.setImage(rotatehhover);
				} else {
					rotateBtn.setImage(rotatevhover);
				}
				break;

			case "spawnAircraftBtn":
				if (aircraftselected == true) {
					spawnAircraftBtn.setImage(spawnclick);
				} else {
					spawnAircraftBtn.setImage(spawnhover);
				}
				break;

			case "spawnBattleshipBtn":
				if (battleshipselected == true) {
					spawnBattleshipBtn.setImage(spawnclick);
				} else {
					spawnBattleshipBtn.setImage(spawnhover);
				}
				break;

			case "spawnSubmarineBtn":
				if (submarineselected == true) {
					spawnSubmarineBtn.setImage(spawnclick);
				} else {
					spawnSubmarineBtn.setImage(spawnhover);
				}
				break;

			case "spawnDestroyerBtn":
				if (destroyerselected == true) {
					spawnDestroyerBtn.setImage(spawnclick);
				} else {
					spawnDestroyerBtn.setImage(spawnhover);
				}
				break;

			case "spawnPatrolBtn":
				if (patrolselected == true) {
					spawnPatrolBtn.setImage(spawnclick);
				} else {
					spawnPatrolBtn.setImage(spawnhover);
				}
				break;
			}
			break;

		case "MOUSE_EXITED":

			switch (ID) {

			case "backBtn":
				backBtn.setImage(back);
				break;

			case "randomBtn":
				randomBtn.setImage(random);
				break;

			case "manualBtn":
				manualBtn.setImage(manual);
				break;

			case "confirmBtn":
				confirmBtn.setImage(confirm);
				break;

			case "rotateBtn":
				if (rotate == false) {
					rotateBtn.setImage(rotateh);
				} else {
					rotateBtn.setImage(rotatev);
				}
				break;

			case "spawnAircraftBtn":
				if (aircraftselected == true) {
					spawnAircraftBtn.setImage(spawnclick);
				} else {
					spawnAircraftBtn.setImage(spawn);
				}
				break;

			case "spawnBattleshipBtn":
				if (battleshipselected == true) {
					spawnBattleshipBtn.setImage(spawnclick);
				} else {
					spawnBattleshipBtn.setImage(spawn);
				}
				break;

			case "spawnSubmarineBtn":
				if (submarineselected == true) {
					spawnSubmarineBtn.setImage(spawnclick);
				} else {
					spawnSubmarineBtn.setImage(spawn);
				}
				break;

			case "spawnDestroyerBtn":
				if (destroyerselected == true) {
					spawnDestroyerBtn.setImage(spawnclick);
				} else {
					spawnDestroyerBtn.setImage(spawn);
				}
				break;

			case "spawnPatrolBtn":
				if (patrolselected == true) {
					spawnPatrolBtn.setImage(spawnclick);
				} else {
					spawnPatrolBtn.setImage(spawn);
				}
				break;
			}
			break;

		case "MOUSE_PRESSED":
			btnClickSound.play();
			btnClickSound.seek(Duration.ZERO);
			switch (ID) {

			case "backBtn":
				backBtn.setImage(backclick);
				break;

			case "randomBtn":
				randomBtn.setImage(randomclick);
				break;

			case "manualBtn":
				manualBtn.setImage(manualclick);
				break;

			case "confirmBtn":
				// TODO
				confirmBtn.setImage(confirmclick);
				audioplayer.stop();
				try {
					BorderPane battle = (BorderPane) FXMLLoader.load(getClass().getResource("BattleScreen.fxml"));
					battleScreen = new Scene(battle, 1024, 768);
					battleScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				} catch (Exception e) {
					// TODO: handle exception
				}

				window.setScene(battleScreen);
				break;

			case "rotateBtn":
				if (rotate == false) {
					rotateBtn.setImage(rotatevhover);
					rotate = true;
				} else {
					rotateBtn.setImage(rotatehhover);
					rotate = false;
				}
				break;

			case "spawnAircraftBtn":
				if (aircraftselected == false) {
					spawnAircraftBtn.setImage(spawnclick);
					aircraftselected = true;
					spawnBattleshipBtn.setDisable(true);
					spawnSubmarineBtn.setDisable(true);
					spawnDestroyerBtn.setDisable(true);
					spawnPatrolBtn.setDisable(true);

				} else if (aircraftselected == true) {
					spawnAircraftBtn.setImage(spawnhover);
					aircraftselected = false;
					spawnBattleshipBtn.setDisable(false);
					spawnSubmarineBtn.setDisable(false);
					spawnDestroyerBtn.setDisable(false);
					spawnPatrolBtn.setDisable(false);
				}
				break;

			case "spawnBattleshipBtn":
				if (battleshipselected == false) {
					spawnBattleshipBtn.setImage(spawnclick);
					battleshipselected = true;
					spawnAircraftBtn.setDisable(true);
					spawnSubmarineBtn.setDisable(true);
					spawnDestroyerBtn.setDisable(true);
					spawnPatrolBtn.setDisable(true);

				} else if (battleshipselected == true) {
					spawnBattleshipBtn.setImage(spawnhover);
					battleshipselected = false;
					spawnAircraftBtn.setDisable(false);
					spawnSubmarineBtn.setDisable(false);
					spawnDestroyerBtn.setDisable(false);
					spawnPatrolBtn.setDisable(false);

				}
				break;

			case "spawnSubmarineBtn":
				if (submarineselected == false) {
					spawnSubmarineBtn.setImage(spawnclick);
					submarineselected = true;
					spawnAircraftBtn.setDisable(true);
					spawnBattleshipBtn.setDisable(true);
					spawnDestroyerBtn.setDisable(true);
					spawnPatrolBtn.setDisable(true);

				} else {
					spawnSubmarineBtn.setImage(spawnhover);
					submarineselected = false;
					spawnAircraftBtn.setDisable(false);
					spawnBattleshipBtn.setDisable(false);
					spawnDestroyerBtn.setDisable(false);
					spawnPatrolBtn.setDisable(false);
				}
				break;

			case "spawnDestroyerBtn":
				if (destroyerselected == false) {
					spawnDestroyerBtn.setImage(spawnclick);
					destroyerselected = true;
					spawnAircraftBtn.setDisable(true);
					spawnBattleshipBtn.setDisable(true);
					spawnSubmarineBtn.setDisable(true);
					spawnPatrolBtn.setDisable(true);

				} else {
					spawnDestroyerBtn.setImage(spawnhover);
					destroyerselected = false;
					spawnAircraftBtn.setDisable(false);
					spawnBattleshipBtn.setDisable(false);
					spawnSubmarineBtn.setDisable(false);
					spawnPatrolBtn.setDisable(false);
				}
				break;

			case "spawnPatrolBtn":
				if (patrolselected == false) {
					spawnPatrolBtn.setImage(spawnclick);
					patrolselected = true;
					spawnAircraftBtn.setDisable(true);
					spawnBattleshipBtn.setDisable(true);
					spawnSubmarineBtn.setDisable(true);
					spawnDestroyerBtn.setDisable(true);
				} else {
					spawnPatrolBtn.setImage(spawnhover);
					patrolselected = false;
					spawnAircraftBtn.setDisable(false);
					spawnBattleshipBtn.setDisable(false);
					spawnSubmarineBtn.setDisable(false);
					spawnDestroyerBtn.setDisable(false);
				}
			}
			break;

		case "MOUSE_RELEASED":

			switch (ID) {

			case "backBtn":
				backBtn.setImage(backhover);
				if (manualSelected == false) {
					window.setScene(menuScreen);
				} else {
					revertSpawnScreen();
				}
				break;

			case "randomBtn":
				randomBtn.setImage(randomhover);
				audioplayer.stop();
				
				// Random spawn user ships
				String aircraftIndexDirection = battleshipLogic.uPlayer.randomSpawn(battleshipLogic.uGrid.getGrid(),
						battleshipLogic.uAircraftC.getShipSize(), battleshipLogic.uAircraftC.getShipChar());
				String aircraftIndex = "" + aircraftIndexDirection.charAt(0) + aircraftIndexDirection.charAt(1);
				String aircraftDirection = "" + aircraftIndexDirection.charAt(2);
				if (aircraftDirection.equalsIgnoreCase("0")) {
					BattleController.sendLayouts(vAircraft, Integer.parseInt(aircraftIndex));
				}
				if (aircraftDirection.equalsIgnoreCase("1")) {
					BattleController.sendLayouts(hAircraft, Integer.parseInt(aircraftIndex));
				}
				//-----------------------------------------------------------------------
				
				String battleshipIndexDirection = battleshipLogic.uPlayer.randomSpawn(battleshipLogic.uGrid.getGrid(),
						battleshipLogic.uBattleship.getShipSize(), battleshipLogic.uBattleship.getShipChar());
				String battleshipIndex = "" + battleshipIndexDirection.charAt(0) + battleshipIndexDirection.charAt(1);
				String battleshipDirection = "" + battleshipIndexDirection.charAt(2);
				if (battleshipDirection.equalsIgnoreCase("0")) {
					BattleController.sendLayouts(vBattleship, Integer.parseInt(battleshipIndex));
				}
				if (battleshipDirection.equalsIgnoreCase("1")) {
					BattleController.sendLayouts(hBattleship, Integer.parseInt(battleshipIndex));
				}
				//-----------------------------------------------------------------------
				
				String submarineIndexDirection = battleshipLogic.uPlayer.randomSpawn(battleshipLogic.uGrid.getGrid(),
						battleshipLogic.uSubmarine.getShipSize(), battleshipLogic.uSubmarine.getShipChar());
				String submarineIndex = "" + submarineIndexDirection.charAt(0) + submarineIndexDirection.charAt(1);
				String submarineDirection = "" + submarineIndexDirection.charAt(2);
				if (submarineDirection.equalsIgnoreCase("0")) {
					BattleController.sendLayouts(vSubmarine, Integer.parseInt(submarineIndex));
				}
				if (submarineDirection.equalsIgnoreCase("1")) {
					BattleController.sendLayouts(hSubmarine, Integer.parseInt(submarineIndex));
				}
				//-----------------------------------------------------------------------
				
				String destroyerIndexDirection = battleshipLogic.uPlayer.randomSpawn(battleshipLogic.uGrid.getGrid(),
						battleshipLogic.uDestroyer.getShipSize(), battleshipLogic.uDestroyer.getShipChar());
				String destroyerIndex = "" + destroyerIndexDirection.charAt(0) + destroyerIndexDirection.charAt(1);
				String destroyerDirection = "" + destroyerIndexDirection.charAt(2);
				if (destroyerDirection.equalsIgnoreCase("0")) {
					BattleController.sendLayouts(vDestroyer, Integer.parseInt(destroyerIndex));
				}
				if (destroyerDirection.equalsIgnoreCase("1")) {
					BattleController.sendLayouts(hDestroyer, Integer.parseInt(destroyerIndex));
				}
				//-----------------------------------------------------------------------
				
				
				String patrolIndexDirection = battleshipLogic.uPlayer.randomSpawn(battleshipLogic.uGrid.getGrid(),
						battleshipLogic.uPatrol.getShipSize(), battleshipLogic.uPatrol.getShipChar());
				String patrolIndex = "" + patrolIndexDirection.charAt(0) + patrolIndexDirection.charAt(1);
				String patrolDirection = "" + patrolIndexDirection.charAt(2);
				if (patrolDirection.equalsIgnoreCase("0")) {
					BattleController.sendLayouts(vPatrol, Integer.parseInt(patrolIndex));
				}
				if (patrolDirection.equalsIgnoreCase("1")) {
					BattleController.sendLayouts(hPatrol, Integer.parseInt(patrolIndex));
				}
				
				// TODO
				try {
					BorderPane battle = (BorderPane) FXMLLoader.load(getClass().getResource("BattleScreen.fxml"));
					battleScreen = new Scene(battle, 1024, 768);
					battleScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				window.setScene(battleScreen);
				break;

			case "manualBtn":
				manualBtn.setImage(manualhover);
				manualSelected = true;
				// Page1
				manualBtn.setImage(manualhover);
				manualBtn.setVisible(false);
				spawnQuestion.setVisible(false);
				randomBtn.setVisible(false);
				// Page2
				spawnManual.setVisible(true);
				spawnFrame.setVisible(true);
				break;

			case "confirmBtn":
				confirmBtn.setImage(confirmhover);
				break;
			}
			break;

		}
	}

	public void revertSpawnScreen() {
		manualSelected = false;
		battleshipLogic.uGrid.resetGrid();
		battleshipLogic.cpuGrid.resetGrid();
		// Back to Page1
		manualBtn.setVisible(true);
		spawnQuestion.setVisible(true);
		randomBtn.setVisible(true);
		// Reset buttons and turn off page2
		aircraftselected = false;
		battleshipselected = false;
		submarineselected = false;
		destroyerselected = false;
		patrolselected = false;

		spawnAircraftBtn.setDisable(false);
		spawnBattleshipBtn.setDisable(false);
		spawnSubmarineBtn.setDisable(false);
		spawnDestroyerBtn.setDisable(false);
		spawnPatrolBtn.setDisable(false);

		spawnAircraftBtn.setVisible(true);
		spawnBattleshipBtn.setVisible(true);
		spawnSubmarineBtn.setVisible(true);
		spawnDestroyerBtn.setVisible(true);
		spawnPatrolBtn.setVisible(true);

		spawnAircraftBtn.setImage(spawn);
		spawnBattleshipBtn.setImage(spawn);
		spawnSubmarineBtn.setImage(spawn);
		spawnDestroyerBtn.setImage(spawn);
		spawnPatrolBtn.setImage(spawn);

		confirmBtn.setDisable(true);
		confirmBtn.setOpacity(0.5);
		// ---
		spawnManual.setVisible(false);
		spawnFrame.setVisible(false);
		// Hide back ships
		vAircraft.setVisible(false);
		hAircraft.setVisible(false);
		vBattleship.setVisible(false);
		hBattleship.setVisible(false);
		vSubmarine.setVisible(false);
		hSubmarine.setVisible(false);
		vDestroyer.setVisible(false);
		hDestroyer.setVisible(false);
		vPatrol.setVisible(false);
		hPatrol.setVisible(false);
		//Turn on Page1
		manualBtn.setVisible(true);
		spawnQuestion.setVisible(true);
		randomBtn.setVisible(true);
	}
}

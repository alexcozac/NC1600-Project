package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class SpawnController extends MenuController implements Initializable, EventHandler {
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

	// Page1
	Image back, backhover, backclick;
	Image spawnq;
	Image random, randomhover, randomclick;
	Image manual, manualhover, manualclick;
	// Page2
	Image gridsquare, selectsquare;
	Image spawnframe;
	Image confirm, confirmhover, confirmclick;
	Image rotateh, rotatehhover, rotatev, rotatevhover;
	Image spawn, spawnhover, spawnclick;
	// Page2 clicked
	boolean aircraftselected = false, battleshipselected = false, submarineselected = false, destroyerselected = false,
			patrolselected = false;
	boolean spawnSelected = false;
	boolean manualSelected = false;

	boolean rotate = false;

	// Coordinates

	Media gameVideo = new Media(new File(
			"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\GameBG.mp4")
					.toURI().toString());
	MediaPlayer gameBG = new MediaPlayer(gameVideo);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			// Page1
			spawnq = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\spawnQuestion.png"));

			back = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\Back.png"));
			backhover = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\BackHover.png"));
			backclick = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\BackClick.png"));

			random = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\Random.png"));
			randomhover = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\RandomHover.png"));
			randomclick = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\RandomClick.png"));
			manual = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\Manual.png"));
			manualhover = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\ManualHover.png"));
			manualclick = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\ManualClick.png"));

			// Page2
			gridsquare = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\GridSquare.png"));
			selectsquare = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\SelectSquare.png"));

			spawnframe = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\SpawnFrame.png"));

			rotatev = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\RotateH.png"));
			rotatevhover = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\RotateHHover.png"));
			rotateh = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\RotateV.png"));
			rotatehhover = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\RotateVHover.png"));

			confirm = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\Confirm.png"));
			confirmhover = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\ConfirmHover.png"));
			confirmclick = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\ConfirmClick.png"));

			spawn = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\Spawn.png"));
			spawnhover = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\SpawnHover.png"));
			spawnclick = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Spawn\\SpawnClick.png"));

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
		// For loop should cover only the grid, (only one element under the grid, the
		// frame, ergo, i=1)
		for (int i = 0; i < Grid.getChildren().size(); i++) {
			ImageView temp = (ImageView) Grid.getChildren().get(i);
			temp.setImage(gridsquare);

		}
		spawnFrame.setImage(spawnframe);
		confirmBtn.setImage(confirm);
		rotateBtn.setImage(rotateh);
		spawnAircraftBtn.setImage(spawn);
		spawnBattleshipBtn.setImage(spawn);
		spawnSubmarineBtn.setImage(spawn);
		spawnDestroyerBtn.setImage(spawn);
		spawnPatrolBtn.setImage(spawn);
		Grid.setVisible(true);
		spawnManual.setVisible(false);

	}

	public void handleGrid(Event event) {
		String type = event.getEventType().toString();
		ImageView source = (ImageView) event.getSource();

		int index = Grid.getChildren().indexOf(source);
		int hCoord = index % 10;
		int vCoord = index / 10;

		switch (type) {

		case "MOUSE_ENTERED":

			try {
				if (aircraftselected == true) {
					if (10 >= battleshipLogic.uAircraftC.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uAircraftC.getShipSize() + hCoord && rotate == true) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 1;
						}
					}
				}

				if (battleshipselected == true) {
					if (10 >= battleshipLogic.uBattleship.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uBattleship.getShipSize() + hCoord && rotate == true) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 1;
						}
					}
				}

				if (submarineselected == true) {
					if (10 >= battleshipLogic.uSubmarine.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uSubmarine.getShipSize() + hCoord && rotate == true) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 1;
						}
					}
				}

				if (destroyerselected == true) {
					if (10 >= battleshipLogic.uDestroyer.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uDestroyer.getShipSize() + hCoord && rotate == true) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 1;
						}
					}
				}

				if (patrolselected == true) {
					if (10 >= battleshipLogic.uPatrol.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uPatrol.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(selectsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uPatrol.getShipSize() + hCoord && rotate == true) {
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
					if (10 >= battleshipLogic.uAircraftC.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uAircraftC.getShipSize() + hCoord && rotate == true) {
						for (int i = 0; i < battleshipLogic.uAircraftC.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
					}
				}

				if (battleshipselected == true) {
					if (10 >= battleshipLogic.uBattleship.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uBattleship.getShipSize() + hCoord && rotate == true) {
						for (int i = 0; i < battleshipLogic.uBattleship.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
					}
				}

				if (submarineselected == true) {
					if (10 >= battleshipLogic.uSubmarine.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uSubmarine.getShipSize() + hCoord && rotate == true) {
						for (int i = 0; i < battleshipLogic.uSubmarine.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
					}
				}

				if (destroyerselected == true) {
					if (10 >= battleshipLogic.uDestroyer.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uDestroyer.getShipSize() + hCoord && rotate == true) {
						for (int i = 0; i < battleshipLogic.uDestroyer.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 1;
						}
					}
				}

				if (patrolselected == true) {
					if (10 >= battleshipLogic.uPatrol.getShipSize() + vCoord && rotate == false) {
						for (int i = 0; i < battleshipLogic.uPatrol.getShipSize(); i++) {
							ImageView line = (ImageView) Grid.getChildren().get(index);
							line.setImage(gridsquare);
							index += 10;
						}
					} else if (10 >= battleshipLogic.uPatrol.getShipSize() + hCoord && rotate == true) {
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
			//----------------------------------------------------
			if (aircraftselected) {
				if (rotate == false) {

				} else {

				}
			//----------------------------------------------------
			} else if (battleshipselected) {
				if (rotate == false) {

				} else {

				}
			//----------------------------------------------------
			} else if (submarineselected) {
				if (rotate == false) {

				} else {

				}
			//----------------------------------------------------
			} else if (destroyerselected) {
				if (rotate == false) {

				} else {

				}
			//----------------------------------------------------
			} else if (patrolselected) {
				if (rotate == false) {

				} else {

				}
			}

			break;

		case "MOUSE_RELEASED":

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

		// scan area made.. use it later
//				try {
//					int center = spawnGrid.getChildren().indexOf(event.getSource());
//					int rowSel = -20;
//					
//					for (int i = 0; i < 3; i++) {
//						rowSel += 10;
//						for (int j = 0; j < 3; j++) {
//							ImageView temp = (ImageView) spawnGrid.getChildren().get(center + rowSel + j - 1);
//							temp.setImage(selectsquare);
//						}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}

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

//				try {
//					int center = spawnGrid.getChildren().indexOf(event.getSource());
//					int rowSel = -20;
//					
//					for (int i = 0; i < 3; i++) {
//						rowSel += 10;
//						for (int j = 0; j < 3; j++) {
//							ImageView temp = (ImageView) spawnGrid.getChildren().get(center + rowSel + j - 1);
//							temp.setImage(gridsquare);
//						}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}

		case "MOUSE_PRESSED":

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
				confirmBtn.setImage(confirmclick);
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
					manualSelected = false;
					// Back to Page1
					manualBtn.setVisible(true);
					spawnQuestion.setVisible(true);
					randomBtn.setVisible(true);
					// Turn off Page2
					spawnManual.setVisible(false);
					spawnFrame.setVisible(false);
					confirmBtn.setVisible(false);
				}
				break;

			case "randomBtn":
				randomBtn.setImage(randomhover);
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
				confirmBtn.setVisible(true);
				break;

			case "confirmBtn":
				confirmBtn.setImage(confirmhover);
				break;
			}
			break;

		}
	}

}

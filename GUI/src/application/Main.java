package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	static Stage window;
	static Scene menuScreen, tutorialScreen, spawnScreen, battleScreen;
	static BattleshipLogic battleshipLogic = new BattleshipLogic();

	public static void StartBattleShip(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		try {
			// BE VERY CAREFUL, IF YOU CALL THE SAME fxml FILE IT WILL RUN INITIALIZE
			// AGAIN!!!!!!! TIME WASTED ON FINDING OUT 3HOURS!!!!!!
			// BorderPane intro =
			// (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			// introScreen = new Scene(intro,1024,768);
			// introScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			BorderPane menu = (BorderPane) FXMLLoader.load(getClass().getResource("MenuScreen.fxml"));
			menuScreen = new Scene(menu, 1024, 768);
			menuScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			BorderPane tutorial = (BorderPane) FXMLLoader.load(getClass().getResource("TutorialScreen.fxml"));
			tutorialScreen = new Scene(tutorial, 1024, 768);
			tutorialScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			BorderPane spawn = (BorderPane) FXMLLoader.load(getClass().getResource("SpawnScreen.fxml"));
			spawnScreen = new Scene(spawn, 1024, 768);
			spawnScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			BorderPane battle = (BorderPane) FXMLLoader.load(getClass().getResource("BattleScreen.fxml"));
			battleScreen = new Scene(battle, 1024, 768);
			battleScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			window.setScene(menuScreen);
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

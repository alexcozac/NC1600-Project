package application;

import java.io.File;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	static Stage window;
	static Scene menuScreen, tutorialScreen, spawnScreen, battleScreen;
	

	Path appDirectory;
	static public String path;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		try {
			appDirectory = new File(System.getProperty("user.dir")).toPath();
			path = appDirectory + "\\src\\application\\GUI Images";
			// BE VERY CAREFUL, IF YOU CALL THE SAME fxml FILE IT WILL RUN INITIALIZE
			// AGAIN!!!!!!! TIME WASTED ON FINDING OUT 3HOURS!!!!!!
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
			

			window.setScene(menuScreen);
			window.show();

		} catch (Exception e) {
		}

	}

}

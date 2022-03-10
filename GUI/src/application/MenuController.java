package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController extends Main implements Initializable, EventHandler{
	

//	static Stage window;
//	static Scene introScreen;
//	static Scene menuScreen;
//	static Scene spawnScreen;
//	static Scene tutorialScreen;
//	static Scene battleScreen;
	
	@FXML MediaView Intro = new MediaView();
	@FXML ImageView startBtn = new ImageView();
	@FXML ImageView tutorialBtn = new ImageView();
	@FXML ImageView menuTitle = new ImageView();
	@FXML MediaView videoBG = new MediaView();
	@FXML MediaView menuAudio = new MediaView();
	Image start, starthover, startclick;
	Image tutorial, tutorialhover, tutorialclick;
	Image menutitle;
	
	
	//Resources for autoPlayFiles
	Media intro = new Media(new File(
			"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\IntroMovie.mp4")
			.toURI().toString());
	Media video = new Media(new File(
			"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Background.mp4")
					.toURI().toString());
	Media audio = new Media(new File(
			"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\MenuMusic.mp3")
					.toURI().toString());
	
	
	MediaPlayer movie = new MediaPlayer(intro);
	MediaPlayer player = new MediaPlayer(video);
	MediaPlayer audioplayer = new MediaPlayer(audio);
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
		//	start = new Image("/GUI Images/StartGame.png"); 
			start = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\StartGame.png"));
			starthover = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\StartGameHover.png"));
			startclick = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\StartGameClick.png"));

		//	start = new Image(new FileInputStream("/GUI Images/StartGame.png"));
			
			tutorial = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\Tutorial.png"));
			tutorialhover = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\TutorialHover.png"));
			tutorialclick = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\TutorialClick.png"));
			menutitle = new Image(new FileInputStream(
					"C:\\Users\\alex_\\OneDrive\\Desktop\\Uni\\02.First Year\\NC1600 - Group Project\\Assessment A+B\\Github\\NC1600-Project\\GUI\\src\\application\\GUI Images\\MenuTitle.png"));
			
			
			Intro.setMediaPlayer(movie);
			Intro.setFocusTraversable(true);
			movie.play();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 


	}
	
	
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		String type = event.getEventType().toString();
		switch (type) {
		
		case "MOUSE_ENTERED":
			
			if(event.getSource()==startBtn) {
				startBtn.setImage(starthover);
			}
			else if(event.getSource()==tutorialBtn) {
				tutorialBtn.setImage(tutorialhover);
			}
			
			break;
			
		case "MOUSE_EXITED":
			
			if(event.getSource()==startBtn) {
				startBtn.setImage(start);
			}
			else if(event.getSource()==tutorialBtn) {
				tutorialBtn.setImage(tutorial);
			}
			
			break;
			
		case "MOUSE_PRESSED":
			
			if(event.getSource()==startBtn) {
				startBtn.setImage(startclick);
			}
			else if(event.getSource()==tutorialBtn) {
				tutorialBtn.setImage(tutorialclick);
			}
			
			break;
			
		case "MOUSE_RELEASED":
			
			if(event.getSource()==startBtn) {
				startBtn.setImage(starthover);
				window.setScene(spawnScreen);
			}
			else if(event.getSource()==tutorialBtn) {
				tutorialBtn.setImage(tutorialhover);
				window.setScene(tutorialScreen);
			}
			
			break;
			
		case "KEY_TYPED":
			
			if (event.getSource()==Intro) {
				movie.stop();
				Intro.toBack();
				Intro.setDisable(true);
				
				
				player.setAutoPlay(true);
				player.setOnEndOfMedia(new Runnable() {
				       public void run() {
				           player.seek(Duration.ZERO);
				         }
				     });
				
				audioplayer.setAutoPlay(true);
				audioplayer.setVolume(0.2);
				audioplayer.setOnEndOfMedia(new Runnable() {
				       public void run() {
				           audioplayer.seek(Duration.ZERO);
				         }
				     });
				
				videoBG.setMediaPlayer(player);
				menuTitle.setImage(menutitle);
				menuAudio.setMediaPlayer(player);
				startBtn.setImage(start);
				tutorialBtn.setImage(tutorial);
				
				
				
			}
			break;
		
		//continue
		}
	
	}

}

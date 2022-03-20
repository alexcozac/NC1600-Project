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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MenuController extends Main implements Initializable, EventHandler{
	
	
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
	Media intro = new Media(new File(path + "\\IntroMovie.mp4").toURI().toString());
	Media video = new Media(new File(path + "\\Background.mp4").toURI().toString());
	static Media audio = new Media(new File(path + "\\MenuMusic.mp3").toURI().toString());
	
	
	MediaPlayer movie = new MediaPlayer(intro);
	MediaPlayer player = new MediaPlayer(video);
	static MediaPlayer audioplayer = new MediaPlayer(audio);
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		start = new Image(path + "\\StartGame.png"); 
		starthover = new Image(path + "\\StartGameHover.png");
		startclick = new Image(path + "\\StartGameClick.png");
		
		tutorial = new Image(path + "\\Tutorial.png");
		tutorialhover = new Image(path + "\\TutorialHover.png");
		tutorialclick = new Image(path + "\\TutorialClick.png");
		menutitle = new Image(path + "\\MenuTitle.png");
				
		Intro.setMediaPlayer(movie);
		Intro.setFocusTraversable(true);
		movie.play();
	}
	
	
	@Override
	public void handle(Event event) {
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
				
				audioplayer.play();
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

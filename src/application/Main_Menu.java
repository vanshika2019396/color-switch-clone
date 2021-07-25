package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main_Menu extends Application {
	MediaPlayer gameMediaPlayer;
	
	@Override
    public void start(Stage stage) throws Exception{
		Media gameSound = new Media(getClass().getResource("Color Switch").toString());
		gameMediaPlayer = new MediaPlayer(gameSound);
		gameMediaPlayer.setAutoPlay(true);
        gameMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        gameMediaPlayer.play();
		Parent mainPage =FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(mainPage,667,500);
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();
    }
	
	public static void main(String[] args) {
        launch(args);
    }
}
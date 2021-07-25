package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PauseScreen extends Application {
	@Override
    public void start(Stage stage) throws Exception{
    	Parent paused=FXMLLoader.load(getClass().getResource("pause_screen.fxml"));
        Scene scene = new Scene(paused,605,453);
        stage.setTitle("Game Paused");
        stage.setScene(scene);
        stage.show();

    }
	
	@FXML
	public void save(MouseEvent e) throws IOException {
		GamePlay.saveData();
		/*Stage stage = new Stage();
		Parent mainPage=FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(mainPage,667,500);
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();*/
        
        ((ImageView) e.getSource()).getScene().getWindow().hide(); // Hide the previously opened window
	}
	
	@FXML
	public void exit(MouseEvent e) throws IOException {
		System.exit(0);
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
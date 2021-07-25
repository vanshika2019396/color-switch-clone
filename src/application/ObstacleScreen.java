package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ObstacleScreen extends Application{
	
	@Override
    public void start(Stage stage) throws Exception{
		Parent mainPage=FXMLLoader.load(getClass().getResource("obstacle.fxml"));
        Scene scene = new Scene(mainPage,600,448);
        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
	public void exit(MouseEvent e) throws IOException {
		System.exit(0);
	}
}

package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PauseScreenController {
	@FXML
	public void loadMainMenu(MouseEvent e) throws IOException {
		Stage stage = new Stage();
		Parent mainPage=FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(mainPage,667,500);
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();
        
        ((ImageView) e.getSource()).getScene().getWindow().hide(); // Hide the previously opened window
	}
	
	@FXML
	public void exit(MouseEvent e) throws IOException {
		System.exit(0);
	}

}

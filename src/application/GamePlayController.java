package application;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GamePlayController {
	@FXML
	public void pauseGame(MouseEvent e) throws Exception {
		Stage stage = new Stage();
		new PauseScreen().start(stage); // Pause the GamePlay
	    ((ImageView) e.getSource()).getScene().getWindow().hide(); // Hide the previously opened window
	}
		
	@FXML
	public void gameOverLoader(MouseEvent e) throws Exception {
		Stage s = new Stage();
		new ObstacleScreen().start(s); // Game Over Screen
		((ImageView) e.getSource()).getScene().getWindow().hide(); // Hide the previously opened window
	}
}
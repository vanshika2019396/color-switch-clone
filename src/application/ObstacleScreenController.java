package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ObstacleScreenController {
	@FXML
	public void restart(MouseEvent e) throws Exception {
		((ImageView) e.getSource()).getScene().getWindow().hide(); // Hide the previously opened window
		
		boolean previous = false;
		for(DataTable dt : Main_MenuController.db.getDatabase()) {
			if(dt.getGameID() == GamePlay.gameID) {
				previous = true;
				GamePlay.score = 0;
				GamePlay.saveData();
			}
		}
		if(!previous && GamePlay.score >= 5) {
			Main_MenuController.db.getDatabase().remove(Main_MenuController.db.getDatabase().size()-1);
		}
		
		Stage s = new Stage();
		new GamePlay().start(s);
		GamePlay.gameID = (int) (Math.random() * 1000);
	}
	
	@FXML
	public void continueGame(MouseEvent e) throws Exception {
		if(GamePlay.score < 5) {
			Alert a = new Alert(AlertType.INFORMATION);
	        a.setContentText("Oops! your current score is less than 5");
	        a.show();
			return;
		}
		
		GamePlay.score -= 5;
		GamePlay.saveData();
		
		int index = 0;
		for(DataTable dt : Main_MenuController.db.getDatabase()) {
			if(dt.getGameID() == GamePlay.gameID) {
				LoadGame.startGame(index);
				((ImageView) e.getSource()).getScene().getWindow().hide(); // Hide the previously opened window
				return;
			}
			index++;
		}
	}
	
	@FXML
	public void exit() {
		boolean previous = false;
		for(DataTable dt : Main_MenuController.db.getDatabase()) {
			if(dt.getGameID() == GamePlay.gameID) {
				previous = true;
				GamePlay.score = 0;
				GamePlay.saveData();
			}
		}
		if(!previous && GamePlay.score >= 5) {
			Main_MenuController.db.getDatabase().remove(Main_MenuController.db.getDatabase().size()-1);
		}
		
		System.exit(0);
	}
}
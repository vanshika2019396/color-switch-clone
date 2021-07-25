package application;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;

public class Main_MenuController {
	static Database db;
	static ListView<String> gameList = new ListView<>();
	
	public void addGames() {
		int i=1;
		for(DataTable dt : db.getDatabase()) {
			gameList.getItems().add(i+ ". " + "Game ID: "+ dt.getGameID() + ", " + "Stars Collected: " + dt.getScore());
			i++;
		}
	}
	
	@FXML
	public void resume(MouseEvent e) throws Exception, ClassNotFoundException, IOException, FileNotFoundException{		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("database.txt"));
			db = (Database) in.readObject();
			
			addGames();
			gameList.setLayoutX(185);
			gameList.setLayoutY(95);
			gameList.setMaxHeight(250);
			gameList.setFixedCellSize(50);
			
			Stage stage = new Stage();
			new LoadGame().start(stage);
	    
			in.close();
			
			((ImageView) e.getSource()).getScene().getWindow().hide(); // Hide the previously opened window
		}
		catch (Exception ex) {
			Alert a = new Alert(AlertType.INFORMATION);
	        a.setContentText("No Saved Game Data!");
	        a.show();
		}
	}
	
	@FXML
	public void loader(MouseEvent e) throws Exception {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("database.txt"));
			db = (Database) in.readObject();
			in.close();
		}
		catch (Exception ex) {
			db = new Database();
		}
		
		Stage s = new Stage();
		GamePlay game = new GamePlay();
		game.start(s); // Start the GamePlay
		GamePlay.gameID = (int) (Math.random() * 1000);
		((ImageView) e.getSource()).getScene().getWindow().hide(); // Hide the previously opened window
	}
	
	@FXML
	public void exit(MouseEvent e) throws Exception {
		System.exit(0);
	}
}
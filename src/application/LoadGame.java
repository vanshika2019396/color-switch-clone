package application;

import java.io.File;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadGame  extends Application {
	@Override
    public void start(Stage stage) throws Exception{
    	AnchorPane loadGame = FXMLLoader.load(getClass().getResource("resume_game.fxml"));
        Scene scene = new Scene(loadGame ,605, 440);
        stage.setTitle("Resume Game");
        stage.setScene(scene);
        stage.show();
        
        loadGame.getChildren().add(Main_MenuController.gameList);
        
        Main_MenuController.gameList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        Main_MenuController.gameList.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	String gameOpen = Main_MenuController.gameList.getSelectionModel().getSelectedItem();
            	
            	try {
					startGame(Integer.parseInt(String.valueOf(gameOpen.charAt(0))) - 1);
					stage.hide();
				}
            	catch (Exception e) {
				}
            	
            }
        });
    }
	
	public static void startGame(int index) throws Exception {	
		DataTable dt = Main_MenuController.db.getDatabase().get(index);
		
		dt.setGameID(dt.getGameID()); // Give the same ID as Saved Game
		Stage s = new Stage();
		GamePlay game = new GamePlay();
		GamePlay.saved = true;
		GamePlay.gameID = dt.getGameID();
		GamePlay.ball.setFill(dt.getColor());
		GamePlay.score = dt.getScore();
		GamePlay.scoreText.setText(Integer.toString(GamePlay.score));
		game.start(s);
    }
	
	@FXML
	public void deleteProgress(MouseEvent e) throws Exception{
		File file = new File("database.txt");
        file.delete();
        
        ((ImageView) e.getSource()).getScene().getWindow().hide(); // Hide the previously opened window
        Stage s = new Stage();
        new Main_Menu().start(s);
        
        Alert a = new Alert(AlertType.INFORMATION);
        a.setContentText("Game Progress Deleted!");
        a.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

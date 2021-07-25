package application;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlay extends Application {
	MediaPlayer bounceMediaPlayer;
	static AnchorPane pane;
	double y = 300;
	ArrayList<RotatingCircle> rotatingCircles;
	ArrayList<DoubleCircle> doubleCircles;
	ArrayList<ColorCircle> colorCircles;
	ArrayList<ColorRectangle> colorRectangles;
	double angle; double deltaTheta;
	static Circle ball = BallsCollision.getInstance();
	static int score = 0;
	static Text scoreText;
	static int gameID;
	static boolean saved;
	
	public GamePlay() {
		scoreText = new Text();
		scoreText.setFill(Color.WHITE);  
		scoreText.setStroke(Color.BLUE);
		scoreText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40)); 
		scoreText.setX(175);
		scoreText.setY(60);
		scoreText.setText("0");
		rotatingCircles = new ArrayList<>();
		doubleCircles = new ArrayList<>();
		colorCircles = new ArrayList<>();
		colorRectangles = new ArrayList<>();
		angle = 0;
		deltaTheta = 1;
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("main_game.fxml"));

		pane = fxmlLoader.load();
	
		Media bounceSound = new Media(getClass().getResource("Ball Bounce").toString());
        
        bounceMediaPlayer = new MediaPlayer(bounceSound);
        bounceMediaPlayer.setAutoPlay(false);
        bounceMediaPlayer.setCycleCount(1);
        bounceMediaPlayer.play();

		pane.getChildren().add(ball);
		pane.getChildren().add(scoreText);

		Scene scene=new Scene(pane, 730, 550);
		stage.setTitle("Game Play");
		stage.setScene(scene);
		stage.show();

		spawnObstacles(pane);

		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> moveUp(event));

		applyGravity();

	}

	public void moveUp(KeyEvent event) {
		if(event.getCode() == KeyCode.SPACE) {
			bounceMediaPlayer.seek(bounceMediaPlayer.getStartTime());
			Timeline t = new Timeline();
			KeyFrame moveUp = new KeyFrame(Duration.millis(4), event1 -> {
				if(ball.getCenterY() <= 390 && ball.getCenterY() >= 200) {
					for(ColorCircle cc : colorCircles) {
						cc.applyFall();
					}

					for(RotatingCircle c : rotatingCircles) {
						c.applyFall();
					}
					
					for(DoubleCircle dc : doubleCircles) {
						dc.applyFall();
					}

					for(ColorRectangle r : colorRectangles) {
						r.applyFall();
					}
				}
				if(ball.getCenterY() >= 200) {
					ball.setCenterY(ball.getCenterY() - 3);
				}
			});
			t.setCycleCount(25);
			t.getKeyFrames().add(moveUp);
			t.play();
		}	
	}

	private void applyGravity() {
		Timeline gravity = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent t) 
			{
				if(ball.getCenterY() < 480) {
					ball.setCenterY(ball.getCenterY() + 1.5);
				}
			}
		}));
		gravity.setCycleCount(Timeline.INDEFINITE);
		gravity.play();
	}

	public void spawnObstacles(AnchorPane pane) {
		Timeline t = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
			
			//ADD ROTATING CIRCLES INCLUDING STARS and ADD COLOR CIRCLES				
			RotatingCircle c = new RotatingCircle(y, 90);
			rotatingCircles.add(c);
			c.startRotation(pane.getScene(), 1.3);
			pane.getChildren().add(c.root);

			y-=200;
			
			ColorCircle cc = new ColorCircle(y);
			colorCircles.add(cc);
			pane.getChildren().add(cc.root);
			cc.collision(ball);

			//ADD COLOR RECTANGLES
			
			y-=100;
			ColorRectangle r = new ColorRectangle(y);
			pane.getChildren().add(r.root);
			r.startTransition(3);
			colorRectangles.add(r);
			r.collision(pane.getScene());
			
			//ADD DOUBLE CIRCLES
			
			y-=350;
			
			DoubleCircle dc = new DoubleCircle(y, y-215, 100, 100);
			doubleCircles.add(dc);
			dc.startRotation(pane, 0.8, 1.2);

			y-=550;

		}));
		t.setCycleCount(10);
		t.play();
	}
	
	public static void saveData() {
		Color x = ((Color) ball.getFill());
		
		//Check whether gameID is already present
		
		for(DataTable dt : Main_MenuController.db.getDatabase()) {
			if(gameID == dt.getGameID()) {
				dt.setColor((int) (x.getRed() * 255), (int) (x.getGreen() * 255), (int) (x.getBlue() * 255));
				dt.setScore(score);
				
				dt.updateGame();
				return;
			}
		}
		
		DataTable dt = new DataTable(gameID, score, (int) (x.getRed() * 255), (int) (x.getGreen() * 255), (int) (x.getBlue() * 255));
		dt.saveGame();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
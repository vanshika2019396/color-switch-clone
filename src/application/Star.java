package application;

import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Star {
	Circle coin;
	private double x = 365;
	private double y;
	private double radius = 15;
	
	public Star(double y) {
		this.coin = new Circle();
		this.y = y;
		coin.setRadius(radius);
		coin.setCenterX(x);
		coin.setCenterY(y);
	}
	
	public void addToPane(AnchorPane pane) throws FileNotFoundException {
		Image im = new Image("/application/star_yellow.png", false);
		coin.setFill(new ImagePattern(im));
		pane.getChildren().add(coin);
		
		
	}
	
	public void collision(Circle ball) {
		Star s = this;
		Timeline checkCollision = new Timeline();
		KeyFrame k = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent t) 
			{
				if(BallsCollision.checkBallStarCollision(ball, s.coin)) {
					GamePlay.score++;
					GamePlay.scoreText.setText(Integer.toString(GamePlay.score));
					s.coin.setCenterY(1000);
				}
			}
		});
		checkCollision.getKeyFrames().add(k);
		checkCollision.setCycleCount(Timeline.INDEFINITE);
		checkCollision.play();
	}
	
	public void applyFall() {
		Circle s = this.coin;
		
		Timeline bringCenterDown = new Timeline(new KeyFrame(Duration.millis(2), new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent t) 
			{
				s.setCenterY(s.getCenterY() + 0.1);
			}
		}));
		bringCenterDown.setCycleCount(20);
		bringCenterDown.play();
	};
}
package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ColorCircle {
	Group root;
	double x_center = 365;
	double y_center, radius;
	Arc arc1, arc2, arc3, arc4;
	Color c1 = Color.rgb(250,225,0);
	Color c2 = Color.rgb(144,13,255);
	Color c3 = Color.rgb(255,1,129);
	Color c4 = Color.rgb(50,219,240);
	
	public ColorCircle(double y) {
		arc1 = new Arc();
		arc2 = new Arc();
		arc3 = new Arc();
		arc4 = new Arc();
		y_center = y;
		radius = 15;
		init();
	}
	
	public Group init() {
		root = new Group();
		arc1.setCenterX(x_center);
		arc1.setCenterY(y_center);
		arc1.setRadiusX(radius);
		arc1.setRadiusY(radius);
		arc1.setStartAngle(0);
		arc1.setLength(90.37167f);
		arc1.setType(ArcType.ROUND);
		arc1.setFill(c1);

		arc2.setCenterX(x_center);
		arc2.setCenterY(y_center);
		arc2.setRadiusX(radius);
		arc2.setRadiusY(radius);
		arc2.setStartAngle(90);
		arc2.setLength(90.37167f);
		arc2.setType(ArcType.ROUND);
		arc2.setFill(c2);

		arc3.setCenterX(x_center);
		arc3.setCenterY(y_center);
		arc3.setRadiusX(radius);
		arc3.setRadiusY(radius);
		arc3.setStartAngle(180);
		arc3.setLength(90.37167f);
		arc3.setType(ArcType.ROUND);
		arc3.setFill(c3);

		arc4.setCenterX(x_center);
		arc4.setCenterY(y_center);
		arc4.setRadiusX(radius);
		arc4.setRadiusY(radius);
		arc4.setStartAngle(270);
		arc4.setLength(90.37167f);
		arc4.setType(ArcType.ROUND);
		arc4.setFill(c4);

		root = new Group(arc1,arc2,arc3,arc4);
		return root;
	}
	
	public void collision(Circle ball) {
		ColorCircle c = this;
		
		Timeline checkCollision = new Timeline();
		KeyFrame k = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent t) 
			{
				if(BallsCollision.checkCollisionRange(ball, c.arc1)) {
					c.arc1.setCenterY(1000);
					c.arc2.setCenterY(1000);
					c.arc3.setCenterY(1000);
					c.arc4.setCenterY(1000);
					
					Random r = new Random();
					int toChange = r.nextInt(4) + 1;
					
					switch (toChange) {
					case 1 :
						ball.setFill(c1);
						break;

					case 2 :
						ball.setFill(c2);
						break;
					
					case 3 :
						ball.setFill(c3);
						break;
						
					case 4 :
						ball.setFill(c4);
						break;
						
					default:
						break;
					}
				}
			}
		});
		checkCollision.getKeyFrames().add(k);
		checkCollision.setCycleCount(Timeline.INDEFINITE);
		checkCollision.play();
	}
	
	public void applyFall() {
		ColorCircle c = this;
		Timeline bringCenterDown = new Timeline(new KeyFrame(Duration.millis(2), new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent t) 
			{
				c.arc1.setCenterY(c.arc1.getCenterY() + 0.1);
				c.arc2.setCenterY(c.arc2.getCenterY() + 0.1);
				c.arc3.setCenterY(c.arc3.getCenterY() + 0.1);
				c.arc4.setCenterY(c.arc4.getCenterY() + 0.1);
			}
		}));
		bringCenterDown.setCycleCount(20);
		bringCenterDown.play();
	}
}
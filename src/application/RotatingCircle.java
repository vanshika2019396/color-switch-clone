package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RotatingCircle {
	Group root;
	Star star;
	double x_center = 365;
	double y_center, radius, angle;
	Arc arc1, arc2, arc3, arc4;
	Color c1 = Color.rgb(250,225,0);
	Color c2 = Color.rgb(144,13,255);
	Color c3 = Color.rgb(255,1,129);
	Color c4 = Color.rgb(50,219,240);

	public RotatingCircle(double x, double y, double r) {
		star = new Star(y-5);
		arc1 = new Arc();
		arc2 = new Arc();
		arc3 = new Arc();
		arc4 = new Arc();
		x_center = x;
		y_center = y;
		radius = r;
		angle = 0;
		init();
	}
	
	public RotatingCircle(double y, double r) {
		star = new Star(y-5);
		arc1 = new Arc();
		arc2 = new Arc();
		arc3 = new Arc();
		arc4 = new Arc();
		y_center = y;
		radius = r;
		angle = 0;
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
		arc1.setType(ArcType.OPEN);
		arc1.setStroke(c1);
		arc1.setStrokeWidth(13);

		arc2.setCenterX(x_center);
		arc2.setCenterY(y_center);
		arc2.setRadiusX(radius);
		arc2.setRadiusY(radius);
		arc2.setStartAngle(90);
		arc2.setLength(90.37167f);
		arc2.setType(ArcType.OPEN);
		arc2.setStroke(c2);
		arc2.setStrokeWidth(13);

		arc3.setCenterX(x_center);
		arc3.setCenterY(y_center);
		arc3.setRadiusX(radius);
		arc3.setRadiusY(radius);
		arc3.setStartAngle(180);
		arc3.setLength(90.37167f);
		arc3.setType(ArcType.OPEN);
		arc3.setStroke(c3);
		arc3.setStrokeWidth(13);

		arc4.setCenterX(x_center);
		arc4.setCenterY(y_center);
		arc4.setRadiusX(radius);
		arc4.setRadiusY(radius);
		arc4.setStartAngle(270);
		arc4.setLength(90.37167f);
		arc4.setType(ArcType.OPEN);
		arc4.setStroke(c4);
		arc4.setStrokeWidth(13);
		
		Image im = new Image("/application/star_yellow.png", false);
		star.coin.setFill(new ImagePattern(im));

		root = new Group(star.coin, arc1,arc2,arc3,arc4);
		return root;
	}
	
	public void startRotation(Scene scene, double deltaTheta) {
		RotatingCircle c = this;
		Timeline time = new Timeline();

		KeyFrame rotatingCircle = new KeyFrame(Duration.millis(10), event -> 
		{			
			angle=(angle+deltaTheta)%360;
			c.arc1.setStartAngle((c.arc1.getStartAngle()+deltaTheta)%360);
			c.arc2.setStartAngle((c.arc2.getStartAngle()+deltaTheta)%360);
			c.arc3.setStartAngle((c.arc3.getStartAngle()+deltaTheta)%360);
			c.arc4.setStartAngle((c.arc4.getStartAngle()+deltaTheta)%360);

			//TEST COLLISION
			boolean collisionLower = false;
			boolean collisionUpper = false;

			if(angle % 360 < 90) {
				if(BallsCollision.checkCollisionBottomRange(GamePlay.ball, c.arc3)) {
					if(!BallsCollision.checkCollision(GamePlay.ball, c.arc3)){
						collisionLower = true;
					}
				}
				if(BallsCollision.checkCollisionUpRange(GamePlay.ball, c.arc1)) {
					if(!BallsCollision.checkCollision(GamePlay.ball, c.arc1)){
						collisionUpper = true;
					}
				}
			}
			else if(angle % 360 < 180){
				if(BallsCollision.checkCollisionBottomRange(GamePlay.ball, c.arc2)) {
					if(!BallsCollision.checkCollision(GamePlay.ball, c.arc2)){
						collisionLower = true;
					}
				}

				if(BallsCollision.checkCollisionUpRange(GamePlay.ball, c.arc4)) {
					if(!BallsCollision.checkCollision(GamePlay.ball, c.arc4)){
						collisionUpper = true;
					}
				}
			}
			else if(angle % 360 < 270) {
				if(BallsCollision.checkCollisionBottomRange(GamePlay.ball, c.arc1)) {
					if(!BallsCollision.checkCollision(GamePlay.ball, c.arc1)){
						collisionLower = true;
					}
				}

				if(BallsCollision.checkCollisionUpRange(GamePlay.ball, c.arc3)) {
					if(!BallsCollision.checkCollision(GamePlay.ball, c.arc3)){
						collisionUpper = true;
					}
				}
			}
			else {
				if(BallsCollision.checkCollisionBottomRange(GamePlay.ball, c.arc4)) {
					if(!BallsCollision.checkCollision(GamePlay.ball, c.arc4)){
						collisionLower = true;
					}
				}

				if(BallsCollision.checkCollisionUpRange(GamePlay.ball, c.arc2)) {
					if(!BallsCollision.checkCollision(GamePlay.ball, c.arc2)){
						collisionUpper = true;
					}
				}
			}

			if(collisionLower || collisionUpper) {					
				try {
					Stage stage1 = new Stage();
					new ObstacleScreen().start(stage1); // Game Over
					time.stop();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if(GamePlay.score >= 5) {
						GamePlay.saveData();
					}
					scene.getWindow().hide(); // Hide the Game Play
				}
			}
			
			if(arc1.getCenterY() > 700) {
				time.stop();
			}
		});
		
		c.star.collision(GamePlay.ball);

		time.getKeyFrames().add(rotatingCircle);
		time.setCycleCount(Timeline.INDEFINITE);
		time.play();
	}

	public void applyFall() {
		RotatingCircle c = this;
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
		
		c.star.applyFall();
	}
}
package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ColorRectangle {
	Group root;
	Rectangle r1, r2, r3, r4, r5;
	double x1, x2, x3, x4, y;
	double width = 182.5;
	double height = 10;
	
	public ColorRectangle(double y){
		x1 = 0;
		x2 = 182.5;
		x3 = 365;
		x4 = 547.5;
		this.y = y;
		r1 = new Rectangle(x1, y, width, height);
		r2 = new Rectangle(x2, y, width, height);
		r3 = new Rectangle(x3, y, width, height);
		r4 = new Rectangle(x4, y, width, height);
		r5 = new Rectangle(730, y, width, height);
		r1.setFill(Color.rgb(250,225,0));
		r2.setFill(Color.rgb(144,13,255));
		r3.setFill(Color.rgb(255,1,129));
		r4.setFill(Color.rgb(50,219,240));
		root = new Group(r1, r2, r3, r4, r5);
	}
	
	public void startTransition(double deltaMove) {
		ColorRectangle r = this;
		
		Timeline transition = new Timeline();
		
		KeyFrame frame = new KeyFrame(Duration.millis(10), event ->
		{
			Rectangle swap = new Rectangle();
			
			if(r1.getX() >= 730) {
				swap = r1;
			}
			else if(r2.getX() >= 730) {
				swap = r2;
			}
			else if(r3.getX() >= 730) {
				swap = r3;
			}
			else if(r4.getX() >= 730) {
				swap = r4;
			}
			else if(r5.getX() >= 730) {
				swap = r5;
			}
			
			if(r1.getX() <= 0 && r1.getX() > -182.3) {
				swap.setFill(r1.getFill());
			}
			else if(r2.getX() <= 0 && r2.getX() > -182.3) {
				swap.setFill(r2.getFill());
			}
			else if(r3.getX() <= 0 && r3.getX() > -182.3) {
				swap.setFill(r3.getFill());
			}
			else if(r4.getX() <= 0 && r4.getX() > -182.3) {
				swap.setFill(r4.getFill());
			}
			else if(r5.getX() <= 0 && r5.getX() > -182.3) {
				swap.setFill(r5.getFill());
			}
			
			r1.setX(r1.getX() - deltaMove);
			r2.setX(r2.getX() - deltaMove);
			r3.setX(r3.getX() - deltaMove);
			r4.setX(r4.getX() - deltaMove);
			r5.setX(r5.getX() - deltaMove);
			
			if(r1.getX() <= -182.3) {
				r1.setX(730);
			}
			else if(r2.getX() <= -182.3) {
				r2.setX(730);
			}
			else if(r3.getX() <= -182.3) {
				r3.setX(730);
			}
			else if(r4.getX() <= -182.3) {
				r4.setX(730);
			}
			else if(r5.getX() <= -182.3) {
				r5.setX(730);
			}
			
			if(r1.getY() > 600) {
				transition.stop();
			}
		});
		
		transition.getKeyFrames().add(frame);
		transition.setCycleCount(Timeline.INDEFINITE);
		transition.play();
	}
	
	public void collision(Scene scene) {
		ColorRectangle r = this;
		
		Timeline checkCollision = new Timeline();
		KeyFrame k = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent t) 
			{
				double top = GamePlay.ball.getCenterY() + GamePlay.ball.getRadius();
				boolean collision = false;
				Color c1 = (Color) GamePlay.ball.getFill();
				
				if(top > r.r1.getY() && top < r.r1.getY()+r.height) {
					Color c2 = (Color) r1.getFill();
					if(c1.getRed() == c2.getRed() && c1.getGreen() == c2.getGreen() && c1.getBlue()==c2.getBlue()) {
			    		collision = true;
			    	}
				}
				else if(top > r.r2.getY() && top < r.r2.getY()+r.height) {
					Color c2 = (Color) r2.getFill();
					if(c1.getRed() == c2.getRed() && c1.getGreen() == c2.getGreen() && c1.getBlue()==c2.getBlue()) {
			    		collision = true;
			    	}
				}
				else if(top > r.r3.getY() && top < r.r3.getY()+r.height) {
					Color c2 = (Color) r3.getFill();
					if(c1.getRed() == c2.getRed() && c1.getGreen() == c2.getGreen() && c1.getBlue()==c2.getBlue()) {
			    		collision = true;
			    	}
				}
				else if(top > r.r4.getY() && top < r.r4.getY()+r.height) {
					Color c2 = (Color) r4.getFill();
					if(c1.getRed() == c2.getRed() && c1.getGreen() == c2.getGreen() && c1.getBlue()==c2.getBlue()) {
			    		collision = true;
			    	}
				}
				else if(top > r.r5.getY() && top < r.r5.getY()+r.height) {
					Color c2 = (Color) r5.getFill();
					if(c1.getRed() == c2.getRed() && c1.getGreen() == c2.getGreen() && c1.getBlue()==c2.getBlue()) {
			    		collision = true;
			    	}
				}
				
				if(collision) {
					try {
						Stage stage1 = new Stage();
						new ObstacleScreen().start(stage1); // Game Over
						checkCollision.stop();
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
				
				if(r1.getY() > 600) {
					checkCollision.stop();
				}
			}
		});
		checkCollision.getKeyFrames().add(k);
		checkCollision.setCycleCount(Timeline.INDEFINITE);
		checkCollision.play();
	}
	
	public void applyFall() {
		ColorRectangle c = this;
		Timeline bringCenterDown = new Timeline(new KeyFrame(Duration.millis(2), new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent t) 
			{
				c.r1.setY(c.r1.getY() + 0.1);
				c.r2.setY(c.r2.getY() + 0.1);
				c.r3.setY(c.r3.getY() + 0.1);
				c.r4.setY(c.r4.getY() + 0.1);
				c.r5.setY(c.r5.getY() + 0.1);
			}
		}));
		bringCenterDown.setCycleCount(20);
		bringCenterDown.play();
	}
}
package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

public class BallsCollision {
	private static Circle ball;

	private BallsCollision() {}
	
	public static Circle getInstance() {
		if(ball == null) {
			ball = new Circle(10, Color.rgb(144,13,255));
			ball.setCenterX(365);
			ball.setCenterY(480);
		}
		return ball;
	}
	
	public static boolean checkBallStarCollision(Circle ball, Circle coin) {
		double x1 = ball.getCenterX();
    	double y1 = ball.getCenterY();
    	double x2 = coin.getCenterX();
    	double y2 = coin.getCenterY();
    	
    	double r1 = ball.getRadius();
    	double r2 = coin.getRadius();
		
    	double distance = Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2);
    	
    	return distance <= Math.pow(r1+r2, 2);
	}
	
	public static boolean checkCollisionRange(Circle c, Arc a) {
    	double x1 = c.getCenterX();
    	double y1 = c.getCenterY();
    	double x2 = a.getCenterX();
    	double y2 = a.getCenterY();
    	
    	double r1 = c.getRadius();
    	double r2 = a.getRadiusX();
		
    	double distance = Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2);
    	
    	return distance <= Math.pow(r1+r2, 2);
	}
	
	public static boolean checkCollisionBottomRange(Circle c, Arc a) {
		boolean collisionRange = false;
    	double x1 = c.getCenterX();
    	double y1 = c.getCenterY();
    	double x2 = a.getCenterX();
    	double y2 = a.getCenterY();
    	
    	double r1 = c.getRadius();
    	double r2 = a.getRadiusX();
    	double t = 13;
    	
    	double distance = Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2);
    	
    	if(distance <= Math.pow(r1+r2, 2)) {
    		if(y1 - r1 > y2 + r2 - t  && y1 - r1 < y2 + r2) {
    			collisionRange = true;
    		}
    	}
    	
    	return collisionRange;
	}
	
	public static boolean checkCollisionUpRange(Circle c, Arc a) {
		boolean collisionRange = false;
    	double x1 = c.getCenterX();
    	double y1 = c.getCenterY();
    	double x2 = a.getCenterX();
    	double y2 = a.getCenterY();
    	
    	double r1 = c.getRadius();
    	double r2 = a.getRadiusX();
    	double t = 15;
    
    	double distance = Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2);
    	
    	if(distance <= Math.pow(r1+r2, 2)) {
    		if(y1 - r1 > y2 - r2 && y1 - r1 < y2 - r2 + t) {
    			collisionRange = true;
    		}
    	}
    	
    	return collisionRange;
	}
	
	public static boolean checkCollision(Circle c, Arc a) {
		boolean collision = false;
    	Color c1 = (Color) c.getFill();
    	Color c2 = (Color) a.getStroke();
    	
    	if(c1.getRed() == c2.getRed() && c1.getGreen() == c2.getGreen() && c1.getBlue()==c2.getBlue()) {
    		collision = true;
    	}
    	
    	return collision;
    }
}

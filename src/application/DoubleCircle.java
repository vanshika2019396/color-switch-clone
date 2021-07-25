package application;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class DoubleCircle {
	Group root;
	RotatingCircle cDown, cUp;
	private double x, yDown, yUp, radiusDown, radiusUp;
	
	public DoubleCircle(double yD, double yU, double rDown, double rUp) {
		x = 365;
		yDown = yD; yUp = yU;
		radiusDown = rDown;
		radiusUp =rUp;
		cDown = new RotatingCircle(x, yDown, radiusDown);
		cUp = new RotatingCircle(x, yUp, radiusUp);
		root = new Group(cDown.root, cUp.root);
	}
	
	public void startRotation(AnchorPane pane, double deltaTheta1, double deltaTheta2) {
		cDown.startRotation(pane.getScene(), deltaTheta1);
		cUp.startRotation(pane.getScene(), deltaTheta2);
		
		pane.getChildren().add(root);
	}
	
	public void applyFall() {
		this.cDown.applyFall();
		this.cUp.applyFall();
	}
}

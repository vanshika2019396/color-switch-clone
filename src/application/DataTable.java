package application;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class DataTable implements Serializable{
	private static final long serialVersionUID = 42L;
	private int gameID;
	private int red;
	private int green;
	private int blue;
	
	private int score;

	public DataTable(int id, int s, int r, int g, int b) {
		this.gameID = id; //(int) (Math.random() * 100);
		this.red = r;
		this.green = g;
		this.blue = b;
		this.score = s;
	}
	
	public void updateGame() {
		try {
			Main_MenuController.db.serialize();
		}
		catch (Exception e) {}
	}
	
	public void saveGame() {
		Main_MenuController.db.getDatabase().add(this);
		
		try {
			Main_MenuController.db.serialize();
		}
		catch (Exception e) {}
	}
	
	public int getScore() {
		return this.score;
	}
	
	public Color getColor() {
		return Color.rgb(red, green, blue);
	}
	
	public int getGameID() {
		return gameID;
	}
	
	public void setGameID(int id) {
		this.gameID = id;
	}
	
	public void setScore(int s) {
		this.score = s;
	}
	
	public void setColor(int r, int g, int b) {
		this.red = r;
		this.green = g;
		this.blue = b;
	}
}

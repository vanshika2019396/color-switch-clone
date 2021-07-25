package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable{
	private static final long serialVersionUID = 42L;
	private ArrayList<DataTable> database;
	
	public Database() {
		this.database = new ArrayList<>();
	}
	
	public void updateDatabase(ArrayList<DataTable> dt) {
		this.database = dt;
	}
	
	public void serialize() throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("database.txt"));
			out.writeObject(this);
		}
		finally {
			out.close();
		}
	}
	
	public ArrayList<DataTable> getDatabase(){
		return this.database;
	}
}

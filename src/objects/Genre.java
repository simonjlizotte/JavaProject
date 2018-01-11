package objects;

/**
 * 
 * @author simonlizotte
 * Genre javabean
 */

public class Genre {

	// properties
	private int ID;
	private String genre;
	
	// Default constructor 


		
	public Genre() {
		
	}
	

	public Genre(String genre) {
		this.genre = genre;
	}
	
	public Genre(int id, String genre) {
		this.ID = id;
		this.genre = genre;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	

	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String toString() {
		return this.getGenre();
	}
	
	
}

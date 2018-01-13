package objects;


/** Concert class
 * 
 * This class will serve to hold the properties of each band (column on the bandTable).
 *
 */
public class Band {
	
	//columns
	private int id;
	private String name;
	private int genreId;

	
	//constructor with no args
	public Band() {
		
	}
		
	//constructor with args
	public Band(int id, String name, int genreId) {
		this.id = id;
		this.name = name;
		this.genreId = genreId;
	}
	
	public Band(String name, int genreId) {
		this.name = name;
		this.genreId = genreId;
	}


	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getGenreId() {
		return genreId;
	}
	
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	
	public String toString() {
		return this.name;
	}	
}

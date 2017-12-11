package objects;

/** Concert class
 * 
 * This class will serve to hold the properties of each concert.
 *
 */
public class Concert {

	//properties
	private int id;
	private String bandName;
	private String genre;
	private String venue;
	private String city;
	private String date;
	private int rating;
	private String pic;
	
	//default constructor
	public Concert() {
		
	}
	
	//constructor taking all properties as parameters
	public Concert(int id, String bandName, String genre, String venue, String city, String date, int rating, String pic) {
		this.id = id;
		this.bandName = bandName;
		this.genre = genre;
		this.venue = venue;
		this.city = city;
		this.date = date;
		this.rating = rating;
		this.pic = pic;
	}

	//getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	
	
}

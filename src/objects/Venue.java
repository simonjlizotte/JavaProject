package objects;

public class Venue {
	
	//properties
	private int id;
	private String venue;
	private String city;
	
	//constructors
	public Venue(String venue, String city) {
		this.city = city;
		this.venue = venue;
	}
	
	public Venue(int id, String venue, String city) {
		this.id = id;
		this.venue = venue;
		this.city = city;
	}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	

}

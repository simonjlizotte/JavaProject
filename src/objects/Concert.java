package objects;

import tables.BandTable;

/** Concert class
 * 
 * This class will serve to hold the properties of each concert.
 *
 */
public class Concert {

	//properties
	private int id;
	private int bandID;
	private int venueID;
	private String date;
	private int rating;
	private String pic;
	
	//default constructor
	public Concert() {
		
	}

	public Concert(int id, int bandID, int venueID, String date, int rating, String pic) {
		this.id = id;
		this.bandID = bandID;
		this.venueID = venueID;
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

	public int getBandID() {
		return bandID;
	}

	public void setBandID(int bandID) {
		this.bandID = bandID;
	}

	public int getVenueID() {
		return venueID;
	}

	public void setVenueID(int venueID) {
		this.venueID = venueID;
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
	
	public String toString() {
		Band band = new Band();
		BandTable bandTable = new BandTable();
		band = bandTable.getBand(this.getBandID());
		
		return  this.getDate() + ", " + band.getName();
	}
}

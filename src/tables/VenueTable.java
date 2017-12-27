package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAOS.VenueDAO;
import database.Const;
import database.Database;
import objects.Genre;
import objects.Venue;

/**
 * The purpose of this class is to manage crud operations for the Venue items inside the Venue table
 * @author josegeorges
 *
 */
public class VenueTable implements VenueDAO{

	//initializing db connection
	Database db = Database.getInstance();
	
	/**
	 * This method will INSERT a new venue INTO the table
	 * 
	 * Query: INSERT INTO venueTable (venue, city) values ('venue', 'city');
	 */
	@Override
	public void createGenre(Venue venue) {
		String query = "INSERT INTO " + Const.TABLE_VENUE + 
				"(" + Const.VENUE_COLUMN_NAME + ", " + Const.VENUE_COLUMN_CITY + ") "
						+ "values ('" +	venue.getVenue() + "', '" + venue.getCity() + "')";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(venue.getVenue() + " successfully added to the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will SELECT ALL venues from the table
	 * 
	 * Query: SELECT * FROM venueTable;
	 */
	@Override
	public ArrayList<Venue> getAllVenues() {
		String query = "SELECT * FROM " + Const.TABLE_VENUE;
		ArrayList<Venue> venues = new ArrayList<Venue>();
		
		try {
			Statement getGenres = db.getConnection().createStatement();
			ResultSet result = getGenres.executeQuery(query);
			//this loop will iterate through each item in the result set and 
				//when it gets to the last item will return false
			while(result.next()) {
				venues.add(new Venue(result.getInt(Const.VENUE_COLUMN_ID),
									result.getString(Const.VENUE_COLUMN_NAME),
									result.getString(Const.VENUE_COLUMN_CITY)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return venues;
	}

	@Override
	public Venue getVenue(int venueID) {
		String query = "SELECT * FROM " + Const.TABLE_VENUE + " WHERE " +
				Const.TABLE_VENUE + " = " + venueID;
	Venue venue = new Venue();
	try {
		Statement getVenue = db.getConnection().createStatement();
		ResultSet result = getVenue.executeQuery(query);
		result.next();
		venue = new Venue(result.getInt(Const.VENUE_COLUMN_ID),
				result.getString(Const.VENUE_COLUMN_NAME),
				result.getString(Const.VENUE_COLUMN_CITY));
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return venue;
	}

	@Override
	public void updateVenue(Venue venue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVenue(Venue venue) {
		// TODO Auto-generated method stub
		
	}

}

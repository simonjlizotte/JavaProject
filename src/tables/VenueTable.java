package tables;

import java.sql.SQLException;
import java.util.ArrayList;

import DAOS.VenueDAO;
import database.Const;
import database.Database;
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

	@Override
	public ArrayList<Venue> getAllVenues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venue getVenue(int venueID) {
		// TODO Auto-generated method stub
		return null;
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

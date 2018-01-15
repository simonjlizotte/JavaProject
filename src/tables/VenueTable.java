package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	static Database db = Database.getInstance();
	
	/**
	 * This method will INSERT a new venue INTO the table
	 * 
	 * Query: INSERT INTO venueTable (venue, city) values ('venue', 'city');
	 * 
	 * SELECT * FROM venueTable WHERE venue LIKE 'sa';
	 */
	public static Venue createVenue(Venue venue) {
		String insertQuery = "INSERT INTO " + Const.TABLE_VENUE + 
				"(" + Const.VENUE_COLUMN_NAME + ", " + Const.VENUE_COLUMN_CITY + ") "
						+ "values ('" +	venue.getVenue() + "', '" + venue.getCity() + "')";
		String selectQuery = "SELECT * FROM " + Const.TABLE_VENUE +  " WHERE " + Const.VENUE_COLUMN_NAME + " LIKE '" +venue.getVenue()+"';";
		try {
			Statement getVenue = db.getConnection().createStatement();
			ResultSet result = getVenue.executeQuery(selectQuery);
			System.out.println("result: " + result.toString());
				if (result.next()) {
					Venue existingVenue = new Venue(result.getInt(Const.VENUE_COLUMN_ID), result.getString(Const.VENUE_COLUMN_NAME), result.getString(Const.VENUE_COLUMN_CITY));
					System.out.println("already in table");
					System.out.println(existingVenue.getVenue());
					return existingVenue;
					
					
				} else {

				getVenue.execute(insertQuery);
				System.out.println(venue.getVenue() + " successfully added to the table");
				String selectAfterInsertQuery = "SELECT * FROM " + Const.TABLE_VENUE +  " WHERE " + Const.VENUE_COLUMN_NAME + " LIKE '" +venue.getVenue()+"';";
				ResultSet newResult = getVenue.executeQuery(selectAfterInsertQuery);
				newResult.next();
				venue = new Venue(newResult.getInt(Const.VENUE_COLUMN_ID), newResult.getString(Const.VENUE_COLUMN_NAME), newResult.getString(Const.VENUE_COLUMN_CITY));
				return venue;
				}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;	
	}

	/**
	 * This method will SELECT ALL venues from the table
	 * 
	 * Query: SELECT * FROM venueTable;
	 */
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

	/**
	 * This method will SELECT a venue FROM the table WHERE id matches
	 * 
	 * Query: SELECT * FROM venueTable WHERE id = 'id';
	 */
	@Override
	public Venue getVenue(int venueId) {
		String query = "SELECT * FROM " 
	+ Const.TABLE_VENUE +  " WHERE " + Const.VENUE_COLUMN_ID + " = '" + venueId+"';";

	Venue venue = null;
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

	/**
	 * This method will UPDATE a venue FROM the table WHERE id matches
	 * 
	 * UPDATE genreTable 
		SET venue = 'venue' 
		WHERE id = 'id';
	 */
	@Override
	public void updateVenue(int venueID, String newVenue) {
		String query = "UPDATE " + Const.TABLE_VENUE +
				" SET " + Const.VENUE_COLUMN_NAME + " = '" + newVenue + "' WHERE "
				+ Const.VENUE_COLUMN_ID + " = " + venueID + ";";
		System.out.println(query);
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(newVenue + " updated from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method will UPDATE a venue FROM the table WHERE id matches
	 * 
	 * UPDATE venueTable 
		SET city = 'city' 
		WHERE id = 'id';
	 */
	@Override
	public void updateCity(int venueID, String newCity) {
		String query = "UPDATE " + Const.TABLE_VENUE +
				" SET " + Const.VENUE_COLUMN_CITY + " = '" + newCity + "' WHERE "
				+ Const.VENUE_COLUMN_ID + " = '" + venueID + "';";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(newCity + " updated from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will DELETE a genre FROM the table WHERE id matches
	 * 
	 * Query: DELETE FROM venueTable WHERE id = 'id';
	 */
	@Override
	public void deleteVenue(Venue venue) {
		String query = "DELETE FROM " + Const.TABLE_VENUE + " WHERE " +
				Const.VENUE_COLUMN_ID + " = " + venue.getId();
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(venue.getVenue() + " deleted from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}

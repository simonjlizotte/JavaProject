package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAOS.ConcertDAO;
import database.Const;
import database.Database;
import objects.Band;
import objects.Concert;
import objects.Genre;
import objects.Venue;

/**
 * The purpose of this class is to manage crud operations for the Concert items inside the Concert table
 * @author josegeorges

 */
public class ConcertTable implements ConcertDAO{

	static //initializing db connection
	Database db = Database.getInstance();
	public static void createConcert(String date, int rating, String pic, Band band, Venue venue) {
		Venue concertVenue = tables.VenueTable.createVenue(venue);
		Band concertBand = tables.BandTable.createBand(band);
		
		String query = "INSERT INTO " + Const.TABLE_CONCERT + " (" + Const.CONCERTS_COLUMN_ID + ", " + Const.CONCERTS_COLUMN_BAND_ID + ", " 
				+ Const.CONCERTS_COLUMN_VENUE_ID + ", " + Const.CONCERTS_COLUMN_DATE + ", " + Const.CONCERTS_COLUMN_RATING + "," + Const.CONCERTS_COLUMN_PIC + ")" +
				"VALUES( 0, " + concertBand.getId() +  ", " + concertVenue.getId() + ", '" +  date + "', " + rating +  ", '" + "utl_raw.cast_to_raw(" + pic + ")" + "');"; 
		String selectQuery = "SELECT * FROM " + Const.TABLE_CONCERT + " WHERE " + Const.CONCERTS_COLUMN_DATE + " LIKE '" + date +"';";
		try {
			Statement getBand = db.getConnection().createStatement();
			ResultSet result = getBand.executeQuery(selectQuery);
			System.out.println("result: " + result.toString());
				if (result.next()) {
					System.out.println("already in table");					
					
				} else {

					db.getConnection().createStatement().execute(query);
					System.out.println(band.getName() + " successfully added to the table");
				}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * This method will SELECT ALL concerts from the table
	 * 
	 * Query: SELECT * FROM concertTable;
	 */
	@Override
	public ArrayList<Concert> getAllConcerts() {
		String query = "SELECT * FROM " + Const.TABLE_CONCERT;
		ArrayList<Concert> concerts = new ArrayList<Concert>();
		
		try {
			Statement getConcerts = db.getConnection().createStatement();
			ResultSet result = getConcerts.executeQuery(query);
			//this loop will iterate through each item in the result set and 
				//when it gets to the last item will return false
			while(result.next()) {
				concerts.add(new Concert(result.getInt(Const.CONCERTS_COLUMN_ID),
									result.getInt(Const.CONCERTS_COLUMN_BAND_ID),
									result.getInt(Const.CONCERTS_COLUMN_VENUE_ID),
									result.getString(Const.CONCERTS_COLUMN_DATE),
									result.getInt(Const.CONCERTS_COLUMN_RATING),
									result.getString(Const.CONCERTS_COLUMN_PIC)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return concerts;
	}

	@Override
	public Concert getConcert(int concertID) {
		String query = "SELECT * FROM " + Const.TABLE_CONCERT + " WHERE " +
				Const.CONCERTS_COLUMN_ID + " = " + concertID;
	Concert concert = new Concert();
	try {
		Statement getConcert = db.getConnection().createStatement();
		ResultSet result = getConcert.executeQuery(query);
		if(result.next()) {
		concert = new Concert(result.getInt(Const.CONCERTS_COLUMN_ID),
				result.getInt(Const.CONCERTS_COLUMN_BAND_ID),
				result.getInt(Const.CONCERTS_COLUMN_VENUE_ID),
				result.getString(Const.CONCERTS_COLUMN_DATE),
				result.getInt(Const.CONCERTS_COLUMN_RATING),
				result.getString(Const.CONCERTS_COLUMN_PIC)); 
		} else {
			System.out.println("Testing Concert");
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return concert;
	}

	@Override
	public void updateDate(Band band) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRating(Band band) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePicture(Band band) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBand(Band band) {
		// TODO Auto-generated method stub
		
	}

}

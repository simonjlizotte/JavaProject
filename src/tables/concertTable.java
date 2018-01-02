package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAOS.concertDAO;
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
public class concertTable implements concertDAO{

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

	@Override
	public ArrayList<Concert> getAllConcerts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Band getConcert(int concertID) {
		// TODO Auto-generated method stub
		return null;
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

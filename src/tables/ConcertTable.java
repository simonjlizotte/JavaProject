package tables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAOS.ConcertDAO;
import database.Const;
import database.Database;
import javafx.scene.image.Image;
import objects.Band;
import objects.Concert;
import objects.Venue;

/**
 * The purpose of this class is to manage crud operations for the Concert items inside the Concert table
 * @author josegeorges

 */
public class ConcertTable implements ConcertDAO{

	static //initializing db connection
	Database db = Database.getInstance();
	
	public static void createConcert(String date, int rating, FileInputStream pic, Band band, Venue venue) {
		Venue concertVenue = tables.VenueTable.createVenue(venue);
		Band concertBand = tables.BandTable.createBand(band);
		int concertID = 0;
		
		String query = "INSERT INTO " + Const.TABLE_CONCERT + " (" + Const.CONCERTS_COLUMN_ID + ", " + Const.CONCERTS_COLUMN_BAND_ID + ", " 
				+ Const.CONCERTS_COLUMN_VENUE_ID + ", " + Const.CONCERTS_COLUMN_DATE + ", " + Const.CONCERTS_COLUMN_RATING + "," + Const.CONCERTS_COLUMN_PIC + ")" +
				"VALUES( 0, " + concertBand.getId() +  ", " + concertVenue.getId() + ", '" +  date + "', '" + rating  + "', null);"; 
		
		String selectQuery = "SELECT * FROM " + Const.TABLE_CONCERT + " WHERE " + Const.CONCERTS_COLUMN_DATE + " LIKE '" + date +"';";
		String selectQuery2 = "SELECT id FROM " + Const.TABLE_CONCERT + " WHERE " + Const.CONCERTS_COLUMN_BAND_ID + " LIKE '" + concertBand.getId() +"';";

		try {
			Statement getBand = db.getConnection().createStatement();
			ResultSet result = getBand.executeQuery(selectQuery);
			
			System.out.println("result: " + result.toString());
				if (result.next()) {
					System.out.println("already in table");					
					
				} else {

					db.getConnection().createStatement().execute(query);
					// This is the code which queries the table for the last entry which is the entry we just added a line above
					Statement statement = db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					ResultSet resultSet = statement.executeQuery("select id from concertTable");
					resultSet.afterLast();
					ConcertTable concerts = new ConcertTable();
					resultSet.previous();
					concertID = resultSet.getInt("id");
					// This calls the function within this class which updates the just entered concerts picture   
					concerts.updatePicture(pic, concertID);
					concerts.getConcertImage(concertID);
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
	/**
	 * 
	 * @param file a file path converted to a file input stream right before calling this function
	 * @param concertID the retrieved id of the concert you wish to change the picture of
	 * Simon Lizotte 
	 * this function updates the table at a selected concert id to recive a picture for the blob column named picture in the database
	 */
	public void updatePicture(FileInputStream file, int concertID) {
		PreparedStatement statement;
		// Test to see if we enter function
		System.out.println("INNNN" + concertID);
		try {
			// test to see if we enter try
			System.out.println("here?");
			// Query string to update entry being provided
			String sqlPhoto = "UPDATE concertTable SET picture = ?  WHERE id=?";
			// Preparing statement
			statement = db.getConnection().prepareStatement(sqlPhoto);
			// Adding values to the question marks in the statement
			// one for file and other for concertID
			// This makes the statement more adaptive
			statement.setBinaryStream(1, file);
			statement.setInt(2, concertID);
			// Execute update
			statement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public Image getConcertImage(int concertID) {
		// This is the code to select an image and populate in the computer directory, we need to be able to select 
					// an entry by its id then use this on the singleconcertpage to query and display that image.
					String sql8 = "SELECT picture FROM  concertTable WHERE id = '" + concertID + "'";
				    PreparedStatement stmt;
					try {
						stmt = db.getConnection().prepareStatement(sql8);
						   ResultSet resultSet = stmt.executeQuery();
						    while (resultSet.next()) {
						      File image = new File("/Users/simonlizotte/Downloads/readImage3.png");
						      //@SuppressWarnings("resource")
							FileOutputStream fos = new FileOutputStream(image);
		
						      byte[] buffer = new byte[1];
						      InputStream is = resultSet.getBinaryStream("picture");
						      while (is.read(buffer) > 0) {
						        fos.write(buffer);
						      }
						      fos.close();
						    }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
		return null;
		
	}

}

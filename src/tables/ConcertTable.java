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
	
	/**
	 * 
	 * This method adds concerts to the concertTable
	 * 
	 * @param date date of the concert
	 * @param rating of the concert
	 * @param pic of the concert
	 * @param band to get the band from the table and access the id
	 * @param venue to get the venue from the table and access the id
	 * @return a confirmation message that will be passed to the ConfirmationMessageScene to display if the 
	 * 	concert was successfully inserted or it was in the table already
	 */
	public static String createConcert(String date, int rating, FileInputStream pic, Band band, Venue venue) {
		Venue concertVenue = tables.VenueTable.createVenue(venue);
		Band concertBand = tables.BandTable.createBand(band);
		int concertID = 0;
		
		String query = "INSERT INTO " + Const.TABLE_CONCERT + " (" + Const.CONCERTS_COLUMN_ID + ", " + Const.CONCERTS_COLUMN_BAND_ID + ", " 
				+ Const.CONCERTS_COLUMN_VENUE_ID + ", " + Const.CONCERTS_COLUMN_DATE + ", " + Const.CONCERTS_COLUMN_RATING + "," + Const.CONCERTS_COLUMN_PIC + ")" +
				"VALUES( 0, " + concertBand.getId() +  ", " + concertVenue.getId() + ", '" +  date + "', '" + rating  + "', null);"; 
		
		String selectQuery = "SELECT * FROM " + Const.TABLE_CONCERT + " WHERE " + Const.CONCERTS_COLUMN_DATE + " LIKE '" + date +"' AND " + Const.CONCERTS_COLUMN_BAND_ID + " = " + concertBand.getId();

		try {
			Statement getBand = db.getConnection().createStatement();
			ResultSet result = getBand.executeQuery(selectQuery);			
				if (result.next()) {
					return band.getName() + " already in table";
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
					return band.getName() + " successfully added to the table";
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
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
	public void updateDate(String date, int concertId) {
		String query = "UPDATE " + Const.TABLE_CONCERT +
				" SET " + Const.CONCERTS_COLUMN_DATE + " = '" + date + "' WHERE "
				+ Const.BANDS_COLUMN_ID + " = '" + concertId + "';";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println( " updated date from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateRating(int newRating, int concertId) {
		String query = "UPDATE " + Const.TABLE_CONCERT +
				" SET " + Const.CONCERTS_COLUMN_RATING + " = '" + newRating + "' WHERE "
				+ Const.BANDS_COLUMN_ID + " = '" + concertId + "';";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println( " updated rating from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void deleteConcert(int concertId) {
		String query = "DELETE FROM " + Const.TABLE_CONCERT + " WHERE " +
				Const.CONCERTS_COLUMN_ID + " = " + concertId;
		try {
			
			db.getConnection().createStatement().execute(query);
			System.out.println("concert deleted from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
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
						      File image = new File("selectedImg.png");
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

	@Override
	public int getYearCount(int year) {
		String query = "SELECT * FROM " + Const.TABLE_CONCERT + " WHERE year("
				+ Const.CONCERTS_COLUMN_DATE + ") = " + year;
	ArrayList<Concert> concerts = new ArrayList<>();
	try {
		Statement getCount = db.getConnection().createStatement();
		ResultSet result = getCount.executeQuery(query);
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
	return concerts.size();
	}

	/**
	 * This method gets the distinc years that appear on the concert table
	 */
	@Override
	public ArrayList<Integer> getAllYears() {
		String query = "SELECT DISTINCT year("+ Const.CONCERTS_COLUMN_DATE +") FROM " + Const.TABLE_CONCERT;
	ArrayList<Integer> years = new ArrayList<>();
	try {
		Statement getYear = db.getConnection().createStatement();
		ResultSet result = getYear.executeQuery(query);
			while(result.next()) {
				years.add(result.getInt("year("+Const.CONCERTS_COLUMN_DATE+")"));
			}		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return years;
	}
	
	public void garbageCollection() {
		// Create array lists for holding data
		ArrayList<Concert> allConcerts = new ArrayList<Concert>();
		ArrayList<Band> allBands = new ArrayList<Band>();
		ArrayList<Venue> allVenues = new ArrayList<Venue>();
		
		//Create Tables 
		BandTable bandTable = new BandTable();
		VenueTable venueTable = new VenueTable();
		
		// Populate arrays 
		allConcerts = this.getAllConcerts();
		allBands = bandTable.getAllBands();
		allVenues = venueTable.getAllVenues();
		
		// Clean band table
		
		for (int i = 0; i < allBands.size(); i++) {
			int count = 0;
			for (int j = 0; j < allConcerts.size(); j++) {
				if (allBands.get(i).getId() == allConcerts.get(j).getBandID()) {
					System.out.println("Band is being used");
				} else {
					count++;
				}
			}
			if (count == allConcerts.size()) {
				String query = "DELETE FROM " + Const.TABLE_BAND + " WHERE " +
						Const.BANDS_COLUMN_ID + " = " + allBands.get(i).getId();
				try {
					
					db.getConnection().createStatement().execute(query);
					System.out.println("unused venue  cleaned from the table");
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		for (int i = 0; i < allVenues.size(); i++) {
			int count = 0;
			System.out.println(count);
			for (int j = 0; j < allConcerts.size(); j++) {
				if (allVenues.get(i).getId() == allConcerts.get(j).getVenueID()) {
					System.out.println("Venue is being used");
				} else {
					count++;
					
				}
			}
			System.out.println(count);
			System.out.println(allConcerts.size());
			if (count == allConcerts.size()) {
				String query = "DELETE FROM " + Const.TABLE_VENUE + " WHERE " +
						Const.VENUE_COLUMN_ID + " = " + allVenues.get(i).getId();
				try {
					
					db.getConnection().createStatement().execute(query);
					System.out.println("unused venue  cleaned from the table");
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		

	}
	

}

package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAOS.BandDAO;
import database.Const;
import database.Database;
import objects.Band;
import objects.Genre;
import tables.GenreTable;


/**
 * The purpose of this class is to manage crud operations for the Band items inside the Band table
 * @author josegeorges
 *
 */
public class BandTable implements BandDAO{

	//initializing db connection
	static Database db = Database.getInstance();
	
	/**
	 * This method will INSERT a new band INTO the table
	 * 
	 * Query Insert: INSERT INTO bandTable ('0', band.getName(), genre.getId()); 
 			
 		Query Select: SELECT * FROM bandTable WHERE bandName LIKE band.getName()
	 */
	// needs to receive the getSelectionmodel.getGenre.GetID() as the selectedGenreValue
	public static Band createBand(Band band) {
		Genre genre = GenreTable.getGenre(band.getGenreId());

		String query = "INSERT INTO " + Const.TABLE_BAND + "(" + Const.BANDS_COLUMN_ID + "," + Const.BANDS_COLUMN_NAME + ","
					+ Const.BANDS_COLUMN_GENRE_ID + ") VALUES (0,'" + band.getName() + "'," + genre.getId() + ");";  
		String selectQuery = "SELECT * FROM " + Const.TABLE_BAND +  " WHERE " + Const.BANDS_COLUMN_NAME + " LIKE '" +band.getName()+"';";
		
		try {
			Statement getBand = db.getConnection().createStatement();
			ResultSet result = getBand.executeQuery(selectQuery);
			System.out.println("result: " + result.toString());
				if (result.next()) {
					Band existingBand = new Band(result.getInt(Const.BANDS_COLUMN_ID), result.getString(Const.BANDS_COLUMN_NAME), result.getInt(Const.BANDS_COLUMN_GENRE_ID));				
					System.out.println("already in table");
					System.out.println(existingBand.getName());
					return existingBand;
					
					
				} else {

				db.getConnection().createStatement().execute(query);
				System.out.println(band.getName() + " successfully added to the table");
				ResultSet newResult = getBand.executeQuery(selectQuery);
				newResult.next();
				band = new Band(newResult.getInt(Const.BANDS_COLUMN_ID), newResult.getString(Const.BANDS_COLUMN_NAME), newResult.getInt(Const.BANDS_COLUMN_GENRE_ID));				
				return band;
				}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;	
	}

	/**
	 * This method will SELECT ALL genres from the table
	 * 
	 * Query: SELECT * FROM bandTable;
	 */
	@Override
	public ArrayList<Band> getAllBands() {
		String query = "SELECT * FROM " + Const.TABLE_BAND;
		ArrayList<Band> bands = new ArrayList<Band>();
		
		try {
			Statement getGenres = db.getConnection().createStatement();
			ResultSet result = getGenres.executeQuery(query);
			//this loop will iterate through each item in the result set and 
				//when it gets to the last item will return false
			while(result.next()) {
				bands.add(new Band(result.getInt(Const.BANDS_COLUMN_ID),
									result.getString(Const.BANDS_COLUMN_NAME)
									, result.getInt(Const.BANDS_COLUMN_GENRE_ID)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bands;
	}

	/**
	 * This method will SELECT a genre FROM the table WHERE id matches
	 * 
	 * Query: SELECT * FROM genreTable WHERE id = 'id';
	 */
	@Override
	public Band getBand(int bandID) {
		String query = "SELECT * FROM " + Const.TABLE_BAND + " WHERE " +
				Const.BANDS_COLUMN_ID + " = " + bandID;
	Band band = new Band();
	try {
		Statement getBand = db.getConnection().createStatement();
		ResultSet result = getBand.executeQuery(query);
		result.next();
		band = new Band(result.getInt(Const.BANDS_COLUMN_ID),
				result.getString(Const.BANDS_COLUMN_NAME), result.getInt(Const.BANDS_COLUMN_GENRE_ID)); 
	}catch(SQLException e) {
		
	}
	return band;
	}

	/**
	 * This method will UPDATE a genre FROM the table WHERE id matches
	 * 
	 * UPDATE bandTable 
		SET band_name = 'band' 
		WHERE id = 'id';
	 */
	@Override
	public void updateBand(int bandId, String newBand) {
		String query = "UPDATE " + Const.TABLE_BAND +
				" SET " + Const.BANDS_COLUMN_NAME + " = '" + newBand + "' WHERE "
				+ Const.BANDS_COLUMN_ID + " = '" + bandId + "';";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(newBand + " updated from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public void updateGenre(int genreId, int bandId) {
		String query = "UPDATE " + Const.TABLE_BAND +
				" SET " + Const.BANDS_COLUMN_GENRE_ID + " = '" + genreId + "' WHERE "
				+ Const.BANDS_COLUMN_ID + " = '" + bandId + "';";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println( " updated GENRE from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * This method will DELETE a band FROM the table WHERE id matches
	 * 
	 * Query: DELETE FROM bandTable WHERE id = 'id';
	 */
	@Override
	public void deleteBand(Band band) {
		String query = "DELETE FROM " + Const.TABLE_BAND + " WHERE " +
				Const.BANDS_COLUMN_ID + " = " + band.getId();
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(band.getName() + " deleted from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * SELECT COUNT(*) FROM bandTable WHERE genre_id = 1;
	 */
	@Override
	public int getGenreCount(int genreId) {
		String query = "SELECT * FROM " + Const.TABLE_BAND + " WHERE "
					+ Const.BANDS_COLUMN_GENRE_ID + " = " + genreId;
		ArrayList<Band> bands = new ArrayList<>();
		try {
			Statement getCount = db.getConnection().createStatement();
			ResultSet result = getCount.executeQuery(query);
			while(result.next()) {
				bands.add(new Band(result.getInt(Const.BANDS_COLUMN_ID),
						result.getString(Const.BANDS_COLUMN_NAME), result.getInt(Const.BANDS_COLUMN_GENRE_ID)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bands.size();
	}


	

}

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


/**
 * The purpose of this class is to manage crud operations for the Band items inside the Band table
 * @author josegeorges
 *
 */
public class BandTable implements BandDAO{

	//initializing db connection
	Database db = Database.getInstance();
	
	/**
	 * This method will INSERT a new band INTO the table
	 * 
	 * Query:INSERT INTO bandTable(band_name, genre_id)
			SELECT 'Joe The Gays', id
  			FROM genreTable
 			WHERE genre_name = 'this'
 			LIMIT 1
	 */
	@Override
	public void createBand(Band band, Genre genre) {
		String query = "INSERT INTO " + Const.TABLE_BAND + 
				"(" + Const.BANDS_COLUMN_NAME + ", "+ Const.BANDS_COLUMN_GENRE_ID +") "
				+ "FROM " + Const.TABLE_GENRE 
				+ "WHERE " + Const.GENRE_COLUMN_NAME + " = '" + genre.getGenre() + "' LIMIT 1;";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(band.getName() + " successfully added to the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
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
	public void updateBand(Band band) {
		String query = "UPDATE " + Const.TABLE_BAND +
				"SET " + Const.BANDS_COLUMN_NAME + " = '" + band.getName() + "' WHERE "
				+ Const.BANDS_COLUMN_ID + " = '" + band.getId() + "';";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(band.getName() + " updated from the table");
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

}

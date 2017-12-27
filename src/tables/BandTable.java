package tables;

import java.sql.SQLException;
import java.util.ArrayList;

import DAOS.BandDAO;
import database.Const;
import database.Database;
import objects.Band;


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
	 * Query: INSERT INTO bandTable (band_name, genre_id) values ('band', 'genreid');
	 */
	@Override
	public void createBand(Band band) {
		String query = "INSERT INTO " + Const.TABLE_BAND + 
				"(" + Const.BANDS_COLUMN_NAME + ", "+ Const.BANDS_COLUMN_GENRE_ID +") "
						+ "values ('" +	band.getName() + "', '" + band.getGenreId() + "')";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(band.getName() + " successfully added to the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Band> getAllBands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Band getBand(int bandID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBand(Band band) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBand(Band band) {
		// TODO Auto-generated method stub
		
	}

}

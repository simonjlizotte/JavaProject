package tables;

import java.util.ArrayList;

import DAOS.BandDAO;
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
	 * Query: INSERT INTO bandTable (genre_name) values ('genre');
	 */
	@Override
	public void createBand(Band band) {
		
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

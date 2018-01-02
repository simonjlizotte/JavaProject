package tables;

import java.util.ArrayList;

import DAOS.ConcertDAO;
import database.Database;
import objects.Band;
import objects.Concert;
import objects.Genre;

/**
 * The purpose of this class is to manage crud operations for the Concert items inside the Concert table
 * @author josegeorges
 *
 */
public class ConcertTable implements ConcertDAO{

	//initializing db connection
	Database db = Database.getInstance();
	
	@Override
	public void createConcert(Concert concert, Band band, Genre genre) {
		
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

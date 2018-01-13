package DAOS;

import java.util.ArrayList;

import objects.Band;

/**
 * Interface to implement inside bandTable class. contains all the crud methods
 * @author josegeorges
 *
 */
public interface BandDAO {
	//Create
	public static Band createBand(Band band) {
		return null;
	}
	//Read
	public ArrayList<Band> getAllBands();
	public Band getBand(int bandID);
	public int getGenreCount(int genreId);
	//Update
	public void updateBand(int bandId, String newBand);
	public void updateGenre(int genreId, int bandId);

	//Delete
	public void deleteBand(Band band);
}

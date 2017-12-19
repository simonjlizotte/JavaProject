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
	public void createBand(Band band);
	//Read
	public ArrayList<Band> getAllBands();
	public Band getBand(int bandID);
	//Update
	public void updateBand(Band band);
	//Delete
	public void deleteBand(Band band);
}

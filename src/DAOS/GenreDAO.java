package DAOS;

import java.util.ArrayList;

import objects.Genre;

/**
 * Interface to implement inside genreTable class. contains all the crud methods
 * @author josegeorges
 *
 */
public interface GenreDAO {
	//Read
	public static ArrayList<Genre> getAllGenres() {
		return null;
	}
	public static Genre getGenre(int genreID) {
		return null;
	}
	
	//insert
	public void insertGenre(String genre);
}

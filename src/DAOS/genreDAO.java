package DAOS;

import java.util.ArrayList;

import objects.Genre;

/**
 * Interface to implement inside genreTable class. contains all the crud methods
 * @author josegeorges
 *
 */
public interface genreDAO {
	//Create
	//Read
	public static ArrayList<Genre> getAllGenres() {
		return null;
	}
	public static Genre getGenre(int genreID) {
		return null;
	}
	//Update
	//public void updateGenre(Genre genre);
	//Delete
	//public void deleteGenre(Genre genre);
}

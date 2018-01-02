package DAOS;

import java.util.ArrayList;

import objects.Genre;

/**
 * Interface to implement inside genreTable class. contains all the crud methods
 * @author josegeorges
 *
 */
public interface GenreDAO {
	//Create
	public void createGenre(Genre genre);
	//Read
	public ArrayList<Genre> getAllGenres();
	public Genre getGenre(int genreID);
	//Update
	public void updateGenre(Genre genre);
	//Delete
	public void deleteGenre(Genre genre);
}

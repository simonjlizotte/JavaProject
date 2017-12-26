package Tables;

import java.sql.SQLException;
import java.util.ArrayList;

import DAOS.GenreDAO;
import database.Const;
import database.Database;
import objects.Genre;

/**
 * The purpose of this class is to manage crud operations for the Genre items inside the Genre table
 * @author josegeorges
 *
 */
public class GenreTable implements GenreDAO{

	//initializing db connection
	Database db = Database.getInstance();
	
	/**
	 * This method will INSERT a new genre INTO the table
	 * 
	 * Query: INSERT INTO genreTable (genre_name) values ('genre');
	 */
	@Override
	public void createGenre(Genre genre) {
		String query = "INSERT INTO " + Const.TABLE_GENRE + 
				"(" + Const.GENRE_COLUMN_NAME + ") values ('" +	genre.getGenre() + "')";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(genre.getGenre() + " successfully added to the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Genre> getAllGenres() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genre getGenre(int genreID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGenre(Genre genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGenre(Genre genre) {
		// TODO Auto-generated method stub
		
	}

}

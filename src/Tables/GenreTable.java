package Tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	/**
	 * This method will SELECT ALL genres from the table
	 * 
	 * Query: SELECT * FROM genreTable;
	 */
	@Override
	public ArrayList<Genre> getAllGenres() {
		String query = "SELECT * FROM " + Const.TABLE_GENRE;
		ArrayList<Genre> genres = new ArrayList<Genre>();
		
		try {
			Statement getGenres = db.getConnection().createStatement();
			ResultSet result = getGenres.executeQuery(query);
			//this loop will iterate through each item in the result set and 
				//when it gets to the last item will return false
			while(result.next()) {
				genres.add(new Genre(result.getInt(Const.GENRE_COLUMN_ID),
									result.getString(Const.GENRE_COLUMN_NAME)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return genres;
	}

	/**
	 * This method will SELECT a genre FROM the table WHERE id matches
	 * 
	 * Query: SELECT * FROM genreTable WHERE id = 'id';
	 */
	@Override
	public Genre getGenre(int genreID) {
		String query = "SELECT * FROM " + Const.TABLE_GENRE + " WHERE " +
					Const.GENRE_COLUMN_ID + " = " + genreID;
		Genre genre = new Genre();
		try {
			Statement getGenre = db.getConnection().createStatement();
			ResultSet result = getGenre.executeQuery(query);
			result.next();
			genre = new Genre(result.getInt(Const.GENRE_COLUMN_ID),
					result.getString(Const.GENRE_COLUMN_NAME)); 
		}catch(SQLException e) {
			
		}
		return genre;
	}

	/**
	 * This method will UPDATE a genre FROM the table WHERE id matches
	 * 
	 * UPDATE genreTable 
		SET genre_name = 'genre' 
		WHERE id = 'id'
	 */
	@Override
	public void updateGenre(Genre genre) {
		String query = "UPDATE * FROM " + Const.TABLE_GENRE + " WHERE " +
				Const.GENRE_COLUMN_ID + " = " + genre.getId();
	}

	/**
	 * This method will DELETE a genre FROM the table WHERE id matches
	 * 
	 * Query: DELETE FROM genreTable WHERE id = 'id';
	 */
	@Override
	public void deleteGenre(Genre genre) {
		String query = "DELETE FROM " + Const.TABLE_GENRE + " WHERE " +
				Const.GENRE_COLUMN_ID + " = " + genre.getId();
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println(genre.getGenre() + " deleted from the table");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

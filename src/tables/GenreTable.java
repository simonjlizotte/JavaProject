package tables;

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
 *
 *UPDATE: we deleted Insert, Update and delete since we decided that genre will be already populated and the user
 * will not have the power to do any of this.
 */
public class GenreTable implements GenreDAO{

	//initializing db connection
	static Database db = Database.getInstance();


	/**
	 * This method will SELECT ALL genres from the table
	 * 
	 * Query: SELECT * FROM genreTable;
	 */
	public static ArrayList<Genre> getAllGenres() {
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
	public static Genre getGenre(int genreID) {
		String query = "SELECT * FROM " + Const.TABLE_GENRE + " WHERE " +
					Const.GENRE_COLUMN_ID + " = " + genreID;
		Genre genre = new Genre();
		try {
			Statement getGenre = db.getConnection().createStatement();
			ResultSet result = getGenre.executeQuery(query);
			if(result.next()) {
			genre = new Genre(result.getInt(Const.GENRE_COLUMN_ID),
					result.getString(Const.GENRE_COLUMN_NAME)); 
			} else {
				System.out.println("Testing Genre");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return genre;
	}
}

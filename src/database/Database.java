package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Const;
import database.Database;
import tables.GenreTable;

/**
 *
 * 
 * @author josegeorges
 *
 *	the database class will be created with a singleton pattern so that only one connection gets open
 *
 *	logs are meant for debugging purposes
 */
public class Database {

	//Statements to query 
	/*
	 * CREATE TABLE genreTable(
    			id int NOT NULL AUTO_INCREMENT,
    			genre_name VARCHAR(50),
    			PRIMARY KEY(id)
    );
	 */
	public static final String CREATE_TABLE_GENRE = "CREATE TABLE " + Const.TABLE_GENRE 
			+ " (" + Const.GENRE_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " 
			+ Const.GENRE_COLUMN_NAME + " VARCHAR(50), "
			+ "PRIMARY KEY(" + Const.GENRE_COLUMN_ID + "));";
	
	
	/*
	 CREATE TABLE bandTable(
		id int NOT NULL AUTO_INCREMENT,
    		band_name VARCHAR(50),
    		genre_id int,
    		PRIMARY KEY(id),
    		FOREIGN KEY (genre_id) REFERENCES genreTable(id)
    	);
	 */
	public static final String CREATE_TABLE_BAND = "CREATE TABLE " + Const.TABLE_BAND 
			+ " (" + Const.BANDS_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " 
			+ Const.BANDS_COLUMN_NAME + " VARCHAR(50), "
			+ Const.BANDS_COLUMN_GENRE_ID + " int, "
			+ "PRIMARY KEY(" + Const.BANDS_COLUMN_ID + "),"
			+ "FOREIGN KEY(" + Const.BANDS_COLUMN_GENRE_ID + ") REFERENCES " + Const.TABLE_GENRE + "(" + Const.GENRE_COLUMN_ID + "));";
		
	/*
	 * CREATE TABLE venueTable(
    		id int NOT NULL AUTO_INCREMENT,
    		venue VARCHAR(50),
    		city VARCHAR(50),
    		PRIMARY KEY(id)
    );
	 */
	public static final String CREATE_TABLE_VENUE = "CREATE TABLE " + Const.TABLE_VENUE
			+ " (" + Const.VENUE_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, "
			+ Const.VENUE_COLUMN_NAME + " VARCHAR(50), " 
			+ Const.VENUE_COLUMN_CITY + " VARCHAR(50), "
			+ "PRIMARY KEY(" + Const.VENUE_COLUMN_ID + "));";
	
	/*CREATE TABLE concertTable(
		    id int NOT NULL AUTO_INCREMENT,
		    band_id int,
		    venue_id int,
		    concert_date DATE,
		    rating TINYINT,
		    picture BLOB,
		    PRIMARY KEY(id),
		    FOREIGN KEY (band_id) REFERENCES bandTable(id),
		    FOREIGN KEY (venue_id) REFERENCES venueTable(id)
		    );
	 */
	public static final String CREATE_TABLE_CONCERT = "CREATE TABLE " + Const.TABLE_CONCERT
			+ " (" + Const.CONCERTS_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, "
			+ Const.CONCERTS_COLUMN_BAND_ID + " int, "
			+ Const.CONCERTS_COLUMN_VENUE_ID + " int, "
			+ Const.CONCERTS_COLUMN_DATE + " DATE, "
			+ Const.CONCERTS_COLUMN_RATING + " TINYINT, " 
			+ Const.CONCERTS_COLUMN_PIC + " LONGBLOB, "
			+ "PRIMARY KEY(" + Const.CONCERTS_COLUMN_ID + "),"
			+ "FOREIGN KEY(" + Const.CONCERTS_COLUMN_BAND_ID + ") REFERENCES " + Const.TABLE_BAND + "(" + Const.BANDS_COLUMN_ID + "),"
			+ "FOREIGN KEY(" + Const.CONCERTS_COLUMN_VENUE_ID + ") REFERENCES " + Const.TABLE_VENUE + "(" + Const.VENUE_COLUMN_ID + "));";

		
	
	
	
	//properties
	private static Database instance = null;
	private Connection connection = null; //this is basically the connection between the server and our application
	
	//private constructor. so it is never called
	private Database() {

		if(connection == null) {
			String url = "jdbc:mysql://localhost/JavaProject";
			String user = "root";
			String password = "";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
				System.out.println("Connection Successfully created");
				
//				//this line is setting up what are we using for the connection
//				Class.forName("com.mysql.jdbc.Driver");
//				connection = DriverManager.getConnection("jdbc:mysql://php.scweb.ca/"+Const.DB_NAME+"?useSSL=false", Const.DB_USERNAME, Const.DB_PASSWORD);
//				System.out.println("Connection Successfully created");
			}catch(Exception e) {
				System.out.println("Something went wrong with the connection");
				e.printStackTrace();
			}
			
			createTable(Const.TABLE_GENRE, CREATE_TABLE_GENRE, connection);
			createTable(Const.TABLE_BAND, CREATE_TABLE_BAND, connection);
			createTable(Const.TABLE_VENUE, CREATE_TABLE_VENUE, connection);
			createTable(Const.TABLE_CONCERT, CREATE_TABLE_CONCERT, connection);



		}else {
			System.out.println("Connection already created");
		}
	}
	
	
	/**
	 * This method is core of the Singleton pattern.
	 * We can call this method anywhere in the project and create the one and only Database.
	 * If this instance has already been created, then this method will be use to return the instance.
	 * 
	 * @return instance: the Database instance
	 */
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	/**
	 * We create the tables using this method. it'll be call when connecting to the database
	 * 
	 * @param tableName name of the table we want to create
	 * @param tableQuery statement to create the table
	 * @param connection connection to the database
	 */
	public void createTable(String tableName, String tableQuery, Connection connection) {
		/**
		 * Create a statement
		 * statements are used to execute queries on the database
		 */
		Statement createTables;
		try {
			//Grab metaData from the database
			DatabaseMetaData md = connection.getMetaData();
			//filter the metaData to filter if the table in question exists
			ResultSet result = md.getTables(null, null, tableName, null);
			//we check if exists by checking if there is any results
			if(result.next()) {
				System.out.println(tableName + " table already exists");
			}else {
				//if not, we create a statements and executed 
				createTables = connection.createStatement();
				createTables.execute(tableQuery);
				System.out.println("The " + tableName + " table has been created");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will simply be use to close the connection
	 * 
	 * it is logging when the database closes for debugging purposes.
	 */
	public void close() {
		System.out.println("Closing connection");
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * simply a getter for the connection object
	 * @return connection
	 */
	public Connection getConnection() {
		return connection;
	}
	
	
	
}

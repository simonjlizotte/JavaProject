package database;

/**
 * 
 * Class should hold all the constants for the table and column names
 *
 */
public class Const {
	
	//credentials (could be placed in a different class that will be ignored by git)
	public static final String DB_NAME = "JavaProject";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "";
		
	//GENRE TABLE
	public static final String TABLE_GENRE = "genreTable";
	public static final String GENRE_COLUMN_ID = "id";
	public static final String GENRE_COLUMN_NAME = "genre_name";
	
	//BAND TABLE
	public static final String TABLE_BAND = "bandTable";
	public static final String BANDS_COLUMN_ID = "id";
	public static final String BANDS_COLUMN_NAME = "band_name";
	public static final String BANDS_COLUMN_GENRE_ID = "genre_id";
	
	//VENUE TABLE
	public static final String TABLE_VENUE = "venueTable";
	public static final String VENUE_COLUMN_ID = "id";
	public static final String VENUE_COLUMN_NAME = "venue";
	public static final String VENUE_COLUMN_CITY = "city";
	
	//CONCERT TABLE
	public static final String TABLE_CONCERT = "concertTable";
	public static final String CONCERTS_COLUMN_ID = "id";
	public static final String CONCERTS_COLUMN_VENUE_ID = "venue_id";
	public static final String CONCERTS_COLUMN_BAND_ID = "band_id";
	public static final String CONCERTS_COLUMN_RATING = "rating";
	public static final String CONCERTS_COLUMN_DATE = "concert_date";
	public static final String CONCERTS_COLUMN_PIC = "picture";
 }

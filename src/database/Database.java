package database;

import java.sql.Connection;

import database.Database;

/**
 * 
 * @author josegeorges
 *
 *	the database class will be created with a singleton pattern so that only one connection gets open
 */
public class Database {

	//properties
	private static Database instance = null;
	private Connection connection = null; //this is basically the connection between the server and our application
	
	//private constructor. so is never called
	private Database() {
		
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
}

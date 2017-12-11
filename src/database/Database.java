package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Database.Const;
import database.Database;

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

	//TODO CREATE a method to create tables needed.
	
	//properties
	private static Database instance = null;
	private Connection connection = null; //this is basically the connection between the server and our application
	
	//private constructor. so it is never called
	private Database() {
		if(connection == null) {
			try {
				//this line is setting up what are we using for the connection
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://php.scweb.ca/"+Const.DB_NAME+"?useSSL=false", Const.DB_USERNAME, Const.DB_PASSWORD);
				System.out.println("Connection Successfully created");
			}catch(Exception e) {
				System.out.println("Something went wrong with the connection");
				e.printStackTrace();
			}
			
			//CREATE QUERIES FOR TABLES SHOULD BE PLACE HERE.
			
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

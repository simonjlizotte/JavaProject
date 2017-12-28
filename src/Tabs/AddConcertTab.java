package Tabs;

import database.Database;
import javafx.scene.control.Tab;

/**
 * 
 * @author josegeorges
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 */
public class AddConcertTab extends Tab{

	//Database
	Database db;
	
	//constants needed
	public static final String TAB_TITLE = "Add Concert"; //title for the tab
	
	//AddConcertTab created
	private static AddConcertTab tab;
	
	//constructor
	private AddConcertTab() {
		this.setText(TAB_TITLE);
		db = Database.getInstance();
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static AddConcertTab getInstance() {
		if(tab == null) {
			tab = new AddConcertTab();
		}
		return tab;
	}
	
}

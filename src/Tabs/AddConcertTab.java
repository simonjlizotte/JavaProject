package Tabs;
import java.io.File;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import objects.Band;
import objects.Genre;
import objects.Venue;
import tables.ConcertTable;

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
		
		this.setText(TAB_TITLE);
		
		//VBox to host the listView
		VBox vbox = new VBox();
		
		Button button = new Button("submit");
		button.setOnMouseClicked(e->{
			System.out.println("pressed");
			Venue venue = new Venue("simond", "city");
			// dont need the below line after 
			Genre genre = new Genre(1, "punk");
			Band band = new Band(1, "band newwdw", genre.getID());
			ConcertTable.createConcert("0001-01-01", 1, "4", band, venue);
		});
		
		vbox.getChildren().add(button);
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setMinHeight(768);
		vbox.setAlignment(Pos.TOP_CENTER);
		this.setContent(vbox);
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static AddConcertTab getInstance() {
		if(tab == null) {
			tab = new AddConcertTab();
		}
		return tab;
	}
	
}

package Tabs;

import java.util.ArrayList;
import java.util.List;
import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import objects.Band;
import objects.Concert;
import tables.BandTable;
import tables.ConcertTable;

/**
 * 
 * @author josegeorges
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 * 
 * @author carmenkerim
 * This tab will display every concert that the user added in a listView
 */
public class ViewConcertTab extends Tab{

	//Database
	Database db;
							
	//constants needed
	public static final String TAB_TITLE = "View Concerts"; //title for the tab
	
	// addConcertTab created
	private static ViewConcertTab tab;
	
	// constructor
	private ViewConcertTab() {
		this.setText(TAB_TITLE);
		// database Instance
		db = Database.getInstance();
		// vBox to host the listView
		BorderPane borderPane = new BorderPane();
				
		//Concert table
		//ConcertTable concertTable = new ConcertTable();
		
		//bands table
		BandTable bandTable = new BandTable();
		
		// listView of band names
		ListView<Band> bandList = new ListView<Band>();
		
		// label to set the title
		Label viewTabTitle = new Label("View Concerts!");
		viewTabTitle.getStyleClass().add("viewTabTitle");
				
		// list of bands
		// grabbing all the bands
		ArrayList<Band> bands = bandTable.getAllBands();
		
		// add those items to the ListView
		bandList.setItems(FXCollections.observableArrayList(bands));
		
		// refresh button
		Button refreshButton = new Button("Refresh");
		refreshButton.setOnAction(e->{
			bandList.setItems(
					FXCollections.observableArrayList(bandTable.getAllBands()));
		});
		
	    // setting the borderPane
	    borderPane.setTop(viewTabTitle);	  	    
	    borderPane.setCenter(bandList);
	    borderPane.setBottom(refreshButton);
	    borderPane.setPadding(new Insets(10,10,10,10));
	    
	    //Getting the position of the borderPane to center
	    borderPane.setAlignment(viewTabTitle, Pos.CENTER);
	    borderPane.setAlignment(refreshButton, Pos.CENTER);
		this.setContent(borderPane);
	}
	//this method will be call when needing the instance of the tab or when first creating it
	public static ViewConcertTab getInstance() {
		if(tab == null) {
			tab = new ViewConcertTab();
		}
		return tab;
	}
	
}


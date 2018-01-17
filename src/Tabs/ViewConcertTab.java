package Tabs;

import java.util.ArrayList;

import SingleConcertView.SingleConcertViewPane;
import SingleConcertView.SingleConcertViewScene;
import database.Database;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import objects.Band;
import objects.Concert;
import objects.Venue;
import tables.BandTable;
import tables.ConcertTable;
import tables.VenueTable;

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
	
	// concert band
	Band concertBand;
	
	//stage
	public static Stage nameStage = new Stage();
	
	//global variable passed to reference the id selected
	public static int num2;
	
	//listview of bands
	public static ListView<Concert> bandList;
	
	Venue venueObject;
	
	// concert displayed
	String itemDisplay;
	
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
				
		//bands table
		BandTable bandTable = new BandTable();
		
		// listView of band names
		 bandList = new ListView<Concert>();
		
		// label to set the title
		Label viewTabTitle = new Label("View Concerts!");
		viewTabTitle.getStyleClass().add("singleViewTitle");
				
		//grabbing the date and band names from the tables to display
		ConcertTable concertTable = new ConcertTable();	
		VenueTable venueTable = new VenueTable();
		ArrayList<String> itemDisplayList = new ArrayList<String>();
		
		ArrayList<Concert> concerts = concertTable.getAllConcerts();
		
		// add those items to the ListView
		bandList.setItems(FXCollections.observableArrayList(concerts));
		
		// onClick event which will if the selected item and diselect it. It will pass the id
		//and call the scene
		bandList.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(final MouseEvent mouseEvent) {
		      Concert selected = bandList.getSelectionModel().getSelectedItem();
		      int selectedNum = bandList.getSelectionModel().getSelectedIndex();
		        if (bandList.getSelectionModel().isSelected(selectedNum)){
		            bandList.getSelectionModel().clearSelection(selectedNum);
		            if(selected != null) {
	                		num2 = selected.getId();
		            }else {
	            			System.out.println("No value");
		            }
	            	
		        	  	Scene scene = new SingleConcertViewScene();
		    			nameStage.setTitle("concert");
		    			nameStage.setScene(scene);
		    			scene.getStylesheets().add("main.css");
		    			nameStage.show();    
		        }
		    }
		});
	
	    // setting the borderPane
	    borderPane.setTop(viewTabTitle);	  	    
	    borderPane.setCenter(bandList);
	    borderPane.setPadding(new Insets(10,10,10,10));
	    //Getting the position of the borderPane to center
	    BorderPane.setAlignment(viewTabTitle, Pos.CENTER);
		this.setContent(borderPane);
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static ViewConcertTab getInstance() {
		if(tab == null) {
			tab = new ViewConcertTab();
		}
		return tab;
		
	}
	
	public void closeNameStage() {
		this.nameStage.close();
	}
	
}


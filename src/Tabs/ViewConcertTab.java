package Tabs;

import java.util.ArrayList;

import SingleConcertView.SingleConcertViewPane;
import SingleConcertView.SingleConcertViewScene;
import database.Database;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
	
	// concert selected
	public static int num;
	
	private static String bandNameFill;
	private static String venueName;
	private static String dateFill;
	
	// concert band
	Band concertBand;
	
	public static int num2;
	
	Venue venueObject;
	
	// concert displayed
	String itemDisplay;
	
	public static String number;
	
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
		ListView<String> bandList = new ListView<String>();
		
		// label to set the title
		Label viewTabTitle = new Label("View Concerts!");
		viewTabTitle.getStyleClass().add("viewTabTitle");
				
		//grabbing the date and band names from the tables to display
		ConcertTable concertTable = new ConcertTable();	
		VenueTable venueTable = new VenueTable();
		ArrayList<String> itemDisplayList = new ArrayList<String>();
		
		ArrayList<Concert> concerts = concertTable.getAllConcerts();
		ArrayList<Venue> venue = venueTable.getAllVenues();
		for (int i = 0; i < concerts.size() ; i++) {
			num = concertTable.getAllConcerts().get(i).getId();
			concertBand = bandTable.getBand(concerts.get(i).getBandID());
			itemDisplay = concerts.get(i).getDate() + ", " + concertBand.getName() + num;
			itemDisplayList.add(itemDisplay);
		}
		
		// add those items to the ListView
		bandList.setItems(FXCollections.observableArrayList(itemDisplayList));
		
//		// this is a mouse event function which currently will o
//		bandList.setOnMouseClicked(new EventHandler<MouseEvent>(){
//
//	          @Override
//	          public void handle(MouseEvent arg0) {
//	        	  	Stage nameStage = new Stage();
//	        	  	Scene scene = new SingleConcertViewScene();
//	    			nameStage.setTitle("concert");
//	    			nameStage.setScene(scene);
//	    			scene.getStylesheets().add("main.css");
//	    			nameStage.show();
//	        	  	System.out.println(bandList.getSelectionModel().getSelectedItem());
//	          }
//	      });
		
		bandList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				for(int j = 0; j < concerts.size(); j++) {
//					num = concertTable.getAllConcerts().get(j).getId();
////					System.out.println("ListView Selection Changed (newValue: " + newValue + i + ")\n");
//				}
				
			System.out.println("ListView Selection Changed (newValue: " + newValue +  ")\n");
			Stage nameStage = new Stage();
        	  	Scene scene = new SingleConcertViewScene();
    			nameStage.setTitle("concert");
    			nameStage.setScene(scene);
    			scene.getStylesheets().add("main.css");
    			nameStage.show();
			}
		});
		
		// refresh button
		Button refreshButton = new Button("Refresh");
		refreshButton.getStyleClass().add("refresh");
		
		refreshButton.setOnAction(e->{
			bandList.setItems(FXCollections.observableArrayList(itemDisplayList));
		});
		
	    // setting the borderPane
	    borderPane.setTop(viewTabTitle);	  	    
	    borderPane.setCenter(bandList);
	    borderPane.setBottom(refreshButton);
	    borderPane.setPadding(new Insets(10,10,10,10));
	    
	    //Getting the position of the borderPane to center
	    BorderPane.setAlignment(viewTabTitle, Pos.CENTER);
	    BorderPane.setAlignment(refreshButton, Pos.CENTER);
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


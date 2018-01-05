package Tabs;

import java.util.ArrayList;
import java.util.List;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import objects.Band;
import objects.Concert;
import objects.Genre;
import objects.Venue;
import tables.ConcertTable;

/**
 * 
 * @author josegeorges
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 * 
 * @author carmenkerim
 * This tab will display every concert that the user added in a listview
 */
public class ViewConcertTab extends Tab{

	//Database
	Database db;
	
	//List of bands
	private ArrayList<Concert> bands;
	
	// listView of band names
	ListView<Concert> bandList;
				
	//Concert table
	ConcertTable concertTable;
	
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
		
		// label to set the title
		Label viewTabTitle = new Label("View Concerts!");
		viewTabTitle.getStyleClass().add("viewTabTitle");
		
		// grabbing all the bands
		bands = concertTable.getAllConcerts();
		
		//a dd those items to the ListView
		bandList.setItems(FXCollections.observableArrayList(bands));
		
//					    
//		// listView
//		bandList = new ListView<Concert>(bands);
//		// setting the preferred width and height
//		bandList.setPrefWidth(150);
//	    bandList.setPrefHeight(150);
		
////	    bandList.setCellFactory(new Callback<ListView<Concert>, ListCell<Concert>>() {
////          public ListCell<Concert> call(ListView<Concert> param) {
////            final ListCell<Concert> cell = new ListCell<Concert>() {
////              @Override
////              public void updateItem(Concert item, boolean empty) {
////                super.updateItem(item, empty);
////                if (item != null) {
////                  setText(item.getBandName());
////                }
////              }
////            }; // ListCell
////            return cell;
////          }
////        }); 
//	    
////
////	    bandList.getSelectionModel().selectedItemProperty()
////	        .addListener(new ChangeListener<Concert>() {
////	          public void changed(ObservableValue<? extends Concert> observable,
////	              Concert oldValue, Concert newValue) { 
////		            Stage selectedStage = new Stage();
////		            	selectedStage.setTitle("Concert view");
////					selectedStage.setScene(new singleConcertViewScene());
////					selectedStage.show();
////	          }
////	        });
//	    
////		
		
//	      final ListView listView = new ListView(data);
//	        listView.setPrefSize(200, 250);
//	        listView.setEditable(true);
//	        
//	        names.addAll(
//	             "Adam", "Alex", "Alfred", "Albert",
//	             "Brenda", "Connie", "Derek", "Donny", 
//	             "Lynne", "Myrtle", "Rose", "Rudolph", 
//	             "Tony", "Trudy", "Williams", "Zach"
//	        );
//	         
//	        for (int i = 0; i < 18; i++) {
//	            data.add("anonym");
//	        }
//	          
//	        listView.setItems(data);
//	        listView.setCellFactory(ComboBoxListCell.forListView(names));              
	               
	        
	    // setting the borderPane
	    borderPane.setTop(viewTabTitle);	  	    
	    borderPane.setCenter(bandList);
	    borderPane.setPadding(new Insets(10,10,10,10));
	    borderPane.setMinHeight(768);
	    
	    //Getting the position of the borderPane to center
	    borderPane.setAlignment(viewTabTitle, Pos.CENTER);
		this.setContent(borderPane);
	}
	
	Venue venue1 = new Venue(2, "simond", "city");
	// Don't need the below line after 
	Genre genre1 = new Genre(1, "punk");
	Band band = new Band(1, "band newwdw", genre1.getID());
	
	//Create dummy list of MyObject
    private void addConcert(){
      
        
    }
    
	//this method will be call when needing the instance of the tab or when first creating it
	public static ViewConcertTab getInstance() {
		if(tab == null) {
			tab = new ViewConcertTab();
		}
		return tab;
	}
	
}


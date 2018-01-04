package Tabs;
import java.io.File;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
			
		//VBox to host the listView
		GridPane pane = new GridPane();
		
		//Declaring insets
		Insets insets = new Insets(10,10,10,10);
		
		//Declaring the Rating

		
		//First Row - Band		
		Text bandName = new Text("Band:");
		pane.add(bandName, 0, 0);
		TextField bandNameInput = new TextField();
		pane.add(bandNameInput, 1, 0);
		
		//Second Row - Venue
		Text venue = new Text("Venue: ");
		pane.add(venue, 0, 1);
		TextField venueInput = new TextField();
		pane.add(venueInput, 1, 1);
		
		//Third Row - City
		Text city = new Text("City: ");
		pane.add(city, 0, 2);
		TextField cityInput = new TextField();
		pane.add(cityInput, 1, 2);
		
		//Fourth Row - Opening Act
		Text openingAct = new Text("Opening Act: ");
		pane.add(openingAct, 0, 3);
		TextField openingActInput = new TextField();
		pane.add(openingActInput, 1, 3);
		
		//Fifth Row - Textfield for the genre, will change to a combobox when the ENUMS are made
		Text genre = new Text("Genre:");
		pane.add(genre, 0, 4);
		TextField genreInput = new TextField();
		pane.add(genreInput, 1, 4);
		
		//Sixth Row - Seat Numbers
		Text seatNumbers = new Text("Seat Numbers: ");
		TextField seatNumbersInput = new TextField();
		pane.add(seatNumbers, 0, 5);
		pane.add(seatNumbersInput, 1, 5);
		
		//Seventh Row - Date attended
		Text dateAttended = new Text("Date Attended: ");
		DatePicker date = new DatePicker();
		pane.add(dateAttended, 0, 6);
		pane.add(date, 1, 6);
		
		
		//Eighth Row - Rating- I will fix this over the weekend
//		Text ratingText = new Text("Rating: ");
//		rating.setMax(5);
//		rating.setUpdateOnHover(false);
//		rating.setPartialRating(true);
//		pane.add(ratingText, 0, 7);
//		pane.add(rating, 1, 7);
		
//		//Final Row - Upload File
//		FileChooser fc = new FileChooser();
//		fc.setTitle("Upload Picture");
//		
		
//		pane.add(fileTest, 0, 9);
//		
//		fileTest.setOnAction(e->{
//			File file = fc.showOpenDialog(null);
//            if (file != null) {
//                openFile(file);
//            }
//		});
//		
		pane.setPadding(insets);
		pane.setVgap(10);
		pane.setHgap(10);
		this.setContent(pane);
		
		Button button = new Button("submit");
//		button.setOnMouseClicked(e->{
//			System.out.println("pressed");
//			Venue venue = new Venue(2, "simond", "city");
//			// dont need the below line after 
//			Genre genre = new Genre(1, "punk");
//			Band band = new Band(1, "band newwdw", genre.getID());
//			ConcertTable.createConcert("0001-01-01", 1, "4", band, venue);
//		});
		
//		pane.add(button, 0, 9);
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static AddConcertTab getInstance() {
		if(tab == null) {
			tab = new AddConcertTab();
		}
		return tab;
	}
	
}

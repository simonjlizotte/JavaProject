package SingleConcertView;

import java.util.ArrayList;

import Tabs.ViewConcertTab;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import objects.Band;
import objects.Concert;
import objects.Genre;
import objects.Venue;
import tables.BandTable;
import tables.ConcertTable;
import tables.GenreTable;
import tables.VenueTable;

public class SingleConcertViewPane extends BorderPane{

	ViewConcertTab viewConcert;
	
	Concert itemSelected;
	public SingleConcertViewPane() {
		// Create a Vbox 
		HBox buttonBox = new HBox();
		GridPane inputs = new GridPane();
		
		//instance of viewConcert
		ViewConcertTab.getInstance();
		
		// id of the concert
		int concertId = ViewConcertTab.num2;
		
		// concertTable created
		ConcertTable concertTable = new ConcertTable();
		
		// bandTable created
		BandTable bandTable = new BandTable();
		
		// genreTable
		GenreTable genreTable = new GenreTable();
		
		// venueTable
		VenueTable venueTable = new VenueTable();
		
		// Getting the concert id of the object passed
		Concert concertObject = concertTable.getConcert(concertId);

		// Band object get the band id 
		Band bandObject = bandTable.getBand(concertObject.getBandID());
		
		// genre table
		Genre genreObject = new Genre();
		genreObject = genreTable.getGenre(bandObject.getGenreId());
		ArrayList<Genre> genreObjectAll = genreTable.getAllGenres();

		// get the venue id
		Venue venueObject = venueTable.getVenue(concertObject.getVenueID());
		
		//Storing the objects values
		String objectName = bandObject.getName();
		String venueName = venueObject.getVenue();
		String genreName = genreObject.getGenre();
		String dateAdded = concertObject.getDate();
		String cityName = venueObject.getCity();
		
		// Title
		Label title = new Label("Concert View");
		
		//Creating the remove and edit buttons
		Button edit = new Button("Update Concert");
		edit.setVisible(true);
		Button remove = new Button("Remove Concert");
		Button saveEdits = new Button("Save Changes");
		saveEdits.setVisible(false);
		
		Label updatesValues = new Label("Update The Values");
		inputs.add(updatesValues, 0, 0);
		updatesValues.setVisible(false);
		
		buttonBox.getChildren().addAll(edit, saveEdits, remove);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10,10,10,10));
		
		remove.setOnMouseClicked(e->{
			concertTable.deleteConcert(concertId);
		});
		
		inputs.setPadding(new Insets(10,10,10,10));
		inputs.setVgap(10);
		inputs.setHgap(10);
		
		remove.setVisible(false);
		//Creating the input fields labels
		Label whatBand = new Label("What band?");
		inputs.add(whatBand, 0, 1);
		
		Label whereAt = new Label("What Venue?");
		inputs.add(whereAt, 0, 2);
		
		Label whatCity = new Label("What City?");
		inputs.add(whatCity, 0, 3);
		
		Label genere = new Label("Genere:");
		inputs.add(genere, 0, 5);
		
		Label dateAttended = new Label("Date Attended:");
		Label dateFormat = new Label("(yyyy/mm/dd)");
		// setting the dataformat to hide
		dateFormat.setVisible(false);
		inputs.add(dateAttended, 0, 7);
		inputs.add(dateFormat, 2, 7);
		
		Label overallRating = new Label("Overall Rating:");
		inputs.add(overallRating, 0, 8);
		
		Label pictures = new Label("Pictures from the Event:");
		inputs.add(pictures, 0, 9);
		
		//Create the TextFields, DatePicker, and ComboBox for the values
		TextField whatBandInput = new TextField();
		whatBandInput.setPromptText("");
		whatBandInput.setText(objectName);
		whatBandInput.setEditable(false);
		inputs.add(whatBandInput, 1, 1);
		
		TextField whereAtInput = new TextField();
		whereAtInput.setPromptText("Venue Name");
		whereAtInput.setText(venueName);
		whereAtInput.setEditable(false);
		inputs.add(whereAtInput, 1, 2);
		
		TextField whatCityInput = new TextField();
		whatCityInput.setEditable(false);
		whatCityInput.setPromptText("City");
		whatCityInput.setText(cityName);
		inputs.add(whatCityInput, 1, 3);

		ComboBox<Genre> genereInput = new ComboBox<>();
		inputs.add(genereInput, 1, 5);
		
		// removing the genre input box
		inputs.getChildren().remove(genereInput);
		
		// creating a genreDisplay and adding it to the GridPane
		Label genreDisplay = new Label(genreName);
		inputs.add(genreDisplay, 1, 5);
		
		DatePicker dateAttendedInput = new DatePicker();
		inputs.add(dateAttendedInput, 1, 7);
		// removing the datePicker from the gridPane
		inputs.getChildren().remove(dateAttendedInput);
		// creating a label and adding it to the GridPane
		Label dateDisplay = new Label(dateAdded);
		inputs.add(dateDisplay, 1, 7);
		
		
		edit.setOnAction((event)->{
			// setting the edit boxes to true
			whatBandInput.setEditable(true);
			whereAtInput.setEditable(true);
			whatCityInput.setEditable(true);
			
			//adding the input boxes back to the grid pane
			inputs.getChildren().remove(genreDisplay);
			inputs.add(genereInput, 1, 5);
			genereInput.setItems(FXCollections.observableArrayList(
						genreObjectAll));
			
			inputs.getChildren().remove(dateDisplay);
			inputs.add(dateAttendedInput, 1, 7);
			
			// showing the date format to show
			dateFormat.setVisible(true);
			// setting the text view visibility
			edit.setVisible(false);
			saveEdits.setVisible(true);
			updatesValues.setVisible(true);
			buttonBox.getChildren().remove(edit);
			remove.setVisible(true);
		});
		
		saveEdits.setOnAction((event)->{
					whatBandInput.setEditable(false);
					whereAtInput.setEditable(false);
					whatCityInput.setEditable(false);

					//adding the input boxes back
					//adding the input boxes back to the grid pane
					inputs.getChildren().remove(genereInput);
					inputs.add(genreDisplay, 1, 5);
				
					inputs.getChildren().remove(dateAttendedInput);
					inputs.add(dateDisplay, 1, 7);

					edit.setVisible(true);
					saveEdits.setVisible(false);
					updatesValues.setVisible(false);
					buttonBox.getChildren().add(0, edit);
					remove.setVisible(false);
					
					//venue update
					venueTable.updateVenue(concertObject.getVenueID(), whereAtInput.getText().toString());
					//city update
					venueTable.updateCity(concertObject.getVenueID(), whatCityInput.getText().toString());
					//band update
					bandTable.updateBand(concertObject.getBandID(), whatBandInput.getText().toString());
				});
		
		
		// add nodes to pane 
		this.setTop(title);
		this.setCenter(inputs);
		this.setBottom(buttonBox);
		
		
		// Set Alignment
		SingleConcertViewPane.setAlignment(title, Pos.CENTER);
		SingleConcertViewPane.setAlignment(buttonBox, Pos.CENTER);
		
	
	}
}

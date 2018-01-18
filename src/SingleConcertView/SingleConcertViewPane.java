package SingleConcertView;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import Tabs.ViewConcertTab;
import confirmationMessage.DeleteMessageScene;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import objects.Band;
import objects.Concert;
import objects.Genre;
import objects.Venue;
import tables.BandTable;
import tables.ConcertTable;
import tables.GenreTable;
import tables.VenueTable;
import viewPhoto.ViewPhotoScene;
/**
 * @author nickstajduhar
 * @author carmenkerim
 * 
 * Singleconcertview to display concerts from the database
 */

public class SingleConcertViewPane extends BorderPane{

	ViewConcertTab viewConcert;
	//stage
	public static Stage nameStage = new Stage();
	
	//TextFields
	TextField whatBandInput;
	
	//DatePicker
	DatePicker dateAttendedInput;
	
	Concert itemSelected;
	public SingleConcertViewPane() {
		// create a VBox to store the buttons
		HBox buttonBox = new HBox();
		buttonBox.getStyleClass().add("buttonBox");
		buttonBox.setSpacing(20);
		
		// gridPane to store the inputs of the 
		GridPane inputs = new GridPane();
		
		// instance of ViewConcertTab
		ViewConcertTab.getInstance();
	
		// id of the concert being passed from the listview
		int concertId = ViewConcertTab.num2;
		
		/**
		 * Creating the tables needed
		 */
		// concertTable created
		ConcertTable concertTable = new ConcertTable();
		
		// bandTable created
		BandTable bandTable = new BandTable();
		
		// genreTable
		GenreTable genreTable = new GenreTable();
		
		// venueTable
		VenueTable venueTable = new VenueTable();
		
		//updates image to selected concert
		concertTable.getConcertImage(concertId);
		
		
		// Getting the concert id of the object passed
		Concert concertObject = concertTable.getConcert(concertId);
		// Band object get the band id 
		Band bandObject = bandTable.getBand(concertObject.getBandID());
		// genre object
		Genre genreObject = new Genre();
		// get the venue id
		Venue venueObject = venueTable.getVenue(concertObject.getVenueID());
		
		//getting the genre
		genreObject = GenreTable.getGenre(bandObject.getGenreId());
		ArrayList<Genre> genreObjectAll = GenreTable.getAllGenres();

		//Storing the objects values
		String objectName = bandObject.getName();
		String venueName = venueObject.getVenue();
		String genreName = genreObject.getGenre();
		String dateAdded = concertObject.getDate();
		String cityName = venueObject.getCity();
		
		//----------------GRID PANE(INPUTS)------------------//
		
		// title
		Label title = new Label("Concert view");
		title.getStyleClass().add("singleViewTitle");
		
		// creating the remove and edit buttons
		Button edit = new Button("Update Concert");
		edit.getStyleClass().add("buttonLoad");
		edit.setVisible(true);
		
		// remove button
		Button removeButton = new Button("Remove Concert");
		
		// save button
		Button saveEdits = new Button("Save Changes");
		
		// update values
		Label updatesValues = new Label("Update The Values");
		
		buttonBox.getChildren().addAll(edit);
		buttonBox.setAlignment(Pos.CENTER);
		
		/**
		 * Have to pass the concertId and concertTable so that we can delete 
		 * the concert right in the confirmation button
		 */
		removeButton.setOnMouseClicked(e->{
			TranslateTransition tt = new TranslateTransition(Duration.millis(200), removeButton);
		     tt.setByY(15f);
		     tt.setCycleCount(2);
		     tt.setAutoReverse(true);
		 
		     tt.play();
			Scene scene = new DeleteMessageScene(whatBandInput.getText().toString(), dateAttendedInput.getValue().toString(), concertId, concertTable );
			concertTable.garbageCollection();
			nameStage.setScene(scene);
			scene.getStylesheets().add("main.css");
			nameStage.show();	
		});
		
		// style class to add the removeButton
	     removeButton.getStyleClass().add("removeButton");
		
		inputs.setVgap(5);
		
		//Creating the input fields labels
		Label whatBand = new Label("Band:");
		whatBand.getStyleClass().add("labelFont");
		inputs.add(whatBand, 0, 0);
		whatBandInput = new TextField();
		whatBandInput.setPromptText("");
		whatBandInput.setText(objectName);
		whatBandInput.setEditable(false);
		inputs.add(whatBandInput, 0, 1);
		
		Label whereAt = new Label("Venue:");
		whereAt.getStyleClass().add("labelFont");
		inputs.add(whereAt, 0, 2);
		
		TextField whereAtInput = new TextField();
		whereAtInput.setPromptText("Name");
		whereAtInput.setText(venueName);
		whereAtInput.setEditable(false);
		inputs.add(whereAtInput, 0, 3);
		
		Label whatCity = new Label("City:");
		whatCity.getStyleClass().add("labelFont");
		inputs.add(whatCity, 0, 4);
		
		TextField whatCityInput = new TextField();
		whatCityInput.setEditable(false);
		whatCityInput.setPromptText("City");
		whatCityInput.setText(cityName);
		inputs.add(whatCityInput, 0, 5);
		
		Label genre = new Label("Genre:");
		genre.getStyleClass().add("labelFont");
		inputs.add(genre, 0, 6);
		
		ComboBox<Genre> genreInput = new ComboBox<>();
		inputs.add(genreInput, 0, 12);
		// removing the genre input box
		inputs.getChildren().remove(genreInput);
		// creating a genreDisplay and adding it to the GridPane
		Label genreDisplay = new Label(genreName);
		inputs.add(genreDisplay,0, 7);
		
		Label dateAttended = new Label("Date Attended:");
		dateAttended.getStyleClass().add("labelFont");
		Label dateFormat = new Label("(yyyy/mm/dd)");
		// setting the dataformat to hide
		dateFormat.setVisible(false);
		inputs.add(dateAttended, 0, 8);
//		inputs.add(dateFormat, 1, 9);

		dateAttendedInput = new DatePicker();
		inputs.add(dateAttendedInput, 0, 9);
		
		Label overallRating = new Label("Overall Rating:");
		overallRating.getStyleClass().add("labelFont");
		inputs.add(overallRating, 0, 12);
	
		Label overallRatingInput = new Label(concertObject.getRating() + "");
		inputs.add(overallRatingInput, 0, 13);
		
		// Combo box of rating 
		ComboBox<Integer> comboRating = new ComboBox<>();
		ArrayList<Integer> ratingArray = new ArrayList<Integer>();
		ratingArray.add(1);
		ratingArray.add(2);
		ratingArray.add(3);
		ratingArray.add(4);
		ratingArray.add(5);
		
		comboRating.setItems(
				FXCollections.observableArrayList(ratingArray));
		comboRating.setValue(concertObject.getRating());
		
		inputs.add(comboRating, 0, 14);
		comboRating.setVisible(false);
		
		Label pictures = new Label("Pictures from the Event:");
		pictures.getStyleClass().add("labelFont");
		Label picturesLink = new Label("View Picture");
		picturesLink.getStyleClass().add("picturesLink");
		
		// shows the photo uploaded
		picturesLink.setOnMouseClicked(e->{
			Scene scene = new ViewPhotoScene();
			nameStage.setScene(scene);
			scene.getStylesheets().add("main.css");
			nameStage.show();
		});
		
		
		inputs.add(pictures, 0, 10);
		inputs.add(picturesLink, 0, 11);
	
		// imageview of the image the user added
		ImageView imageDisplay = new ImageView();
		File file = new File("selectedImg.png");
        Image image = new Image(file.toURI().toString());
        imageDisplay.setImage(image);		
		imageDisplay.setFitWidth(150);
		BorderPane.setAlignment(imageDisplay, Pos.CENTER_RIGHT);
		imageDisplay.setPreserveRatio(true);
		
		// removing the datePicker from the gridPane
		inputs.getChildren().remove(dateAttendedInput);
		
		// creating a label and adding it to the GridPane
		Label dateDisplay = new Label(dateAdded);
		inputs.add(dateDisplay, 0, 9);		
	
		/**
		 * This edit button will make the labels and input boxes to editable
		 */
		edit.setOnAction((event)->{
			TranslateTransition tt = new TranslateTransition(Duration.millis(200), edit);
		     tt.setByY(15f);
		     tt.setCycleCount(2);
		     tt.setAutoReverse(true);
		 
		     tt.play();
			// setting the edit boxes to true
			whatBandInput.setEditable(true);
			whereAtInput.setEditable(true);
			whatCityInput.setEditable(true);
			title.getStyleClass().add("singleViewTitleEdit");

			//adding the input boxes back to the grid pane
			inputs.getChildren().remove(genreDisplay);
			inputs.add(genreInput, 0, 7);
			genreInput.setItems(FXCollections.observableArrayList(
						genreObjectAll));
			
			genreInput.setValue(GenreTable.getGenre(bandObject.getGenreId()));
			
			// Setting current date value so it doesnt add a null value
			LocalDate inputDate = LocalDate.parse(dateAdded);
			dateAttendedInput.setValue(inputDate);
			inputs.getChildren().remove(dateDisplay);
			inputs.add(dateAttendedInput, 0, 9);
			
			// showing the date format to show
			dateFormat.setVisible(true);
			title.setText("Update Values");
			buttonBox.getChildren().remove(edit);
			buttonBox.getChildren().addAll(saveEdits, removeButton);
			ConcertTable concertTableTest = new ConcertTable();
			ArrayList<Concert> test = concertTableTest.getAllConcerts();
			if (test.size() != 0) {
			concertTable.garbageCollection();
			}
			comboRating.setVisible(true);
			overallRatingInput.setVisible(false);
		});
			
		/**
		 * This save button will update all the values needed
		 */
		saveEdits.setOnAction((event)->{
			TranslateTransition tt = new TranslateTransition(Duration.millis(2000), saveEdits);
		     tt.setByY(15f);
		     tt.setCycleCount(2);
		     tt.setAutoReverse(true);
		 
		     tt.play();
					title.setText("ConcertView");
					whatBandInput.setEditable(false);
					whereAtInput.setEditable(false);
					whatCityInput.setEditable(false);
					comboRating.setVisible(false);
					overallRatingInput.setVisible(true);


					//adding the input boxes back
					//adding the input boxes back to the grid pane
					inputs.getChildren().remove(genreInput);
					inputs.add(genreDisplay, 0, 7);
				
					inputs.getChildren().remove(dateAttendedInput);
					inputs.add(dateDisplay, 1, 7);

					edit.setVisible(true);
					saveEdits.setVisible(false);
					updatesValues.setVisible(false);
					buttonBox.getChildren().add(0, edit);
					removeButton.setVisible(false);
					
					//venue update
					venueTable.updateVenue(concertObject.getVenueID(), whereAtInput.getText().toString());
					
					//city update
					venueTable.updateCity(concertObject.getVenueID(), whatCityInput.getText().toString());
					
					//band update
					bandTable.updateBand(concertObject.getBandID(), whatBandInput.getText().toString());
					bandTable.updateGenre(genreInput.getSelectionModel().getSelectedItem().getId(), concertObject.getBandID());
					genreDisplay.setText(genreInput.getSelectionModel().getSelectedItem().getGenre());
					concertTable.updateDate(dateAttendedInput.getValue().toString().toUpperCase().trim(), concertObject.getId());
					concertTable.updateRating(comboRating.getValue(), concertObject.getId());
					dateDisplay.setText(dateAttendedInput.getValue().toString());
					// clean tables of unused data
					ConcertTable concertTableTest = new ConcertTable();
					ArrayList<Concert> test = concertTableTest.getAllConcerts();
					if (test.size() != 0) {
					concertTable.garbageCollection();
					}
					ViewConcertTab.bandList.setItems(FXCollections.observableArrayList(concertTable.getAllConcerts()));
					ViewConcertTab.nameStage.close();
					
				});
		
		saveEdits.getStyleClass().add("buttonLoad");
		
		inputs.setAlignment(Pos.TOP_CENTER);
		inputs.getStyleClass().add("inputsLay");
		// Set Alignment
		SingleConcertViewPane.setAlignment(title, Pos.CENTER);
		SingleConcertViewPane.setAlignment(inputs, Pos.TOP_CENTER);
		SingleConcertViewPane.setAlignment(buttonBox, Pos.CENTER);	
		
		// add nodes to pane 
		this.setTop(title);
		this.setCenter(inputs);
		this.setBottom(buttonBox);
	}
}

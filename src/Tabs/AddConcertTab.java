package Tabs;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import confirmationMessage.ConfirmationMessageScene;
import database.Database;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import objects.Band;
import objects.Genre;
import objects.Venue;
import tables.ConcertTable;
import tables.GenreTable;

/**
 * 
 * @author josegeorges
 * @author carmenkerim
 * @author simonlizotte
 * @author adambazzi
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 */
public class AddConcertTab extends Tab{
	//file String
	File file;
	String filePath;
	
	//stage
	public static Stage nameStage = new Stage();
	
	//Database
	Database db;
	
	// concert table
	ConcertTable concertTable = new ConcertTable();
	
	//constants needed
	public static final String TAB_TITLE = "Add Concert"; //title for the tab
	
	//AddConcertTab created
	private static AddConcertTab tab;
	
	//constructor
	private AddConcertTab() {
		this.setText(TAB_TITLE);
		
		db = Database.getInstance();
			
		GenreTable genreTable = new GenreTable();
		genreTable.insertGenre("PUNK");
		genreTable.insertGenre("METAL");
		genreTable.insertGenre("POP");
		genreTable.insertGenre("REGGAE");
		genreTable.insertGenre("SKA");
		genreTable.insertGenre("ROCK");
		genreTable.insertGenre("HIP-HOP");
		
		// Title
		Label title = new Label("Add A Concert");
		title.getStyleClass().add("singleViewTitle");
		
		//prompt that the photo was added
		Label photoAddedLabel = new Label("Photo Attached");
		photoAddedLabel.getStyleClass().add("photoAddedLabel");
		
		//VBox to host the listView
		GridPane pane = new GridPane();
		
		BorderPane container = new BorderPane();
		
		//Declaring insets
		Insets insets = new Insets(10,10,10,10);
		
		//Declaring the Ratin
		 
		//First Row - Band		
		Text bandName = new Text("Band:");
		pane.add(bandName, 0, 0);
		TextField bandNameInput = new TextField();
		pane.add(bandNameInput, 0 , 1);
		
		bandNameInput.setOnMouseClicked(e->{
			 FadeTransition ft = new FadeTransition();
			 ft.setDuration(Duration.millis(2300.0));
		     ft.setFromValue(1.0);
		     ft.setToValue(0.3);
		     ft.setCycleCount(4);
		     ft.setAutoReverse(true);
		     ft.play();
		});
		//Second Row - Venue
		Text venue = new Text("Venue: ");
		pane.add(venue, 0, 2);
		TextField venueInput = new TextField();
		pane.add(venueInput, 0, 3);
		
		//Third Row - City
		Text city = new Text("City: ");
		pane.add(city, 0, 4);
		TextField cityInput = new TextField();
		pane.add(cityInput, 0, 5);
		
		//Fifth Row - Genre
		Text genre = new Text("Genre:");
		pane.add(genre, 0 , 6 );		
		ComboBox<Genre> comboGenre = new ComboBox<>();
		comboGenre.setItems(
				FXCollections.observableArrayList(
						GenreTable.getAllGenres()));
		pane.add(comboGenre, 0, 7);
		
		//Seventh Row - Date attended
		Text dateAttended = new Text("Date Attended: ");
		DatePicker date = new DatePicker();
		pane.add(dateAttended, 0, 8);
		pane.add(date, 0, 9);
		
		Text missingFields = new Text("MISSING SOME FIELDS");
		missingFields.setVisible(false);
		pane.add(missingFields, 0, 14);
		
		//Eighth Row - Rating- I will fix this over the weekend
		Text ratingText = new Text("Rating: ");
		ComboBox<Integer> comboRating = new ComboBox<>();
		ArrayList<Integer> ratingArray = new ArrayList<Integer>();
		ratingArray.add(1);
		ratingArray.add(2);
		ratingArray.add(3);
		ratingArray.add(4);
		ratingArray.add(5);
		comboRating.setItems(
				FXCollections.observableArrayList(ratingArray));
		comboRating.setValue(ratingArray.get(0));
		pane.add(ratingText, 0, 12);
		pane.add(comboRating, 0, 13);
		
		//Final Row - Upload File
		Text uploadPic = new Text("Upload a picture: ");
		Button btnLoad = new Button("Load");
	    btnLoad.getStyleClass().add("buttonLoad");
		pane.add(uploadPic, 0, 10);
		pane.add(btnLoad, 0,11);
        
		btnLoad.setOnAction(new EventHandler<ActionEvent>() {

        	
			@Override
			public void handle(ActionEvent event) {
				 FileChooser fileChooser = new FileChooser();
	             
		            //Setting filters so that the user can only add jpg and png
				 	FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		              
		            //Show open file dialog
		            if ( (file = fileChooser.showOpenDialog(null)) != null) {
		            		filePath = file.getAbsolutePath();
		               	pane.getChildren().remove(uploadPic);
		            		pane.add(photoAddedLabel, 0, 10);
		            		btnLoad.setVisible(false);
		            }
		            //file = fileChooser.showOpenDialog(null).getAbsolutePath();
			}
        });
        
        pane.getStyleClass().add("paneAdd");
        pane.setMaxWidth(300);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(insets);
		pane.setVgap(10);
		pane.setHgap(10);
		
//		this.setContent(pane);
		
		Button button = new Button("Submit");
		button.getStyleClass().add("submit");
		button.setOnMouseClicked(e->{
			//Animation
			TranslateTransition tt = new TranslateTransition(Duration.millis(200), button);
		     tt.setByY(15f);
		     tt.setCycleCount(2);
		     tt.setAutoReverse(true);
		 
		     tt.play();
			//checking that there are no fields missing
			if(venueInput.getText().isEmpty() || cityInput.getText().isEmpty() || bandNameInput.getText().isEmpty()
					|| comboGenre.getSelectionModel().isEmpty() || comboRating.getSelectionModel().isEmpty() || date.getValue() == null || file == null){
				missingFields.setVisible(true);
			}else {
			
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(filePath);
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
			Venue venueObject = new Venue(venueInput.getText().toString().toUpperCase().trim(), cityInput.getText().toString().toUpperCase());
			System.out.println(comboGenre.getValue().getId());
			Band band = new Band(bandNameInput.getText().toString().toUpperCase().trim(), comboGenre.getValue().getId());
			String confirmation = ConcertTable.createConcert(date.getValue().toString().toUpperCase().trim(), comboRating.getValue(), fis, band, venueObject);
			ViewConcertTab.bandList.setItems(FXCollections.observableArrayList(concertTable.getAllConcerts()));
			missingFields.setVisible(false);
			venueInput.clear();
			bandNameInput.clear();
			cityInput.clear();
			
			// removing the added photo label
     		pane.getChildren().remove(photoAddedLabel);
     		pane.add(uploadPic, 0, 10);
			
//			// reseting the genre
//			comboGenre.getItems().clear();
//			comboGenre.setItems(
//					FXCollections.observableArrayList(
//							GenreTable.getAllGenres()));
			
			date.setValue(null);
			comboGenre.setValue(null);
			btnLoad.setVisible(true);

			date.getEditor().clear();
			
//			//reseting the rating
//			comboRating.getItems().clear();
//			comboRating.setItems(
//					FXCollections.observableArrayList(ratingArray));
			
			Scene scene = new ConfirmationMessageScene(confirmation);
			nameStage.setScene(scene);
			scene.getStylesheets().add("main.css");
			nameStage.show();  
			}
		});
		
//		pane.add(button, 1, 9);
		HBox submitContainer = new HBox();
		submitContainer.getChildren().add(button);
		submitContainer.setPadding(new Insets(10,10,20,10));
		submitContainer.setAlignment(Pos.CENTER);
		
		container.setCenter(pane);
		container.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);
		container.setBottom(submitContainer);
		this.setContent(container);
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static AddConcertTab getInstance() {
		if(tab == null) {
			tab = new AddConcertTab();
		}
		return tab;
	}

	
}

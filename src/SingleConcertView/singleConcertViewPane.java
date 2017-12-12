package SingleConcertView;

import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
/**
 * Created a pane to store the credits of the program 
 * there is an associated scene for this pane 
 */
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class singleConcertViewPane extends BorderPane {
	
	public singleConcertViewPane() {
		// Create a Vbox 
		HBox buttonBox = new HBox();
		GridPane inputs = new GridPane();
		
		// Create text items 
		
		// Title
		Label title = new Label("Concert View");
		
		//Creating the remove and edit buttons
		Button edit = new Button("Update Concert");
		Button remove = new Button("Remove Concert");
		
		buttonBox.getChildren().addAll(edit, remove);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10,10,10,10));
		
		inputs.setPadding(new Insets(10,10,10,10));
		inputs.setVgap(10);
		inputs.setHgap(10);
		
		//Creating the input fields labels
		Label whatBand = new Label("What band?");
		inputs.add(whatBand, 0, 0);
		Label whereAt = new Label("What Venue?");
		inputs.add(whereAt, 0, 1);
		Label whatCity = new Label("What City?");
		inputs.add(whatCity, 0, 2);
		Label openingAct = new Label("Who was the opening act?");
		inputs.add(openingAct, 0, 3);
		Label genere = new Label("Genere:");
		inputs.add(genere, 0, 4);
		Label seats = new Label("Seat Numbers & Section:");
		inputs.add(seats, 0, 5);
		Label dateAttended = new Label("Date Attended:");
		Label dateFormat = new Label("dd/mm/yyyy");
		inputs.add(dateAttended, 0, 6);
		inputs.add(dateFormat, 2, 6);
		Label overallRating = new Label("Overall Rating:");
		inputs.add(overallRating, 0, 7);
		Label pictures = new Label("Pictures from the Event:");
		inputs.add(pictures, 0, 8);
		
		//Create the TextFields, DatePicker, and ComboBox for the values
		TextField whatBandInput = new TextField("Band Name");
		inputs.add(whatBandInput, 1, 0);
		TextField whereAtInput = new TextField("Venue Name");
		inputs.add(whereAtInput, 1, 1);
		TextField whatCityInput = new TextField("City");
		inputs.add(whatCityInput, 1, 2);
		TextField openingActInput = new TextField("Opening Act");
		inputs.add(openingActInput, 1, 3);
		ComboBox<ArrayList> genereInput = new ComboBox<>();
		inputs.add(genereInput, 1, 4);
		TextField seatsInput = new TextField("Seats and Section");
		inputs.add(seatsInput, 1, 5);
		DatePicker dateAttendedInput = new DatePicker();
		inputs.add(dateAttendedInput, 1, 6);
		TextField overallRatingInput = new TextField("Rating");
		inputs.add(overallRatingInput, 1, 7);
		
		// add nodes to pane 
		
		this.setTop(title);
		this.setCenter(inputs);
		this.setBottom(buttonBox);
		
		
		// Set Alignment
		singleConcertViewPane.setAlignment(title, Pos.CENTER);
		singleConcertViewPane.setAlignment(buttonBox, Pos.CENTER);
		
	
	}

}

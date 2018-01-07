package SingleConcertView;

import java.util.ArrayList;

import Tabs.ViewConcertTab;
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
import objects.Venue;
import tables.BandTable;
import tables.ConcertTable;

public class SingleConcertViewPane extends BorderPane{

	ViewConcertTab viewConcert;
	
	Concert itemSelected;
	public SingleConcertViewPane() {
		// Create a Vbox 
		HBox buttonBox = new HBox();
		GridPane inputs = new GridPane();
		
		viewConcert.getInstance();
		
		int concertId = viewConcert.num2;
		
		ConcertTable concertTable = new ConcertTable();
		
		Concert concertObject = concertTable.getConcert(concertId);
		
		BandTable bandTable = new BandTable();
		
		Band bandObject = bandTable.getBand(concertObject.getBandID());
		
		bandObject.getName();
		System.out.println(concertId);
		
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
		
		buttonBox.getChildren().addAll(edit, remove, saveEdits);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10,10,10,10));
		
		inputs.setPadding(new Insets(10,10,10,10));
		inputs.setVgap(10);
		inputs.setHgap(10);
		
		//Creating the input fields labels
		Label whatBand = new Label("What band?");
		inputs.add(whatBand, 0, 1);
		Label whereAt = new Label("What Venue?");
		inputs.add(whereAt, 0, 2);
		Label whatCity = new Label("What City?");
		inputs.add(whatCity, 0, 3);
		Label openingAct = new Label("Who was the opening act?");
		inputs.add(openingAct, 0, 4);
		Label genere = new Label("Genere:");
		inputs.add(genere, 0, 5);
		Label seats = new Label("Seat Numbers & Section:");
		inputs.add(seats, 0, 6);
		Label dateAttended = new Label("Date Attended:");
		Label dateFormat = new Label("dd/mm/yyyy");
		inputs.add(dateAttended, 0, 7);
		inputs.add(dateFormat, 2, 7);
		Label overallRating = new Label("Overall Rating:");
		inputs.add(overallRating, 0, 8);
		Label pictures = new Label("Pictures from the Event:");
		inputs.add(pictures, 0, 9);
		
		//Create the TextFields, DatePicker, and ComboBox for the values
		TextField whatBandInput = new TextField();
		whatBandInput.setPromptText(bandObject.getName());
		whatBandInput.setEditable(false);
		inputs.add(whatBandInput, 1, 1);
		TextField whereAtInput = new TextField();
		whereAtInput.setPromptText("Venue Name");
		whereAtInput.setEditable(false);
		inputs.add(whereAtInput, 1, 2);
		TextField whatCityInput = new TextField();
		whatCityInput.setEditable(false);
		whatCityInput.setPromptText("City");
		inputs.add(whatCityInput, 1, 3);
		TextField openingActInput = new TextField();
		openingActInput.setEditable(false);
		openingActInput.setPromptText("Opening Act");
		inputs.add(openingActInput, 1, 4);
		ComboBox<ArrayList> genereInput = new ComboBox<>();
		inputs.add(genereInput, 1, 5);
		TextField seatsInput = new TextField();
		seatsInput.setEditable(false);
		seatsInput.setPromptText("Seats and Section");
		inputs.add(seatsInput, 1, 6);
		DatePicker dateAttendedInput = new DatePicker();
		inputs.add(dateAttendedInput, 1, 7);
		
		edit.setOnAction((event)->{
			whatBandInput.setEditable(true);
			whereAtInput.setEditable(true);
			whatCityInput.setEditable(true);
			openingActInput.setEditable(true);
			seatsInput.setEditable(true);
			
			edit.setVisible(false);
			saveEdits.setVisible(true);
			updatesValues.setVisible(true);
			
		});
		
		saveEdits.setOnAction((event)->{
					whatBandInput.setEditable(false);
					whereAtInput.setEditable(false);
					whatCityInput.setEditable(false);
					openingActInput.setEditable(false);
					seatsInput.setEditable(false);
					
					edit.setVisible(true);
					saveEdits.setVisible(false);
					updatesValues.setVisible(false);
					
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

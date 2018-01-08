package Tabs;



import java.io.FileInputStream;
import java.io.FileNotFoundException;


import database.Database;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import objects.Band;
import objects.Genre;
import objects.Venue;
import tables.ConcertTable;
import tables.GenreTable;

/**
 * 
 * @author josegeorges
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 */
public class AddConcertTab extends Tab{
	
	//file String
	String file;
	
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
		
		
		//Fifth Row - Genre
		Text genre = new Text("Genre:");
		pane.add(genre, 0, 4);		
		ComboBox<Genre> comboGenre = new ComboBox<>();
		comboGenre.setItems(
				FXCollections.observableArrayList(
						GenreTable.getAllGenres()));
		pane.add(comboGenre, 1, 4);
		
		//Seventh Row - Date attended
		Text dateAttended = new Text("Date Attended: ");
		DatePicker date = new DatePicker();
		pane.add(dateAttended, 0, 6);
		pane.add(date, 1, 6);
		
		Text missingFields = new Text("MISSING SOME FIELDS");
		missingFields.setVisible(false);
		pane.add(missingFields, 0, 10);
		
		
		//Eighth Row - Rating- I will fix this over the weekend
//		Text ratingText = new Text("Rating: ");
//		rating.setMax(5);
//		rating.setUpdateOnHover(false);
//		rating.setPartialRating(true);
//		pane.add(ratingText, 0, 7);
//		pane.add(rating, 1, 7);
		
		//Final Row - Upload File
		Text uploadPic = new Text("Upload a picture: ");
		Button btnLoad = new Button("Load");
		ImageView imageDisplay = new ImageView();
		pane.add(uploadPic, 0, 7);
		pane.add(btnLoad, 1, 7);
		pane.add(imageDisplay, 2, 7);
        btnLoad.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				 FileChooser fileChooser = new FileChooser();
	             
		            //Set extension filter
		           // FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		            fileChooser.getExtensionFilters().addAll(extFilterPNG);
		              
		            //Show open file dialog
		            file = fileChooser.showOpenDialog(null).getAbsolutePath();
		 	
			}
        		
        });
		
		
		pane.setPadding(insets);
		pane.setVgap(10);
		pane.setHgap(10);
		this.setContent(pane);
		
		Button button = new Button("submit");
		button.setOnMouseClicked(e->{
			//checking that there are no fields missing
			if(venueInput.getText().isEmpty() || cityInput.getText().isEmpty() || bandNameInput.getText().isEmpty()
					|| comboGenre.getSelectionModel().isEmpty() || date.getValue() == null){
				missingFields.setVisible(true);
			}else {
			
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			Venue venueObject = new Venue(venueInput.getText().toString().toUpperCase().trim(), cityInput.getText().toString());
			System.out.println(comboGenre.getValue().getId());
			Band band = new Band(bandNameInput.getText().toString().toUpperCase().trim(), comboGenre.getValue().getId());
			ConcertTable.createConcert(date.getValue().toString().toUpperCase().trim(), 1, fis, band, venueObject);

			
			
			// This is the code to select an image and populate in the computer directory, we need to be able to select 
			// an entry by its id then use this on the singleconcertpage to query and display that image.
//			String sql8 = "SELECT picture FROM  concertTable WHERE id = 1273";
//		    PreparedStatement stmt;
//			try {
//				stmt = db.getConnection().prepareStatement(sql8);
//				   ResultSet resultSet = stmt.executeQuery();
//				    while (resultSet.next()) {
//				      File image = new File("/Users/simonlizotte/Downloads/readImage3.png");
//				      //@SuppressWarnings("resource")
//					FileOutputStream fos = new FileOutputStream(image);
//
//				      byte[] buffer = new byte[1];
//				      InputStream is = resultSet.getBinaryStream("picture");
//				      while (is.read(buffer) > 0) {
//				        fos.write(buffer);
//				      }
//				      fos.close();
//				    }
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		 

			missingFields.setVisible(false);

			}
		});
		pane.add(button, 0, 9);
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static AddConcertTab getInstance() {
		if(tab == null) {
			tab = new AddConcertTab();
		}
		return tab;
	}

	
}

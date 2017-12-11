package SingleConcertView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * Created a pane to store the credits of the program 
 * there is an associated scene for this pane 
 */
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class singleConcertViewPane extends BorderPane {
	
	public singleConcertViewPane() {
		// Create a Vbox 
		VBox box = new VBox();
		
		// Create text items 
		
		// Title
		Label title = new Label("SELECTED:");
		
		//Creating the remove and edit buttons
		Button edit = new Button("Update Concert");
		Button remove = new Button("Remove Concert");
		
		
		// add nodes to pane 
		
		this.setTop(title);
		
		
		// Set Alignment
		singleConcertViewPane.setAlignment(title, Pos.CENTER);
		singleConcertViewPane.setAlignment(box, Pos.CENTER);
		
	
	}

}

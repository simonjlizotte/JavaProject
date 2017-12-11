package SingleConcertView;

import javafx.geometry.Pos;
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
		
		
		// add nodes to pane 
		
		this.setTop(title);
		
		// Set Alignment
		singleConcertViewPane.setAlignment(title, Pos.CENTER);
		
	
	}

}

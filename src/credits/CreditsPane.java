package credits;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
/**
 * Created a pane to store the credits of the program 
 * there is an associated scene for this pane 
 */
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CreditsPane extends BorderPane {
	
	public CreditsPane() {
		// Create a Vbox 
		VBox box = new VBox();
		
		// Create text items 
		
		// Title
		Label title = new Label("Credits:");
		
		// Developers
		Label carmen = new Label("Carmen Kerim");
		Label jose = new Label("Jose Georges");
		Label nick = new Label("Nick Stajduhar");
		Label adam = new Label("Adam Bazzi");
		Label simon = new Label("Simon Lizotte");
		
		// Add Labels to V Box
		
		box.getChildren().addAll(carmen,jose,nick,adam,simon);
		
		// Set vbox alignment 
		box.setAlignment(Pos.CENTER);
		
		
		// add nodes to pane 
		
		this.setTop(title);
		this.setCenter(box);
		
		// Set Alignment
		CreditsPane.setAlignment(title, Pos.CENTER);
		CreditsPane.setAlignment(box, Pos.CENTER);
		
	
	}

}

package confirmationMessage;

import Tabs.AddConcertTab;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * This Pane will be showing when the user hits the submit button while adding a Concert
 * @author josegeorges
 *
 */
public class ConfirmationMessagePane extends BorderPane{

	public ConfirmationMessagePane(String message) {
		//message
		Label confirmationMessage = new Label(message);
		this.setCenter(confirmationMessage);
		
		//it has a delay so that after 3 seconds it closes by itself
		PauseTransition delay = new PauseTransition(Duration.seconds(3));
		delay.setOnFinished( event -> AddConcertTab.nameStage.close() );
		delay.play();

	}
}

package confirmationMessage;

import Tabs.AddConcertTab;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class ConfirmationMessagePane extends BorderPane{

	public ConfirmationMessagePane(String message) {
		Label confirmationMessage = new Label(message);
		this.setCenter(confirmationMessage);
		
		PauseTransition delay = new PauseTransition(Duration.seconds(3));
		delay.setOnFinished( event -> AddConcertTab.nameStage.close() );
		delay.play();

	}
}

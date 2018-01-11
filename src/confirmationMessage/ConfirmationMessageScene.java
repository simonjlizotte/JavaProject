package confirmationMessage;

import javafx.scene.Scene;

public class ConfirmationMessageScene extends Scene{

	public ConfirmationMessageScene(String message) {
		super(new ConfirmationMessagePane(message), 600, 400);
	}

}

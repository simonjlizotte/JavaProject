package confirmationMessage;

import javafx.scene.Scene;

/**
 * Scene to host the ConfirmationMessagePane
 * @author josegeorges
 *
 */
public class ConfirmationMessageScene extends Scene{

	public ConfirmationMessageScene(String message) {
		super(new ConfirmationMessagePane(message), 500, 200);
	}

}

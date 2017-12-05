package credits;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
/** 
 * 
 * @author simonlizotte
 * Created a Sources Pane and scene much like the Credits scene and pane
 * externalized and trackable will be accessible through new stage and lambda in main stage
 * 
 *
 */
public class SourcesPane extends BorderPane {

	public SourcesPane() {
		// Create Label 
		Label title = new Label("Sources");
		
		// Set label to top of pane 
		
		this.setTop(title);
		
		
	}
}

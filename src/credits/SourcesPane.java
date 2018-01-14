package credits;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
		
		VBox sources = new VBox();
		
		// Create Label
		Label title = new Label("Sources:");
		title.setAlignment(Pos.CENTER);
		Label appIcon = new Label("App Icon: Icon made by: Freepik from (https://www.flaticon.com/) Creative Commons BY 3.0");
		Label splashLogo = new Label("Splash Logo: Icon made by: Eucalyp from (https://www.flaticon.com/) Creative Commons BY 3.0");
		
		sources.getChildren().addAll(appIcon,splashLogo);
		sources.setAlignment(Pos.CENTER);
		
		// Set label to top of pane 
		
		this.setTop(title);
		this.setCenter(sources);
		
		
	}
}

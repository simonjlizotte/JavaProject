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
		sources.getStyleClass().add("popUpBackground");
		
		// Create Label
		Label title = new Label("Sources");
		title.getStyleClass().add("titleWhite");
		
		title.setAlignment(Pos.CENTER);
		Label appIcon = new Label("App Icon: Icon made by: Freepik from (https://www.flaticon.com/) Creative Commons BY 3.0");
		appIcon.getStyleClass().add("whiteLabel");
		Label splashLogo = new Label("Splash Logo: Icon made by: Eucalyp from (https://www.flaticon.com/) Creative Commons BY 3.0");
		splashLogo.getStyleClass().add("whiteLabel");
		Label mysql = new Label("MySQL connector (https://www.mysql.com) (https://downloads.mysql.com/docs/licenses/connector-j-5.1-gpl-en.pdf)");
		mysql.getStyleClass().add("whiteLabel");
		sources.getChildren().addAll(appIcon, splashLogo, mysql);
		sources.setAlignment(Pos.CENTER);
		
		// Set label to top of pane 
		this.setTop(title);
		this.setCenter(sources);
		this.getStyleClass().add("popUpBackground");
	}
}

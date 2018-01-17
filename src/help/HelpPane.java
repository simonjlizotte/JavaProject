package help;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
/**
 * 
 * @author simonlizotte
 * Created and externalized the help pane and assosciated scene
 */
public class HelpPane extends BorderPane {

	public HelpPane() {
		
		// Create title
		Label title = new Label("Help");
		title.getStyleClass().add("titleWhite");
		
		// Set title to top
		
		this.setTop(title);
		BorderPane.setAlignment(title,Pos.CENTER);
		
		// Rest of code to follow here 
		
		TextArea helpContent = new TextArea();
		
		// Set Size Constraints of text area
		helpContent.setMaxWidth(505);
		helpContent.setMaxHeight(300);
		
		// Set content 
		
		String help = "\tConcert Tracker is a personalized piece of software designed to allow the user to keep track of their past concert experiences.  This is done by continually adding in the content the individual consumes and allowing the software to run analytics on this data.  It displays useful and creative information in which the user will find fun and exciting in many ways.  From just remembering an experience they may have forgotten, jogging their memory to remember an experience they may have otherwise forgotten.  Or the new data that the software displays that the human brain isn’t powerful enough to process, such as a compilation of cities and concert attended in ratio.  The years attended and the genres, as well as other metrics to be added along through future iterations.  The usage of data storage and manipulation allows us endless possibilities in bringing you the user new experiences, and allows you to never forget these memories.  \n\n\tIf you have experienced any problems or BUGS with our software please report them to: \n\nfakeEmail@fakedomain.com \n\n\tAnd if you have any suggestions please contact our development team here at:\n\n pleaseGiveUsFreeIdeas@thankYou.ca.";
		helpContent.setText(help);
		helpContent.setWrapText(true);
		helpContent.setEditable(false);
		// Add TextArea to Pane
		
		this.setCenter(helpContent);
		this.getStyleClass().add("popUpBackground");
		BorderPane.setAlignment(helpContent, Pos.CENTER);
		
	}
}

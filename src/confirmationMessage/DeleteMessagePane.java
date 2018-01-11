package confirmationMessage;

import SingleConcertView.SingleConcertViewPane;
import Tabs.ViewConcertTab;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import tables.ConcertTable;

/**
 * This pane will appear when the user decides to remove a concert. it will be a confirmation to make sure the
 * 	user actually wanted to delete the concert
 * @author josegeorges
 *
 */
public class DeleteMessagePane extends BorderPane{
	
	public DeleteMessagePane(String bandName, String date, int concertId, ConcertTable concertTable) {
		//question
		Text message = new Text("Are you sure you want to delete the concert that " + bandName + " performed on " + date + " from your database?");
		message.setWrappingWidth(300);
		message.setTextAlignment(TextAlignment.CENTER);
		message.getStyleClass().add("messageWarn");
		BorderPane.setAlignment(message, Pos.CENTER);
		this.setTop(message);
		
		//buttons
		HBox buttons = new HBox();
		Button yes = new Button("Yes");
		Button no = new Button("No");
		no.getStyleClass().add("buttonLoad");
		yes.getStyleClass().add("buttonLoad");
		buttons.getChildren().addAll(yes, no);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(20);
		this.setCenter(buttons);
		
		//event listeners for the buttons
		yes.setOnMouseClicked(e->{
			concertTable.deleteConcert(concertId);
			ViewConcertTab.nameStage.close();
			ViewConcertTab.bandList.setItems(FXCollections.observableArrayList(concertTable.getAllConcerts()));
			SingleConcertViewPane.nameStage.close();
		});
		
		no.setOnMouseClicked(e->{
			SingleConcertViewPane.nameStage.close();
		});
	}
	
	
}

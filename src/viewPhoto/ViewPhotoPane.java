package viewPhoto;

import java.io.File;

import Tabs.AddConcertTab;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 *@author carmenkerim
 * This will display the image of the concert the user visited
 */

public class ViewPhotoPane extends BorderPane {
		
	public ViewPhotoPane() {
		// title
		Label title = new Label("Photo From Concert");
		title.getStyleClass().add("singleViewTitle");
		
		// imageview of the image the user added
		ImageView imageDisplay = new ImageView();
		File file = new File("selectedImg.png");
        Image image = new Image(file.toURI().toString());
        imageDisplay.setImage(image);		
		imageDisplay.setFitWidth(250);
		imageDisplay.setPreserveRatio(true);
	
		BorderPane.setAlignment(imageDisplay, Pos.CENTER);
		BorderPane.setAlignment(title, Pos.CENTER);
		this.getStyleClass().add("imageView");
		this.setTop(title);
		this.setCenter(imageDisplay);
		}
	}

package Tabs;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import objects.Concert;

/**
 * 
 * @author josegeorges
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 */
public class ViewConcertTab extends Tab{
	private List<Concert> bandNames;
	
	//Instance of the Concert object
	Concert conertNames = new Concert();
	
	//constants needed
	public static final String TAB_TITLE = "View Concerts"; //title for the tab
	
	//AddConcertTab created
	private static ViewConcertTab tab;
	
	//constructor
	private ViewConcertTab() {
		this.setText(TAB_TITLE);
		
		//VBox to host the listView
		VBox vbox = new VBox();
		
		ListView<String> bandList = new ListView<String>();
		
		ObservableList<String> bands =FXCollections.observableArrayList (
		    "Band1", "Band2", "Band3", "Band4", "Band1", "Band2", "Band3", "Band4", "Band1", "Band2", "Band3", "Band4");
		bandList.setItems(bands);
		
		vbox.getChildren().add(bandList);
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setAlignment(Pos.TOP_CENTER);
		this.setContent(vbox);
	}
	
	public void bandNameArrayList() {
		bandNames = new ArrayList<Concert>();
		bandNames.add(new Concert("Torture Kosher"));
		bandNames.add(new Concert("Torture Kosher"));
		bandNames.add(new Concert("Torture Kosher"));
		bandNames.add(new Concert("Torture Kosher"));
		bandNames.add(new Concert("Torture Kosher"));
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static ViewConcertTab getInstance() {
		if(tab == null) {
			tab = new ViewConcertTab();
		}
		return tab;
	}
	
}


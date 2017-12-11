package Tabs;

import java.util.ArrayList;

import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
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

	//Instance of the Concert object
	Concert conertNames = new Concert();
	
	//constants needed
	public static final String TAB_TITLE = "View Concerts"; //title for the tab
	
	//AddConcertTab created
	private static ViewConcertTab tab;
	
	//constructor
	private ViewConcertTab() {
		this.setText(TAB_TITLE);
		
		//VBox to host the listview
		VBox vbox = new VBox();
		
		
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static ViewConcertTab getInstance() {
		if(tab == null) {
			tab = new ViewConcertTab();
		}
		return tab;
	}
	
}


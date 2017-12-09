package Tabs;

import javafx.scene.control.Tab;

/**
 * 
 * @author josegeorges
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 */
public class ChartTab extends Tab{

	//constants needed
	public static final String TAB_TITLE = "Charts"; //title for the tab
	
	//AddConcertTab created
	private static ChartTab tab;
	
	//constructor
	private ChartTab() {
		this.setText(TAB_TITLE);
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static ChartTab getInstance() {
		if(tab == null) {
			tab = new ChartTab();
		}
		return tab;
	}
	
}
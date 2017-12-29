package Tabs;

import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;

/**
 * 
 * @author josegeorges
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 * 
 *@author carmenkerim
 *
 *This tab is used to display 3 main charts. 
 * 1.cities chart
 * 2.genre chart
 * 3.year chart
 */
public class ChartTab extends Tab{

	//constants needed
	public static final String TAB_TITLE = "Charts"; //title for the tab
	
	//AddConcertTab created
	private static ChartTab tab;
	
	//constructor
	private ChartTab() {
		this.setText(TAB_TITLE);
		
		// pieChart for cities
        PieChart citiesChart = new PieChart();
        
        // pieChart for genre
        PieChart genresChart = new PieChart();
        
        // pieChart for year
        PieChart yearChart = new PieChart();
     
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static ChartTab getInstance() {
		if(tab == null) {
			tab = new ChartTab();
		}
		return tab;
	}
	
}
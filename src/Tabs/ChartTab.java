package Tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author josegeorges
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 */

public class ChartTab extends Tab{
	public static GridPane pane;
	
	
	//constants needed
	public static final String TAB_TITLE = "Charts"; //title for the tab
	
	//AddConcertTab created
	private static ChartTab tab;
	
	//constructor
	private ChartTab() {
		this.setText(TAB_TITLE);
		pane = new GridPane();
		
		// pieChart for cities
		PieChart citiesChart = new PieChart();
		
		// pieChart for genre
		PieChart genresChart = new PieChart();
		
		// pieChart for year
		PieChart yearChart = new PieChart();
		
		yearChart.setTitle("All Coins Found");
		yearChart.setLabelsVisible(true);
		
		ObservableList<PieChart.Data> yearData = 
				FXCollections.observableArrayList(
						new PieChart.Data("Hi", 2));
						
		yearChart.setData(yearData);
		
		pane.add(yearChart, 0, 0);
		
		this.setContent(pane);
		
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static ChartTab getInstance() {
		if(tab == null) {
			tab = new ChartTab();
		}
		return tab;
	}
	
}
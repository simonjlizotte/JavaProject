package Tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
		
		
		citiesChart.setTitle("Cities");
		citiesChart.setLabelsVisible(true);
		//Sample obserableList for the cities
				ObservableList<PieChart.Data> citiesData = 
						FXCollections.observableArrayList(
								new PieChart.Data("Windsor", 2));	
		//Set the data to the citiesChart
		citiesChart.setData(citiesData);
		
		genresChart.setTitle("Genres");
		genresChart.setLabelsVisible(true);
		//Sample obserableList for the cities
				ObservableList<PieChart.Data> genresData = 
						FXCollections.observableArrayList(
								new PieChart.Data("Hard", 2));	
		//Set the data to the citiesChart
		genresChart.setData(genresData);
		
		//Title to the yearChart
		yearChart.setTitle("Year");
		yearChart.setLabelsVisible(true);
		//Sample obserableList for the yearChart
		ObservableList<PieChart.Data> yearData = 
				FXCollections.observableArrayList(
						new PieChart.Data("2007", 2));	
		//Set the data to the yearChart
		yearChart.setData(yearData);
		
		pane.setHgap(10);
		pane.setPadding(new Insets(10,10,10,10));
		//Added yearChart to pane
		pane.add(yearChart, 0, 0);
		pane.add(genresChart, 0, 1);
		pane.add(citiesChart, 1, 0);
		
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
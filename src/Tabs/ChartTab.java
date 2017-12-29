package Tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
		
	    //------- citites chart -------//
        PieChart citiesChart = new PieChart();
        
        // setting the title and labels
        citiesChart.setTitle("Cities");
        citiesChart.setLabelsVisible(true);
        
        // sample obserableList for the cities
        ObservableList<PieChart.Data> citiesData =
            FXCollections.observableArrayList(
                new PieChart.Data("Windsor", 2));
        
        // set the data to the citiesChart
        citiesChart.setData(citiesData);
        
        //------- genre chart -------//
        PieChart genresChart = new PieChart();
        
        // setting the title and labels
        genresChart.setTitle("Genres");
        genresChart.setLabelsVisible(true);
        
        //Sample obserableList for the cities
        ObservableList<PieChart.Data> genresData =
            FXCollections.observableArrayList(
                new PieChart.Data("Hard", 2));
        //Set the data to the citiesChart
        genresChart.setData(genresData);
        
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
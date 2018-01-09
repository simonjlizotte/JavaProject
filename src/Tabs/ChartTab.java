package Tabs;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import objects.Venue;
import tables.VenueTable;

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
	
	ViewConcertTab viewConcert;
	//instance of venueTable
	VenueTable venueTable = new VenueTable();
	
	//constants needed
	public static final String TAB_TITLE = "Charts"; //title for the tab
	
	//AddConcertTab created
	private static ChartTab tab;
	
	//Gridpane used to display the charts
	public static GridPane pane;
	
	// used to store the title and gridpane
	public static BorderPane paneHolder;
	
	//constructor
	private ChartTab() {
		this.setText(TAB_TITLE);
		 
		viewConcert.getInstance();
		
		pane = new GridPane();
		paneHolder = new BorderPane();
		
		ArrayList<Venue> cityCount = venueTable.getAllVenues();
			
		ArrayList<String> cityCounter = new ArrayList<String>();
		
		for(int i = 0; i < cityCount.size(); i++) {
			String cityName = cityCount.get(i).getCity();
			System.out.println(cityName);
			cityCounter.add(cityName);
		}
		
		System.out.println(cityCounter);
	

		// Label to set the title
		Label chartTabTitle = new Label("CHARTS OF YOUR FAV DATA!");
		chartTabTitle.getStyleClass().add("chartTabTitle");
	   
		//------- citites chart -------//
        PieChart citiesChart = new PieChart();
       
        // set the data to the citiesChart
//        citiesChart.setData("");
        
        // setting the title and labels
        citiesChart.setTitle("Cities");
        citiesChart.setLabelsVisible(true);
        
//        // sample obserableList for the cities
//        ObservableList<PieChart.Data> citiesData =
//            FXCollections.observableArrayList(
//                new PieChart.Data("Windsor", 2));

        
        //------- genre chart -------//
        PieChart genresChart = new PieChart();
        
        // setting the title and labels
        genresChart.setTitle("Genres");
        genresChart.setLabelsVisible(true);
        
        //Sample obserableList for the genres
        ObservableList<PieChart.Data> genresData =
            FXCollections.observableArrayList(
                new PieChart.Data("Hard", 2));
        
        //Set the data to the genresChart
        genresChart.setData(genresData);
        
        //------- year chart -------//
        PieChart yearChart = new PieChart();
        
        // title to the yearChart
        yearChart.setTitle("Year");
        yearChart.setLabelsVisible(true);
        
        // sample obserableList for the yearChart
        ObservableList<PieChart.Data> yearData =
            FXCollections.observableArrayList(
                new PieChart.Data("2007", 2));
      
        // set the data to the yearChart
        yearChart.setData(yearData);
        
        //Setting the spacing of the charts
        pane.setHgap(10);
        
        // padding around the charts
        pane.setPadding(new Insets(10,10,10,10));
        
        // added yearChart,genres, and titles chart to pane
        pane.add(yearChart, 0, 0);
        pane.add(genresChart, 0, 1);
        pane.add(citiesChart, 1, 0);
        
        paneHolder.setTop(chartTabTitle);
        BorderPane.setAlignment(chartTabTitle, Pos.CENTER);
        paneHolder.setCenter(pane);
        //setting the pane to the tab view
        this.setContent(paneHolder);
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static ChartTab getInstance() {
		if(tab == null) {
			tab = new ChartTab();
		}
		return tab;
	}
	
}
package Tabs;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import objects.Genre;
import tables.BandTable;
import tables.ConcertTable;
import tables.GenreTable;
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
		 
		
		pane = new GridPane();
		paneHolder = new BorderPane();
		

		
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

        //Set the data to the genresChart
        genresChart.setData(populateGenreChart());
        
//------- year chart -------//
        PieChart yearChart = new PieChart();
        
        // title to the yearChart
        yearChart.setTitle("Year");
        yearChart.setLabelsVisible(true);
      
        // set the data to the yearChart
        yearChart.setData(populateYearChart());

//--------- refresh button -------\\
        
        Button refreshButton = new Button("Refresh Charts");
        refreshButton.setOnMouseClicked(e->{
            genresChart.setData(populateGenreChart());
            yearChart.setData(populateYearChart());
        });
        
        //Setting the spacing of the charts
        pane.setHgap(10);
        
        // padding around the charts
        pane.setPadding(new Insets(10,10,10,10));
        
        // added yearChart,genres, and titles chart to pane
        pane.add(yearChart, 0, 0);
        pane.add(genresChart, 0, 1);
        pane.add(citiesChart, 1, 0);
        pane.add(refreshButton, 1, 1);
        
        paneHolder.setTop(chartTabTitle);
        BorderPane.setAlignment(chartTabTitle, Pos.CENTER);
        paneHolder.setCenter(pane);
        //setting the pane to the tab view
        this.setContent(paneHolder);
	}
	
	/**JoseGeorges
	 * This method populates the GenreChart;
	 * @return an observableList of piechart.data
	 */
	public ObservableList<PieChart.Data> populateGenreChart() {
        //we need the bandTable
		BandTable bandTable =  new BandTable();
		//we get all the genres and add them inside an ArrayList
		ArrayList<Genre> genres = GenreTable.getAllGenres();
		//this will hold the data for the charts
		ArrayList<PieChart.Data> dataList = new ArrayList<>();
		//this loop will call the getGenreCount, getGenre and add both to a new pieChart, which will
		//then be added to the dataList. This happens with each genre inside of the database.
		for (int i = 0; i < genres.size(); i++) {
			int genreAmount = bandTable.getGenreCount(genres.get(i).getId());
			String genreName = genres.get(i).getGenre();
			PieChart.Data tempData = new PieChart.Data(genreName, genreAmount);
			dataList.add(tempData);
		}
        //observableList for the genres
        ObservableList<PieChart.Data> genresData =
            FXCollections.observableArrayList(dataList);
        return genresData;
	}
	
	public ObservableList<PieChart.Data> populateYearChart(){
		//we need the bandTable
		ConcertTable concertTable =  new ConcertTable();
		//we get all the genres and add them inside an ArrayList
		ArrayList<Integer> years = concertTable.getAllYears();
		//this will hold the data for the charts
		ArrayList<PieChart.Data> dataList = new ArrayList<>();
		//this loop will call the getGenreCount, getGenre and add both to a new pieChart, which will
		//then be added to the dataList. This happens with each genre inside of the database.
				for (int year : years) {
					int yearAmount = concertTable.getYearCount(year);
					PieChart.Data tempData = new PieChart.Data(Integer.toString(year), yearAmount);
					dataList.add(tempData);
				}
		        //observableList for the genres
		        ObservableList<PieChart.Data> yearsData =
		            FXCollections.observableArrayList(dataList);
		        return yearsData;
	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static ChartTab getInstance() {
		if(tab == null) {
			tab = new ChartTab();
		}
		return tab;
	}
	
}
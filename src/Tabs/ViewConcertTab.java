package Tabs;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import objects.Concert;

/**
 * 
 * @author josegeorges
 *
 * This tab is designed to follow a singleton pattern, meaning that there will only be access
 * to one instance of this tab.
 * 
 * @author carmenkerim
 * This tab will display every concert that the user added in a listview
 */
public class ViewConcertTab extends Tab{
	private List<Concert> bandNames;
	
	//observable list
	ObservableList<Concert> bands;
	
	//Listview of band names
	ListView<Concert> bandList;
			
	//Instance of the Concert object
	Concert conertNames = new Concert();
	
	//constants needed
	public static final String TAB_TITLE = "View Concerts"; //title for the tab
	
	//AddConcertTab created
	private static ViewConcertTab tab;
	
	//constructor
	private ViewConcertTab() {
		this.setText(TAB_TITLE);
		
		// vBox to host the listView
		BorderPane borderPane = new BorderPane();
		
		// Label to set the title
		Label viewTabTitle = new Label("View Concerts!");
		
		// observable list
		bands = FXCollections.observableArrayList();
					    
		// listView
		bandList = new ListView<Concert>(bands);
		// setting the preferred width and height
		bandList.setPrefWidth(150);
	    bandList.setPrefHeight(150);
	   
	    
//	    bandList.setCellFactory(new Callback<ListView<Concert>, ListCell<Concert>>() {
//          public ListCell<Concert> call(ListView<Concert> param) {
//            final ListCell<Concert> cell = new ListCell<Concert>() {
//              @Override
//              public void updateItem(Concert item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item != null) {
//                  setText(item.getBandName());
//                }
//              }
//            }; // ListCell
//            return cell;
//          }
//        }); 
	    
//
//	    bandList.getSelectionModel().selectedItemProperty()
//	        .addListener(new ChangeListener<Concert>() {
//	          public void changed(ObservableValue<? extends Concert> observable,
//	              Concert oldValue, Concert newValue) { 
//		            Stage selectedStage = new Stage();
//		            	selectedStage.setTitle("Concert view");
//					selectedStage.setScene(new singleConcertViewScene());
//					selectedStage.show();
//	          }
//	        });
//		
	
	    borderPane.setCenter(bandList);
	    borderPane.setPadding(new Insets(10,10,10,10));
	    borderPane.setMinHeight(768);
		this.setContent(borderPane);
	}
	
	
	//ArrayList of band names TODO: Need to use this method eventually*
//	public void bandNameArrayList() {
//		bandNames = new ArrayList<Concert>();
//		bandNames.add(new Concert("Torture Kosher"));
//		bandNames.add(new Concert("Torture Kosher"));
//		bandNames.add(new Concert("Torture Kosher"));
//		bandNames.add(new Concert("Torture Kosher"));
//		bandNames.add(new Concert("Torture Kosher"));
//	}
	
	//this method will be call when needing the instance of the tab or when first creating it
	public static ViewConcertTab getInstance() {
		if(tab == null) {
			tab = new ViewConcertTab();
		}
		return tab;
	}
	
}


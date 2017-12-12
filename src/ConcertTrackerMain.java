
import credits.CreditsScene;
import credits.SourcesScene;
import help.HelpScene;
import SingleConcertView.singleConcertViewScene;
import Tabs.AddConcertTab;
import Tabs.ChartTab;
import Tabs.ViewConcertTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConcertTrackerMain extends Application{
	
	public static Stage mainStage;

	public static void main(String[] args)  {
		// Launch application with argument args for mac operations	
		Application.launch(args);
	}
	
	// Implemented methods of Application
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Building MenuBar
		MenuBar menu = new MenuBar();
		
		// Build File Menu
		Menu file = new Menu("File");
		Menu help = new Menu("Help");
		Menu credits = new Menu("Credits");
		Menu Select = new Menu("Select");
		
		// Build file Menu Items
		MenuItem quit = new MenuItem("Quit");
		// Set Lambda
		quit.setOnAction((event) -> {
			System.exit(0);
		});
		
		// Build help Menu Items 
		MenuItem helpFile = new MenuItem ("Help");
		helpFile.setOnAction((event) -> {
			Stage helpStage = new Stage();
			helpStage.setTitle("HELP");
			helpStage.setScene(new HelpScene());
			helpStage.show();
		});
		
		// Build credits Menu Items 
		MenuItem creditSources = new MenuItem("Sources");
		// Set Lambda
		creditSources.setOnAction((event) -> {
			Stage creditStage = new Stage();
			creditStage.setTitle("SOURCES");
			creditStage.setScene(new SourcesScene());
			creditStage.show();
		});
		MenuItem creditNames = new MenuItem("Credits");
		// Set lambda
		creditNames.setOnAction((event) -> {
			Stage nameStage = new Stage();
			nameStage.setTitle("CREDITS");
			nameStage.setScene(new CreditsScene());
			nameStage.show();
		});
		
		MenuItem selected = new MenuItem("ConcertTest");
		selected.setOnAction((event) -> {
			Stage selectedStage = new Stage();
			selectedStage.setTitle("ConcertTesting");
			selectedStage.setScene(new singleConcertViewScene());
			selectedStage.show();
		});
		
		// Add File Menu Items to menu 
		file.getItems().addAll(quit);
		help.getItems().addAll(helpFile);
		credits.getItems().addAll(creditSources, creditNames);
		Select.getItems().addAll(selected);
		
		// Add all submenus to menu 
		menu.getMenus().addAll(file, help, credits, Select);
		
		//Create a TabPane
		TabPane tabPane = new TabPane();
		//Create instances of the Tabs that are going to be used in the Tab
		AddConcertTab addConcertTab = AddConcertTab.getInstance();
		ViewConcertTab viewConcertTab = ViewConcertTab.getInstance();
		ChartTab chartTab = ChartTab.getInstance();
		//Adding the tabs to the tabPane
		tabPane.getTabs().addAll(addConcertTab, viewConcertTab, chartTab);
		//setting the tabPane up so that the tabs are not closable
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); 
		
		BorderPane pane = new BorderPane();
		// Set menu to the top of that pane 
		pane.setTop(menu);
		// Set the tabPane to the center of the pane
		pane.setCenter(tabPane);
		// Create scene
		Scene scene = new Scene(pane, 1024, 768);
		
		 mainStage = primaryStage;
		 mainStage.setScene(scene);
		 mainStage.setTitle("Concert Tracker");
		 mainStage.show();
		
	}

}

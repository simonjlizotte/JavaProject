import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConcertTrackerMain extends Application{

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
		
		// Build file Menu Items
		MenuItem quit = new MenuItem("Quit");
		
		// Build help Menu Items 
		MenuItem helpFile = new MenuItem ("Help");
		
		// Build credits Menu Items 
		MenuItem creditSources = new MenuItem("Sources");
		MenuItem creditNames = new MenuItem("Credits");
		
		// Add File Menu Items to menu 
		file.getItems().addAll(quit);
		help.getItems().addAll(helpFile);
		credits.getItems().addAll(creditSources, creditNames);
		
		// Add all submenus to menu 
		menu.getMenus().addAll(file, help, credits);
		
		//ADD CODE HERE
		
		
		
		
		// Create a BorderPane 
		BorderPane pane = new BorderPane();
		// Set menu to the top of that pane 
		pane.setTop(menu);
		// Create scene
		Scene scene = new Scene(pane, 1024, 768);
		// Add scene to stage 
		primaryStage.setScene(scene);
		// Set Title 
		primaryStage.setTitle("Concert Tracker");
		// Show Stage
		primaryStage.show();
		
		
		
		
		
		
	}

}

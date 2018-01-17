
import credits.CreditsScene;
import credits.SourcesScene;
import help.HelpScene;

import javafx.util.Duration;
import java.io.File;

import Tabs.AddConcertTab;
import Tabs.ChartTab;
import Tabs.ViewConcertTab;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ConcertTrackerMain extends Application{
	
	//Defining Application Icon and Splash Screen Image
	public static String APPLICATION_ICON = "stage.png";
	 public static String SPLASH_IMAGE =
			 "splash.png"; 
	 
	 //Creating the splashLayout pane
	    private Pane splashLayout;
	    //Creating a progress bar for the splash screen
	    private ProgressBar loadProgress;
	    //Creating progress text
	    private Label progressText;
	    //Creating a main stage
	    private Stage mainStage;
	    //Setting the splash screen width and height
	    private static final int SPLASH_WIDTH = 960;
	    private static final int SPLASH_HEIGHT = 440;

	public static void main(String[] args)  {
		// Launch application with argument args for mac operations	
		Application.launch(args);
	}
	
	@Override
    public void init() {
		//Adding the image view and image for the splash screen
		ImageView splash = new ImageView(new Image(
                new File(SPLASH_IMAGE).toURI().toString()
        ));
		
		//Creating a progress bar
        loadProgress = new ProgressBar();
        //Setting the progress bar width
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        //Setting the progress text
        progressText = new Label("Will find your concerts . . .");
        //Making the splashLayout pane a VBox
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                "-fx-background-color: rgb(255,195,111); " +
                "-fx-border-width:5; " 
        );
        //Adding a drop shadow effect
        splashLayout.setEffect(new DropShadow());
    }
	
	  @Override
	    public void start(final Stage initStage) throws Exception {
		  //Creating a task that takes a observable list string
	        final Task<ObservableList<String>> concertTaskList = new Task<ObservableList<String>>() {
	            @Override
	            protected ObservableList<String> call() throws InterruptedException {
	            	//Creating a foundConcerts Observable list
	                ObservableList<String> foundConcerts = FXCollections.<String>observableArrayList();
	                //Creating a Observable list of concerts
	                ObservableList<String> concertList = FXCollections.observableArrayList(
	                        		 "Green Holiday", "Infinity Works", "Weekend Chronicle", "Burning Whizz", "Sumo Tonixs",
	                              "Mission Kettle", "Freeing Cheetah", "Fez Whiz"
	                        );
	                	//Updating the message
	                updateMessage("Finding Concerts . . .");
	                for (int i = 0; i < concertList.size(); i++) {
	                    Thread.sleep(400);
	                    updateProgress(i + 1, concertList.size());
	                    String nextConcert = concertList.get(i);
	                    foundConcerts.add(nextConcert);
	                    updateMessage("Finding Concerts . . . Found " + nextConcert);
	                }
	                Thread.sleep(400);
	                updateMessage("All concerts found.");

	                return foundConcerts;
	            }
	        };

	        showSplash(
	                initStage,
	                concertTaskList,
	                () -> showMainStage(concertTaskList.valueProperty())
	        );
	        new Thread(concertTaskList).start();
	    }
	
	// Implemented methods of Application
	private void showMainStage(ReadOnlyObjectProperty<ObservableList<String>> concert) {
		
		 mainStage = new Stage(StageStyle.DECORATED);
	        mainStage.setTitle("Concert Tracker");
	        mainStage.getIcons().add(new Image(
	        		new File(APPLICATION_ICON).toURI().toString()
	        ));
		
		// Building MenuBar
		MenuBar menu = new MenuBar();
		
		menu.getStyleClass().add("menu");
		
		// Build File Menu
		Menu file = new Menu("File");
		Menu help = new Menu("Help");
		Menu credits = new Menu("Credits");
		
		// Build file Menu Items
		MenuItem quit = new MenuItem("Quit");
		
		// Set Lambda
		quit.setOnAction((event) -> {
			System.exit(0);
		});
		//Fade in animation for the menu bar
		FadeTransition ft = new FadeTransition();
		ft.setNode(menu);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setDuration(Duration.millis(2000));
		ft.play();
		
		// Build help Menu Items 
		MenuItem helpFile = new MenuItem ("Help");
		helpFile.setOnAction((event) -> {
			Stage helpStage = new Stage();
			Scene helpScene = new HelpScene();
			helpStage.setTitle("HELP");
			helpStage.setScene(helpScene);
			helpScene.getStylesheets().add("main.css");		 
			helpStage.show();
			
		 
		});
		
		// Build credits Menu Items 
		MenuItem creditSources = new MenuItem("Sources");
		// Set Lambda
		creditSources.setOnAction((event) -> {
			Stage creditStage = new Stage();
			Scene sourceScene = new SourcesScene();
			creditStage.setTitle("SOURCES");
			creditStage.setScene(sourceScene);
			sourceScene.getStylesheets().add("main.css");
			creditStage.show();
		});
		
		MenuItem creditNames = new MenuItem("Credits");
		
		// Set lambda
		creditNames.setOnAction((event) -> {
			Stage nameStage = new Stage();
			Scene creditsScene = new CreditsScene();
			nameStage.setTitle("CREDITS");
			nameStage.setScene(creditsScene);
			creditsScene.getStylesheets().add("main.css");
			nameStage.show();
		});
		
		
		// Add File Menu Items to menu 
		file.getItems().addAll(quit);
		help.getItems().addAll(helpFile);
		credits.getItems().addAll(creditSources, creditNames);
		
		// Add all submenus to menu 
		menu.getMenus().addAll(file, help, credits);
		
		//Create a TabPane
		TabPane tabPane = new TabPane();
		//Create instances of the Tabs that are going to be used in the Tab
		AddConcertTab addConcertTab = AddConcertTab.getInstance();
		ViewConcertTab viewConcertTab = ViewConcertTab.getInstance();
		ChartTab chartTab = ChartTab.getInstance();
		
		
		//Adding the tabs to the tabPaneviewConcertTab
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
		scene.getStylesheets().add("main.css");
		
		 mainStage.setScene(scene);
	     mainStage.show();
		
			
	}
	
	 private void showSplash(
	            final Stage initStage,
	            Task<?> task,
	            InitCompletionHandler initCompletionHandler
	    ) {
	        progressText.textProperty().bind(task.messageProperty());
	        loadProgress.progressProperty().bind(task.progressProperty());
	        task.stateProperty().addListener((observableValue, oldState, newState) -> {
	            if (newState == Worker.State.SUCCEEDED) {
	                loadProgress.progressProperty().unbind();
	                loadProgress.setProgress(1);
	                initStage.toFront();
	                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
	                fadeSplash.setFromValue(1.0);
	                fadeSplash.setToValue(0.0);
	                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
	                fadeSplash.play();

	                initCompletionHandler.complete();
	            } 
	        });

	        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
	        final Rectangle2D bounds = Screen.getPrimary().getBounds();
	        initStage.setScene(splashScene);
	        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
	        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
	        initStage.initStyle(StageStyle.TRANSPARENT);
	        initStage.setAlwaysOnTop(true);
	        initStage.show();
	    }

	    public interface InitCompletionHandler {
	        void complete();
	    }

}

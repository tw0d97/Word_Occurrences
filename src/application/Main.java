package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * This class creates the GUI for which to interact the the Word Occurrences application
 * @author Tristan Wood
 * @version 1.0
 */
public class Main extends Application {
	
	static Text wordsLabel = new Text();
	static Label occurrencesLabel = new Label();
	
	/**
	 *  Creates GUI given specified elements in method
	 * @param Takes object of type Stage from JavaFX
	 */
	@Override
	public void start(Stage stage) {
		try {
			  Text title = new Text("In The Raven");
		      Text subHeading1 = new Text("The Word(s): ");       
		      Text subHeading2 = new Text("Occurs: "); 

		      //Creating a Grid Pane 
		      GridPane gridPane = new GridPane();
		      
		      Button next = new Button("Next");
		      Button showTop20 = new Button("Show Top 20 Words");
		      
		      //Setting size for the pane 
		      gridPane.setMinSize(400, 200); 
		      
		      //Setting the padding  
		      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		      
		      //Setting the vertical and horizontal gaps between the columns 
		      gridPane.setVgap(5); 
		      gridPane.setHgap(5);       
		      
		      //Setting the Grid alignment 
		      gridPane.setAlignment(Pos.CENTER); 
		       
		      //Arranging all the nodes in the grid
		      gridPane.add(title, 0, 0);
		      gridPane.add(subHeading1, 0, 2); 
		      gridPane.add(wordsLabel, 1, 2); 
		      gridPane.add(subHeading2, 0, 3); 
		      gridPane.add(occurrencesLabel,1, 3);
		      gridPane.add(next, 0, 6); 
		      gridPane.add(showTop20, 1, 6);
		      
		       
		      //Styling nodes  
		      title.setFill(Color.LIGHTGRAY);
		      subHeading1.setFill(Color.LIGHTGRAY);
		      subHeading2.setFill(Color.LIGHTGRAY);
		      next.setTextFill(Color.LIGHTGRAY);
		      showTop20.setTextFill(Color.LIGHTGRAY);
		      wordsLabel.setFill(Color.LIGHTGRAY);
		      occurrencesLabel.setTextFill(Color.LIGHTGRAY);
		      
		      title.setFont(Font.font("Garamond", 32));
		      subHeading1.setFont(Font.font("Garamond", 26));
		      subHeading2.setFont(Font.font("Garamond", 26));
		      next.setFont(Font.font("Garamond", 26));
		      showTop20.setFont(Font.font("Garamond", 26));
		      wordsLabel.setFont(Font.font("Garamond", 26));
		      occurrencesLabel.setFont(Font.font("Garamond", 26));

		      next.setStyle("-fx-background-color: #1a0606");  
		      showTop20.setStyle("-fx-background-color: #1a0606");
		      gridPane.setStyle("-fx-background-color: #671818"); 
		      
		      next.setOnAction(e -> WordManager.makeLabel());
		      showTop20.setOnAction(e -> WordManager.showTop20());
		      
		      //Creating a scene object 
		      Scene scene = new Scene(gridPane); 
		       
		      //Setting title to the Stage 
		      stage.setTitle("Word Occurrences"); 
		      
		      //Change App Icon
		      //stage.getIcons().add(new Image("icon.png"));
		         
		      //Adding scene to the stage 
		      stage.setScene(scene);
		      
		      //Displaying the contents of the stage 
		      stage.show(); 
		   
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Uses word manager to gather all information needed for a successful iteration of the word occurrences application.
	 * Calls launch to set stage GUI launch application
	 * @param args
	 */
	public static void main(String[] args) {
		WordManager.countWords("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm", "body");
		launch(args);
		
	}
}

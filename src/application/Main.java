package application;
	
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


/**
 * Application.Main is the class to launch the main application
 * @author Jacob Rahimi
 */
public class Main extends Application {
	/**
	 * This method specifies the parameters of the initial AnchorPane
	 * and Scene to start from, and it utilizes the MainMenu view as 
	 * its starting point.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			URL url = new File("src/fxml/MainMenu.fxml").toURI().toURL();
			AnchorPane root = FXMLLoader.load(url);
			Scene scene = new Scene(root,600,400);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("data/Command-Cyber-Logo.png"));
			primaryStage.setTitle("Main Menu");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This is the main method to launch the java application.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

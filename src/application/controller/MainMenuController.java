package application.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.LocalCatalog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * application.controller.MainMenuController is the controller for the MainMenu view.
 * It provides functionality for displaying the connection status to
 * the download website for the online catalog and the current path of the
 * local catalog directory. Additionally, it allows the user to switch to
 * the online catalog view, switch to the local catalog view, change the
 * local catalog path, and open the GitHub page for more information about
 * the project.
 * 
 * @author Brandon Chung, Hunter Drummond, Leon Le, Jacob Rahimi 
 */
public class MainMenuController implements Initializable {
	
	@FXML
    private AnchorPane mainPane;

    @FXML
    private Text ConnectionStatusLabel;

    @FXML
    private Text ConnectionStatus;

    @FXML
    private Circle StatusIcon;
    
    @FXML
    private Label CurrentFilePath;

    /**
     * This is a method to allow functionality of the GoToOnlineCatalog button to switch the scene to the OnlineCatalog.
     * @param event - the event from clicking on the button which is used to reference the scene to switch the view
     * @throws IOException - errors when there was an issue loading the fxml file
     */
    @FXML
    void GoToOnlineCatalog(ActionEvent event) throws IOException {
    	// Load the Online Catalog fxml file
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/OnlineCatalog.fxml"));
    	mainPane = loader.load();
    	
    	// Load the scene
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setTitle("Online Catalog");
		window.show();
    }

    /**
     * This is a method to allow functionality of the GoToLocalCatalog button to switch 
     * the scene to the LocalCatalog
     * @param event - the event from clicking on the button which is used to reference the scene to switch the view
     * @throws IOException - errors when there was an issue loading the fxml file
     */
    @FXML
    void GoToLocalCatalog(ActionEvent event) throws IOException {
    	// Load the Local Catalog fxml file
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/LocalCatalog.fxml"));
    	mainPane = loader.load();
    	
    	// Load the scene
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setTitle("Local Catalog");
		window.show();
    }

    /**
     * This is a method that prompts the user to change the local catalog path to a folder
     * on their system. When a new path is selected, it updates the local catalog path.
     */
    @FXML
    void ChangeFilePath() {
    	DirectoryChooser folderChooser = new DirectoryChooser();
    	File file = folderChooser.showDialog(null);
    	if (file != null) {
    		String absolFile = file.getAbsolutePath();
    		LocalCatalog.catalogPath = absolFile;
    		CurrentFilePath.setText("Current Catalog Path: " + LocalCatalog.catalogPath);
    	}
    	else{
    		System.out.println("ERROR: No directory was selected.");
    	}
    }

    /**
     * This is a method that will launch the project's GitHub page to allow the user to gather more
     * information about the application through the README.
     * @throws IOException - errors when there was an issue opening the github page.
     */
    @FXML
    void PromptAbout() throws IOException {
    	Desktop.getDesktop().browse(URI.create("https://github.com/Jacob-Rahimi/CS-3443-003-Group-06"));
    }

	/**
	 *This method is ran on the initialization of the MainMenu view and it displays the current
	 *local catalog path as well as checks the connection status to the download website for 
	 *the online catalog.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Display the current local catalog path
		CurrentFilePath.setText("Current Catalog Path: " + LocalCatalog.catalogPath);
		
		//Establish a thread to run this process in the background to reduce wait times
		new Thread() {
			public void run() {
				// Checks the connection status of https://public.cyber.mil/stigs/
				try {
					ConnectionStatus.setText("Currently querrying the server");
					StatusIcon.setFill(Color.WHITE);
					URL url = new URL("https://public.cyber.mil/stigs/");
					HttpURLConnection huc = (HttpURLConnection) url.openConnection();
					int responseCode = huc.getResponseCode();
					
					if (responseCode != 200) { // Sets status icon to Red if unavailable
						ConnectionStatus.setText("Server unavailable");
						StatusIcon.setFill(Color.RED);
					}
					else {
						ConnectionStatus.setText("Server is available");
						StatusIcon.setFill(Color.web("0x1fff66"));
					}
				} catch (Exception e) {
					ConnectionStatus.setText("There was an issue querying the server.");
					StatusIcon.setFill(Color.BLACK);
					System.out.println(e);
				}
			}
		}.start();
	} 
}

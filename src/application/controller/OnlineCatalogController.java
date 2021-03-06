package application.controller;

import java.io.IOException;

import application.model.OnlineCatalog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for OnlineCatalog Class. Maintains Online Catalog Scene, 
 * including file lists and buttons to select and download files.
 * @author Hunter Drummond
 */
public class OnlineCatalogController {

	private OnlineCatalog oc = new OnlineCatalog();
	
	@FXML
    private AnchorPane mainPane;
	
    @FXML
    private ListView<String> DownloadList;

    @FXML
    private ListView<String> OnlineCatalogList;

    @FXML
    private TextArea DownloadStatusTextArea;

    /**
     * Loads the Online Catalog list with the full listing of 
     * available STIG files to download.
     * @throws IOException extends the IOException from Online Catalog's loadOnlineCatalog.
     */
    public void initialize() throws IOException {
		oc.loadOnlineCatalog(OnlineCatalogList);
	}
    
    /**
     * Maintains button functionality for traveling back to the Main Menu screen.
     * @param event takes in the event when the "Main Menu" button is pressed.
     * @throws IOException when there is an issue loading the FXML file
     */
    @FXML
    void GoToMainMenu(ActionEvent event) throws IOException {
    	// Load the Main Menu fxml file
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/MainMenu.fxml"));
    	mainPane = loader.load();
    	
    	// Load the scene
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setTitle("Main Menu");
		window.show();
    }

    /**
     * Maintains button functionality for removing an item from the 
     * "Files to be downloaded" list.
     */
    @FXML
    void RemoveFromDownloadList() {
    	if (DownloadList.getSelectionModel().getSelectedItem() != null) 
    		DownloadList.getItems().remove( DownloadList.getSelectionModel().getSelectedItem() );
    }

    /**
     * Maintains button functionality for adding an item to the 
     * "Files to be downloaded" list.
     */
    @FXML
    void AddToDownloadList() {
    	oc.queueFiles( DownloadList );
    }

    /**
     * Maintains button functionality for downloading the group of selected files 
     * from the "Files to be downloaded" list.
     */
    @FXML
    void Download() {
    	// Check to see if there are files to be downloaded
    	if(DownloadList.getItems().size() == 0) {
    		DownloadStatusTextArea.setText("The download list is empty, no files were downloaded.");
    		return;
    	}
    	
    	// Try to download the files specified in the DownloadList
    	try {
    		oc.downloadFiles(DownloadList);
    		DownloadStatusTextArea.setText("Successfully downloaded the following files: " + DownloadList.getItems().toString().replaceAll("[\\[\\]]", ""));
    		
    		// Clearing the DownloadList after files finish downloading.
    		DownloadList.getItems().removeAll();
    		DownloadList.getItems().clear();
    	} catch (Exception e) {
    		DownloadStatusTextArea.setText("There was an issue downloading one of the requested files.");
    		e.printStackTrace();
    	}
    }

    /**
     * Maintains button functionality traveling to the Local Catalog screen.
     * @param event takes in the event when the "Local Catalog" button is pressed.
     * @throws IOException when there was an issue loading the FXML file
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

}

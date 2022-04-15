package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import application.model.LocalCatalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LocalCatalogController implements Initializable{
	/* The LocalCatalogController is the controller for the LocalCatalog.
	 * It provides functionality to browse your own local folders to upload, delete and update .xml files. */
    @FXML
    private AnchorPane mainPane;
    // ListView to display the xml files
    @FXML
    private ListView<String> STIGCatalogListView;
    
    /* This method allows you to go to the STIGView once you select a certain .xml file on the list view and click on the view button.
     * It throws an exception if there is an issue loading a file in.*/
    @FXML
    void GoToSTIGViewer(ActionEvent event) throws IOException, ParserConfigurationException, SAXException {
    	if(STIGCatalogListView.getSelectionModel().getSelectedItem() != null) {
	    	// Load the STIGViewer fxml file
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/STIGViewer.fxml"));
	    	mainPane = loader.load();
	    	 		
	    	// Load the controller and initialize the STIGDocument with the selected STIG
	    	STIGViewerController controller = loader.getController();
	    	controller.initializeSTIGViewer(new File(LocalCatalog.catalogPath + "\\" + STIGCatalogListView.getSelectionModel().getSelectedItem()) );
	    	
	    	// Load the scene
	    	Scene scene = new Scene(mainPane);
	    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.setTitle("STIG Viewer");
			window.setResizable(true);
			window.setWidth(1200);
			window.setHeight(800);
			window.show();
    	}
    }
    
    /* When you're on the local catalog, the main menu button when pressed will take you abck to the main menu.*/
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
    
    /* Uploads the STIG file and updates the arraylist and listview to add the xml file.*/
    @FXML
    void UploadSTIG(ActionEvent event) throws IOException {
    	LocalCatalog.uploadXML(STIGCatalogListView);
    	updateSTIGCatalog();
    }
    
    /* Deletes the STIG file that is highlighted on the list view and calls upon the uploadSTIGCatalog to update the listView and arrraylist.*/
    @FXML
    public void DeleteSTIG(ActionEvent event) {
    	if (STIGCatalogListView.getSelectionModel().getSelectedItem() != null) {
    		
    		File stigFile = new File(LocalCatalog.catalogPath + "\\" + STIGCatalogListView.getSelectionModel().getSelectedItem());
    		LocalCatalog.deleteSTIGs(stigFile);
    		updateSTIGCatalog();
    	}
    }
    
    // Initializes the listview and arraylist with however many .xml files are in the directory.
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateSTIGCatalog();
	}
	
    // updates the arraylist and listview.
	void updateSTIGCatalog() {
		ArrayList<String> xmlList = LocalCatalog.readList();
		ObservableList<String> xml = FXCollections.observableArrayList(xmlList);
		STIGCatalogListView.setItems(xml);
		xmlList.clear();
	}
}

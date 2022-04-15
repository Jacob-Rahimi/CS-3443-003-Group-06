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
	 * It provides functionality to browse your own local folders to upload, delete and view .xml files
	 * */
    @FXML
    private AnchorPane mainPane;
    // ListView to display the xml files
    @FXML
    private ListView<String> STIGCatalogListView;
    
    
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

    @FXML
    void UploadSTIG(ActionEvent event) throws IOException {
    	LocalCatalog.uploadXML(STIGCatalogListView);
    	updateSTIGCatalog();
    }
    
    
    @FXML
    public void DeleteSTIG(ActionEvent event) {
    	if (STIGCatalogListView.getSelectionModel().getSelectedItem() != null) {
    		
    		File stigFile = new File(LocalCatalog.catalogPath + "\\" + STIGCatalogListView.getSelectionModel().getSelectedItem());
    		LocalCatalog.deleteSTIGs(stigFile);
    		updateSTIGCatalog();
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateSTIGCatalog();
	}
    
	void updateSTIGCatalog() {
		ArrayList<String> xmlList = LocalCatalog.readList();
		ObservableList<String> xml = FXCollections.observableArrayList(xmlList);
		STIGCatalogListView.setItems(xml);
		xmlList.clear();
	}
}

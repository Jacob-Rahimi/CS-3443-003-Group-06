package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ListView<String> STIGCatalogListView;

    @FXML
    void GoToSTIGViewer(ActionEvent event) {
    	try {
    		// Load the STIGViewer fxml file
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/STIGViewer.fxml"));
    		mainPane = loader.load();
    	
    		// Load the controller and initialize the STIGDocument with the selected STIG
    		STIGViewerController controller = loader.getController();
    		controller.initializeSTIGViewer("I:\\test\\U_MS_Windows_10_STIG_V2R3_Manual-xccdf.xml");
    	
    		// Load the scene
    		Scene scene = new Scene(mainPane);
    		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.setTitle("STIG Viewer");
			window.setResizable(true);
			window.setWidth(1200);
			window.setHeight(800);
			window.show();
    	} catch( Exception e ) {
    		e.printStackTrace();
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
    void UploadSTIG(ActionEvent event) {
    	// TODO - implement functionality to prompt the user to select a file to copy over to the local catalog
    }

    @FXML
    void DeleteSTIG(ActionEvent event) {
    	// TODO - implement functionality to delete the file from the local catalog
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File catalogLocation = new File("src/data");
		ArrayList<String> xmlList = LocalCatalog.enumerate(catalogLocation);
		ObservableList<String> xml = FXCollections.observableArrayList(xmlList);
		
		STIGCatalogListView.setItems(xml);
		
	}
    
   
}

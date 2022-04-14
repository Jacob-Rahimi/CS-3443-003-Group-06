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

    }

    @FXML
    void DeleteSTIG(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File catalogLocation = new File("src/data");
		ArrayList<String> xmlList = LocalCatalog.stigsArray(catalogLocation);
		ObservableList<String> xml = FXCollections.observableArrayList(xmlList);
		
		STIGCatalogListView.setItems(xml);
		
	}
    
   
}

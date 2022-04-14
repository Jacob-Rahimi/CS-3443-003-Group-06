package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OnlineCatalogController {

    @FXML
    private ListView<?> DownloadList;

    @FXML
    private ListView<?> OnlineCatalogList;

    @FXML
    private ProgressIndicator DownloadProgressBar;

    @FXML
    private TextArea DownloadStatusTextArea;

    @FXML
    private AnchorPane mainPane;

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
    void RemoveFromDownloadList(ActionEvent event) {

    }

    @FXML
    void AddToDownloadList(ActionEvent event) {

    }

    @FXML
    void Download(ActionEvent event) {

    }

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
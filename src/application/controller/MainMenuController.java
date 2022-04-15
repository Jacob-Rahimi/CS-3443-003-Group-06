package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    @FXML
    void ChangeFilePath(ActionEvent event) {
    	// TODO: Implementing settings (just for local catalog directory)
    	DirectoryChooser folderChooser = new DirectoryChooser();
    	File file = folderChooser.showDialog(null);
    	if (file != null) {
    		String absolFile = file.getAbsolutePath();
    		LocalCatalog.catalogPath = absolFile;
    		CurrentFilePath.setText(LocalCatalog.catalogPath);
    	}
    	else{
    		System.out.println("ERROR: No directory was selected.");
    	}
    }

    @FXML
    void PromptAbout(ActionEvent event) {
    	// TODO: Implement about page (just for GitHub page)
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		CurrentFilePath.setText(LocalCatalog.catalogPath);
		
		// Checks the connection status of https://public.cyber.mil/stigs/
		try {
			URL url = new URL("https://public.cyber.mil/stigs/");
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			int responseCode = huc.getResponseCode();
			
			if (responseCode == 404) { // Sets status icon to Red if unavailable
				ConnectionStatus.setText("Server unavailable");
				StatusIcon.setFill(Color.RED);
			}
			else {
				ConnectionStatus.setText("Server is available");
				StatusIcon.setFill(Color.web("0x1fff66"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
    
    
}

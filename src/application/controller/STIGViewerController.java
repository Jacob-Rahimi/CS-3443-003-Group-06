package application.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import application.model.STIGDocument;
import application.model.STIGRule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class STIGViewerController {
	
	STIGDocument referenceSTIG;
	STIGDocument filteredSTIG;
	ObservableList<STIGRule> stigRuleList = FXCollections.observableArrayList();

	@FXML
    private AnchorPane mainPane;
	
	// STIG Rule Table
    @FXML
    private TableView<STIGRule> STIGRuleTable;
    
    @FXML
    private TableColumn<STIGRule, String> VulnIDColumn;
    
    @FXML
    private TableColumn<STIGRule, String> STIGIDColumn;
    
    @FXML
    private TableColumn<STIGRule, String> RuleIDColumn;
    
    @FXML
    private TableColumn<STIGRule, String> RuleNameColumn;
    
    // Filter Table
    @FXML
    private TableView<?> FilterTable;

    @FXML
    private TableColumn<?, ?> FilterFieldColumn;

    @FXML
    private TableColumn<?, ?> FilterTypeColumn;

    @FXML
    private TableColumn<?, ?> FilterTextColumn;
    
    // Filter Buttons & Fields
    @FXML
    private ChoiceBox<String> FilterField;
    
    @FXML
    private TextField FilterTextField;
    
    @FXML
    private ToggleGroup MatchType;

    @FXML
    private RadioButton MatchButton;

    @FXML
    private RadioButton ContainsButton;
    
    // Rule Severity Chart

    @FXML
    private PieChart RuleSeverityChart;

    // STIG Rule Content
    
    @FXML
    private TextArea STIGRuleHeader;

    @FXML
    private TextArea STIGRuleContent;

    
    
    @FXML
    void AddFilter(ActionEvent event) {

    }

    @FXML
    void DeleteFilter(ActionEvent event) {

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
		window.setWidth(600);
		window.setHeight(439);
		window.setResizable(false);
		window.setTitle("Main Menu");
		window.show();
    }

    @FXML
    void GoToOnlineCatalog(ActionEvent event) throws IOException {
    	// Load the Online Catalog fxml file
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/OnlineCatalog.fxml"));
    	mainPane = loader.load();
    	
    	// Load the scene
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setWidth(600);
		window.setHeight(439);
		window.setResizable(false);
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
		window.setWidth(600);
		window.setHeight(439);
		window.setResizable(false);
		window.setTitle("Local Catalog");
		window.show();
    }

    @FXML
    void loadSTIGRule(ActionEvent event) {

    }
    
    void initializeSTIGViewer( String stigFileName ) throws ParserConfigurationException, SAXException, IOException {
    	// Populate the referenceSTIG and the filteredSTIG (filtered STIG is initialized with no filter in place)
    	referenceSTIG = new STIGDocument( stigFileName );
    	filteredSTIG = referenceSTIG;
    	
    	// Update Filtered STIG Table
    	updateFilteredSTIGTable();
    	
    }
    
    void updateFilteredSTIGTable() {
    	VulnIDColumn.setCellValueFactory(new PropertyValueFactory<STIGRule, String>("vulID"));
        STIGIDColumn.setCellValueFactory(new PropertyValueFactory<STIGRule, String>("stigID"));
        RuleIDColumn.setCellValueFactory(new PropertyValueFactory<STIGRule, String>("subVulID"));
        RuleNameColumn.setCellValueFactory(new PropertyValueFactory<STIGRule, String>("groupTitle"));
    	STIGRuleTable.getItems().setAll( filteredSTIG.getStigRuleArrayList() );
    }

}

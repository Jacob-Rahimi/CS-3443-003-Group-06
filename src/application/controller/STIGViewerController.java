package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import application.model.STIGDocument;
import application.model.STIGFilter;
import application.model.STIGRule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class STIGViewerController {
	
	STIGDocument referenceSTIG;
	ArrayList<STIGRule> filteredSTIGRules;
	
	PropertyValueFactory<STIGRule, String> vulID = new PropertyValueFactory<STIGRule, String>("vulID");
	PropertyValueFactory<STIGRule, String> subVulID = new PropertyValueFactory<STIGRule, String>("subVulID");
	PropertyValueFactory<STIGRule, String> stigID = new PropertyValueFactory<STIGRule, String>("stigID");
	PropertyValueFactory<STIGRule, String> severityCat = new PropertyValueFactory<STIGRule, String>("severityCat");
	PropertyValueFactory<STIGRule, String> groupTitle = new PropertyValueFactory<STIGRule, String>("groupTitle");
	PropertyValueFactory<STIGRule, String> ruleTitle = new PropertyValueFactory<STIGRule, String>("ruleTitle");
	PropertyValueFactory<STIGRule, String> ruleDiscussion = new PropertyValueFactory<STIGRule, String>("ruleDiscussion");
	PropertyValueFactory<STIGRule, String> checkText = new PropertyValueFactory<STIGRule, String>("checkText");
	PropertyValueFactory<STIGRule, String> fixText = new PropertyValueFactory<STIGRule, String>("fixText");
	PropertyValueFactory<STIGRule, String> CCI = new PropertyValueFactory<STIGRule, String>("CCI");
	
	

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
    private TableView<STIGFilter> FilterTable;

    @FXML
    private TableColumn<STIGFilter, String> FilterFieldColumn;

    @FXML
    private TableColumn<STIGFilter, String> FilterTypeColumn;

    @FXML
    private TableColumn<STIGFilter, String> FilterTextColumn;
    
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
    private TextFlow STIGRuleHeader;

    @FXML
    private TextFlow STIGRuleContent;

    
    
    @FXML
    void AddFilter(ActionEvent event) {
    	// Detect if there is a valid selected in the FilteredField and the FilterTextArea
    	if(FilterField.getSelectionModel().getSelectedItem() != null && FilterTextField.getText() != null ) {
    		STIGFilter filterRow = new STIGFilter(FilterField.getSelectionModel().getSelectedItem(), (MatchButton.isSelected() ? "Matches" : "Contains"), FilterTextField.getText());
    		
    		FilterFieldColumn.setCellValueFactory( new PropertyValueFactory<STIGFilter, String>("field") );
    		FilterTypeColumn.setCellValueFactory( new PropertyValueFactory<STIGFilter, String>("type") );
    		FilterTextColumn.setCellValueFactory( new PropertyValueFactory<STIGFilter, String>("text") );
    		
    		FilterTable.getItems().add(filterRow);
    		updateFilteredSTIG();
    	}
    }

    @FXML
    void DeleteFilter() {
    	// Detect if an filter is available and selected
    	if(FilterTable.getSelectionModel().getSelectedItem() != null) {
    		// Delete the filter
    		FilterTable.getItems().remove(FilterTable.getSelectionModel().getSelectedItem());
    		// Update the filtered STIG
    		updateFilteredSTIG();
    	}
    }
    
    @FXML
    void displaySelectedRule(MouseEvent event) {
    	// Ensure that the table was double clicked to change the selected rule
    	if( event.getClickCount() == 2 ) {
    		// Get selected STIGRule
    		STIGRule selectedRule = STIGRuleTable.getSelectionModel().getSelectedItem();
    		
    		// Clear the previous content
    		STIGRuleHeader.getChildren().clear();
    		STIGRuleContent.getChildren().clear();
    		
    		// Generate the Header
    		// Header line 1
    		Text tempTitle = new Text(referenceSTIG.getStigTitle() + "\n");
    		tempTitle.setStyle("-fx-font-weight: bold");
    		
    		// Header line 2
    		Text vulIDHeader = new Text("Vul ID: ");
    		vulIDHeader.setStyle("-fx-font-weight: bold");
    		Text vulIDContent = new Text(selectedRule.getVulID());
    		
    		Text ruleIDHeader = new Text("\tRule ID: ");
    		ruleIDHeader.setStyle("-fx-font-weight: bold");
    		Text ruleIDContent = new Text(selectedRule.getSubVulID());
    		
    		Text stigIDHeader = new Text("\tSTIG ID: ");
    		stigIDHeader.setStyle("-fx-font-weight: bold");
    		Text stigIDContent = new Text(selectedRule.getStigID() + "\n");
    		
    		// Header line 2
    		Text severityHeader = new Text("Severity: ");
    		severityHeader.setStyle("-fx-font-weight: bold");
    		Text severityContent = new Text(selectedRule.getSeverityCat());
    		
    		Text legacyIDsHeader = new Text("\tLegacy IDs: ");
    		legacyIDsHeader.setStyle("-fx-font-weight: bold");
    		Text legacyIDsContent = new Text(selectedRule.getLegacyIDs().toString().replaceAll("[\\[\\]]", "") + "\n");
    		
    		// Generate the Content
    		// Group Title
    		Text groupTitleHeader = new Text("Group Title: ");
    		groupTitleHeader.setStyle("-fx-font-weight: bold");
    		Text groupTitleContent = new Text(selectedRule.getGroupTitle() + "\n");
    		
    		// Rule Title
    		Text ruleTitleHeader = new Text("Rule Title: ");
    		ruleTitleHeader.setStyle("-fx-font-weight: bold");
    		Text ruleTitleContent = new Text(selectedRule.getRuleTitle() + "\n");
    		
    		// Discussion
    		Text discussionHeader = new Text("Discussion: ");
    		discussionHeader.setStyle("-fx-font-weight: bold");
    		Text discussionContent = new Text(selectedRule.getRuleDiscussion() + "\n");
    		
    		// Check Text
    		Text checkTextHeader = new Text("Check Text: ");
    		checkTextHeader.setStyle("-fx-font-weight: bold");
    		Text checkTextContent = new Text(selectedRule.getCheckText() + "\n");
    		
    		// Fix Text
    		Text fixTextHeader = new Text("Fix Text: ");
    		fixTextHeader.setStyle("-fx-font-weight: bold");
    		Text fixTextContent = new Text(selectedRule.getFixText() + "\n");
    		
    		// CCI
    		
    		STIGRuleHeader.getChildren().addAll(tempTitle, 
    											vulIDHeader, vulIDContent, ruleIDHeader, ruleIDContent, stigIDHeader, stigIDContent,
    											severityHeader, severityContent, legacyIDsHeader, legacyIDsContent);
    		STIGRuleHeader.setStyle("-fx-font-size: 14px");
    		
    		STIGRuleContent.getChildren().addAll(groupTitleHeader, groupTitleContent,
    											ruleTitleHeader, ruleTitleContent,
    											discussionHeader, discussionContent,
    											checkTextHeader, checkTextContent,
    											fixTextHeader, fixTextContent);
    		STIGRuleContent.setStyle("-fx-font-size: 14px");
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
    
    void initializeSTIGViewer( String stigFileName ) throws ParserConfigurationException, SAXException, IOException {
    	// Populate the referenceSTIG and the filteredSTIG (filtered STIG is initialized with no filter in place)
    	referenceSTIG = new STIGDocument( stigFileName );
    	
    	// Update Filtered STIG Data
    	updateFilteredSTIG();
    	
    	// Initialize choice box
    	FilterField.getItems().addAll( "vulID", "subVulID", "stigID", "severityCat", "groupTitle", "ruleTitle", "ruleDiscussion", "checkText", "fixText", "CCI");
    	FilterField.getSelectionModel().select(0);
    	
    }
    
    void updateFilteredSTIG() {
    	filteredSTIGRules = referenceSTIG.getStigRuleArrayList();
    	
    	// Update STIG Rule Table
    	VulnIDColumn.setCellValueFactory(vulID);
        STIGIDColumn.setCellValueFactory(stigID);
        RuleIDColumn.setCellValueFactory(subVulID);
        RuleNameColumn.setCellValueFactory(groupTitle);
    	STIGRuleTable.getItems().setAll( filteredSTIGRules );
    	
    	// Update Pie Chart
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
    			new PieChart.Data("CAT I (High)", 1),
    			new PieChart.Data("CAT II (Medium)", 2),
    			new PieChart.Data("CAT III (Low)", 3));
    	RuleSeverityChart.setData(pieChartData);
    	RuleSeverityChart.setLegendSide(Side.BOTTOM);
    	RuleSeverityChart.setLegendVisible(true);
    }

}

package application.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

/**
 * application.controller.STIGViewerController is the controller for the STIGViewer view.
 * It provides functionality for filtering STIGRules, displaying severity distribution of 
 * filtered STIGRules, and viewing information of a STIGRule.
 * 
 * @author Jacob Rahimi
 */
public class STIGViewerController {
	
	/**
	 * referenceSTIG is a STIGDocument that is generated from the XML file used to initialize the STIGViewer view
	 */
	STIGDocument referenceSTIG;
	
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
    
    /**
     * This is a method to allow functionality of the GoToMainMenu button to switch 
     * the scene to the MainMenu
     * @param event - the event from clicking on the button which is used to reference the scene to switch the view
     * @throws IOException - errors when there was an issue loading the fxml file
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
		window.setMaximized(false);
		window.setWidth(600);
		window.setHeight(439);
		window.setResizable(false);
		window.setTitle("Main Menu");
		window.show();
    }

    /**
     * This is a method to allow functionality of the GoToOnlineCatalog button to switch 
     * the scene to the OnlineCatalog
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
		window.setMaximized(false);
		window.setWidth(600);
		window.setHeight(439);
		window.setResizable(false);
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
		window.setMaximized(false);
		window.setWidth(600);
		window.setHeight(439);
		window.setResizable(false);
		window.setTitle("Local Catalog");
		window.show();
    }
    
    /**
     * Adds a STIGFilter generated from the contents of FilterField, MatachButton, and FilterTextField
     * to the FilterTable, and updates the displayed filteredSTIGRules.
     */
    @FXML
    void AddFilter() {
    	// Detect if there is a valid selected in the FilteredField and the FilterTextArea
    	if(FilterField.getSelectionModel().getSelectedItem() != null && FilterTextField.getText() != null ) {
    		STIGFilter filterRow = new STIGFilter(FilterField.getSelectionModel().getSelectedItem(), (MatchButton.isSelected() ? "Matches" : "Contains"), FilterTextField.getText());
    		
    		FilterFieldColumn.setCellValueFactory( new PropertyValueFactory<STIGFilter, String>("field") );
    		FilterTypeColumn.setCellValueFactory( new PropertyValueFactory<STIGFilter, String>("type") );
    		FilterTextColumn.setCellValueFactory( new PropertyValueFactory<STIGFilter, String>("text") );
    		
    		FilterTable.getItems().add(filterRow);
    		updateFilteredSTIGRules();
    	}
    }

    /**
     * Deletes the selected STIGFilter rule from the FilterTable, and updates the displayed filteredSTIGRules
     */
    @FXML
    void DeleteFilter() {
    	// Detect if an filter is available and selected
    	if(FilterTable.getSelectionModel().getSelectedItem() != null) {
    		// Delete the filter
    		FilterTable.getItems().remove(FilterTable.getSelectionModel().getSelectedItem());
    		// Update the filtered STIG
    		updateFilteredSTIGRules();
    	}
    }
    
    /**
     * Displays the STIGRule selected from the STIGRuleTable
     * @param event - the MouseEvent which is used to verify that the user double clicked on a row which changes the selected row
     * @throws IOException throws an error if there was an issue collecting the CCI content
     * @throws SAXException throws an error if there was an issue collecting the CCI content
     * @throws ParserConfigurationException throws an error if there was an issue collecting the CCI content
     */
    @FXML
    void displaySelectedRule(MouseEvent event) throws ParserConfigurationException, SAXException, IOException {
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
    		Text groupTitleContent = new Text(selectedRule.getGroupTitle() + "\n\n");
    		
    		// Rule Title
    		Text ruleTitleHeader = new Text("Rule Title: ");
    		ruleTitleHeader.setStyle("-fx-font-weight: bold");
    		Text ruleTitleContent = new Text(selectedRule.getRuleTitle() + "\n\n");
    		
    		// Discussion
    		Text discussionHeader = new Text("Discussion: ");
    		discussionHeader.setStyle("-fx-font-weight: bold");
    		Text discussionContent = new Text(selectedRule.getRuleDiscussion() + "\n\n");
    		
    		// Check Text
    		Text checkTextHeader = new Text("Check Text: ");
    		checkTextHeader.setStyle("-fx-font-weight: bold");
    		Text checkTextContent = new Text(selectedRule.getCheckText() + "\n\n");
    		
    		// Fix Text
    		Text fixTextHeader = new Text("Fix Text: ");
    		fixTextHeader.setStyle("-fx-font-weight: bold");
    		Text fixTextContent = new Text(selectedRule.getFixText() + "\n\n");
    		
    		// CCI
    		Text CCIHeader = new Text("CCI: ");
    		CCIHeader.setStyle("-fx-font-weight: bold");
    		Text CCIContent = new Text(selectedRule.getCCI() + ": " + selectedRule.getCCIContent() );
    		
    		STIGRuleHeader.getChildren().addAll(tempTitle, 
    											vulIDHeader, vulIDContent, ruleIDHeader, ruleIDContent, stigIDHeader, stigIDContent,
    											severityHeader, severityContent, legacyIDsHeader, legacyIDsContent);
    		STIGRuleHeader.setStyle("-fx-font-size: 14px");
    		
    		STIGRuleContent.getChildren().addAll(groupTitleHeader, groupTitleContent,
    											ruleTitleHeader, ruleTitleContent,
    											discussionHeader, discussionContent,
    											checkTextHeader, checkTextContent,
    											fixTextHeader, fixTextContent,
    											CCIHeader, CCIContent);
    		STIGRuleContent.setStyle("-fx-font-size: 14px");
    	}
    }
    
    /**
     * This method initializes the STIGViewer Controller by loading the referenceSTIG, displaying the STIGRules, and adding the FilterField items.
     * @param stigFileName - the file to be read to initialize the referenceSTIG
     * @throws ParserConfigurationException - errors when there was issue in parsing the file
     * @throws SAXException - errors when there was an issue reading/parsing the file
     * @throws IOException - errors when there was an issue reading form the file
     */
    void initializeSTIGViewer( File stigFile ) throws ParserConfigurationException, SAXException, IOException {
    	// Populate the referenceSTIG and the filteredSTIG (filtered STIG is initialized with no filter in place)
    	referenceSTIG = new STIGDocument( stigFile );
    	
    	// Update Filtered STIG Data
    	updateFilteredSTIGRules();
    	
    	// Initialize choice box
    	FilterField.getItems().addAll( "vulID", "subVulID", "stigID", "severityCat", "groupTitle", "ruleTitle", "ruleDiscussion", "fixText", "CCI");
    	FilterField.getSelectionModel().select(0);
    	
    }
    
    /**
     * Generates a STIGRule ArrayList with only STIGRules that match the filters, 
     * and uses this STIGRule ArrayList to update the STIG Rule Table and Rule Severity Chart.
     */
    void updateFilteredSTIGRules() {
    	// Generate the filteredSTIGRules ArrayList
    	ArrayList<STIGRule> filteredSTIGRules;
    	if( FilterTable.getItems().size() == 0) 
    		filteredSTIGRules = referenceSTIG.getStigRuleArrayList();
    	else {
    		filteredSTIGRules = new ArrayList<STIGRule>();
    		for( STIGRule rule : referenceSTIG.getStigRuleArrayList() ) {
    			for( STIGFilter filter : FilterTable.getItems() ) {
    				if(filter.getField().equals("vulID") && filter.getPattern().matcher(rule.getVulID()).matches() ) {
    					filteredSTIGRules.add(rule);
    					break;
    				}
    				else if(filter.getField().equals("subVulID") && filter.getPattern().matcher(rule.getSubVulID()).matches() ) {
    					filteredSTIGRules.add(rule);
    					break;
    				}
    				else if(filter.getField().equals("stigID") && filter.getPattern().matcher(rule.getStigID()).matches() ) {
    					filteredSTIGRules.add(rule);
    					break;
    				}
    				else if(filter.getField().equals("severityCat") && filter.getPattern().matcher(rule.getSeverityCat()).matches() ) {
    					filteredSTIGRules.add(rule);
    					break;
    				}
    				else if(filter.getField().equals("groupTitle") && filter.getPattern().matcher(rule.getGroupTitle()).matches() ) {
    					filteredSTIGRules.add(rule);
    					break;
    				}
    				else if(filter.getField().equals("ruleTitle") && filter.getPattern().matcher(rule.getRuleTitle()).matches() ) {
    					filteredSTIGRules.add(rule);
    					break;
    				}
    				else if(filter.getField().equals("ruleDiscussion") && filter.getPattern().matcher(rule.getRuleDiscussion()).matches() ) {
    					filteredSTIGRules.add(rule);
    					break;
    				}
    				else if(filter.getField().equals("fixText") && filter.getPattern().matcher(rule.getFixText()).matches() ) {
    					filteredSTIGRules.add(rule);
    					break;
    				}
    				else if(filter.getField().equals("CCI") && filter.getPattern().matcher(rule.getCCI()).matches() ) {
    					filteredSTIGRules.add(rule);
    					break;
    				}
    			}
    		}
    	}
    
    	// Update STIG Rule Table
    	VulnIDColumn.setCellValueFactory(new PropertyValueFactory<STIGRule, String>("vulID"));
        STIGIDColumn.setCellValueFactory(new PropertyValueFactory<STIGRule, String>("stigID"));
        RuleIDColumn.setCellValueFactory(new PropertyValueFactory<STIGRule, String>("subVulID"));
        RuleNameColumn.setCellValueFactory(new PropertyValueFactory<STIGRule, String>("groupTitle"));
    	STIGRuleTable.getItems().setAll( filteredSTIGRules );
    	
    	// Clear the displayed Rule
    	STIGRuleHeader.getChildren().clear();
		STIGRuleContent.getChildren().clear();
    	
    	// Collect Data for the Rule Severity Chart
    	int catI = 0;
    	int catII = 0;
    	int catIII = 0;
    	for( STIGRule rule: filteredSTIGRules) {
    		if( rule.getSeverityCat().equals("CAT I") ) catI++;
    		else if( rule.getSeverityCat().equals("CAT II") ) catII++; 
    		else if( rule.getSeverityCat().equals("CAT III") ) catIII++;
    	}
    	// Update the Rule Severity Chart
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    	if( catI > 0 ) 
    		pieChartData.add(new PieChart.Data("CAT I (High) - " + catI, catI));
    	if( catII > 0 )
    		pieChartData.add(new PieChart.Data("CAT II (Medium) - " + catII, catII));
    	if( catIII > 0 )
    		pieChartData.add(new PieChart.Data("CAT III (Low) - " + catIII, catIII));
    	RuleSeverityChart.setData(pieChartData);
    	RuleSeverityChart.setLegendSide(Side.BOTTOM);
    	RuleSeverityChart.setLegendVisible(true);
    }

}

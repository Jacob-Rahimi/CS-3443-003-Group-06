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
	private STIGDocument referenceSTIG;
	
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
    	if( !(FilterField.getSelectionModel().getSelectedItem().isEmpty()) && !(FilterTextField.getText().isEmpty()) ) {
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
     */
    @FXML
    void displaySelectedRule(MouseEvent event) {
    	// Ensure that the table was double clicked to change the selected rule
    	if( event.getClickCount() == 2 && !STIGRuleTable.getSelectionModel().isEmpty() ) {
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
    		Text legacyIDsContent = new Text("N/A");
    		if( !selectedRule.getLegacyIDs().isEmpty() )
    			legacyIDsContent = new Text(selectedRule.getLegacyIDs().toString().replaceAll("[\\[\\]]", "") + "\n");
    		
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
    		Text CCIContent = new Text("N/A");
    		if( selectedRule.getCCI() != null )
    			CCIContent = new Text(selectedRule.getCCI() + ": " + selectedRule.getCCIContent() );
    		
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
     * @param stigFile - the file to be read to initialize the referenceSTIG
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
    	FilterField.getItems().addAll( "Vul ID (Group ID)", "subVul ID (Rule ID)", "STIG ID", "Severity Category", "Legacy IDs", "Group Title", "Rule Title", "Rule Discussion", "Check Text", "Fix Text", "CCI", "CCI Content");
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
    				boolean ruleFilterMatch = false;
    				switch (filter.getField()){
    					case "Vul ID (Group ID)":
    						if( (filter.getType().equals("Matches") && rule.getVulID().equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getVulID().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "subVul ID (Rule ID)":
    						if( (filter.getType().equals("Matches") && rule.getSubVulID().equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getSubVulID().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "STIG ID":
    						if( (filter.getType().equals("Matches") && rule.getStigID().equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getStigID().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "Severity Category":
    						if( (filter.getType().equals("Matches") && rule.getSeverityCat().equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getSeverityCat().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "Legacy IDs":
    						if( (filter.getType().equals("Matches") && rule.getLegacyIDs().toString().replaceAll("[\\[\\]]", "").equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getLegacyIDs().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "Group Title":
    						if( (filter.getType().equals("Matches") && rule.getGroupTitle().equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getGroupTitle().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "Rule Title":
    						if( (filter.getType().equals("Matches") && rule.getRuleTitle().equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getRuleTitle().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "Rule Discussion":
    						if( (filter.getType().equals("Matches") && rule.getRuleDiscussion().equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getRuleDiscussion().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "Check Text":
    						if( (filter.getType().equals("Matches") && rule.getCheckText().equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getCheckText().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "Fix Text":
    						if( (filter.getType().equals("Matches") && rule.getFixText().equals(filter.getText())) 
    								|| (filter.getType().equals("Contains") && rule.getFixText().contains(filter.getText())) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "CCI":
    						if( rule.getCCI() != null 
    							&&  ( (filter.getType().equals("Matches") && rule.getCCI().equals(filter.getText())) 
    								  || (filter.getType().equals("Contains") && rule.getCCI().contains(filter.getText()))) ) {
    							filteredSTIGRules.add(rule);
    							ruleFilterMatch = true;
    						}
    						break;
    					case "CCI Content":
    						if( (filter.getType().equals("Matches") && rule.getCCIContent().equals(filter.getText())) 
        							|| (filter.getType().equals("Contains") && rule.getCCIContent().contains(filter.getText())) ) {
        						filteredSTIGRules.add(rule);
        						ruleFilterMatch = true;
    						}
    						break;
    				}
    				// Skip other filters checks if the rule already matched to one filter
    				if( ruleFilterMatch )
    					break;
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

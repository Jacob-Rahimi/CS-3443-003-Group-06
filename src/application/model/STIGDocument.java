package application.model;

import java.util.ArrayList;

// XML-related packages
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class STIGDocument {
	// Header Information	
	public String stigTitle;
	public String stigDescription;
	// Main Information
	ArrayList<STIGRule> stigRuleArrayList = new ArrayList<STIGRule>();
	
	/**
	 * This constructor assigns the object's variables based on the provided fileName argument
	 * @param stigFileName This is the filename of the XML STIG file that will be read from to assign the object's variables
	 * @throws ParserConfigurationException throws an error if there was an error parsing the file
	 * @throws IOException throws an error if there was an issue reading the file
	 * @throws SAXException throws an error if there was a SAX error or warning
	 */
	public STIGDocument( File stigFile ) throws ParserConfigurationException, SAXException, IOException {
		
		// Create the document builder and parse the XML file
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(stigFile);
		doc.getDocumentElement().normalize();
		
		// Get <benchmark> and create a NodeList of its child nodes
		Node benchmark = doc.getElementsByTagName("Benchmark").item(0);
		NodeList benchmarkNodeList = benchmark.getChildNodes();
		
		// Iterate through <benchmark>'s child nodes and collect STIG data
		for ( int i = 0; i < benchmarkNodeList.getLength(); i++ ) {
			Node benchmarkChildNode = benchmarkNodeList.item(i);
			
			// Collect stigTitle
			if( benchmarkChildNode.getNodeName().equals("title") )
				this.stigTitle = benchmarkChildNode.getTextContent();
			// Collect stigDescription
			if( benchmarkChildNode.getNodeName().equals("description") )
				this.stigDescription = benchmarkChildNode.getTextContent();
			// Collect stigRule
			if( benchmarkChildNode.getNodeName().equals("Group") ) {
				stigRuleArrayList.add( new STIGRule(benchmarkChildNode) );
			}
		}
			
	}
	
	
	
	// Getter and setters
	/**
	 * A getter method for the object's stigTitle variable
	 * @return Returns the object's stigTitle variable
	 */
	public String getStigTitle() {
		return stigTitle;
	}
	/**
	 * A setter method for the object's stigTitle variable
	 * @param stigTitle the String to be used for the object's stigTitle variable
	 */
	public void setStigTitle(String stigTitle) {
		this.stigTitle = stigTitle;
	}
	
	/**
	 * A getter method for the object's stigDescription variable
	 * @return Returns the object's stigDescription variable
	 */
	public String getStigDescription() {
		return stigDescription;
	}
	/**
	 * A setter method for the object's stigDescription variable
	 * @param stigDescription the String to be used for the object's stigDescription variable
	 */
	public void setStigDescription(String stigDescription) {
		this.stigDescription = stigDescription;
	}
	
	/**
	 * A getter method for the object's stigRuleArrayList ArrayList
	 * @return Returns the object's stigRuleArrayList ArrayList
	 */
	public ArrayList<STIGRule> getStigRuleArrayList() {
		return stigRuleArrayList;
	}
	/**
	 * A setter method for the object's stigRuleArrayList ArrayList of STIGRules
	 * @param stigRuleArrayList the ArrayList of STIGRules to be used for the object's stigRuleArrayList
	 */
	public void setStigRuleArrayList(ArrayList<STIGRule> stigRuleArrayList) {
		this.stigRuleArrayList = stigRuleArrayList;
	}
	
}

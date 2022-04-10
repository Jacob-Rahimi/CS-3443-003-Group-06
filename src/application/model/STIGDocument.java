package application.model;

import java.util.ArrayList;

// XML-related packages
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class STIGDocument {
	// Header Information	
	public String stigTitle;
	public String stigDescription;
	// Main Information
	ArrayList<STIGGroup> stigGroupArrayList = new ArrayList<STIGGroup>();
	
	/**
	 * This constructor assigns the object's variables based on the provided fileName argument
	 * @param stigFileName This is the filename of the XML STIG file that will be read from to assign the object's variables
	 */
	public void stigDocumentReader( String stigFileName ) {
		try {
			File stigFile = new File(stigFileName);
			
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
				// Collect stigGroup
				if( benchmarkChildNode.getNodeName().equals("Group") ) {
					stigGroupArrayList.add( new STIGGroup(benchmarkChildNode) );
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
	 * A getter method for the object's stigGroupArrayList ArrayList
	 * @return Returns the object's stigGroupArrayList ArrayList
	 */
	public ArrayList<STIGGroup> getStigGroupArrayList() {
		return stigGroupArrayList;
	}
	/**
	 * A setter method for the object's stigGroupArrayList ArrayList of STIGGroups
	 * @param stigGroupArrayList the ArrayList of STIGGroups to be used for the object's stigGroupArrayList
	 */
	public void setStigGroupArrayList(ArrayList<STIGGroup> stigGroupArrayList) {
		this.stigGroupArrayList = stigGroupArrayList;
	}
	
}

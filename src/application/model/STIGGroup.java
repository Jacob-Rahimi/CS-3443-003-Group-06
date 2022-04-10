package application.model;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class STIGGroup {
	// Header Information
	public String vulID;							// Also known as Group ID
	// Main Content
	public String groupTitle;
	// subVulns (STIG Rules)
	public ArrayList<STIGRule> stigRuleArrayList = new ArrayList<STIGRule>();
	
	
	/**
	 * This constructor assigns the object's variables based on the provided groupNode argument
	 * @param groupNode This is the DOM Node that will be read from to assign the object's variables
	 */
	public STIGGroup(Node groupNode) {
		// Collect vulID (Group ID)
		this.vulID = groupNode.getAttributes().getNamedItem("id").getTextContent();
		
		// Iterate through <Group>'s child nodes and collect Group data
		NodeList groupNodeList = groupNode.getChildNodes();
		for ( int i = 0; i < groupNodeList.getLength(); i++ ) {
			Node groupChildNode = groupNodeList.item(i);
			
			// Collect groupTitle
			if( groupChildNode.getNodeName().equals("title") )
				this.groupTitle = groupChildNode.getTextContent();
			// Collect subVulns
			if( groupChildNode.getNodeName().equals("Rule") )
				stigRuleArrayList.add( new STIGRule(groupChildNode) );
		}
	}
	
	// Getter and Setters
	/**
	 * A getter method for the object's vulID variable
	 * @return Returns the object's vulID variable
	 */
	public String getVulID() {
		return vulID;
	}
	/**
	 * A setter method for the object's vulID variable
	 * @param vulID the String to be used for the object's vulID variable
	 */
	public void setVulID(String vulID) {
		this.vulID = vulID;
	}
	
	/**
	 * A getter method for the object's groupTitle variable
	 * @return Returns the object's groupTitle variable
	 */
	public String getGroupTitle() {
		return groupTitle;
	}
	/**
	 * A setter method for the object's groupTitle variable
	 * @param groupTitle the string to be used for the object's groupTitle variable
	 */
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
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
	 * @param stigRuleArrayList the ArrayList of STIGRules for the object's stigRuleArrayList ArrayList
	 */
	public void setStigRuleArrayList(ArrayList<STIGRule> stigRuleArrayList) {
		this.stigRuleArrayList = stigRuleArrayList;
	}
	
	
}

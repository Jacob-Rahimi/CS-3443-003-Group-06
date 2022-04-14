package application.model;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class STIGRule {
	// Header Information
	public String vulID;		// Also known as Group ID
	public String subVulID;		// Also known as Rule ID
	public String stigID;
	public String severityCat;	// Severity Category {CAT I, CAT II, CAT III}
	public ArrayList<String> legacyIDs = new ArrayList<String>();
	// Main Content
	public String groupTitle;
	public String ruleTitle;
	public String ruleDiscussion;
	public String checkText;
	public String fixText;
	// References
	public String CCI;

	/**
	 * This constructor assigns the object's variables based on the provided groupNode argument
	 * @param groupNode This is the DOM Node that will be read from to assign the object's variables
	 */
	public STIGRule(Node groupNode) {
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
			if( groupChildNode.getNodeName().equals("Rule") ) {
				// Collect subvulID (Group ID)
				this.subVulID = groupChildNode.getAttributes().getNamedItem("id").getTextContent();
				
				// Collect severityCat
				String severity = groupChildNode.getAttributes().getNamedItem("severity").getTextContent();
				if( severity.equalsIgnoreCase("high") )
					this.severityCat = "CAT I";
				else if( severity.equalsIgnoreCase("medium") )
					this.severityCat = "CAT II";
				else if( severity.equalsIgnoreCase("low") )
					this.severityCat = "CAT III";
				
				// Iterate through <Rule>'s child nodes and collect Rule data
				NodeList ruleNodeList = groupChildNode.getChildNodes();
				for ( int j = 0; j < ruleNodeList.getLength(); j++ ) {
					Node ruleChildNode = ruleNodeList.item(j);
					
					// Collect ruleTitle
					if( ruleChildNode.getNodeName().equals("title") )
						this.ruleTitle = ruleChildNode.getTextContent();
					// Collect ruleDiscussion
					if( ruleChildNode.getNodeName().equals("description") ) {
						this.ruleDiscussion = ruleChildNode.getTextContent().replaceFirst("<VulnDiscussion>", "").replaceFirst("</VulnDiscussion>.*", "") ;
					}
					// Collect legacyID
					if( ruleChildNode.getNodeName().equals("ident") && ruleChildNode.getAttributes().getNamedItem("system").getTextContent().equals("http://cyber.mil/legacy") )
						this.legacyIDs.add(ruleChildNode.getTextContent());
					// Collect CCI
					if( ruleChildNode.getNodeName().equals("ident") && ruleChildNode.getAttributes().getNamedItem("system").getTextContent().equals("http://cyber.mil/cci") ) 
						this.CCI = ruleChildNode.getTextContent();
					// Collect fixText
					if( ruleChildNode.getNodeName().equals("fixtext") )
						this.fixText = ruleChildNode.getTextContent();
					// Collect checkText
					if( ruleChildNode.getNodeName().equals("check") ) {
						NodeList checkNodeList = ruleChildNode.getChildNodes();
						for( int k = 0; k < checkNodeList.getLength(); k++) {
							Node checkChildNode = checkNodeList.item(k);
							if( checkChildNode.getNodeName().equals("check-content") )
								this.checkText += checkChildNode.getTextContent() + "\n";
						}
					}
				}
			}
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
	 * A getter method for the object's subVulID variable
	 * @return Returns the object's subVulID variable
	 */
	public String getSubVulID() {
		return subVulID;
	}
	/**
	 * A setter method for the object's subVulID variable
	 * @param subVulID the String to be used for the object's subVulID variable
	 */
	public void setSubVulID(String subVulID) {
		this.subVulID = subVulID;
	}
	
	/**
	 * A getter method for the object's getStigID variable
	 * @return Returns the object's getStigID variable
	 */
	public String getStigID() {
		return stigID;
	}
	/**
	 * A setter method for the object's setStigID variable
	 * @param stigID the String to be used for the object's stigID variable
	 */
	public void setStigID(String stigID) {
		this.stigID = stigID;
	}
	
	/**
	 * A getter method for the object's severityCat variable
	 * @return Returns the object's severityCat variable
	 */
	public String getSeverityCat() {
		return severityCat;
	}
	/**
	 * A setter method for the object's severityCat variable
	 * @param severityCat the String to be used for the object's severityCat variable
	 */
	public void setSeverityCat(String severityCat) {
		this.severityCat = severityCat;
	}
	
	/**
	 * A getter method for the object's LegacyID ArrayList
	 * @return Returns the object's LegacyID ArrayList
	 */
	public ArrayList<String> getLegacyIDs() {
		return legacyIDs;
	}
	/**
	 * A setter method for the object's LegacyID ArrayList
	 * @param legacyIDs the ArrayList of Strings to be used for the object's LegacyID ArrayList
	 */
	public void setLegacyIDs(ArrayList<String> legacyIDs) {
		this.legacyIDs = legacyIDs;
	}
	
	/**
	 * A getter method for the object's ruleTitle variable
	 * @return Returns the object's ruleTitle variable
	 */
	public String getRuleTitle() {
		return ruleTitle;
	}
	/**
	 * A setter method for the object's ruleTitle variable
	 * @param ruleTitle the String to be used for the object's ruleTitle variable
	 */
	public void setRuleTitle(String ruleTitle) {
		this.ruleTitle = ruleTitle;
	}
	
	/**
	 * A getter method for the object's ruleDiscussion variable
	 * @return Returns the object's ruleDiscussion variable
	 */
	public String getRuleDiscussion() {
		return ruleDiscussion;
	}
	/**
	 * A setter method for the object's ruleDiscussion variable
	 * @param ruleDiscussion the String to be used for the object's ruleDiscussion variable
	 */
	public void setRuleDiscussion(String ruleDiscussion) {
		this.ruleDiscussion = ruleDiscussion;
	}
	
	/**
	 * A getter method for the object's checkText variable
	 * @return Returns the object's checkText variable
	 */
	public String getCheckText() {
		return checkText;
	}
	/**
	 * A setter method for the object's checkText variable
	 * @param checkText the String to be used for the object's checkText variable
	 */
	public void setCheckText(String checkText) {
		this.checkText = checkText;
	}
	
	/**
	 * A getter method for the object's fixText variable
	 * @return Returns the object's fixText variable
	 */
	public String getFixText() {
		return fixText;
	}
	/**
	 * A setter method for the object's fixText variable
	 * @param fixText the String to be used for the object's fixText variable
	 */
	public void setFixText(String fixText) {
		this.fixText = fixText;
	}
	
	/**
	 * A getter method for the object's CCI variable
	 * @return Returns the object's CCI variable
	 */
	public String getCCI() {
		return CCI;
	}
	/**
	 * A setter method for the object's CCI variable
	 * @param CCI the String to be used for the object's CCI variable
	 */
	public void setCCI(String CCI) {
		this.CCI = CCI;
	}
}

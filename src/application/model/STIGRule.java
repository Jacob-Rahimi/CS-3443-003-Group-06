package application.model;

import java.util.ArrayList;

public class STIGRule {
	// Header Information
	public String subVulID;					// Also known as Rule ID
	public String stigID;
	public String severityCat;				// Severity Category {CAT I, CAT II, CAT III}
	public ArrayList<String> legacyIDs[];
	// Main Content
	public String ruleTitle;
	public String ruleDiscussion;
	public String checkText;
	public String fixText;
	// References
	public String CCI;
	
	// Getter and Setters
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
	public ArrayList<String>[] getLegacyIDs() {
		return legacyIDs;
	}
	/**
	 * A setter method for the object's LegacyID ArrayList
	 * @param legacyIDs the ArrayList of Strings to be used for the object's LegacyID ArrayList
	 */
	public void setLegacyIDs(ArrayList<String>[] legacyIDs) {
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

package application.model;

import java.util.ArrayList;

public class STIGGroup {
	// Header Information
	public String vulID;							// Also known as Group ID
	// Main Content
	public String groupTitle;
	// subVulns (STIG Rules)
	public ArrayList<STIGRule> stigRuleArrayList;
	
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

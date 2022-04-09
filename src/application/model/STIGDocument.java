package application.model;

import java.util.ArrayList;

public class STIGDocument {
	// Header Information	
	public String stigTitle;
	public String stigDescription;
	// Main Information
	ArrayList<STIGGroup> stigGroupArrayList;
	
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

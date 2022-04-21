package application.model;

/**
 * application.model.STIGFilter is the class to represent a filter 
 * that is to be applied to the list of STIG rules.
 * @author Jacob Rahimi
 */
public class STIGFilter {
	private String field;
	private String type;
	private String text;
	
	/**
	 * This constructor assigns the parameter values to the object's corresponding variables.
	 * @param filterField - the filterField String to be copied to the object's field variable
	 * @param filterType - the filterType String to be copied to the object's type variable
	 * @param filterText - the filterText String to be copied to the object's text variable
	 */
	public STIGFilter(String filterField, String filterType, String filterText) {
		this.field = filterField;
		this.type = filterType;
		this.text = filterText;
	}
	
	// Getter and Setters
	/**
	 * A getter method for the object's field variable
	 * @return Returns the object's field variable
	 */
	public String getField() {
		return field;
	}
	/**
	 * A setter method for the object's field variable
	 * @param field - the String to be used for the object's field variable
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * A getter method for the object's type variable
	 * @return Returns the object's type variable
	 */
	public String getType() {
		return type;
	}
	/**
	 * A setter method for the object's type variable
	 * @param type - the String to be used for the object's type variable
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * A getter method for the object's text variable
	 * @return Returns the object's text variable
	 */
	public String getText() {
		return text;
	}
	/**
	 * A setter method for the object's text variable
	 * @param text - the String to be used for the object's text variable
	 */
	public void setText(String text) {
		this.text = text;
	}
}

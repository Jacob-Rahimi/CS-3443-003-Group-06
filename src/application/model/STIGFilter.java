package application.model;

import java.util.regex.Pattern;

/**
 * application.model.STIGFilter is the class to represent a filter 
 * that is to be applied to the list of STIG rules. It includes
 * a REGEX pattern generator based on the parameters provided
 * to the constructor method.
 * @author Jacob Rahimi
 */
public class STIGFilter {
	private String field;
	private String type;
	private String text;
	private Pattern pattern;
	
	/**
	 * This constructor assigns the parameter values to the object's corresponding variables as well
	 * as generates a pattern based on the filterType and filterText parameters passed into it.
	 * @param filterField - the filterField String to be copied to the object's field variable
	 * @param filterType - the filterType String to be copied to the object's type variable
	 * @param filterText - the filterText String to be copied to the object's text variable
	 */
	public STIGFilter(String filterField, String filterType, String filterText) {
		this.field = filterField;
		this.type = filterType;
		this.text = filterText;
		
		// Generate the pattern based on if "Matches" or "Contains" was selected
		if( filterType.equals("Matches") ) pattern = Pattern.compile("^" + filterText + "$", Pattern.CASE_INSENSITIVE);
		else pattern = Pattern.compile(".*" + filterText + ".*", Pattern.CASE_INSENSITIVE);
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
	/**
	 * A getter method for the object's pattern variable
	 * @return Returns the object's pattern variable
	 */
	public Pattern getPattern() {
		return pattern;
	}
	/**
	 * A setter method for the object's pattern variable
	 * @param pattern - the Pattern to be used for the object's pattern variable
	 */
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
}

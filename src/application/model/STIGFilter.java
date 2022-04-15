package application.model;

import java.util.regex.Pattern;

public class STIGFilter {
	public String field;
	public String type;
	public String text;
	public Pattern pattern;
	
	public STIGFilter(String filterField, String filterType, String filterText) {
		this.field = filterField;
		this.type = filterType;
		this.text = filterText;
		
		// Generate the pattern based on if "Matches" or "Contains" was selected
		if( filterType.equals("Matches") ) pattern = Pattern.compile("^" + filterText + "$", Pattern.CASE_INSENSITIVE);
		else pattern = Pattern.compile(".*" + filterText + ".*", Pattern.CASE_INSENSITIVE);
	}
	
	// Getter and Setters

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
}

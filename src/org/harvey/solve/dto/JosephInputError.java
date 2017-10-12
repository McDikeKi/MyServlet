package org.harvey.solve.dto;

public class JosephInputError {
	String field;
	String message;
	
	public JosephInputError() {
		this.field = null;
		this.message = null;
	}
	
	public JosephInputError(String field, String message) {
		this.field = field;
		this.message = message;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

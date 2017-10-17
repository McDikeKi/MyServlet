package org.harvey.solve.dto;

public class JosephProblemInputError {
	String field;
	String message;
	
	public JosephProblemInputError() {
		this.field = null;
		this.message = null;
	}
	
	public JosephProblemInputError(String message,String field) {
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

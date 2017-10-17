package org.harvey.solve.dto;

import java.util.List;

import org.harvey.solve.annotation.Mapping;

public class JosephProblemResponse extends DataTransferObject{
	@Mapping(jsonFieldName="person")
	private String person;
	private List<JosephProblemInputError> errors;
	
	public JosephProblemResponse() {
		person = null;
		errors = null;
	}
	
	public JosephProblemResponse(String person,List<JosephProblemInputError> errors) {
		this.person = person;
		this.errors = errors;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public List<JosephProblemInputError> getErrors() {
		return errors;
	}

	public void setErrors(List<JosephProblemInputError> errors) {
		this.errors = errors;
	}
}

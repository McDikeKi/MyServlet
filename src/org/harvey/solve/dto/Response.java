package org.harvey.solve.dto;

import java.util.List;

import org.harvey.solve.annotation.Mapping;

public class Response extends DataTransferObject{
	@Mapping(jsonFieldName="person")
	private String person;
	private List<JosephInputError> errors;
	
	public Response() {
		person = null;
		errors = null;
	}
	
	public Response(String person,List<JosephInputError> errors) {
		this.person = person;
		this.errors = errors;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public List<JosephInputError> getErrors() {
		return errors;
	}

	public void setErrors(List<JosephInputError> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "Response [person=" + person + "]";
	}


}

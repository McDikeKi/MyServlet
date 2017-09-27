package org.harvey.solve.dto;

import org.harvey.solve.annotation.Mapping;

public class Response extends DataTransferObject{
	@Mapping(jsonFieldName="person")
	private String person;
	
	public Response() {
		person = null;
	}
	
	public Response(String person) {
		super();
		this.person = person;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Response [person=" + person + "]";
	}


}

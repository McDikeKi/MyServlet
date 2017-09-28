package org.harvey.solve.dto;

import org.harvey.solve.annotation.Mapping;

public class Persons {
	@Mapping(jsonFieldName="persons")
	private String[] person;
	
	public Persons() {
		this.person = null;
	}

	public String[] getPerson() {
		return person;
	}

	public void setPerson(String[] person) {
		this.person = person;
	}

}

package org.harvey.solve.dto;

import org.harvey.solve.annotation.Mapping;

public class Circle {
	@Mapping(jsonFieldName = "persons")
	private String[] persons;
	@Mapping(jsonFieldName = "start")
	private Integer start;
	@Mapping(jsonFieldName = "interval")
	private Integer interval;
	
	public Circle() {
		this.persons = null;
		this.start = 0;
		this.interval = 0;
	}
	
	public Circle(String[] persons, int start, int interval) {
		super();
		this.persons = persons;
		this.start = start;
		this.interval = interval;
	}

	public String[] getPersons() {
		return persons;
	}

	public void setPersons(String[] persons) {
		this.persons = persons;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "JosephRequest [start=" + start
				+ ", interval=" + interval + "]";
	}
	
	
}

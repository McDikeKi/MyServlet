package org.harvey.solve.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.harvey.solve.annotation.Mapping;
import org.harvey.solve.validator.ListNotHasNull;
import org.harvey.solve.validator.MinLength;

public class Circle {
	@Mapping(jsonFieldName = "persons")
	@NotNull(message="persons can't be null")
	@MinLength(value=1,message="persons's size can't be 0")
	@ListNotHasNull(message="persons can't have a null element")
	private List<String> persons;
	
	@Mapping(jsonFieldName = "start")
	@Min(value = 0 ,message="start can't be less than 1")
	private Integer start;
	
	@Mapping(jsonFieldName = "interval")
	@Min(value = 0 ,message="start can't be less than 1")
	private Integer interval;
	
	public Circle() {
		this.persons = null;
		this.start = 0;
		this.interval = 0;
	}
	
	public Circle(List<String> persons, int start, int interval) {
		super();
		this.persons = persons;
		this.start = start;
		this.interval = interval;
	}

	public List<String> getPersons() {
		return persons;
	}

	public void setPersons(List<String> persons) {
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

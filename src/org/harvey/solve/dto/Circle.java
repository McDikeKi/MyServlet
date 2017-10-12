package org.harvey.solve.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.harvey.solve.annotation.Mapping;
import org.harvey.solve.validator.ListNoSameName;
import org.harvey.solve.validator.ListNotHasNull;
import org.harvey.solve.validator.MinLength;

public class Circle {
	@Mapping(jsonFieldName = "persons")
	@NotNull(message="Persons can't be null")
	@MinLength(value=1,message="Persons size can't be 0")
	@ListNotHasNull(message="Persons can't have a null element")
	@ListNoSameName(message="Persons can't have same names")
	private List<String> persons;
	
	@Mapping(jsonFieldName = "start")
	@NotNull(message="Start can't be null")
	@Min(value = 0 ,message="Start can't be less than 0")
	private Integer start;
	
	@Mapping(jsonFieldName = "interval")
	@NotNull(message="Interval can't be null")
	@Min(value = 1 ,message="Interval can't be less than 1")
	private Integer interval;
	
	public Circle() {
		this.persons = null;
		this.start = null;
		this.interval = null;
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

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "JosephRequest [start=" + start
				+ ", interval=" + interval + "]";
	}
	
	
}

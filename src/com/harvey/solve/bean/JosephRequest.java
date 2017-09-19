package com.harvey.solve.bean;

import java.util.List;

public class JosephRequest {
	private List<String> persons;
	private String start;
	private String interval;
	

	public JosephRequest(List<String> persons, String start, String interval) {
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
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	@Override
	public String toString() {
		return "JosephRequest [start=" + start
				+ ", interval=" + interval + "]";
	}
	
	
}

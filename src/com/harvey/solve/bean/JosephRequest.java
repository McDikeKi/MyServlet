package com.harvey.solve.bean;

import java.util.List;

public class JosephRequest {
	private List<String> persons;
	private int start;
	private int interval;
	
	public JosephRequest(List<String> persons, int start, int interval) {
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

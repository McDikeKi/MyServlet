package com.harvey.solve.bean;

import java.util.List;

public class JosephRequest {
	private List<Person> circle;
	private int start;
	private int interval;
	
	public JosephRequest(List<Person> circle, int start, int interval) {
		this.circle = circle;
		this.start = start;
		this.interval = interval;
	}
	public List<Person> getCircle() {
		return circle;
	}
	public void setCircle(List<Person> circle) {
		this.circle = circle;
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
		return "JosephRequest [circle=" + circle.get(0) + ", start=" + start
				+ ", interval=" + interval + "]";
	}
	
	
}

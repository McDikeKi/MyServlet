package org.harvey.solve.dto;

import org.harvey.solve.annotation.Mapping;

public class Request extends DataTransferObject {
	@Mapping(jsonFieldName="circle")
	private Circle circle;

	public Request() {
		this.circle = null;
	}
	
	public Request(Circle circle) {
		this.circle = circle;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	@Override
	public String toString() {
		return "Request [start=" + circle.getStart() + "]";
	}
	
	
}

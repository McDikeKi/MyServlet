package org.harvey.solve.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.harvey.solve.annotation.Mapping;

public class JosephProblemRequest extends DataTransferObject {
	@Mapping(jsonFieldName = "circle")
	@Valid
	@NotNull(message = "Circle can't be null")
	private JosephProblemCircle circle;

	public JosephProblemRequest() {
		this.circle = null;
	}

	public JosephProblemRequest(JosephProblemCircle circle) {
		this.circle = circle;
	}

	public JosephProblemCircle getCircle() {
		return circle;
	}

	public void setCircle(JosephProblemCircle circle) {
		this.circle = circle;
	}
}

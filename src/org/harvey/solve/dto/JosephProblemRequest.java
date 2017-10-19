package org.harvey.solve.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.harvey.solve.annotation.Mapping;
import org.harvey.solve.validator.constraint.ListSizeLimitation;

public class JosephProblemRequest extends DataTransferObject {
	@Mapping(jsonFieldName = "circle")
	@Valid
	@NotNull(message = "Circle can't be null")
	@ListSizeLimitation(listFieldName="persons",limitedFieldName="start"
								,message="{errormessage.start.largerthanlistsize}")
	@ListSizeLimitation(listFieldName="persons",limitedFieldName="interval"
								,message="{errormessage.interval.largerthanlistsize}")
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

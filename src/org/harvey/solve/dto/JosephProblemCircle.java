package org.harvey.solve.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.harvey.solve.annotation.Mapping;
import org.harvey.solve.validator.constraint.ListNotHasNull;
import org.harvey.solve.validator.constraint.ListSizeLimitation;
import org.harvey.solve.validator.constraint.MinLength;
import org.harvey.solve.validator.constraint.NoRepetitionList;

@ListSizeLimitation(listFieldName="persons",limitedFieldName="start"
			,message="{errormessage.start.largerthanlistsize}")
@ListSizeLimitation(listFieldName="persons",limitedFieldName="interval"
			,message="{errormessage.interval.largerthanlistsize}")
public class JosephProblemCircle {
	@Mapping(jsonFieldName = "circle.persons")
	@NotNull(message="{errormessage.null}")
	@MinLength(value=1,message="{errormessage.circle.minsize}")
	@ListNotHasNull(message="{errormessage.circle.nonullelement}")
	@NoRepetitionList(message="{errormessage.circle.nosameelement}")
	private List<String> persons;
	
	@Mapping(jsonFieldName = "circle.start")
	@NotNull(message="{errormessage.null}")
	@Min(value = 0 ,message="{errormessage.start.minvalue}")
	private Integer start;
	
	@Mapping(jsonFieldName = "circle.interval")
	@NotNull(message="{errormessage.null}")
	@Min(value = 1 ,message="{errormessage.interval.minvalue}")
	private Integer interval;
	
	public JosephProblemCircle() {
		this.persons = null;
		this.start = null;
		this.interval = null;
	}
	
	public JosephProblemCircle(List<String> persons, Integer start, Integer interval) {
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
}

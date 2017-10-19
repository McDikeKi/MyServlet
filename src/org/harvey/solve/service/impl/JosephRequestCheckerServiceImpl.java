package org.harvey.solve.service.impl;

import java.util.List;

import org.harvey.solve.dto.JosephProblemCircle;
import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.JosephRequestCheckerService;

public class JosephRequestCheckerServiceImpl implements JosephRequestCheckerService {
	private static final String MESSAGE_REQUEST_NULL = "Request is null";
	private static final String MESSAGE_CIRCLE_NULL = "Circle is null - circle";
	private static final String MESSAGE_PERSONS_NULL = "Persons is null - circle.persons";
	private static final String MESSAGE_START_NULL = "Start is null - circle.start";
	private static final String MESSAGE_INTERVAL_NULL = "Interval is null - circle.interval";
	private static final String MESSAGE_START_VALUE_WRONG = "Wrong input for start - circle.start";
	private static final String MESSAGE_INTERVAL_VALUE_WRONG = "Wrong input for interval - circle.interval";
	private static final String MESSAGE_START_INDEX_OUT_OF_BOUNDS = "Start index out of bounds - circle.start";
	private static final String MESSAGE_INTERVAL_OUT_OF_BOUNDS = "Interval out of bounds - circle.interval";
	
	public boolean check(JosephProblemRequest request) throws NullValueException, WrongValueException{		
		if (request == null) {
			throw new NullValueException(MESSAGE_REQUEST_NULL);
		} else {
			JosephProblemCircle circle = request.getCircle();
			if (circle == null) {
				throw new NullValueException(MESSAGE_CIRCLE_NULL);
			} else {
				List<String> persons = circle.getPersons();
				if (persons == null || persons.size() == 0) {
					throw new NullValueException(MESSAGE_PERSONS_NULL);
				} else {
					Integer start = circle.getStart();
					Integer interval = circle.getInterval();
					if(start == null){
						throw new NullValueException(MESSAGE_START_NULL);
					}
					if(interval == null){
						throw new NullValueException(MESSAGE_INTERVAL_NULL);
					}
					if (start < 0) {
						throw new WrongValueException(MESSAGE_START_VALUE_WRONG);
					}
					if (interval < 1) {
						throw new WrongValueException(MESSAGE_INTERVAL_VALUE_WRONG);
					}
					if (start >= persons.size()) {
						throw new WrongValueException(MESSAGE_START_INDEX_OUT_OF_BOUNDS);
					}
					if (interval > persons.size()) {
						throw new WrongValueException(MESSAGE_INTERVAL_OUT_OF_BOUNDS);
					}
					return true;
				}
			}
		}
	}
}

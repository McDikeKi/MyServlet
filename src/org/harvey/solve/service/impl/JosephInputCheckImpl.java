package org.harvey.solve.service.impl;

import org.harvey.solve.exception.JsonFieldWrongTypeException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.JosephInputCheck;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JosephInputCheckImpl implements JosephInputCheck {
	private static final String MESSAGE_CIRCLE_PARSING_ERROR = "Circle field error in json";
	private static final String MESSAGE_START_PARSING_ERROR = "Start field error in json";
	private static final String MESSAGE_INTERVAL_PARSING_ERROR = "Interval field error in json";
	private static final String MESSAGE_PERSONS_PARSING_ERROR = "Persons field error in json";
	private static final String MESSAGE_REQUEST_NULL = "Request is null";
	private static final String MESSAGE_CIRCLE_NULL = "Circle is null";
	private static final String MESSAGE_PERSONS_NULL = "Persons is null";
	private static final String MESSAGE_PERSONS_VALUE_WRONG = "Only one name in persons";
	private static final String MESSAGE_START_VALUE_WRONG = "Wrong input for start";
	private static final String MESSAGE_INTERVAL_VALUE_WRONG = "Wrong input for interval";
	private static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Start index out of bounds";

	public void check(JSONObject request) throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		if (request == null) {
			throw new NullValueException(MESSAGE_REQUEST_NULL);
		} else {
			JSONObject circle;
			try {
				circle = request.getJSONObject("circle");
			} catch (Exception e) {
				throw new JsonFieldWrongTypeException(MESSAGE_CIRCLE_PARSING_ERROR, e);
			}
			if (circle == null) {
				throw new NullValueException(MESSAGE_CIRCLE_NULL);
			} else {
				JSONArray persons;
				try {
					persons = circle.getJSONArray("persons");
				} catch (Exception e) {
					throw new JsonFieldWrongTypeException(MESSAGE_PERSONS_PARSING_ERROR, e);
				}
				if (persons == null || persons.length() == 0) {
					throw new NullValueException(MESSAGE_PERSONS_NULL);
				} else {
					int start;
					int interval;
					try {
						start = circle.getInt("start");
					} catch (Exception e) {
						throw new JsonFieldWrongTypeException(MESSAGE_START_PARSING_ERROR, e);
					}
					try {
						interval = circle.getInt("interval");
					} catch (Exception e) {
						throw new JsonFieldWrongTypeException(MESSAGE_INTERVAL_PARSING_ERROR, e);
					}
					if (start < 0) {
						throw new WrongValueException(MESSAGE_START_VALUE_WRONG);
					}
					if (interval < 1) {
						throw new WrongValueException(MESSAGE_INTERVAL_VALUE_WRONG);
					}
					if (persons.length() == 1) {
						throw new WrongValueException(MESSAGE_PERSONS_VALUE_WRONG);
					}
					if (start >= persons.length()) {
						throw new WrongValueException(MESSAGE_INDEX_OUT_OF_BOUNDS);
					}
				}
			}
		}
	}
}

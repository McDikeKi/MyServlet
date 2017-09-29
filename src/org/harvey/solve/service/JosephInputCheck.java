package org.harvey.solve.service;

import org.harvey.solve.exception.JsonFieldWrongTypeException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.json.JSONObject;

public interface JosephInputCheck {
	public void check(JSONObject request) throws NullValueException, WrongValueException, JsonFieldWrongTypeException;
}

package org.harvey.solve.business;

import org.harvey.solve.exception.IlligalInputException;
import org.json.JSONObject;

public interface CheckJosephRequest {
	public void check(JSONObject request) throws IlligalInputException;
}

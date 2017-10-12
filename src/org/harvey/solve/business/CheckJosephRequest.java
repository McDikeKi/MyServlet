package org.harvey.solve.business;

import org.harvey.solve.exception.IllegalInputException;
import org.json.JSONObject;

public interface CheckJosephRequest {
	public void check(JSONObject request) throws IllegalInputException;
}

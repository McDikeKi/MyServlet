package org.harvey.solve.business.impl;

import org.harvey.solve.business.CheckJosephRequest;
import org.harvey.solve.exception.IlligalInputException;
import org.harvey.solve.exception.JsonFieldWrongTypeException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.JosephInputCheck;
import org.json.JSONObject;

public class CheckJosephRequestImpl implements CheckJosephRequest{
	private static final String EXCEPTION_MESSAGE = "Illegal input for Joseph Problem";	
	private JosephInputCheck josephInputCheck;
	
	public void setJosephInputCheck(JosephInputCheck josephInputCheck) {
		this.josephInputCheck = josephInputCheck;
	}

	public void check(JSONObject request) throws IlligalInputException{
		try {
			josephInputCheck.check(request);
		} catch (NullValueException e) {
			throw new IlligalInputException(EXCEPTION_MESSAGE, e);
		} catch (WrongValueException e) {
			throw new IlligalInputException(EXCEPTION_MESSAGE, e);
		} catch (JsonFieldWrongTypeException e) {
			throw new IlligalInputException(EXCEPTION_MESSAGE, e);
		}
	}
}

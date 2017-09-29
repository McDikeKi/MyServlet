package org.harvey.solve.business.businessimpl;

import org.harvey.solve.business.CheckJosephRequest;
import org.harvey.solve.exception.IlligalInputException;
import org.harvey.solve.exception.JsonFieldWrongTypeException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.JosephInputCheck;
import org.harvey.solve.service.serviceimpl.JosephInputCheckImpl;
import org.json.JSONObject;

public class CheckJosephRequestImpl implements CheckJosephRequest{
	private static final String EXCEPTION_MESSAGE = "Illegal input for Joseph Problem";
	public void check(JSONObject request) throws IlligalInputException{
		try {
			JosephInputCheck josephInputCheck = new JosephInputCheckImpl();
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

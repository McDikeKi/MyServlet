package org.harvey.solve.business.impl;

import org.harvey.solve.business.JosephRequestCheckerBusiness;
import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.exception.IllegalInputException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.JosephRequestCheckerService;

public class JosephRequestCheckerBusinessImpl implements JosephRequestCheckerBusiness{
	private static final String EXCEPTION_MESSAGE = "Illegal input for Joseph Problem";
	private static JosephRequestCheckerService josephRequestCheckerService;

	public static void setJosephRequestCheckerService(JosephRequestCheckerService josephRequestCheckerService) {
		JosephRequestCheckerBusinessImpl.josephRequestCheckerService = josephRequestCheckerService;
	}

	public void check(JosephProblemRequest request) throws IllegalInputException{
		try {
			josephRequestCheckerService.check(request);
		} catch (NullValueException e) {
			throw new IllegalInputException(EXCEPTION_MESSAGE, e);
		} catch (WrongValueException e) {
			throw new IllegalInputException(EXCEPTION_MESSAGE, e);
		}
	}
}

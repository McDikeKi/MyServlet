package org.harvey.solve.business;

import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.exception.IllegalInputException;

public interface JosephRequestCheckerBusiness {
	public void check(JosephProblemRequest request) throws IllegalInputException;
}

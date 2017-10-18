package org.harvey.solve.service;

import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;

public interface JosephRequestCheckerService {
	public void check(JosephProblemRequest request) throws NullValueException,WrongValueException;
}

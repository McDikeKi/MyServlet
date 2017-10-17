package org.harvey.solve.service;

import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;

public interface FibonacciRequestCheckerService {
	public void check(String lengthStr) throws WrongValueException, NullValueException;
}

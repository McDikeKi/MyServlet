package org.harvey.solve.business;

import org.harvey.solve.exception.IllegalInputException;

public interface CheckFibonacciInput{
	public void check(String lengthStr) throws IllegalInputException;
}

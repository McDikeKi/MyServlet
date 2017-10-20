package org.harvey.solve.business.impl;

import org.harvey.solve.business.FibonacciRequestCheckerBusiness;
import org.harvey.solve.exception.IllegalInputException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.FibonacciRequestCheckerService;

public class FibonacciRequestCheckerBusinessImpl implements FibonacciRequestCheckerBusiness{
	private static final String EXCEPTION_MESSAGE = "Illegal input for Fibonacci Problem";	
	private FibonacciRequestCheckerService fibonacciRequestCheckerService;

	public void setFibonacciRequestCheckerService(FibonacciRequestCheckerService fibonacciRequestCheckerService) {
		this.fibonacciRequestCheckerService = fibonacciRequestCheckerService;
	}

	@Override
	public boolean check(String lengthStr) throws IllegalInputException {
		// TODO Auto-generated method stub
		try {
			fibonacciRequestCheckerService.check(lengthStr);
		} catch (WrongValueException e) {
			throw new IllegalInputException(EXCEPTION_MESSAGE,e);
		} catch (NullValueException e) {
			throw new IllegalInputException(EXCEPTION_MESSAGE,e);
		}
		return true;
	}

}

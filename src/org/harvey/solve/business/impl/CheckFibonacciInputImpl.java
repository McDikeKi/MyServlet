package org.harvey.solve.business.impl;

import org.harvey.solve.business.CheckFibonacciInput;
import org.harvey.solve.exception.IllegalInputException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.FibonacciInputCheck;

public class CheckFibonacciInputImpl implements CheckFibonacciInput{
	private static final String EXCEPTION_MESSAGE = "Illegal input for Fibonacci Problem";	
	private FibonacciInputCheck fibonacciInputCheck;

	public void setFibonacciInputCheck(FibonacciInputCheck fibonacciInputCheck) {
		this.fibonacciInputCheck = fibonacciInputCheck;
	}

	@Override
	public void check(String lengthStr) throws IllegalInputException {
		// TODO Auto-generated method stub
		try {
			fibonacciInputCheck.check(lengthStr);
		} catch (WrongValueException e) {
			throw new IllegalInputException(EXCEPTION_MESSAGE,e);
		} catch (NullValueException e) {
			// TODO Auto-generated catch block
			throw new IllegalInputException(EXCEPTION_MESSAGE,e);
		}
	}

}

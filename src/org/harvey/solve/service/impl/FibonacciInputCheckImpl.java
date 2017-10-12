package org.harvey.solve.service.impl;

import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.FibonacciInputCheck;

public class FibonacciInputCheckImpl implements FibonacciInputCheck{
	private static final String MESSAGE_NULL_VALUE_ERROR = "Input is null";
	private static final String MESSAGE_NOT_A_NUMBER_ERROR = "Input is not a integer";
	private static final String MESSAGE_NEGATIVE_NUMBER_ERROR = "Input is a negative number";
	@Override
	public void check(String lengthStr) throws WrongValueException, NullValueException {
		Integer length;
		if("".equals(lengthStr)){
			throw new NullValueException(MESSAGE_NULL_VALUE_ERROR);
		}
		try {  
	        length = Integer.parseInt(lengthStr);  
	    } catch (NumberFormatException e) {  
	        throw new WrongValueException(MESSAGE_NOT_A_NUMBER_ERROR,e);
	    }
		if(length<0){
			throw new WrongValueException(MESSAGE_NEGATIVE_NUMBER_ERROR);
		}
	}

}

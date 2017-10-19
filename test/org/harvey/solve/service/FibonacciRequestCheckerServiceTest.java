package org.harvey.solve.service;

import static org.junit.Assert.*;

import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.impl.FibonacciRequestCheckerServiceImpl;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FibonacciRequestCheckerServiceTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	private static FibonacciRequestCheckerService fibonacciRequestCheckerService;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		fibonacciRequestCheckerService = new FibonacciRequestCheckerServiceImpl();
	}

	@Test
	public void testThrowNullValueExceptionForLengthStrIsNull() throws WrongValueException, NullValueException {
		String lengthStr = null;
		thrown.expect(NullValueException.class);
		thrown.expectMessage("Input is null");
		fibonacciRequestCheckerService.check(lengthStr);
	}
	
	@Test
	public void testThrowNullValueExceptionForLengthStrIsEmpty() throws WrongValueException, NullValueException {
		String lengthStr = "";
		thrown.expect(NullValueException.class);
		thrown.expectMessage("Input is null");
		fibonacciRequestCheckerService.check(lengthStr);
	}
	
	@Test
	public void testWrongValueExceptionForLengthStrNotANumber() throws WrongValueException, NullValueException {
		String lengthStr = "a1";
		thrown.expect(WrongValueException.class);
		thrown.expectMessage("Input is not a integer");
		fibonacciRequestCheckerService.check(lengthStr);
	}
	
	@Test
	public void testWrongValueExceptionForLengthLessThanZero() throws WrongValueException, NullValueException {
		String lengthStr = "-1";
		thrown.expect(WrongValueException.class);
		thrown.expectMessage("Input is a negative number");
		fibonacciRequestCheckerService.check(lengthStr);
	}
	
	@Test
	public void testLegalInput() {
		String lengthStr = "2";
		try {
			fibonacciRequestCheckerService.check(lengthStr);
		} catch (WrongValueException e) {
			fail("expected no Exception");
		} catch (NullValueException e) {
			fail("expected no Exception");
		}
	}

}

package org.harvey.solve.business;

import static org.mockito.Mockito.*;
import org.harvey.solve.business.impl.FibonacciRequestCheckerBusinessImpl;
import org.harvey.solve.causematcher.ExceptionCauseMatcher;
import org.harvey.solve.exception.IllegalInputException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.FibonacciRequestCheckerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FibonacciRequestCheckerBusinessTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@InjectMocks
	private FibonacciRequestCheckerBusiness business = new FibonacciRequestCheckerBusinessImpl();
 
	@Mock
	private FibonacciRequestCheckerService service;

	@Before
    public void setUp() { 
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testThrowIllegalInputExceptionForNullValueException() throws IllegalInputException, WrongValueException, NullValueException {
		String lengthStr = null;
		String errorMessage = "Input is null";
		when(service.check(lengthStr)).thenThrow(new NullValueException(errorMessage));
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage("Illegal input for Fibonacci Problem");
		thrown.expectCause(new ExceptionCauseMatcher(NullValueException.class, errorMessage));
		business.check(lengthStr);
		verify(service).check(lengthStr);
	}
	
	@Test
	public void testThrowIllegalInputExceptionForWrongValueException() throws IllegalInputException, WrongValueException, NullValueException {
		String lengthStr = "a";
		String errorMessage = "Input is not a integer";
		when(service.check(lengthStr)).thenThrow(new WrongValueException(errorMessage));
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage("Illegal input for Fibonacci Problem");
		thrown.expectCause(new ExceptionCauseMatcher(WrongValueException.class, errorMessage));
		business.check(lengthStr);
		verify(service).check(lengthStr);
	}
}

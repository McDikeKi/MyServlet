package org.harvey.solve.business;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.harvey.solve.business.impl.JosephRequestCheckerBusinessImpl;
import org.harvey.solve.causematcher.ExceptionCauseMatcher;
import org.harvey.solve.dto.JosephProblemCircle;
import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.exception.IllegalInputException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.JosephRequestCheckerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

public class JosephRequestCheckerBusinessTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@InjectMocks
	private JosephRequestCheckerBusiness business = new JosephRequestCheckerBusinessImpl();
 
	@Mock
	private JosephRequestCheckerService service;

	@Before
    public void setUp() { 
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testThrowIllegalInputExceptionForNullValueException() throws IllegalInputException, WrongValueException, NullValueException {
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		persons.add("c");
		JosephProblemCircle circle = new JosephProblemCircle(persons, null, 2);
		JosephProblemRequest request = new JosephProblemRequest(circle);
		
		String errorMessage = "Start is null - circle.start";
		when(service.check(request)).thenThrow(new NullValueException(errorMessage));
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage("Illegal input for Joseph Problem");
		thrown.expectCause(new ExceptionCauseMatcher(NullValueException.class, errorMessage));
		business.check(request);
		verify(service).check(request);
	}
	
	@Test
	public void testThrowIllegalInputExceptionForWrongValueException() throws IllegalInputException, WrongValueException, NullValueException {
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		persons.add("c");
		JosephProblemCircle circle = new JosephProblemCircle(persons, 5, 2);
		JosephProblemRequest request = new JosephProblemRequest(circle);
		
		String errorMessage = "Start index out of bounds - circle.start";
		when(service.check(request)).thenThrow(new WrongValueException(errorMessage));
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage("Illegal input for Joseph Problem");
		thrown.expectCause(new ExceptionCauseMatcher(WrongValueException.class, errorMessage));
		business.check(request);
		verify(service,new Times(1)).check(request);
	}
}

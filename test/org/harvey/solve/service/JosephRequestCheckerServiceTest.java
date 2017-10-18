package org.harvey.solve.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.harvey.solve.dto.JosephProblemCircle;
import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.exception.JsonFieldWrongTypeException;
import org.harvey.solve.exception.NullValueException;
import org.harvey.solve.exception.WrongValueException;
import org.harvey.solve.service.impl.JosephRequestCheckerServiceImpl;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JosephRequestCheckerServiceTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	private static JosephRequestCheckerService josephRequestCheckerService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		josephRequestCheckerService = new JosephRequestCheckerServiceImpl();
	}

	@Test
	public void checkThrowNullValueExceptionForRequestIsNull() throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		JosephProblemRequest request = null;
		thrown.expect(NullValueException.class);
		thrown.expectMessage("Request is null");
		josephRequestCheckerService.check(request);
	}
	
	@Test
	public void checkThrowNullValueExceptionForCircleIsNull() throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		JosephProblemRequest request = new JosephProblemRequest();
		JosephProblemCircle circle = null;
		request.setCircle(circle);
		thrown.expect(NullValueException.class);
		thrown.expectMessage("Circle is null - circle");
		josephRequestCheckerService.check(request);
	}
	
	@Test
	public void checkThrowNullValueExceptionForPersonsIsNull() throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		JosephProblemRequest request = new JosephProblemRequest();
		JosephProblemCircle circle = new JosephProblemCircle(null, 1, 1);
		request.setCircle(circle);
		thrown.expect(NullValueException.class);
		thrown.expectMessage("Persons is null - circle.persons");
		josephRequestCheckerService.check(request);
	}
	
	@Test
	public void checkThrowNullValueExceptionForStartIsNull() throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		JosephProblemRequest request = new JosephProblemRequest();
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		JosephProblemCircle circle = new JosephProblemCircle(persons, null, 1);
		request.setCircle(circle);
		thrown.expect(NullValueException.class);
		thrown.expectMessage("Start is null - circle.start");
		josephRequestCheckerService.check(request);
	}
	
	@Test
	public void checkThrowNullValueExceptionForIntervalIsNull() throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		JosephProblemRequest request = new JosephProblemRequest();
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		JosephProblemCircle circle = new JosephProblemCircle(persons, 1, null);
		request.setCircle(circle);
		thrown.expect(NullValueException.class);
		thrown.expectMessage("Interval is null - circle.interval");
		josephRequestCheckerService.check(request);
	}
	
	@Test
	public void checkWrongValueExceptionForStartIsLessThanZero() throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		JosephProblemRequest request = new JosephProblemRequest();
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		JosephProblemCircle circle = new JosephProblemCircle(persons,-2, 1);
		request.setCircle(circle);
		thrown.expect(WrongValueException.class);
		thrown.expectMessage("Wrong input for start - circle.start");
		josephRequestCheckerService.check(request);
	}
	
	@Test
	public void checkWrongValueExceptionForIntervalIsLessThanOne() throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		JosephProblemRequest request = new JosephProblemRequest();
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		JosephProblemCircle circle = new JosephProblemCircle(persons,0, 0);
		request.setCircle(circle);
		thrown.expect(WrongValueException.class);
		thrown.expectMessage("Wrong input for interval - circle.interval");
		josephRequestCheckerService.check(request);
	}
	
	@Test
	public void checkWrongValueExceptionForStartLargerThanListSize() throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		JosephProblemRequest request = new JosephProblemRequest();
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		JosephProblemCircle circle = new JosephProblemCircle(persons,2, 1);
		request.setCircle(circle);
		thrown.expect(WrongValueException.class);
		thrown.expectMessage("Start index out of bounds - circle.start");
		josephRequestCheckerService.check(request);
	}

	@Test
	public void checkWrongValueExceptionForIntervalLargerThanListSize() throws NullValueException, WrongValueException, JsonFieldWrongTypeException {
		JosephProblemRequest request = new JosephProblemRequest();
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		JosephProblemCircle circle = new JosephProblemCircle(persons,0,3);
		request.setCircle(circle);
		thrown.expect(WrongValueException.class);
		thrown.expectMessage("Interval out of bounds - circle.interval");
		josephRequestCheckerService.check(request);
	}
	
	@Test
	public void checkLegalInput() {
		JosephProblemRequest request = new JosephProblemRequest();
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		JosephProblemCircle circle = new JosephProblemCircle(persons,0,2);
		request.setCircle(circle);
		try {
			josephRequestCheckerService.check(request);
		} catch (NullValueException e) {
			fail("expected no exception");
		} catch (WrongValueException e) {
			fail("expected no exception");
		}
	}
}

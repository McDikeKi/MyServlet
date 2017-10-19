package org.harvey.solve.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.harvey.solve.business.impl.JosephProblemSolverBusinessImpl;
import org.harvey.solve.dto.JosephProblemCircle;
import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.dto.JosephProblemResponse;
import org.harvey.solve.service.JosephProblemSolverService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

public class JosephProblemSolverBusinessTest {
	@InjectMocks
	private JosephProblemSolverBusiness business = new JosephProblemSolverBusinessImpl();
 
	@Mock
	private JosephProblemSolverService service;

	@Before
    public void setUp() { 
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testSolve() {
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		persons.add("c");
		JosephProblemCircle circle = new JosephProblemCircle(persons, 0, 2);
		JosephProblemRequest request = new JosephProblemRequest(circle);
		
		String result = "c";
		when(service.solve(persons, 0, 2)).thenReturn(result);
		
		JosephProblemResponse response = business.solve(request);
		verify(service,new Times(1)).solve(persons, 0, 2); 
		
		assertEquals(result, response.getPerson());
	}

}

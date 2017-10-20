package org.harvey.solve.business;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

import org.harvey.solve.business.impl.FibonacciProblemSolverBusinessImpl;
import org.harvey.solve.service.FibonacciProblemSolverService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FibonacciProblemSolverBusinessTest {
	@InjectMocks
	private FibonacciProblemSolverBusiness business = new FibonacciProblemSolverBusinessImpl();
 
	@Mock
	private FibonacciProblemSolverService service;

	@Before
    public void setUp() { 
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testSolve() {
		int length = 2;
		List<BigDecimal> result = new ArrayList<>();
		result.add(new BigDecimal(0));
		result.add(new BigDecimal(1));
		
		when(service.getSequenceResult(length)).thenReturn(result);
		
		List<BigDecimal> actualResult = business.solve(length);
		verify(service).getSequenceResult(length); 
		
		assertEquals(new BigDecimal(0), actualResult.get(0));
		assertEquals(new BigDecimal(1), actualResult.get(1));
	}
}

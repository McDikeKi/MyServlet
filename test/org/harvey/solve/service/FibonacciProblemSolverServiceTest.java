package org.harvey.solve.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.harvey.solve.service.FibonacciProblemSolverService;
import org.harvey.solve.service.impl.FibonacciProblemSolverServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

public class FibonacciProblemSolverServiceTest {
	private static FibonacciProblemSolverService fibonacciProblemSolverService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fibonacciProblemSolverService = new FibonacciProblemSolverServiceImpl();
	}

	@Test
	public void testGetSequenceResult() {
		int length = 1;
		List<BigDecimal> resultList = fibonacciProblemSolverService.getSequenceResult(length);
		
		assertNotNull(resultList);		
	}
	
	@Test
	public void testGetSequenceResultLength() {
		int length;
		
		length = 1;
		assertEquals(length,fibonacciProblemSolverService.getSequenceResult(length).size());
		
		length = 10;
		assertEquals(length,fibonacciProblemSolverService.getSequenceResult(length).size());
		
		length = 99;
		assertEquals(length,fibonacciProblemSolverService.getSequenceResult(length).size());
		
	}
	
	@Test
	public void testGetSequenceResultFinalElement() {		
		int requestLength;
		List<BigDecimal> resultList;
		int resultLength;
		BigDecimal lastElement;
		
		requestLength = 1;
		resultList = fibonacciProblemSolverService.getSequenceResult(requestLength);
		resultLength = resultList.size();
		lastElement = resultList.get(resultLength-1); 
		
		assertEquals(new BigDecimal(0),lastElement);
		
		requestLength = 2;
		resultList = fibonacciProblemSolverService.getSequenceResult(requestLength);
		resultLength = resultList.size();
		lastElement = resultList.get(resultLength-1); 
		
		assertEquals(new BigDecimal(1),lastElement);
		
		requestLength = 10;
		resultList = fibonacciProblemSolverService.getSequenceResult(requestLength);
		resultLength = resultList.size();
		lastElement = resultList.get(resultLength-1); 
		
		assertEquals(new BigDecimal(34),lastElement);
		
	}

}

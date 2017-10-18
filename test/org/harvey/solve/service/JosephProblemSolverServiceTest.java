package org.harvey.solve.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.harvey.solve.service.JosephProblemSolverService;
import org.harvey.solve.service.impl.JosephProblemSolverServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

public class JosephProblemSolverServiceTest {
	private static JosephProblemSolverService josephRequestSolverService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		josephRequestSolverService = new JosephProblemSolverServiceImpl();
	}

	@Test
	public void testSolve() {
		List<String> circle = new ArrayList<>();
		int startIndex;
		int interval;
		String result;
		
		circle.add("a");
		circle.add("bb");
		circle.add("ccc");
		circle.add("dddd");
		circle.add("eeeee");
		circle.add("ffffff");
		
		startIndex = 0;
		interval = 1;	
		result = josephRequestSolverService.solve(circle, startIndex, interval);
		assertEquals("ffffff",result);
		
		startIndex = 2;
		interval = 5;
		result = josephRequestSolverService.solve(circle, startIndex, interval);
		assertEquals("ccc",result);
		
		startIndex = 5;
		interval = 8;
		result = josephRequestSolverService.solve(circle, startIndex, interval);
		assertEquals("bb",result);		
	}

}

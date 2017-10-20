package org.harvey.solve.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.harvey.solve.business.FibonacciProblemSolverBusiness;
import org.harvey.solve.business.FibonacciRequestCheckerBusiness;
import org.harvey.solve.exception.IllegalInputException;
import org.harvey.solve.exception.WrongValueException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class FibonacciProblemControllerTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private FibonacciProblemController controller = new FibonacciProblemController();
	
	@Mock
	private FibonacciProblemSolverBusiness fibonacciProblemSolverBusiness;
	
	@Mock
	private FibonacciRequestCheckerBusiness fibonacciRequestCheckerBusiness;
	
	@Before
    public void setUp() { 
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
	
	@Test
	public void testFibonacciProblemInput() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/FibonacciProblem/ProblemInput"))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.view()
		        		.name("FibonacciInput"))
		        .andReturn();
	}

	@Test
	public void testFinbonacciProblemSolveNoException() throws Exception {
		String lengthStr = "2"; 
		List<BigDecimal> resultList = new ArrayList<>();
		resultList.add(new BigDecimal(0));
		resultList.add(new BigDecimal(1));
		//String jsonStr = "{\"circle\":{\"start\":0,\"interval\":2,\"persons\":[\"a\",\"c\",\"b\"]}}";
		when(fibonacciProblemSolverBusiness.solve(Integer.valueOf(lengthStr)))
				.thenReturn(resultList);
		when(fibonacciRequestCheckerBusiness.check(lengthStr))
				.thenReturn(true);
		
		@SuppressWarnings("unchecked")
		List<BigDecimal> actualResultList = (List<BigDecimal>) mockMvc.perform(MockMvcRequestBuilders.get("/FibonacciProblem/ProblemSolve").param("length", lengthStr))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("FibonacciResult"))
		        .andReturn().getModelAndView().getModelMap().get("list");

		assertEquals(new BigDecimal(0), actualResultList.get(0));
		assertEquals(new BigDecimal(1), actualResultList.get(1));
	}
	
	@Test
	public void testFinbonacciProblemSolveWithException() throws Exception {
		String lengthStr = "-1";
		List<BigDecimal> resultList = new ArrayList<>();
		
		when(fibonacciProblemSolverBusiness.solve(Integer.valueOf(lengthStr)))
				.thenReturn(resultList);
		when(fibonacciRequestCheckerBusiness.check(lengthStr))
				.thenThrow(new IllegalInputException("Illegal input for Fibonacci Problem",new WrongValueException("Input is a negative number")));
		
		String errorMessage = (String) mockMvc.perform(MockMvcRequestBuilders.get("/FibonacciProblem/ProblemSolve").param("length", lengthStr))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("ErrorPage"))
		        .andReturn().getModelAndView().getModelMap().get("errorMessage");

		assertEquals("Illegal input for Fibonacci Problem - Input is a negative number", errorMessage);
	}

}

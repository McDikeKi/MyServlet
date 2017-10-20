package org.harvey.solve.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.harvey.solve.business.JosephProblemSolverBusiness;
import org.harvey.solve.business.JosephRequestCheckerBusiness;
import org.harvey.solve.dto.JosephProblemCircle;
import org.harvey.solve.dto.JosephProblemInputError;
import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.dto.JosephProblemResponse;
import org.harvey.solve.exception.IllegalInputException;
import org.harvey.solve.exception.NullValueException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springmvc-servlet.xml"})
public class JosephProblemControllerTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private JosephProblemController controller = new JosephProblemController();
	
	@Mock
	private JosephProblemSolverBusiness josephProblemSolverBusiness;
	
	@Mock
	private JosephRequestCheckerBusiness josephRequestCheckerBusiness;
	
	@Before
    public void setUp() { 
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
	@Test
	public void testJosephProblemInput() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/JosephProblem/ProblemInput"))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.view().name("JosephInput"));
	}

	@Test
	public void testJosephProblemSolveWithLegalRequest() throws Exception {
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		persons.add("c");
		JosephProblemCircle circle = new JosephProblemCircle(persons, 0, 2);
		JosephProblemRequest request = new JosephProblemRequest(circle);
		JosephProblemResponse response = new JosephProblemResponse("c", null);
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		String requestStr = gson.toJson(request);
		String responseStr = gson.toJson(response);

		when(josephProblemSolverBusiness.solve(Mockito.any(JosephProblemRequest.class)))
				.thenReturn(response);
		when(josephRequestCheckerBusiness.check(request))
				.thenReturn(true);
		String actualResponseStr = mockMvc.perform(MockMvcRequestBuilders.post("/JosephProblem/ProblemSolve")
				.contentType(MediaType.APPLICATION_JSON).content(requestStr))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andReturn().getResponse().getContentAsString();
		
		assertEquals(responseStr, actualResponseStr);
	}
	
	@Test
	public void testJosephProblemSolveWithIllegalRequest() throws Exception {
		List<String> persons = new ArrayList<>();
		persons.add("a");
		persons.add("b");
		persons.add("c");
		List<JosephProblemInputError> errors = new ArrayList<>();
		errors.add(new JosephProblemInputError("can't be null", "circle.start"));
		errors.add(new JosephProblemInputError("can't be null","circle"));
		JosephProblemCircle circle = new JosephProblemCircle(null,null, 2);
		JosephProblemRequest request = new JosephProblemRequest(circle);
		JosephProblemResponse response = new JosephProblemResponse(null,errors);
		
		Gson gson = new GsonBuilder().setPrettyPrinting()
			    .disableHtmlEscaping().serializeNulls().create();
		String requestStr = gson.toJson(request);
		String responseStr = gson.toJson(response);

		when(josephProblemSolverBusiness.solve(Mockito.any(JosephProblemRequest.class)))
				.thenReturn(response);
		when(josephRequestCheckerBusiness.check(request))
				.thenThrow(new IllegalInputException("Illegal input for Joseph Problem"
								,new NullValueException("Persons is null - circle.persons")));
		String actualResponseStr = mockMvc.perform(MockMvcRequestBuilders.post("/JosephProblem/ProblemSolve")
				.contentType(MediaType.APPLICATION_JSON).content(requestStr))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andReturn().getResponse().getContentAsString();
		
		assertEquals(responseStr, actualResponseStr);
		
		persons = new ArrayList<>();
		persons.add("a");
		persons.add("a");
		persons.add("c");
		errors = new ArrayList<>();
		errors.add(new JosephProblemInputError("can't have same names", "circle.persons"));
		errors.add(new JosephProblemInputError("start can't be lager than list size","circle.start"));
		circle = new JosephProblemCircle(persons,5, 2);
		request = new JosephProblemRequest(circle);
		response = new JosephProblemResponse(null,errors);
		
		requestStr = gson.toJson(request);
		responseStr = gson.toJson(response);
		
		when(josephProblemSolverBusiness.solve(Mockito.any(JosephProblemRequest.class)))
				.thenReturn(response);
		when(josephRequestCheckerBusiness.check(request))
				.thenThrow(new IllegalInputException("Illegal input for Joseph Problem"
								,new NullValueException("Start index out of bounds - circle.start")));
		actualResponseStr = mockMvc.perform(MockMvcRequestBuilders.post("/JosephProblem/ProblemSolve")
				.contentType(MediaType.APPLICATION_JSON).content(requestStr))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andReturn().getResponse().getContentAsString();
		
		assertEquals(responseStr, actualResponseStr);	
	}

}

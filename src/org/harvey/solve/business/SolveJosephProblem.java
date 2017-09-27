package org.harvey.solve.business;


import org.harvey.solve.dto.Request;
import org.harvey.solve.dto.Response;
import org.harvey.solve.service.JosephProblemFunction;

public class SolveJosephProblem {
	
	public static Response solve(Request request){	
		String result = JosephProblemFunction.getFinalElement(request.getCircle().getPersons() ,
				Integer.valueOf(request.getCircle().getStart()) , Integer.valueOf(request.getCircle().getInterval()));
		return new Response(result);
	}
	
}

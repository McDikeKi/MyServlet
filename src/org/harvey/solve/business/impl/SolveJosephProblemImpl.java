package org.harvey.solve.business.impl;


import org.harvey.solve.business.SolveJosephProblem;
import org.harvey.solve.dto.Request;
import org.harvey.solve.dto.Response;
import org.harvey.solve.service.JosephProblemFunction;

public class SolveJosephProblemImpl implements SolveJosephProblem{
	private JosephProblemFunction josephProblemFunction;
	
	public void setJosephProblemFunction(JosephProblemFunction josephProblemFunction) {
		this.josephProblemFunction = josephProblemFunction;
	}

	public Response solve(Request request){
		String result = josephProblemFunction.getFinalElement(request.getCircle().getPersons() ,
				Integer.valueOf(request.getCircle().getStart()) , Integer.valueOf(request.getCircle().getInterval()));
		return new Response(result);
	}
}

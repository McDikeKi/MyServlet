package org.harvey.solve.business.businessimpl;


import org.harvey.solve.business.SolveJosephProblem;
import org.harvey.solve.dto.Request;
import org.harvey.solve.dto.Response;
import org.harvey.solve.service.JosephProblemFunction;
import org.harvey.solve.service.serviceimpl.JosephProblemFunctionImpl;

public class SolveJosephProblemImpl implements SolveJosephProblem{
	public Response solve(Request request){
		JosephProblemFunction josephProblemFunction = new JosephProblemFunctionImpl();
		String result = josephProblemFunction.getFinalElement(request.getCircle().getPersons() ,
				Integer.valueOf(request.getCircle().getStart()) , Integer.valueOf(request.getCircle().getInterval()));
		return new Response(result);
	}
}

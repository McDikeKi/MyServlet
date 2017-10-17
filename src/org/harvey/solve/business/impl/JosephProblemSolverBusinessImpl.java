package org.harvey.solve.business.impl;


import org.harvey.solve.business.JosephProblemSolverBusiness;
import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.dto.JosephProblemResponse;
import org.harvey.solve.service.JosephProblemSolverService;

public class JosephProblemSolverBusinessImpl implements JosephProblemSolverBusiness{
	private JosephProblemSolverService josephProblemSolverService;


	public void setJosephProblemSolverService(JosephProblemSolverService josephProblemSolverService) {
		this.josephProblemSolverService = josephProblemSolverService;
	}

	public JosephProblemResponse solve(JosephProblemRequest request){
		String result = josephProblemSolverService.solve(request.getCircle().getPersons() ,
				Integer.valueOf(request.getCircle().getStart()) , Integer.valueOf(request.getCircle().getInterval()));
		return new JosephProblemResponse(result,null);
	}
}

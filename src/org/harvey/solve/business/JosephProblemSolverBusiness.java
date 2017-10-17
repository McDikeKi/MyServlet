package org.harvey.solve.business;

import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.dto.JosephProblemResponse;

public interface JosephProblemSolverBusiness {
	public JosephProblemResponse solve(JosephProblemRequest request);
}

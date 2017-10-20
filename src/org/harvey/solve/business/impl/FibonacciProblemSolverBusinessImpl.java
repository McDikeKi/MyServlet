package org.harvey.solve.business.impl;

import java.math.BigDecimal;
import java.util.List;

import org.harvey.solve.business.FibonacciProblemSolverBusiness;
import org.harvey.solve.service.FibonacciProblemSolverService;

public class FibonacciProblemSolverBusinessImpl implements FibonacciProblemSolverBusiness{
	private FibonacciProblemSolverService fibonacciProblemSolverService;
	
	public FibonacciProblemSolverBusinessImpl() {
		this.fibonacciProblemSolverService = null;
	}
	
	public FibonacciProblemSolverBusinessImpl(FibonacciProblemSolverService fibonacciProblemSolverService) {
		this.fibonacciProblemSolverService = fibonacciProblemSolverService;
	}

	public void setFibonacciProblemSolverService(FibonacciProblemSolverService fibonacciProblemSolverService) {
		this.fibonacciProblemSolverService = fibonacciProblemSolverService;
	}

	@Override
	public List<BigDecimal> solve(int length) {
		return fibonacciProblemSolverService.getSequenceResult(length);
	}

}

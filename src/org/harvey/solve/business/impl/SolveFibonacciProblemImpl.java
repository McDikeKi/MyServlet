package org.harvey.solve.business.impl;

import java.math.BigDecimal;
import java.util.List;

import org.harvey.solve.business.SolveFibonacciProblem;
import org.harvey.solve.service.FibonacciSequenceFunction;

public class SolveFibonacciProblemImpl implements SolveFibonacciProblem{
	private FibonacciSequenceFunction fibonacciSequenceFunction;

	public void setFibonacciSequenceFunction(FibonacciSequenceFunction fibonacciSequenceFunction) {
		this.fibonacciSequenceFunction = fibonacciSequenceFunction;
	}

	@Override
	public List<BigDecimal> Solve(int length) {
		return fibonacciSequenceFunction.getSequenceResult(length);
	}

}

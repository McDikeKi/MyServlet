package org.harvey.solve.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.harvey.solve.service.FibonacciProblemSolverService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author haiwei.jia
 * 
 *         This is the class created to solve the fibonacci sequence problem
 * 
 */

@Service
public class FibonacciProblemSolverServiceImpl implements FibonacciProblemSolverService {
	private static final BigDecimal firstElement = new BigDecimal(0);
	private static final BigDecimal secondElement = new BigDecimal(1);;
	private final int ZERO = 0;
	private final int FIRST_LENGTH = 1;
	private final int SECOND_LENGTH = 2;
	/**
	 * This is the algorithm of the fibonacci sequence problem @param num: The
	 * length of the fibonacci sequence @return a list that contains the
	 * fibonacci sequence @exception
	 */
	
	public List<BigDecimal> getSequenceResult(int num) {
		

		List<BigDecimal> list = new ArrayList<>();
		if (num == ZERO) {
		}
		if (num == FIRST_LENGTH) {
			list.add(firstElement);
		} else if (num == SECOND_LENGTH) {
			list.add(firstElement);
			list.add(secondElement);
		} else {
			list.add(firstElement);
			list.add(secondElement);
			BigDecimal previous0 = firstElement;
			BigDecimal previous1 = secondElement;
			BigDecimal current;
			for (int i = 1; i < num - 1; i++) {
				current = previous0.add(previous1);
				list.add(current);
				previous0 = previous1;
				previous1 = current;
			}
		}
		return list;
	}
}

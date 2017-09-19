package com.harvey.solve.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author haiwei.jia
 * 
 * This is the class created to solve the fibonacci sequence problem
 * 
 */
public class FibonacciSequenceFunction{
	
	/**
	 * This is the algorithm of the fibonacci sequence problem
	 * @param num: The length of the fibonacci sequence
	 * @return a list that contains the fibonacci sequence
	 * @exception
	 */
	public static List<BigDecimal> getSequenceResult(int num){
		final int ZERO = 0;
		final int FIRST_NUM = 1;
		final BigDecimal FIRST_VALUE = new BigDecimal(0);
		final int SECOND_NUM = 2;
		final BigDecimal SECOND_VALUE = new BigDecimal(1);
		
		List<BigDecimal> list = new ArrayList<>();
		if(num==ZERO){
		}
		if(num==FIRST_NUM){
			list.add(FIRST_VALUE);
		}
		else if(num==SECOND_NUM){
			list.add(FIRST_VALUE);
			list.add(SECOND_VALUE);
		}
		else{
			list.add(FIRST_VALUE);
			list.add(SECOND_VALUE);
			BigDecimal previous0 = FIRST_VALUE;
			BigDecimal previous1 = SECOND_VALUE;
			BigDecimal current;
			for(int i = 1;i<num-1;i++){
				current = previous0.add(previous1);
				list.add(current);
				previous0 = previous1;
				previous1 = current;
			}
		}
		return list;
	}
}

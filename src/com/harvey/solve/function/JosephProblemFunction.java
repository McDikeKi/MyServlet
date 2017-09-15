package com.harvey.solve.function;

import java.util.List;


/**
 * 
 * @author haiwei.jia
 * 
 * This is the class created to solve the joseph problem
 * 
 */
public class JosephProblemFunction{
	 /**
	 /* This is the algorithm of the joseph problem
	 * @param circle: the String of the names (separated by spaces)
	 * @param startIndex: the start index
	 * @param interval: the interval
	 * @return the last person's name
	 */
	public static String getFinalElement(List<String> list, int startIndex,
			int interval) {
		
		final int FINAL_SIZE = 1;
		final int FINAL_INDEX = 0;
		int index = startIndex;
		
		while (list.size() > FINAL_SIZE) {
			int currentindex = index + interval - 1;
			index = (index + interval) % list.size();
			if (index != 0) {
				index -= 1;
			}
			list.remove(currentindex % list.size());
		}
		return list.get(FINAL_INDEX);
	}
}

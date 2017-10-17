package org.harvey.solve.service.impl;

import java.util.List;

import org.harvey.solve.linkedlist.DataNode;
import org.harvey.solve.linkedlist.SingleLinkedList;
import org.harvey.solve.service.JosephProblemSolverService;

/**
 * 
 * @author haiwei.jia
 * 
 *         This is the class created to solve the joseph problem
 * 
 */


public class JosephProblemSolverServiceImpl implements JosephProblemSolverService {
	/**
	 * /* This is the algorithm of the joseph problem
	 * 
	 * @param circle:
	 *            the String of the names (separated by spaces)
	 * @param startIndex:
	 *            the start index
	 * @param interval:
	 *            the interval
	 * @return the last person's name
	 */
	public String solve(List<String> liStrings, int startIndex, int interval) {
		final int FINAL_SIZE = 1;
		int index = startIndex;
		
		SingleLinkedList nameList = new SingleLinkedList();
		for (String li : liStrings) {
			nameList.append(li);
		}
		DataNode pointer = nameList.getHead();
		for (int i = 0; i < index; i++) {
			pointer = pointer.next();
		}
		while (nameList.length() > FINAL_SIZE) {
			for (int i = 0; i < interval - 1; i++) {
				pointer = pointer.next();
			}
			DataNode node = pointer;
			pointer = pointer.next();
			nameList.remove(node);
		}
		return nameList.getHead().getName();
	}
}

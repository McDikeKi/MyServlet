package org.harvey.solve.LinkedList;

public class DataNode {
	private String name;
	private DataNode next;	
	
	public DataNode() {
		this.name = null;
		this.next = null;
	}
	
	public DataNode(String name, DataNode next) {
		this.name = name;
		this.next = next;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataNode getNext() {
		return next;
	}
	public void setNext(DataNode next) {
		this.next = next;
	}	
	public DataNode next() {
		return next;
	}
}

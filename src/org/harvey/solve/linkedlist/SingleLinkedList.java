package org.harvey.solve.linkedlist;

public class SingleLinkedList {
	private DataNode head;
	private int length;

	public SingleLinkedList() {
		this.head = null;
		length = 0;
	}

	public int length(){
		return length;
	}
	
	public int getLength(){
		return length;
	}
	
	public void setLength(int length){
		this.length = length;
	}
	
	public DataNode getHead() {
		return head;
	}
	
	public void setHead(DataNode head){
		this.head = head;
	}
	
	public void append(String name){
		if(head == null){
			DataNode node = new DataNode();
			node.setName(name);
			head = node;
			node.setNext(node);
		}
		else{
			DataNode pointer = head;
			while(pointer.getNext()!=head){
				pointer = pointer.next();
			}
			DataNode node = new DataNode();
			node.setName(name);
			pointer.setNext(node);
			node.setNext(head);
		}
		length++;
	}
	
	public void remove(DataNode node){
		if(length!=0){
			DataNode pointer = head;
			while(pointer.getNext()!=node){
				pointer = pointer.next();
				if(pointer == head)
					return;
			}
			if(length == 1){
				head = null;
				length--;
			}
			else{
				if(pointer.next()==head){
					head = head.next();
				}
				pointer.setNext(pointer.next().next());
				length--;
			}
		}
	}
	
}

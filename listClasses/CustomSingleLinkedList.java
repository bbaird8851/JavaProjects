package listClasses;

import java.util.Iterator;

public class CustomSingleLinkedList<E>{
	
	class  Node {
		private E data;
		private Node next;
		
		public Node(E data) {
			this.data = data;
			this.next = null;
		}
		
		public E getData() {
			return data;
		}
	}
	private Node head;
	private Node tail;
	private int size;
	
	public CustomSingleLinkedList() {
		head = tail = null;
		size = 0;
	}
	
	public boolean addToHead(E data) {
		Node newNode = new Node(data);
		if(head == null) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		size++;
		return true;
	}
	
	public boolean addToTail(E data) {
		Node newNode = new Node(data);
		if(tail == null) {
			head = tail = newNode;
		} else {
			Node prev = head;
			while(prev.next != null) {
				prev = prev.next;
			}
			prev = newNode;
		}
		size++;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		Node curr = head;
		while(curr != null) {
			str.append("\"").append(curr.data).append("\"").append(" ");
			curr = curr.next;
		}
		return str.toString();
	}
	
	public static void main(String[] args) {
		CustomSingleLinkedList<Integer> numList = new CustomSingleLinkedList<>();
		numList.addToTail(1);
		numList.addToTail(2);
		numList.addToTail(3);
		
		System.out.println(numList.toString());
	}

}

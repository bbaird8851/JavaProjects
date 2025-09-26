package listClasses;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;

public class BasicLinkedList<T> implements Iterable <T>{
	class Node{
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
		}
	}
	
	protected Node head;
	protected Node tail;
	protected int size;
	
	public BasicLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public BasicLinkedList<T> addToEnd(T data){
		return this;
	}
	
	public BasicLinkedList<T> addToFront(T data){
		return this;
	}
	
	public T getFirst() {
		return head.data;
	}
	
	public T getLast() {
		return tail.data;
	}
	
	public BasicLinkedList<T> remove(T target, Comparator<T> comparator){
		return this;
	}
	
	public ArrayList<T> getReverseArrayList(){
		ArrayList<T> list = new ArrayList<>();
		return list;
	}
	
	public BasicLinkedList<T> getReverseList() {
		BasicLinkedList<T> list = new BasicLinkedList<T>();
		reverseAux(list, head);
		return list;
	}
	
	private void reverseAux(BasicLinkedList<T> list, Node curr) {
		if(curr == tail) {
			list.addToEnd(curr.data);
			return;
		} else {
			reverseAux(list, curr.next);
			list.addToEnd(curr.data);
		}
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return null;
	}
}

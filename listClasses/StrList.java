package listClasses;

import java.util.Iterator;

public class StrList <T> implements Iterable <T>{
	class Node{
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
		}
	}

	protected Node head;
	protected int size;

	public StrList() {
		head = null;
		size = 0;
	}

	public StrList<T> add(T data){
		Node curr = new Node(data);

		if(head == null) {
			head = curr;
		} else {
			curr.next = head;
			head = curr;
		} size++;
		return this;
	}

	public void reverse() {
		if (head == null) {
			return;
		}
		reverseAux(head, null);
	}

	public void reverseAux(Node curr, Node prev) {
		/* If last node mark it head*/
		if (curr.next == null) {
			head = curr;
			/* Update next to prev node */
			curr.next = prev;
			return;
		}
		Node next = curr.next;
		curr.next = prev;
		reverseAux(next, curr);
	}


	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		StrList<String> list = new StrList<>();
		list.add("PHE");list.add("PHI");list.add("PHO");list.add("PHUM");

		System.out.println(list);
		System.out.println("------------Now Reversed------------");
		list.reverse();
		System.out.println(list);
	}

	public String toString() {
		String str = "\" ";
		Node curr = head;
		while(curr != null) {
			str += curr.data + " ";
			curr = curr.next;
		}
		return str + "\"";
	}
}

package myLinkedList;

public class MyLinkedList<T extends Comparable<T>> { /* Notice the parameter */
	private class Node {
		private T data;
		private Node next;

		private Node(T data) {
			this.data = data;
			//next = null; /* do we really need to do this? */
		}
	}

	/* List head pointer */
	private Node head;

	/* We don't actually need it */
	public MyLinkedList() {
		head = null;
	}

	/* Adding at the front of the list */
	public MyLinkedList<T> add(T data) {
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;

		return this;
	}

	public String toString() {
		String result = "\" ";
		Node curr = head;

		while (curr != null) {
			result += curr.data + " ";

			curr = curr.next;
		}

		return result + "\"";
	}

	public boolean find(T target) {
		Node curr = head;

		while (curr != null) {
			if (curr.data.compareTo(target) == 0) {
				return true;
			}

			curr = curr.next;
		}

		return false;
	}

	public T getLastElement() {
		if (head == null) {
			return null;
		}

		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}

		return curr.data;
	}

	/* No insertion will take place if list empty or targetElement not found */
	public void insertElementAfter(T targetElement, T toInsert) {
		Node curr = head;

		while (curr != null) {
			if (curr.data.compareTo(targetElement) == 0) {
				Node newNode = new Node(toInsert);
				newNode.next = curr.next;
				curr.next = newNode;
				return; /* what would happen if we don't return? */
			}

			curr = curr.next;
		}
	}

	/* No insertion will take place if list empty or targetElement not found */
	public void insertElementBefore(T targetElement, T toInsert) {
		Node prev = null, curr = head;

		while (curr != null) {
			if (curr.data.compareTo(targetElement) == 0) {
				Node newNode = new Node(toInsert);
				if (curr == head) {
					newNode.next = head;
					head = newNode;
				} else {
					prev.next = newNode;
					newNode.next = curr;
				}
				return;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
	}

	public void delete(T targetElement) {
		Node prev = null, curr = head;

		while (curr != null) {
			if (curr.data.compareTo(targetElement) == 0) { //deleting something in the beginning
				if (curr == head) {
					head = head.next;
				} else { //make previous checked next checked element.
					prev.next = curr.next;
				}
				return;
			} else {//move to next element
				prev = curr;
				curr = curr.next;
			}
		}
	}

	public MyLinkedList<T> getListWithDataInBetween(T start, T end) {
		MyLinkedList<T> newList = new MyLinkedList<T>();

		if (head != null) {
			Node curr = head, last = null;

			while (curr != null) {
				//is the current name alphabetically in between the starting name and ending name?
				if (curr.data.compareTo(start) >= 0 && curr.data.compareTo(end) <= 0) {
					Node newNode = new Node(curr.data);
					if (newList.head == null) {
						newList.head = newNode;
					} else {
						last.next = newNode;
					}
					last = newNode;
				}
				curr = curr.next;
			}
		}

		return newList;
	}
}
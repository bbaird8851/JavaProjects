package listClasses;

public class LinkedList {
	protected class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}


	Node head;

	// Default constructor
	public LinkedList() {
		head = null;
	}

	// Method to remove the last element if it's equal to the first element
	public void removeLastIfEqualFirst() {
		if (head == null || head.next == null) {
			return;
		}

		// Call to the auxiliary recursive method
		removeLastIfEqualFirstAux(head, null);
	}

	// Auxiliary recursive method
	private void removeLastIfEqualFirstAux(Node current, Node prev) {
		Node first = head;// mark first node.
		
		if (current.next == null) { // We have reached the end of the list
			if (current.data == first.data) { // If the last node's data is equal to the first node's data
				prev.next = null; // Remove the last node
			}
			return;
		}

		removeLastIfEqualFirstAux(current.next, current); // Recursive call with the next node
	}

	// Method to add nodes to the list for testing
	public void add(int data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
		} else {
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
		}
	}
	
	public String toString() {
		String str = "";
		Node curr = head;
		while(curr != null) {
			str += curr.data + "|";
			curr = curr.next;
		}
		return str;
	}
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		System.out.println("Before: ");
		list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);list.add(1);
		System.out.println(list);
		System.out.println("After: ");
		list.removeLastIfEqualFirst();
		System.out.println(list);
	}

}


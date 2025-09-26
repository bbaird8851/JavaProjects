package CMSC315Project3;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryTreeInteger {

	/*
	 * Create a custom binary tree
	 * that allows the user to 
	 * define the values and assign
	 * a location to them.
	 */
	public static class Node {
		private int key;
		private Node left, right;

		public Node(int key) {
			this.key= key;
			this.left = null;
			this.right = null;
		}
	}

	private Node root;
	
	//regular constructor if already balanced
	public BinaryTreeInteger(String preOrderedString) throws TreeSyntaxException {
	    List<Integer> parts = parseTree(preOrderedString);
		root = buildTree(parts, new ArrayList<>(List.of(0)));
	}
	
	//reconstruct to a balanced tree
	public BinaryTreeInteger(List<Integer> numList) {
		Collections.sort(numList);
		root = buildBalancedTree(numList, 0, numList.size()-1);
	}


	/*
	 * (root, left, right) is the format
	 * put spaced around the brackets for
	 * extra measure then trim the input
	 */
	private List<Integer> parseTree(String input) throws TreeSyntaxException {
	    List<Integer> parts = new ArrayList<>();

		input = input.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ");
		Scanner scan = new Scanner(input.trim());

		while(scan.hasNext()) {
	        String part = scan.next();
	        if (part.equals("*")) {
	        	// "*" represents null nodes.
	            parts.add(null);
	        } else if (!part.equals("(") && !part.equals(")")) {
	            parts.add(Integer.parseInt(part));
	        }
	    }
		
		scan.close();
		return parts;
		
	}

	private Node buildTree(List<Integer> values, ArrayList<Integer> index) {
		if(index.get(0) >= values.size()) {return null;}

	    Integer value = values.get(index.get(0));
	    index.set(0, index.get(0) + 1);

	    if(value == null) {return null;}

	    // Create node and recursively build subtrees.
	    Node node = new Node(value);
	    node.left = buildTree(values, index);
	    node.right = buildTree(values, index);
	    
		return node;
	}
	
	private Node buildBalancedTree(List<Integer> values, int start, int end) {
		if(start > end) {return null;}

		int median = (start + end) / 2;
		Node node = new Node(values.get(median));
		node.left = buildBalancedTree(values, start, median - 1);
		node.right = buildBalancedTree(values, median + 1, end);

		return node;
	}
	
	public void printTree() {
		printTree(root, 0);
	}
	/*
	 * Prints tree with "   " indentation
	 * indicating the height and depth.
	 * Recursively print left and right subtrees.
	 */
	private void printTree(Node node, int depth) {
		if(node == null) {return;}

		for(int i = 0; i < depth; i++) {
			System.out.print("   ");
		}
		System.out.println(node.key);

		printTree(node.left, depth + 1);
		printTree(node.right, depth + 1);
	}


	public boolean isBST() {
		return isBSTAux(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	/*
	 * BST if the 
	 * left subtree contains values less than the node.
	 * right subtree contains values greater than the node.
	 */

	private boolean isBSTAux(Node node, int min, int max) {
		if(node == null) {return true;}
		if(node.key <= min || node.key > max) {return false;}
		
		return isBSTAux(node.left, min, node.key) &&
			   isBSTAux(node.right, node.key, max);
	}

	public boolean isBalanced() {
		return checkHeight(root) != -1;
	}

	/*
	 * A tree is balanced if the height difference
	 * between the left and the right is at most 1.
	 */
	
	private int checkHeight(Node node) {
		if(node == null) {return 0;}

		int leftHeight = checkHeight(node.left);
		if(leftHeight == -1) {return -1;}

		int rightHeight = checkHeight(node.right);
		if(rightHeight == -1) {return -1;}

		if(Math.abs(leftHeight - rightHeight) > 1) {return -1;}

		return Math.max(leftHeight, rightHeight) + 1;
	}

	public int getHeight() {
		return getHeightAux(root);
	}
	
	/*
	 * Calculate the height
	 * add recursively from left and right subtrees.
	 */
	
	private int getHeightAux(Node node) {
		if(node == null) {return -1;}
		return Math.max(getHeightAux(node.left), getHeightAux(node.right)) + 1;
	}
	
	/*
	 * Organizes the list of values from a tree.
	 */
	
	public List<Integer> getValues(){
		List<Integer> values = new ArrayList<>();
		inNumOrder(root, values);
		
		return values;
	}

	private void inNumOrder(Node node, List<Integer> values) {
		if(node == null) {return;}
		
		inNumOrder(node.left, values);
		values.add(node.key);
		inNumOrder(node.right, values);
	}
	
}
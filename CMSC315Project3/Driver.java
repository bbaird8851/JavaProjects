package CMSC315Project3;

import java.util.List;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while(true) {
			System.out.print("Enter a binary tree: ");
			String input = scan.nextLine();
			
			try {
				BinaryTreeInteger tree = new BinaryTreeInteger(input);
				tree.printTree();

				if(!tree.isBST()) {
					System.out.println("It is not a binary search tree");
					displayChange(tree);
				} else if (tree.isBalanced()) {
					System.out.println("It is a balanced binary search tree");
				} else {
					System.out.println("It is a binary search tree but it is not balanced");
					displayChange(tree);
				}
			} catch(TreeSyntaxException e) {
				System.out.println("Error: " + e.getMessage());
			}

			System.out.print("More trees? Y or N: ");

			if(!scan.nextLine().equalsIgnoreCase("Y")) break;
		}
		scan.close();
		
	}
	
	private static void displayChange(BinaryTreeInteger tree) {

		List<Integer> nodes = tree.getValues();
		BinaryTreeInteger balancedTree = new BinaryTreeInteger(nodes);

		balancedTree.printTree();
		int height = tree.getHeight();
		int balHeight = balancedTree.getHeight();

		System.out.println("Original tree has height: " + height);
		System.out.println("Balanced tree has height: " + balHeight);
	}

}

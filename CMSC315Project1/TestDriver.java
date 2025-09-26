package CMSC315Project1;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class TestDriver {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		SourceFile f1 = null;

		while(f1 == null) {
			System.out.println("Enter a fileName to read: ");
			String fileName = scan.nextLine();

			try {
				f1 = new SourceFile(fileName);
			} catch (Exception e) {
				System.out.println("file: " + fileName + " is nowhere to be found.");
			}
		}

		Stack<Character> delimStack = new Stack<>();

		try {
			char currChar;

			//while char is not null
			while((currChar = f1.getNextChar()) != '\0') {
				if(leftDelimiter(currChar)) {
					delimStack.push(currChar);
				} else if(rightDelimiter(currChar)) {
					if(delimStack.isEmpty() || !matching(delimStack.peek(), currChar)) {
						System.out.println("Mismatched delimiter: '" + currChar + "' at " + f1.getCurrPosition());
						return;
					} else {
						delimStack.pop();
					}
				}
			}

			if(!delimStack.isEmpty()) {
				System.out.println("Unmatched delimiter: '" + delimStack.peek() + "' at the end of file.");
			} else {
				System.out.println("All delimiters matched.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scan.close();
	}

	private static boolean leftDelimiter(char c) {
		return c == '(' || c == '[' || c == '{';
	}

	private static boolean rightDelimiter(char c) {
		return c == ')' || c == ']' || c == '}';
	}

	private static boolean matching(char left, char right) {
		return (left == '(' && right == ')' ||
				left == '[' && right == ']' ||
				left == '{' && right == '}' );
	}
}

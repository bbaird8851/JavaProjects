package CMSC315Project1;
import java.io.*;

public class SourceFile {
	private BufferedReader javaFileReader;
	private String currLine;
	private int lineNum;
	private int charIndex;
	
	//constructor
	public SourceFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		
		if(!file.exists()) {
			throw new FileNotFoundException("File not found: " + fileName);
		}
		javaFileReader = new BufferedReader(new FileReader(file));
		currLine = null;
		lineNum = 0;
		charIndex = -1;
	}
	
	//method that returns next char ignoring chars that are from comments or string literals

	public char getNextChar() throws IOException {
	    boolean inSingleLineComment = false;
	    boolean inMultiLineComment = false;
	    boolean inStringLiteral = false;
	    boolean inCharLiteral = false;

	    while (true) {
	        if (currLine == null || charIndex >= currLine.length() - 1) {
	            currLine = javaFileReader.readLine();
	            if (currLine == null) {
	                throw new IOException("End of file reached");
	            }
	            currLine += "\n"; // Ensure new lines are considered
	            lineNum++;
	            charIndex = -1;
	        }

	        charIndex++;
	        char currentChar = currLine.charAt(charIndex);

	        if (inSingleLineComment) {
	            if (currentChar == '\n') {
	                inSingleLineComment = false;
	            }
	            continue;
	        }

	        if (inMultiLineComment) {
	            if (currentChar == '*' && charIndex + 1 < currLine.length() && currLine.charAt(charIndex + 1) == '/') {
	                inMultiLineComment = false;
	                charIndex++; // Skip '/'
	            }
	            continue;
	        }

	        if (inStringLiteral) {
	            if (currentChar == '"' && currLine.charAt(charIndex - 1) != '\\') {
	                inStringLiteral = false;
	            }
	            continue;
	        }

	        if (inCharLiteral) {
	            if (currentChar == '\'' && currLine.charAt(charIndex - 1) != '\\') {
	                inCharLiteral = false;
	            }
	            continue;
	        }

	        // Detecting comments and literals
	        if (currentChar == '/' && charIndex + 1 < currLine.length()) {
	            char nextChar = currLine.charAt(charIndex + 1);
	            if (nextChar == '/') {
	                inSingleLineComment = true;
	                continue;
	            } else if (nextChar == '*') {
	                inMultiLineComment = true;
	                charIndex++; // Skip '*'
	                continue;
	            }
	        }

	        if (currentChar == '"') {
	            inStringLiteral = true;
	            continue;
	        }

	        if (currentChar == '\'') {
	            inCharLiteral = true;
	            continue;
	        }

	        return currentChar;
	    }
	}
	
	 public String getCurrPosition() {
	        return "Line: " + lineNum + ", Char: " + (charIndex + 1);
	    }
}

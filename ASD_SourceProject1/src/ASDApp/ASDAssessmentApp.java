package ASDApp;

import java.util.Scanner;

/**
 * Main application class for ASD Assessment Console Application
 * Entry point for the ASD assessment questionnaire system
 */
public class ASDAssessmentApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ConsoleInterface ui = new ConsoleInterface(scanner);

		System.out.println("=================================================");
		System.out.println("    ASD Assessment Questionnaire System");
		System.out.println("=================================================");
		System.out.println();

		try {
			ui.runAssessment();
		} catch (Exception e) {
			System.err.println("An unexpected error occurred: " + e.getMessage());
			System.err.println("Please restart the application and try again.");
		} finally {
			scanner.close();
		}
	}
}

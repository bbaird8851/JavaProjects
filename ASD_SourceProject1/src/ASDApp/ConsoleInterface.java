package ASDApp;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ConsoleInterface {
	private Scanner scanner;
	private Assessment assessment;

	public ConsoleInterface(Scanner scanner) {
		this.scanner = scanner;
		this.assessment = new Assessment();
	}

	/**
	 * Main method to run the complete assessment process
	 */
	public void runAssessment() {
		displayWelcomeMessage();

		if (!getConsentToProceed()) {
			System.out.println("Assessment cancelled. Thank you for your time.");
			return;
		}

		displayInstructions();
		conductQuestionnaire();
		displayResults();
	}

	/**
	 * Display welcome message and assessment overview
	 */
	private void displayWelcomeMessage() {
		System.out.println("Welcome to the Adolescent Autism Spectrum Quotient (AQ)");
		System.out.println();
		System.out.println("IMPORTANT DISCLAIMER:");
		System.out.println("This is the official AQ screening questionnaire developed by");
		System.out.println("S. Baron-Cohen et al. (2006) for identifying autism spectrum traits.");
		System.out.println("It is NOT a diagnostic instrument.");
		System.out.println("Only qualified healthcare professionals can provide a formal diagnosis.");
		System.out.println();
		System.out.println("This assessment consists of " + assessment.getTotalQuestions() + " questions");
		System.out.println("and should take approximately 10-15 minutes to complete.");
		System.out.println();
		System.out.println("NOTE: Questions are written in third-person ('S/he...')");
		System.out.println("Please answer as if describing yourself.");
		System.out.println();
	}

	/**
	 * Get user consent to proceed with assessment
	 */
	private boolean getConsentToProceed() {
		System.out.print("Do you understand and wish to proceed? (y/n): ");
		String response = scanner.nextLine().trim().toLowerCase();

		while (!response.equals("y") && !response.equals("n") && 
				!response.equals("yes") && !response.equals("no")) {
			System.out.print("Please enter 'y' for yes or 'n' for no: ");
			response = scanner.nextLine().trim().toLowerCase();
		}

		return response.equals("y") || response.equals("yes");
	}

	/**
	 * Display instructions for completing the assessment
	 */
	private void displayInstructions() {
		System.out.println();
		System.out.println("INSTRUCTIONS:");
		System.out.println("- Please answer each question honestly based on your typical experiences");
		System.out.println("- Select the response option that best describes you");
		System.out.println("- Enter the number corresponding to your choice");
		System.out.println("- You can type 'back' to return to the previous question");
		System.out.println("- Type 'quit' at any time to exit the assessment");
		System.out.println();
		System.out.println("Press Enter to begin...");
		scanner.nextLine();
		System.out.println();
	}

	/**
	 * Conduct the main questionnaire
	 */
	private void conductQuestionnaire() {
		int currentQuestionIndex = 0;

		while (currentQuestionIndex < assessment.getTotalQuestions()) {
			Question currentQuestion = assessment.getQuestions().get(currentQuestionIndex);

			displayProgressIndicator(currentQuestionIndex + 1, assessment.getTotalQuestions());
			currentQuestion.displayQuestion();

			String userInput = getUserInput();

			// Handle special commands
			if (userInput.equalsIgnoreCase("quit")) {
				System.out.println("Assessment cancelled.");
				return;
			}

			if (userInput.equalsIgnoreCase("back")) {
				if (currentQuestionIndex > 0) {
					currentQuestionIndex--;
					System.out.println("Returning to previous question...\n");
				} else {
					System.out.println("This is the first question. Cannot go back.\n");
				}
				continue;
			}

			// Validate and process numeric input
			try {
				int choice = Integer.parseInt(userInput);

				if (currentQuestion.isValidChoice(choice)) {
					assessment.recordResponse(currentQuestionIndex, choice);
					currentQuestionIndex++;
					System.out.println(); // Add spacing between questions
				} else {
					System.out.println("Invalid choice. Please select a number between 1 and " + 
							currentQuestion.getNumberOfOptions() + ".");
					System.out.println();
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number, 'back', or 'quit'.");
				System.out.println();
			}
		}

		// Complete the assessment
		if (assessment.areAllQuestionsAnswered()) {
			assessment.completeAssessment();
			System.out.println("Assessment completed successfully!");
			System.out.println();
		}
	}

	/**
	 * Display progress indicator
	 */
	private void displayProgressIndicator(int current, int total) {
		System.out.println("Progress: " + current + "/" + total);

		// Create a simple progress bar
		int progressLength = 30;
		int filledLength = (int) ((double) current / total * progressLength);

		System.out.print("[");
		for (int i = 0; i < progressLength; i++) {
			if (i < filledLength) {
				System.out.print("=");
			} else {
				System.out.print(" ");
			}
		}
		System.out.println("] " + (int)((double) current / total * 100) + "%");
		System.out.println();
	}

	/**
	 * Get user input with proper handling
	 */
	private String getUserInput() {
		System.out.print("Your choice (or 'back'/'quit'): ");
		return scanner.nextLine().trim();
	}

	/**
	 * Display the final results
	 */
	private void displayResults() {
		if (!assessment.isCompleted()) {
			System.out.println("Cannot display results - assessment not completed.");
			return;
		}

		try {
			// Calculate scores
			int totalScore = ScoreCalculator.calculateTotalScore(assessment);
			ScoreCalculator.CategoryScores categoryScores = ScoreCalculator.calculateCategoryScores(assessment);

			// Interpret results
			ResultInterpreter.AssessmentResult result = ResultInterpreter.interpretResults(totalScore, categoryScores);

			// Display results
			displayResultsHeader();
			displayScoreSummary(result);
			displayCategoryBreakdown(categoryScores);
			displayInterpretation(result);
			displayRecommendations(result);
			displayDisclaimer();

		} catch (Exception e) {
			System.err.println("Error calculating results: " + e.getMessage());
		}
	}

	/**
	 * Display results header
	 */
	private void displayResultsHeader() {
		System.out.println("=================================================");
		System.out.println("           ASSESSMENT RESULTS");
		System.out.println("=================================================");
		System.out.println();
	}

	/**
	 * Display AQ score summary
	 */
	private void displayScoreSummary(ResultInterpreter.AssessmentResult result) {
		System.out.println("AQ SCORE SUMMARY:");
		System.out.println("Total AQ Score: " + result.getTotalScore() + "/50");
		System.out.println("Autism Spectrum Trait Level: " + result.getRiskLevel());
		System.out.println();

		// Add reference information
		System.out.println("REFERENCE INFORMATION:");
		System.out.println("- Most neurotypical individuals score 0-15");
		System.out.println("- Scores of 26+ may indicate autism spectrum traits");
		System.out.println("- Scores of 32+ are strongly associated with autism spectrum conditions");
		System.out.println();
	}

	/**
	 * Display AQ category breakdown
	 */
	private void displayCategoryBreakdown(ScoreCalculator.CategoryScores categoryScores) {
		System.out.println("AQ SUBSCALE BREAKDOWN:");
		System.out.println("Social Skills: " + categoryScores.getSocialSkillsScore() + "/10");
		System.out.println("Attention Switching: " + categoryScores.getAttentionSwitchingScore() + "/10");
		System.out.println("Attention to Detail: " + categoryScores.getAttentionToDetailScore() + "/10");
		System.out.println("Communication: " + categoryScores.getCommunicationScore() + "/10");
		System.out.println("Imagination: " + categoryScores.getImaginationScore() + "/10");
		System.out.println();

		System.out.println("DETAILED SUBSCALE ANALYSIS:");
		System.out.println(ResultInterpreter.interpretCategoryScores(categoryScores));
		System.out.println();
	}

	/**
	 * Display interpretation
	 */
	private void displayInterpretation(ResultInterpreter.AssessmentResult result) {
		System.out.println("INTERPRETATION:");
		System.out.println(wrapText(result.getOverallInterpretation(), 80));
		System.out.println();
	}

	/**
	 * Display recommendations
	 */
	private void displayRecommendations(ResultInterpreter.AssessmentResult result) {
		System.out.println("RECOMMENDATIONS:");
		System.out.println(wrapText(result.getRecommendation(), 80));
		System.out.println();
	}

	/**
	 * Display final disclaimer
	 */
	private void displayDisclaimer() {
		System.out.println("=================================================");
		System.out.println("IMPORTANT REMINDER:");
		System.out.println("This screening tool is not a substitute for professional");
		System.out.println("diagnosis. If you have concerns about autism spectrum");
		System.out.println("differences, please consult with a qualified healthcare");
		System.out.println("professional for a comprehensive evaluation.");
		System.out.println("=================================================");
		System.out.println();
		System.out.println("Thank you for completing the assessment.");
	}

	/**
	 * Wrap text to specified line length
	 */
	private String wrapText(String text, int lineLength) {
		if (text.length() <= lineLength) {
			return text;
		}

		StringBuilder wrapped = new StringBuilder();
		String[] words = text.split(" ");
		int currentLineLength = 0;

		for (String word : words) {
			if (currentLineLength + word.length() + 1 > lineLength) {
				wrapped.append("\n");
				currentLineLength = 0;
			}

			if (currentLineLength > 0) {
				wrapped.append(" ");
				currentLineLength++;
			}

			wrapped.append(word);
			currentLineLength += word.length();
		}

		return wrapped.toString();
	}
}
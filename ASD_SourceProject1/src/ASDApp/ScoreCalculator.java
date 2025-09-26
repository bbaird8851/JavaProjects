package ASDApp;
import java.util.List;

/**
 * Calculates scores for the ASD assessment
 * Processes user responses and computes total and category scores
 */
public class ScoreCalculator {

	/**
	 * Calculate the total score for the assessment
	 * @param assessment The completed assessment
	 * @return The total score
	 */
	public static int calculateTotalScore(Assessment assessment) {
		if (!assessment.isCompleted()) {
			throw new IllegalStateException("Cannot calculate score for incomplete assessment");
		}

		List<Question> questions = assessment.getQuestions();
		List<Integer> responses = assessment.getUserResponses();
		int totalScore = 0;

		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			int userChoice = responses.get(i);
			// Convert from 1-based to 0-based indexing
			totalScore += question.getScoreForChoice(userChoice - 1);
		}

		return totalScore;
	}

	/**
	 * Calculate category-specific scores for AQ questionnaire
	 * @param assessment The completed assessment
	 * @return CategoryScores object containing breakdown by AQ domains
	 */
	public static CategoryScores calculateCategoryScores(Assessment assessment) {
		if (!assessment.isCompleted()) {
			throw new IllegalStateException("Cannot calculate scores for incomplete assessment");
		}

		List<Question> questions = assessment.getQuestions();
		List<Integer> responses = assessment.getUserResponses();

		int socialSkillsScore = 0;
		int attentionSwitchingScore = 0;
		int attentionToDetailScore = 0;
		int communicationScore = 0;
		int imaginationScore = 0;

		// Map AQ questions to subscales based on Baron-Cohen et al. (2006)
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			int userChoice = responses.get(i);
			int questionScore = question.getScoreForChoice(userChoice - 1);

			int questionId = question.getQuestionId();

			// Social Skills subscale (10 items)
			if (questionId == 1 || questionId == 11 || questionId == 13 || questionId == 15 || 
					questionId == 22 || questionId == 36 || questionId == 44 || questionId == 45 || 
					questionId == 47 || questionId == 48) {
				socialSkillsScore += questionScore;
			}
			// Attention Switching subscale (10 items)
			else if (questionId == 2 || questionId == 4 || questionId == 10 || questionId == 16 || 
					questionId == 25 || questionId == 32 || questionId == 34 || questionId == 37 || 
					questionId == 43 || questionId == 46) {
				attentionSwitchingScore += questionScore;
			}
			// Attention to Detail subscale (10 items)
			else if (questionId == 5 || questionId == 6 || questionId == 9 || questionId == 12 || 
					questionId == 19 || questionId == 23 || questionId == 28 || questionId == 29 || 
					questionId == 30 || questionId == 49) {
				attentionToDetailScore += questionScore;
			}
			// Communication subscale (10 items)
			else if (questionId == 7 || questionId == 17 || questionId == 18 || questionId == 26 || 
					questionId == 27 || questionId == 31 || questionId == 33 || questionId == 35 || 
					questionId == 38 || questionId == 39) {
				communicationScore += questionScore;
			}
			// Imagination subscale (10 items)
			else if (questionId == 3 || questionId == 8 || questionId == 14 || questionId == 20 || 
					questionId == 21 || questionId == 24 || questionId == 40 || questionId == 41 || 
					questionId == 42 || questionId == 50) {
				imaginationScore += questionScore;
			}
		}

		return new CategoryScores(socialSkillsScore, attentionSwitchingScore, attentionToDetailScore, 
				communicationScore, imaginationScore);
	}

	/**
	 * Inner class to hold AQ category scores
	 */
	public static class CategoryScores {
		private final int socialSkillsScore;
		private final int attentionSwitchingScore;
		private final int attentionToDetailScore;
		private final int communicationScore;
		private final int imaginationScore;

		public CategoryScores(int socialSkillsScore, int attentionSwitchingScore, int attentionToDetailScore, 
				int communicationScore, int imaginationScore) {
			this.socialSkillsScore = socialSkillsScore;
			this.attentionSwitchingScore = attentionSwitchingScore;
			this.attentionToDetailScore = attentionToDetailScore;
			this.communicationScore = communicationScore;
			this.imaginationScore = imaginationScore;
		}

		public int getSocialSkillsScore() { return socialSkillsScore; }
		public int getAttentionSwitchingScore() { return attentionSwitchingScore; }
		public int getAttentionToDetailScore() { return attentionToDetailScore; }
		public int getCommunicationScore() { return communicationScore; }
		public int getImaginationScore() { return imaginationScore; }
		public int getTotalScore() { 
			return socialSkillsScore + attentionSwitchingScore + attentionToDetailScore + 
					communicationScore + imaginationScore; 
		}
	}
} 

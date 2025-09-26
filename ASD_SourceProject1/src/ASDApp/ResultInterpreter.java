package ASDApp;

/**
 * Interprets assessment results and provides meaningful feedback
 * Converts numerical scores into interpretative text
 */
public class ResultInterpreter {

	// AQ Score thresholds for interpretation
	private static final int LOW_THRESHOLD = 5;
	private static final int MODERATE_THRESHOLD = 15;
	private static final int HIGH_THRESHOLD = 25;
	private static final int VERY_HIGH_THRESHOLD = 32;
	private static final int MAX_SCORE = 50;

	/**
	 * Interpret the overall assessment results
    /**
	 * Interpret the overall assessment results
	 * @param totalScore The total score from the assessment
	 * @return Interpretation object with results and recommendations
	 */
	public static AssessmentResult interpretResults(int totalScore, ScoreCalculator.CategoryScores categoryScores) {
		if (totalScore < 0 || totalScore > MAX_SCORE) {
			throw new IllegalArgumentException("Invalid total score: " + totalScore);
		}

		String overallInterpretation = getOverallInterpretation(totalScore);
		String recommendation = getRecommendation(totalScore);
		String riskLevel = getRiskLevel(totalScore);

		return new AssessmentResult(totalScore, overallInterpretation, recommendation, riskLevel, categoryScores);
	}

	/**
	 * Get overall interpretation based on AQ total score
	 */
	private static String getOverallInterpretation(int totalScore) {
		if (totalScore <= LOW_THRESHOLD) {
			return "Your AQ score suggests minimal autism spectrum traits. " +
					"Most of your responses indicate typical patterns in social communication, attention, and behavioral preferences.";
		} else if (totalScore <= MODERATE_THRESHOLD) {
			return "Your AQ score suggests some autism spectrum traits are present. " +
					"You may experience certain challenges in social situations or have specific preferences in attention and communication patterns.";
		} else if (totalScore <= HIGH_THRESHOLD) {
			return "Your AQ score suggests moderate autism spectrum traits. " +
					"You may experience more significant challenges in social communication and have distinct patterns in attention and behavioral preferences.";
		} else if (totalScore <= VERY_HIGH_THRESHOLD) {
			return "Your AQ score suggests considerable autism spectrum traits. " +
					"You may experience notable challenges in social communication and have strong patterns consistent with autism spectrum differences.";
		} else {
			return "Your AQ score suggests many autism spectrum traits. " +
					"Your responses indicate significant patterns consistent with autism spectrum differences across multiple domains.";
		}
	}

	/**
	 * Get recommendations based on AQ score
	 */
	private static String getRecommendation(int totalScore) {
		if (totalScore <= LOW_THRESHOLD) {
			return "Your AQ score is in the typical range. No specific action is indicated based on this screening. " +
					"If you have ongoing concerns about social communication or behavioral patterns, consider discussing them with a healthcare professional.";
		} else if (totalScore <= MODERATE_THRESHOLD) {
			return "Your AQ score suggests some autism spectrum traits. Consider discussing these results with a healthcare professional, " +
					"especially if you experience challenges in daily life related to social communication or attention patterns. " +
					"They can provide more detailed assessment and support strategies.";
		} else if (totalScore <= HIGH_THRESHOLD) {
			return "Your AQ score suggests moderate autism spectrum traits. We recommend consulting with a qualified healthcare professional " +
					"for a comprehensive evaluation. A formal assessment can help identify specific support strategies and accommodations that may be beneficial.";
		} else if (totalScore <= VERY_HIGH_THRESHOLD) {
			return "Your AQ score suggests considerable autism spectrum traits. We strongly recommend seeking a comprehensive evaluation " +
					"from a qualified healthcare professional specializing in autism spectrum conditions.";
		} else {
			return "Your AQ score suggests significant autism spectrum traits. We strongly recommend seeking a comprehensive evaluation " +
					"from a qualified healthcare professional specializing in autism spectrum conditions. Early identification and appropriate " +
					"support can significantly improve quality of life and daily functioning.";
		}
	}

	/**
	 * Determine risk level based on score
	 */
	private static String getRiskLevel(int totalScore) {
		if (totalScore <= LOW_THRESHOLD) {
			return "Minimal";
		} else if (totalScore <= MODERATE_THRESHOLD) {
			return "Low-Moderate";
		} else if (totalScore <= HIGH_THRESHOLD) {
			return "Moderate";
		} else if (totalScore <= VERY_HIGH_THRESHOLD) {
			return "High";
		} else {
			return "Very High";
		}
	}

	/**
	 * Interpret AQ category-specific scores
	 */
	public static String interpretCategoryScores(ScoreCalculator.CategoryScores categoryScores) {
		StringBuilder interpretation = new StringBuilder();

		// Social Skills interpretation
		int socialScore = categoryScores.getSocialSkillsScore();
		interpretation.append("Social Skills (Score: ").append(socialScore).append("/10): ");
		if (socialScore <= 2) {
			interpretation.append("Typical social skills and interpersonal abilities");
		} else if (socialScore <= 5) {
			interpretation.append("Some challenges in social skills and social interactions");
		} else if (socialScore <= 7) {
			interpretation.append("Notable difficulties in social skills and social understanding");
		} else {
			interpretation.append("Significant challenges in social skills and social interactions");
		}
		interpretation.append("\n\n");

		// Attention Switching interpretation
		int attentionSwitchingScore = categoryScores.getAttentionSwitchingScore();
		interpretation.append("Attention Switching (Score: ").append(attentionSwitchingScore).append("/10): ");
		if (attentionSwitchingScore <= 2) {
			interpretation.append("Typical flexibility and attention switching abilities");
		} else if (attentionSwitchingScore <= 5) {
			interpretation.append("Some difficulties with attention switching and flexibility");
		} else if (attentionSwitchingScore <= 7) {
			interpretation.append("Notable challenges with attention switching and adapting to change");
		} else {
			interpretation.append("Significant difficulties with attention switching and cognitive flexibility");
		}
		interpretation.append("\n\n");

		// Attention to Detail interpretation
		int detailScore = categoryScores.getAttentionToDetailScore();
		interpretation.append("Attention to Detail (Score: ").append(detailScore).append("/10): ");
		if (detailScore <= 2) {
			interpretation.append("Typical attention to detail and pattern recognition");
		} else if (detailScore <= 5) {
			interpretation.append("Enhanced attention to detail and pattern recognition");
		} else if (detailScore <= 7) {
			interpretation.append("Strong attention to detail and exceptional pattern recognition");
		} else {
			interpretation.append("Exceptional attention to detail and highly focused pattern recognition");
		}
		interpretation.append("\n\n");

		// Communication interpretation
		int communicationScore = categoryScores.getCommunicationScore();
		interpretation.append("Communication (Score: ").append(communicationScore).append("/10): ");
		if (communicationScore <= 2) {
			interpretation.append("Typical communication patterns and social conversation skills");
		} else if (communicationScore <= 5) {
			interpretation.append("Some differences in communication style and social conversation");
		} else if (communicationScore <= 7) {
			interpretation.append("Notable challenges in communication and social conversation");
		} else {
			interpretation.append("Significant differences in communication style and social interaction");
		}
		interpretation.append("\n\n");

		// Imagination interpretation
		int imaginationScore = categoryScores.getImaginationScore();
		interpretation.append("Imagination (Score: ").append(imaginationScore).append("/10): ");
		if (imaginationScore <= 2) {
			interpretation.append("Typical imagination and creative thinking abilities");
		} else if (imaginationScore <= 5) {
			interpretation.append("Some differences in imaginative and creative thinking");
		} else if (imaginationScore <= 7) {
			interpretation.append("Notable differences in imagination and creative expression");
		} else {
			interpretation.append("Significant differences in imaginative thinking and creative expression");
		}

		return interpretation.toString();
	}

	/**
	 * Inner class to hold complete assessment results
	 */
	public static class AssessmentResult {
		private final int totalScore;
		private final String overallInterpretation;
		private final String recommendation;
		private final String riskLevel;
		private final ScoreCalculator.CategoryScores categoryScores;

		public AssessmentResult(int totalScore, String overallInterpretation, String recommendation, 
				String riskLevel, ScoreCalculator.CategoryScores categoryScores) {
			this.totalScore = totalScore;
			this.overallInterpretation = overallInterpretation;
			this.recommendation = recommendation;
			this.riskLevel = riskLevel;
			this.categoryScores = categoryScores;
		}

		// Getters
		public int getTotalScore() { return totalScore; }
		public String getOverallInterpretation() { return overallInterpretation; }
		public String getRecommendation() { return recommendation; }
		public String getRiskLevel() { return riskLevel; }
		public ScoreCalculator.CategoryScores getCategoryScores() { return categoryScores; }
	}
}

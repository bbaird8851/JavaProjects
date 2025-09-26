package ASDApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the ASD assessment questionnaire
 * Contains all questions and tracks user responses
 */

public class Assessment {
	private List<Question> questions;
	private List<Integer> userResponses;
	private boolean isCompleted;

	/**
	 * Constructor initializes the assessment with predefined questions
	 */
	public Assessment() {
		this.questions = new ArrayList<>();
		this.userResponses = new ArrayList<>();
		this.isCompleted = false;
		initializeQuestions();
	}

	/**
	 * Initialize the assessment with official AQ (Autism Quotient) questions
	 * Based on the Adolescent Autism Spectrum Quotient (Ages 12-15 years)
	 * by S. Baron-Cohen et al. (2006)
	 */
	private void initializeQuestions() {
		String[] responseOptions = {
				"Definitely Agree",
				"Slightly Agree", 
				"Slightly Disagree",
				"Definitely Disagree"
		};

		// Question 1 - Social preference (reverse scored)
		questions.add(new Question(1,
				"S/he prefers to do things with others rather than on her/his own.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 2 - Repetitive behaviors
		questions.add(new Question(2,
				"S/he prefers to do things the same way over and over again.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 3 - Imagination (reverse scored)
		questions.add(new Question(3,
				"If s/he tries to imagine something, s/he finds it very easy to create a picture in her/his mind.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 4 - Attention to detail/focus
		questions.add(new Question(4,
				"S/he frequently gets so strongly absorbed in one thing that s/he loses sight of other things.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 5 - Sensory sensitivity
		questions.add(new Question(5,
				"S/he often notices small sounds when others do not.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 6 - Attention to detail
		questions.add(new Question(6,
				"S/he usually notices car number plates or similar strings of information.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 7 - Social communication
		questions.add(new Question(7,
				"Other people frequently tell her/him that what s/he has said is impolite, even though s/he thinks it is polite.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 8 - Imagination (reverse scored)
		questions.add(new Question(8,
				"When s/he is reading a story, s/he can easily imagine what the characters might look like.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 9 - Special interests
		questions.add(new Question(9,
				"S/he is fascinated by dates.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 10 - Social skills (reverse scored)
		questions.add(new Question(10,
				"In a social group, s/he can easily keep track of several different people's conversations.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 11 - Social situations (reverse scored)
		questions.add(new Question(11,
				"S/he finds social situations easy.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 12 - Attention to detail
		questions.add(new Question(12,
				"S/he tends to notice details that others do not.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 13 - Social preference
		questions.add(new Question(13,
				"S/he would rather go to a library than a party.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 14 - Imagination (reverse scored)
		questions.add(new Question(14,
				"S/he finds making up stories easy.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 15 - Social interest (reverse scored)
		questions.add(new Question(15,
				"S/he finds her/himself drawn more strongly to people than to things.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 16 - Special interests
		questions.add(new Question(16,
				"S/he tends to have very strong interests, which s/he gets upset about if s/he can't pursue.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 17 - Social communication (reverse scored)
		questions.add(new Question(17,
				"S/he enjoys social chit-chat.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 18 - Communication style
		questions.add(new Question(18,
				"When s/he talks, it isn't always easy for others to get a word in edgeways.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 19 - Special interests
		questions.add(new Question(19,
				"S/he is fascinated by numbers.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 20 - Theory of mind
		questions.add(new Question(20,
				"When s/he is reading a story, s/he finds it difficult to work out the characters' intentions.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 21 - Reading preferences
		questions.add(new Question(21,
				"S/he doesn't particularly enjoy reading fiction.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 22 - Social skills
		questions.add(new Question(22,
				"S/he finds it hard to make new friends.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 23 - Attention to detail
		questions.add(new Question(23,
				"S/he notices patterns in things all the time.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 24 - Social preference (reverse scored)
		questions.add(new Question(24,
				"S/he would rather go to the theatre than a museum.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 25 - Routine flexibility (reverse scored)
		questions.add(new Question(25,
				"It does not upset him/her if his/her daily routine is disturbed.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 26 - Social communication
		questions.add(new Question(26,
				"S/he frequently finds that s/he doesn't know how to keep a conversation going.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 27 - Social understanding (reverse scored)
		questions.add(new Question(27,
				"S/he finds it easy to \"read between the lines\" when someone is talking to her/him.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 28 - Attention to detail (reverse scored)
		questions.add(new Question(28,
				"S/he usually concentrates more on the whole picture, rather than the small details.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 29 - Memory (reverse scored)
		questions.add(new Question(29,
				"S/he is not very good at remembering phone numbers.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 30 - Attention to detail (reverse scored)
		questions.add(new Question(30,
				"S/he doesn't usually notice small changes in a situation, or a person's appearance.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 31 - Social awareness (reverse scored)
		questions.add(new Question(31,
				"S/he knows how to tell if someone listening to him/her is getting bored.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 32 - Multitasking (reverse scored)
		questions.add(new Question(32,
				"S/he finds it easy to do more than one thing at once.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 33 - Communication
		questions.add(new Question(33,
				"When s/he talks on the phone, s/he is not sure when it's her/his turn to speak.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 34 - Spontaneity (reverse scored)
		questions.add(new Question(34,
				"S/he enjoys doing things spontaneously.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 35 - Social understanding
		questions.add(new Question(35,
				"S/he is often the last to understand the point of a joke.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 36 - Theory of mind (reverse scored)
		questions.add(new Question(36,
				"S/he finds it easy to work out what someone is thinking or feeling just by looking at their face.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 37 - Task switching (reverse scored)
		questions.add(new Question(37,
				"If there is an interruption, s/he can switch back to what s/he was doing very quickly.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 38 - Social communication (reverse scored)
		questions.add(new Question(38,
				"S/he is good at social chit-chat.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 39 - Communication style
		questions.add(new Question(39,
				"People often tell her/him that s/he keeps going on and on about the same thing.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 40 - Imagination (reverse scored)
		questions.add(new Question(40,
				"When s/he was younger, s/he used to enjoy playing games involving pretending with other children.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 41 - Special interests
		questions.add(new Question(41,
				"S/he likes to collect information about categories of things (e.g. types of car, types of bird, types of train, types of plant, etc.).",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 42 - Theory of mind
		questions.add(new Question(42,
				"S/he finds it difficult to imagine what it would be like to be someone else.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 43 - Planning
		questions.add(new Question(43,
				"S/he likes to plan any activities s/he participates in carefully.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 44 - Social enjoyment (reverse scored)
		questions.add(new Question(44,
				"S/he enjoys social occasions.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 45 - Theory of mind
		questions.add(new Question(45,
				"S/he finds it difficult to work out people's intentions.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 46 - Anxiety about change
		questions.add(new Question(46,
				"New situations make him/her anxious.",
				responseOptions,
				new int[]{1, 1, 0, 0}
				));

		// Question 47 - Social interest (reverse scored)
		questions.add(new Question(47,
				"S/he enjoys meeting new people.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 48 - Social skills (reverse scored)
		questions.add(new Question(48,
				"S/he is a good diplomat.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 49 - Memory (reverse scored)
		questions.add(new Question(49,
				"S/he is not very good at remembering people's date of birth.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));

		// Question 50 - Imagination (reverse scored)
		questions.add(new Question(50,
				"S/he finds it very easy to play games with children that involve pretending.",
				responseOptions,
				new int[]{0, 0, 1, 1}
				));
	}

	/**
	 * Record a user's response to a question
	 * @param questionIndex The index of the question (0-based)
	 * @param response The user's response choice (1-based)
	 */
	public void recordResponse(int questionIndex, int response) {
		if (questionIndex < 0 || questionIndex >= questions.size()) {
			throw new IllegalArgumentException("Invalid question index: " + questionIndex);
		}

		Question question = questions.get(questionIndex);
		if (!question.isValidChoice(response)) {
			throw new IllegalArgumentException("Invalid response choice: " + response);
		}

		// Ensure userResponses list is large enough
		while (userResponses.size() <= questionIndex) {
			userResponses.add(0);
		}

		userResponses.set(questionIndex, response);
	}

	/**
	 * Mark the assessment as completed
	 */
	public void completeAssessment() {
		if (userResponses.size() != questions.size()) {
			throw new IllegalStateException("Cannot complete assessment: not all questions answered");
		}
		this.isCompleted = true;
	}

	/**
	 * Check if all questions have been answered
	 * @return true if all questions answered, false otherwise
	 */
	public boolean areAllQuestionsAnswered() {
		return userResponses.size() == questions.size() && 
				userResponses.stream().noneMatch(response -> response == 0);
	}

	// Getters
	public List<Question> getQuestions() { return questions; }
	public List<Integer> getUserResponses() { return userResponses; }
	public boolean isCompleted() { return isCompleted; }
	public int getTotalQuestions() { return questions.size(); }
	public int getAnsweredQuestions() { return userResponses.size(); }
}


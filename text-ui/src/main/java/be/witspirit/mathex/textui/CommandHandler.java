package be.witspirit.mathex.textui;


public interface CommandHandler {

    /**
     * Called prior to each interaction with the user.
     *
     * @param session The current ExerciseSession
     * @return The message to the user prior to his input
     */
    String instructUser(ExerciseSession session);

    /**
     * Called after the user has given input
     *
     * @param userInput The input the user provided
     * @param session The current ExerciseSession
     * @return Feedback on the userInput (NOTE: The next instruction will be obtained via instructUser) and the next handler to install
     */
    FeedbackAndHandler handleUserInput(String userInput, ExerciseSession session);

}


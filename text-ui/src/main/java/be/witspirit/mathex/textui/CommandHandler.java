package be.witspirit.mathex.textui;


public interface CommandHandler {

    /**
     * Called prior to each interaction with the user.
     *
     * @return The message to the user prior to his input
     */
    String instructUser();

    /**
     * Called after the user has given input
     *
     * @param userInput The input the user provided
     * @return Feedback on the userInput (NOTE: The next instruction will be obtained via instructUser)
     */
    String handleUserInput(String userInput);

}


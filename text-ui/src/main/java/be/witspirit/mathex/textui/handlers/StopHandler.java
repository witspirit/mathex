package be.witspirit.mathex.textui.handlers;

import be.witspirit.mathex.textui.ExerciseSession;
import be.witspirit.mathex.textui.FeedbackAndHandler;

/**
* Terminates the Exercise Session
*/
public class StopHandler extends CommonHandler {
    @Override
    public String instructUser(ExerciseSession session) {
        session.stop();
        return "";
    }

    @Override
    public FeedbackAndHandler handleUserInput(String userInput, ExerciseSession session) {
        throw new IllegalStateException("Ui has been stopped");
    }
}

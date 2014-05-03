package be.witspirit.mathex.textui.handlers;

import be.witspirit.mathex.textui.ExerciseSession;
import be.witspirit.mathex.textui.FeedbackAndHandler;

/**
* Responsible for startup of the Exercise Session
*/
public class StartHandler extends CommonHandler {

    @Override
    public String instructUser(ExerciseSession session) {
        session.start();
        return "Welkom bij rekenoefeningen !\n" +
               "Tot hoeveel mogen de oefeningen gaan ? ";
    }

    @Override
    public FeedbackAndHandler handleUserInput(String userInput, ExerciseSession session) {
        try {
            int max = num(userInput);
            return new FeedbackAndHandler("", new TypeSelectionHandler(max));

        } catch (NumberFormatException nfe) {
            return super.handleUserInput(userInput, session);
        }
    }

}

package be.witspirit.mathex.textui.handlers;

import be.witspirit.mathex.SumGenerator;
import be.witspirit.mathex.textui.CommandHandler;
import be.witspirit.mathex.textui.ExerciseSession;
import be.witspirit.mathex.textui.FeedbackAndHandler;

/**
* Selects the type of Exercise to be performed
*/
public class TypeSelectionHandler extends CommonHandler {

    private int maxValue;

    public TypeSelectionHandler(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public String instructUser(ExerciseSession session) {
        return "Welk soort oefeningen had je graag gemaakt ?\n" +
                "A) 1 + 2 = 3\n" +
                "B) 1 + 2 + 3 = 6\n" +
                "Jouw keuze: ";
    }

    @Override
    public FeedbackAndHandler handleUserInput(String userInput, ExerciseSession session) {
        SumGenerator sumGenerator = new SumGenerator(0, maxValue);
        CommandHandler handler;
        switch (userInput.toUpperCase()) {
            case "A" :
                // Sum
                handler = new ExerciseHandler<>(sumGenerator::sum);
                break;
            case "B" :
                // Sum3
                handler = new ExerciseHandler<>(sumGenerator::sum3);
                break;
            default:
               return super.handleUserInput(userInput, session);
        }
        return new FeedbackAndHandler("Ok, hier gaan we...", handler);
    }

}

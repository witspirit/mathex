package be.witspirit.mathex.textui.handlers;

import be.witspirit.mathex.textui.CommandHandler;
import be.witspirit.mathex.textui.ExerciseSession;
import be.witspirit.mathex.textui.FeedbackAndHandler;

/**
* Base class for some common inputs
*/
public class CommonHandler implements CommandHandler {

    @Override
    public String instructUser(ExerciseSession session) {
        return "";
    }

    @Override
    public FeedbackAndHandler handleUserInput(String userInput, ExerciseSession session) {
        userInput = userInput.toUpperCase();
        switch (userInput) {
            case "S":
                return new FeedbackAndHandler("Tot gauw !", new StopHandler());
            case "I":
                StringBuilder sb = new StringBuilder();
                sb.append("Statistieken\n");
                sb.append("Correct = ").append(session.getStats().getCorrect()).append("\n");
                sb.append("Fout = ").append(session.getStats().getFault()).append("\n");
                sb.append("Totaal = ").append(session.getStats().getTotal()).append("\n");
                double score = ((double) session.getStats().getCorrect() / (double) session.getStats().getTotal()) * 100;
                sb.append("Score = ").append(String.format("%2.2f %%", score));
                return new FeedbackAndHandler(sb.toString(), this);
        }
        return new FeedbackAndHandler("Antwoord niet begrepen", this);
    }

    protected int num(String value) {
        return Integer.parseInt(value);
    }
}

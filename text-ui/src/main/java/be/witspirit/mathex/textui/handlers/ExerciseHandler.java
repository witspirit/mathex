package be.witspirit.mathex.textui.handlers;

import be.witspirit.mathex.Exercise;
import be.witspirit.mathex.textui.ExerciseSession;
import be.witspirit.mathex.textui.FeedbackAndHandler;

import java.util.function.Supplier;

/**
* Evaluates Exercises
*/
public class ExerciseHandler<T extends Exercise> extends CommonHandler {

    private Supplier<T> exerciseSupplier;
    protected T currentExercise = null;
    private boolean repeated = false;

    public ExerciseHandler(Supplier<T> exerciseSupplier) {
        this.exerciseSupplier = exerciseSupplier;
        currentExercise = exerciseSupplier.get(); // We don't count the first exercise, as the last one will not be answered either
    }

    @Override
    public String instructUser(ExerciseSession session) {
        return currentExercise.getExerciseRep();
    }

    @Override
    public FeedbackAndHandler handleUserInput(String command, ExerciseSession session) {
        try {
            int solution = num(command);

            if (solution == currentExercise.getOutput()) {
                if (!repeated) {
                    session.getStats().incCorrect();
                }

                repeated = false;
                currentExercise = exerciseSupplier.get();
                session.getStats().incTotal();

                return new FeedbackAndHandler("Juist !", this);
            } else {
                // On mistake we keep the current sum
                session.getStats().incFault();
                repeated = true;
                return new FeedbackAndHandler("Fout !", this);
            }

        } catch (NumberFormatException nfe) {
            return super.handleUserInput(command, session);
        }
    }
}

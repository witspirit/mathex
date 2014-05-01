package be.witspirit.mathex.textui.uimodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to make exercises via the UiInteraction abstraction
 */
public class ExerciseMaker {

    private UiInteraction ui;
    private List<SumRep> sums = new ArrayList<>();

    public ExerciseMaker(UiInteraction ui) {
        this.ui = ui;
    }

    public ExerciseMaker correct(int nrOfExercises) {
        for (int i=0; i < nrOfExercises; i++) {
            SumRep sum = ui.line(1).extractSumRep();
            ui = ui.command(sum.getOutput());
        }
        return this;
    }

    public ExerciseMaker fault(int nrOfExercises, int repeatedFaults) {
        for (int i=0; i < nrOfExercises; i++) {
            SumRep sum = ui.line(1).extractSumRep();
            sums.add(sum);
            ui = ui.command(sum.getOutput()+1); // Fault
            for (int r=0; r < repeatedFaults; r++) {
                sums.add(ui.line(1).extractSumRep());
                ui = ui.command(sum.getOutput()+1); // Fault
            }
            sums.add(ui.line(1).extractSumRep());
            ui = ui.command(sum.getOutput()); // Same is repeated, so I immediately do it correctly
        }
        return this;
    }

    public UiInteraction ui() {
        return ui;
    }


    public List<SumRep> getSums() {
        return sums;
    }
}

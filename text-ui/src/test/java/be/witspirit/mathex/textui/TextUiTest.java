package be.witspirit.mathex.textui;


import be.witspirit.mathex.Sum;
import be.witspirit.mathex.support.SumAssert;
import be.witspirit.mathex.textui.uimodel.UiInteraction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TextUiTest {

    UiInteraction ui;

    @Before
    public void setUp() {
        ui = UiInteraction.launch();
    }

    @After
    public void tearDown() {
        ui.stop();
    }

    @Test
    public void startStop() { // Covers the starting message and the stopping message
        UiInteraction ui = UiInteraction.launch();
        ui.assertLines("Welkom bij rekenoefeningen !","Tot hoeveel mogen de oefeningen gaan ?");

        ui.command("S").assertLines("Tot gauw !");

        // And we expect the TextUi to have stopped
    }

    @Test
    public void singleCorrectAnswer() {
        ui = ui.command(0); // Should only lead to a 0+0 or 0-0 exercise
        ui.line(0).assertEquals("Ok, hier gaan we...");
        Sum sum = ui.line(1).extractSum();
        Assert.assertEquals(0, sum.getInput1());
        Assert.assertEquals(0, sum.getInput2());

        ui = ui.command(Integer.toString(sum.getOutput()));
        ui.line(0).assertEquals("Juist !");
        ui.line(1).extractSum();
    }

    @Test
    public void singleFaultyAnswer() {
        ui = ui.command(0); // Should only lead to a 0+0 or 0-0 exercise
        ui.line(0).assertEquals("Ok, hier gaan we...");
        ui.line(1).extractSum();

        ui = ui.command(6);
        ui.line(0).assertEquals("Fout !");
        ui.line(1).extractSum();
    }

    @Test
    public void faultyNonNumericInputOnSetupQuestion() {
        ui.command("FAULT").line(0).assertEquals("Antwoord niet begrepen");
    }

    @Test
    public void faultyNonNumericInputOnExerciseQuestion() {
        ui.command(0).command("FAULT").line(0).assertEquals("Antwoord niet begrepen");
    }

    @Test
    public void ensureDiverse() {
        ui = ui.command(10);

        // Let's make a few exercises and collect them in sums
        List<Sum> sums = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            System.out.println(ui.getContent());

            Sum sum = ui.line(1).extractSum();
            sums.add(sum);
            ui = ui.command(sum.getOutput());
        }

        SumAssert.assertCompliantAndDiverse(10, 0, 10, sums);
    }


}

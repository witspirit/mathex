package be.witspirit.mathex.textui;


import be.witspirit.mathex.textui.uimodel.UiInteraction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        ui = ui.command("0"); // Should only lead to a 0+0 or 0-0 exercise
        ui.line(0).assertEquals("Ok, hier gaan we...");
        ui.line(1).assertMatches("0 (\\+|-) 0 = ");

        ui = ui.command("0");
        ui.line(0).assertEquals("Juist !");
        ui.line(1).assertMatches("0 (\\+|-) 0 = ");
    }

    @Test
    public void singleFaultyAnswer() {
        ui = ui.command("0"); // Should only lead to a 0+0 or 0-0 exercise
        ui.line(0).assertEquals("Ok, hier gaan we...");
        ui.line(1).assertMatches("0 (\\+|-) 0 = ");

        ui = ui.command("6");
        ui.line(0).assertEquals("Fout !");
        ui.line(1).assertMatches("0 (\\+|-) 0 = ");
    }

    @Test
    public void faultyNonNumericInputOnSetupQuestion() {
        ui.command("FAULT").assertLines("Antwoord niet begrepen");
    }

    @Test
    public void faultyNonNumericInputOnExerciseQuestion() {
        ui.command("0").command("FAULT").assertLines("Antwoord niet begrepen");
    }

}

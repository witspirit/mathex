package be.witspirit.mathex.textui;


import be.witspirit.mathex.textui.uimodel.UiInteraction;
import org.junit.Assert;
import org.junit.Test;

public class TextUiTest {

    @Test
    public void startStop() {
        UiInteraction ui = UiInteraction.launch();
        Assert.assertEquals("Welkom bij rekenoefeningen !\nTot hoeveel mogen de oefeningen gaan ?\n", ui.getContent());

        ui = ui.command("S");
        Assert.assertEquals("Tot gauw !\n", ui.getContent());

        // And we expect the TextUi to have stopped
    }

    @Test
    public void singleCorrectAnswer() {
        UiInteraction ui = UiInteraction.launch();

        ui = ui.command("0"); // Should only lead to a 0+0 or 0-0 exercise
        Assert.assertTrue(ui.getContent().startsWith("Ok, hier gaan we...\n0 "));
        Assert.assertTrue(ui.getContent().endsWith("0 = "));

        ui = ui.command("0");
        Assert.assertTrue(ui.getContent().startsWith("Juist !\n0 "));
        Assert.assertTrue(ui.getContent().endsWith("0 = "));

        ui = ui.stop();
        Assert.assertEquals("Tot gauw !\n", ui.getContent());
    }

    @Test
    public void singleFaultyAnswer() {
        UiInteraction ui = UiInteraction.launch();

        ui = ui.command("0"); // Should only lead to a 0+0 or 0-0 exercise
        Assert.assertTrue(ui.getContent().startsWith("Ok, hier gaan we...\n0 "));
        Assert.assertTrue(ui.getContent().endsWith("0 = "));

        ui = ui.command("6");
        Assert.assertTrue(ui.getContent().startsWith("Fout !\n0 "));
        Assert.assertTrue(ui.getContent().endsWith("0 = "));

        ui = ui.stop();
        Assert.assertEquals("Tot gauw !\n", ui.getContent());
    }

    @Test
    public void faultyNonNumericInputOnSetupQuestion() {
        UiInteraction ui = UiInteraction.launch();

        ui = ui.command("FAULT");
        Assert.assertEquals("Antwoord niet begrepen\n", ui.getContent());
    }

    @Test
    public void faultyNonNumericInputOnExerciseQuestion() {
        UiInteraction ui = UiInteraction.launch();

        ui = ui.command("0");

        ui = ui.command("FAULT");
        Assert.assertEquals("Antwoord niet begrepen\n", ui.getContent());
    }

}

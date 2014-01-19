package be.witspirit.mathex.textui;


import org.junit.Assert;
import org.junit.Test;

public class TextUiTest {

    @Test
    public void startStop() {
        TextUi ui = new TextUi();
        String output = ui.start();
        Assert.assertEquals("Welkom bij rekenoefeningen !\nTot hoeveel mogen de oefeningen gaan ?\n", output);

        output = ui.command("S");
        Assert.assertEquals("Tot gauw !\n", output);


        // And we expect the TextUi to have stopped
    }

    @Test
    public void singleCorrectAnswer() {
        TextUi ui = new TextUi();
        String output = ui.start();

        output = ui.command("0"); // Should only lead to a 0+0 or 0-0 exercise
        Assert.assertTrue(output.startsWith("Ok, hier gaan we...\n0 "));
        Assert.assertTrue(output.endsWith("0 = "));

        output = ui.command("0");
        Assert.assertTrue(output.startsWith("Juist !\n0 "));
        Assert.assertTrue(output.endsWith("0 = "));

        output = ui.command("S");
        Assert.assertEquals("Tot gauw !\n", output);
    }

    @Test
    public void singleFaultyAnswer() {
        TextUi ui = new TextUi();
        String output = ui.start();

        output = ui.command("0"); // Should only lead to a 0+0 or 0-0 exercise
        Assert.assertTrue(output.startsWith("Ok, hier gaan we...\n0 "));
        Assert.assertTrue(output.endsWith("0 = "));

        output = ui.command("6");
        Assert.assertTrue(output.startsWith("Fout !\n0 "));
        Assert.assertTrue(output.endsWith("0 = "));

        output = ui.command("S");
        Assert.assertEquals("Tot gauw !\n", output);
    }

    @Test
    public void faultyNonNumericInputOnSetupQuestion() {
        TextUi ui = new TextUi();
        String output = ui.start();

        output = ui.command("FAULT");
        Assert.assertEquals("Antwoord niet begrepen\n", output);
    }

    @Test
    public void faultyNonNumericInputOnExerciseQuestion() {
        TextUi ui = new TextUi();
        String output = ui.start();
        output = ui.command("0");

        output = ui.command("FAULT");
        Assert.assertEquals("Antwoord niet begrepen\n", output);
    }

}

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
        ui.assertLines("Welkom bij rekenoefeningen !","Tot hoeveel mogen de oefeningen gaan ? ");

        ui.command("S").assertLines("Tot gauw !");

        // No further commands allowed
        try {
            ui.command("5");
            Assert.fail("UI accepted commands after stop");
        } catch (IllegalStateException e) {
            Assert.assertEquals("Ui has been stopped", e.getMessage());
        }

        // And we also accept lowercase
        UiInteraction.launch().command("s").assertLines("Tot gauw !");
    }

    @Test
    public void selectSimpleSum() {
        ui = ui.command(10).assertLines("Welk soort oefeningen had je graag gemaakt ?", "A) 1 + 2 = 3", "B) 1 + 2 + 3 = 6", "Jouw keuze: ");
        ui = ui.command("A");
        ui.line(1).assertSum();
    }

    @Test
    public void selectSum3() {
        ui = ui.command(10).assertLines("Welk soort oefeningen had je graag gemaakt ?", "A) 1 + 2 = 3", "B) 1 + 2 + 3 = 6", "Jouw keuze: ");
        ui = ui.command("B");
        ui.line(1).assertSum3();
    }

    @Test
    public void singleCorrectAnswer() {
        ui = ui.command(0).command("A"); // Should only lead to a 0+0 or 0-0 exercise
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
        ui = ui.command(10).command("A");
        ui.line(0).assertEquals("Ok, hier gaan we...");
        Sum sumOrig = ui.line(1).extractSum();

        ui = ui.command(sumOrig.getOutput()+1); // Ensure faulty answer
        ui.line(0).assertEquals("Fout !");
        Sum sumAfterError = ui.line(1).extractSum();

        // After an error we present the original exercise again
        Assert.assertEquals(sumOrig, sumAfterError);
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
        ui = ui.command(10).command("A");

        // Let's make a few exercises and collect them in sums
        List<Sum> sums = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            System.out.println(ui.getContent());

            Sum sum = ui.line(1).extractSum();
            sums.add(sum);
            ui = ui.command(sum.getOutput());
        }

        SumAssert.assertCompliantAndDiverseSums(10, 0, 10, sums);
    }

    @Test
    public void statsAllCorrect() {
        ui.command(10).command("A").make().correct(5);

        Stats stats = ui.getStats();

        Assert.assertEquals(5, stats.getTotal());
        Assert.assertEquals(5, stats.getCorrect());
        Assert.assertEquals(0, stats.getFault());
    }

    @Test
    public void statsMixed() {
        ui.command(10).command("A").make().correct(5).fault(5, 0).correct(2).fault(2,1);

        Stats stats = ui.getStats();

        Assert.assertEquals(14, stats.getTotal());
        Assert.assertEquals(7, stats.getCorrect());
        Assert.assertEquals(9, stats.getFault());
    }

    @Test
    public void statsInUi() {
        ui = ui.command(10).command("A").make().correct(92).fault(8, 0).ui();

        ui = ui.command("i");
        ui.line(0).assertEquals("Statistieken");
        ui.line(1).assertEquals("Correct = 92");
        ui.line(2).assertEquals("Fout = 8");
        ui.line(3).assertEquals("Totaal = 100");
        ui.line(4).assertEquals("Score = 92,00 %");
    }


}

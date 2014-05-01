package be.witspirit.mathex.textui;

import be.witspirit.mathex.textui.uimodel.UiInteraction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

/**
 * Tests the Statistics feature of the TextUi across the various exercise types
 */
@RunWith(Parameterized.class)
public class TextUiStatsTest {

    @Parameter(0)
    public String exerciseType;

    @Parameter(1)
    public String exerciseDescription;

    @Parameters(name = "Verify Statistics over exercises of type {0}) {1}")
    public static List<Object[]> exerciseTypes() {
        return Arrays.asList(new Object[][] {
                { "A", "a +/- b" },
                { "B", "a + b + c"}
        });
    }

    private UiInteraction ui;

    @Before
    public void setUp() {
        ui = UiInteraction.launch().command(10).command(exerciseType);
    }

    @After
    public void tearDown() {
        ui.stop();
    }

    @Test
    public void statsAllCorrect() {
        ui.make().correct(5);

        Stats stats = ui.getStats();

        Assert.assertEquals(5, stats.getTotal());
        Assert.assertEquals(5, stats.getCorrect());
        Assert.assertEquals(0, stats.getFault());
    }

    @Test
    public void statsMixed() {
        ui.make().correct(5).fault(5, 0).correct(2).fault(2,1);

        Stats stats = ui.getStats();

        Assert.assertEquals(14, stats.getTotal());
        Assert.assertEquals(7, stats.getCorrect());
        Assert.assertEquals(9, stats.getFault());
    }

    @Test
    public void statsInUi() {
        ui = ui.make().correct(92).fault(8, 0).ui();

        ui = ui.command("i");
        ui.line(0).assertEquals("Statistieken");
        ui.line(1).assertEquals("Correct = 92");
        ui.line(2).assertEquals("Fout = 8");
        ui.line(3).assertEquals("Totaal = 100");
        ui.line(4).assertEquals("Score = 92,00 %");
    }
}

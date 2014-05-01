package be.witspirit.mathex.textui;

import be.witspirit.mathex.support.SumAssert;
import be.witspirit.mathex.support.SumRep;
import be.witspirit.mathex.textui.uimodel.UiInteraction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

/**
 * Tests the diversity characteristics of the TextUi across the various exercise types
 */
@RunWith(Parameterized.class)
public class TextUiExerciseDiversityTest {

    @Parameter(0)
    public String exerciseType;

    @Parameter(1)
    public String exerciseDescription;

    @Parameter(2)
    public boolean operatorDiversity;

    @Parameters(name = "Verify exercise diversity over exercises of type {0}) {1}")
    public static List<Object[]> exerciseTypes() {
        return Arrays.asList(new Object[][] {
                { "A", "a +/- b", true },
                { "B", "a + b + c", false}
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
    public void ensureDiverse() {
        List<SumRep> sums = ui.make().correct(10).getSums();

        SumAssert.assertCompliantAndDiverseSumReps(10, 0, 10, operatorDiversity, sums);
    }
}

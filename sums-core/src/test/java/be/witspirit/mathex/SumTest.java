package be.witspirit.mathex;

import org.junit.Assert;
import org.junit.Test;

public class SumTest {

    @Test
    public void sumEqualityTest() {
        Sum sum1 = new Sum(0, "+", 1);
        Sum sum2 = new Sum(0, "+", 1);
        Sum sum3 = new Sum(1, "+", 1);
        Sum sum4 = new Sum(0, "+", 2);
        Sum sum5 = new Sum(0, "-", 1);

        Assert.assertEquals(true,sum1.equals(sum1));
        Assert.assertEquals(true,sum1.equals(sum2));
        Assert.assertEquals(false,sum1.equals(sum3));
        Assert.assertEquals(false,sum1.equals(sum4));
        Assert.assertEquals(false,sum1.equals(sum5));
    }

    @Test
    public void checkSumToString() {
        Assert.assertEquals("0 + 1 = 1", new Sum(0, "+", 1).toString());
    }

    @Test
    public void checkOutputCalculation() {
        Assert.assertEquals(1,new Sum(0, "+", 1).getOutput());
        Assert.assertEquals(2,new Sum(1, "+", 1).getOutput());
        Assert.assertEquals(2,new Sum(0, "+", 2).getOutput());
        Assert.assertEquals(-1,new Sum(0, "-", 1).getOutput());
    }

}

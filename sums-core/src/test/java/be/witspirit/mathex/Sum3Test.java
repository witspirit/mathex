package be.witspirit.mathex;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for Sum3, a sum with 3 terms
 */
public class Sum3Test {

    @Test
    public void sum3EqualityTest() {
        Sum3 sum1 = new Sum3(1,2,3);
        Sum3 sum2 = new Sum3(1,2,3);
        Sum3 sum3 = new Sum3(3,2,1);
        Sum3 sum4 = new Sum3(1,1,1);
        Sum3 sum5 = new Sum3(6,0,0);

        Assert.assertEquals(true, sum1.equals(sum1));
        Assert.assertEquals(true, sum1.equals(sum2));
        Assert.assertEquals(false, sum1.equals(sum3));
        Assert.assertEquals(false, sum1.equals(sum4));
        Assert.assertEquals(false, sum1.equals(sum5));
    }

    @Test
    public void sum3toString() {
        Sum3 sum = new Sum3(1,2,3);

        Assert.assertEquals("1 + 2 + 3 = 6", sum.toString());
    }

    @Test
    public void outputCalculation() {
        Assert.assertEquals(0, new Sum3(0,0,0).getOutput());
        Assert.assertEquals(3, new Sum3(1,1,1).getOutput());
        Assert.assertEquals(6, new Sum3(1,2,3).getOutput());
        Assert.assertEquals(6, new Sum3(2,2,2).getOutput());

    }


}

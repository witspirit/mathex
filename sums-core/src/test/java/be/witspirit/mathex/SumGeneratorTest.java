package be.witspirit.mathex;

import be.witspirit.mathex.support.SumAssert;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SumGeneratorTest {

    @Test
    public void zeroSum() {
        SumGenerator generator = new SumGenerator(0, 0);

        Sum sum = generator.sum();

        Assert.assertEquals(0, sum.getInput1());
        Assert.assertEquals(0, sum.getInput2());
        Assert.assertEquals(0, sum.getOutput());
    }

    @Test
    public void zeroSum3() {
        SumGenerator generator = new SumGenerator(0, 0);

        Sum3 sum = generator.sum3();

        Assert.assertEquals(0, sum.getT1());
        Assert.assertEquals(0, sum.getT2());
        Assert.assertEquals(0, sum.getT3());
        Assert.assertEquals(0, sum.getOutput());
    }


    @Test
    public void sumGeneratorGeneratesDiverseCompliantSums() {
        SumGenerator generator = new SumGenerator(0, 10);
        List<Sum> sums = generator.generateSum(10);

        SumAssert.assertCompliantAndDiverseSums(10, 0, 10, sums);
    }

    @Test
    public void sumGeneratorGeneratesDiverseCompliantSum3s() {
        SumGenerator generator = new SumGenerator(0, 10);
        List<Sum3> sums = generator.generateSum3(10);

        SumAssert.assertCompliantAndDiverseSum3s(10, 0, 10, sums);
    }

    @Test
    public void generatesExpectedNumberOfSums() {
        SumGenerator generator = new SumGenerator(0, 10);
        List<Sum> sums2 = generator.generateSum(2);
        List<Sum> sums5 = generator.generateSum(5);

        Assert.assertEquals(2,sums2.size());
        Assert.assertEquals(5,sums5.size());
    }

    @Test
    public void generatesExpectedNumberOfSum3s() {
        SumGenerator generator = new SumGenerator(0, 10);
        List<Sum3> sums2 = generator.generateSum3(2);
        List<Sum3> sums5 = generator.generateSum3(5);

        Assert.assertEquals(2,sums2.size());
        Assert.assertEquals(5,sums5.size());
    }
}

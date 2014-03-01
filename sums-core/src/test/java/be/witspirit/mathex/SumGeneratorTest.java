package be.witspirit.mathex;

import be.witspirit.mathex.support.SumAssert;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SumGeneratorTest {

    @Test
    public void zeroSum() {
        SumGenerator generator = new SumGenerator(0, 0);

        Sum sum = generator.generateSum();

        Assert.assertEquals(0, sum.getInput1());
        Assert.assertEquals(0, sum.getInput2());
        Assert.assertEquals(0, sum.getOutput());
    }

    @Test
    public void sumGeneratorGeneratesDiverseCompliantSums() {
        SumGenerator generator = new SumGenerator(0, 10);
        List<Sum> sums = generator.generate(10);

        SumAssert.assertCompliantAndDiverse(10, 0, 10, sums);
    }

    @Test
    public void generatesExpectedNumberOfSums() {
        SumGenerator generator = new SumGenerator(0, 10);
        List<Sum> sums2 = generator.generate(2);
        List<Sum> sums5 = generator.generate(5);

        Assert.assertEquals(2,sums2.size());
        Assert.assertEquals(5,sums5.size());
    }
}

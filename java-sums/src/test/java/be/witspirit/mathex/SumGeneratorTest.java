package be.witspirit.mathex;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SumGeneratorTest {

    @Test
    public void sumGeneratorGeneratesDiverseCompliantSums() {
        SumGenerator generator = new SumGenerator(0, 10);
        List<Sum> sums = generator.generate(10);

        Assert.assertNotNull(sums);
        Assert.assertEquals(10, sums.size());

        Set<Integer> uniqueInput1 = new HashSet<Integer>();
        Set<String> uniqueOperator = new HashSet<String>();
        Set<Integer> uniqueInput2 = new HashSet<Integer>();
        Set<Integer> uniqueOutput = new HashSet<Integer>();

        for (Sum sum : sums) {
            System.out.println(sum.toString());

            Assert.assertTrue(sum.getInput1() >= 0 && sum.getInput1() <= 10);
            Assert.assertTrue(sum.getOperator().equals("+") || sum.getOperator().equals("-"));
            Assert.assertTrue(sum.getInput2() >= 0 && sum.getInput2() <= 10);
            Assert.assertTrue(sum.getOutput() >= 0 && sum.getOutput() <= 10);

            uniqueInput1.add(sum.getInput1());
            uniqueOperator.add(sum.getOperator());
            uniqueInput2.add(sum.getInput2());
            uniqueOutput.add(sum.getOutput());

        }

        Set<Sum> uniqueSums = new HashSet<Sum>(sums);
        Assert.assertTrue(uniqueSums.size()>1);

        Assert.assertTrue(uniqueInput1.size()>1);
        Assert.assertTrue(uniqueOperator.size()>1);
        Assert.assertTrue(uniqueInput2.size()>1);
        Assert.assertTrue(uniqueOutput.size()>1);

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

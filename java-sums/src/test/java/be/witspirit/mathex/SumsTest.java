package be.witspirit.mathex;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SumsTest {

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
    public void sumEqualityTst() {
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
        Assert.assertEquals("0+1=1", new Sum(0, "+", 1).toString());
    }

    @Test
    public void checkOutputCalculation() {
        Assert.assertEquals(1,new Sum(0, "+", 1).getOutput());
        Assert.assertEquals(2,new Sum(1, "+", 1).getOutput());
        Assert.assertEquals(2,new Sum(0, "+", 2).getOutput());
        Assert.assertEquals(-1,new Sum(0, "-", 1).getOutput());
    }
}

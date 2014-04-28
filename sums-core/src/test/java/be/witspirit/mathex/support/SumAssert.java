package be.witspirit.mathex.support;

import be.witspirit.mathex.Sum;
import be.witspirit.mathex.Sum3;
import org.junit.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Helpful assertions on Sums
 */
public class SumAssert {

    public static void assertCompliantAndDiverseSums(int size, int min, int max, List<Sum> sums) {
        if (size < 10) {
            throw new IllegalArgumentException("Minimum sample size = 10");
        }
        if (max - min < 3) {
            throw new IllegalArgumentException("Minimum difference between min and max = 3");
        }
        Assert.assertNotNull(sums);
        Assert.assertEquals(size, sums.size());

        Set<Integer> uniqueInput1 = new HashSet<>();
        Set<String> uniqueOperator = new HashSet<>();
        Set<Integer> uniqueInput2 = new HashSet<>();
        Set<Integer> uniqueOutput = new HashSet<>();

        for (Sum sum : sums) {
            System.out.println(sum.toString());

            Assert.assertTrue(sum.getInput1() >= min && sum.getInput1() <= max);
            Assert.assertTrue(sum.getOperator().equals("+") || sum.getOperator().equals("-"));
            Assert.assertTrue(sum.getInput2() >= min && sum.getInput2() <= max);
            Assert.assertTrue(sum.getOutput() >= min && sum.getOutput() <= max);

            uniqueInput1.add(sum.getInput1());
            uniqueOperator.add(sum.getOperator());
            uniqueInput2.add(sum.getInput2());
            uniqueOutput.add(sum.getOutput());

        }

        Set<Sum> uniqueSums = new HashSet<>(sums);
        Assert.assertTrue(uniqueSums.size()>1);

        Assert.assertTrue("Insufficient variation on input1", uniqueInput1.size()>1);
        Assert.assertTrue("Insufficient variation on operator", uniqueOperator.size()>1);
        Assert.assertTrue("Insufficient variation on input2", uniqueInput2.size()>1);
        Assert.assertTrue("Insufficient variation on output", uniqueOutput.size()>1);
    }

    public static void assertCompliantAndDiverseSum3s(int size, int min, int max, List<Sum3> sums) {
        if (size < 10) {
            throw new IllegalArgumentException("Minimum sample size = 10");
        }
        if (max - min < 3) {
            throw new IllegalArgumentException("Minimum difference between min and max = 3");
        }
        Assert.assertNotNull(sums);
        Assert.assertEquals(size, sums.size());

        Set<Integer> uniqueInput1 = new HashSet<>();
        Set<Integer> uniqueInput2 = new HashSet<>();
        Set<Integer> uniqueInput3 = new HashSet<>();
        Set<Integer> uniqueOutput = new HashSet<>();

        for (Sum3 sum : sums) {
            System.out.println(sum.toString());

            Assert.assertTrue(sum.getT1() >= min && sum.getT1() <= max);
            Assert.assertTrue(sum.getT2() >= min && sum.getT2() <= max);
            Assert.assertTrue(sum.getT3() >= min && sum.getT3() <= max);
            Assert.assertTrue(sum.getOutput() >= min && sum.getOutput() <= max);

            uniqueInput1.add(sum.getT1());
            uniqueInput2.add(sum.getT2());
            uniqueInput3.add(sum.getT3());
            uniqueOutput.add(sum.getOutput());

        }

        Set<Sum3> uniqueSums = new HashSet<>(sums);
        Assert.assertTrue("Insufficient variation on Sum3s", uniqueSums.size()>1);

        Assert.assertTrue("Insufficient variation on T1", uniqueInput1.size()>1);
        Assert.assertTrue("Insufficient variation on T2", uniqueInput2.size()>1);
        Assert.assertTrue("Insufficient variation on T3", uniqueInput3.size()>1);
        Assert.assertTrue("Insufficient variation on output", uniqueOutput.size()>1);
    }
}

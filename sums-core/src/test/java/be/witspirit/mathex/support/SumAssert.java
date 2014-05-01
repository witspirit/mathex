package be.witspirit.mathex.support;

import be.witspirit.mathex.Sum;
import be.witspirit.mathex.Sum3;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Helpful assertions on Sums
 */
public class SumAssert {

    private static final Set<String> VALID_OPERATORS = new HashSet<>(Arrays.asList(new String[] {"+", "-"} ));


    public static void assertCompliantAndDiverseSums(int size, int min, int max, List<Sum> sums) {
        List<SumRep> sumReps = sums.stream().map(sum -> new SumRepAdapter(sum)).collect(Collectors.toList());
        assertCompliantAndDiverseSumReps(size, min, max, true, sumReps);
    }

    public static void assertCompliantAndDiverseSum3s(int size, int min, int max, List<Sum3> sums) {
        List<SumRep> sumReps = sums.stream().map(sum -> new Sum3RepAdapter(sum)).collect(Collectors.toList());
        assertCompliantAndDiverseSumReps(size, min, max, false, sumReps);
    }

    public static void assertCompliantAndDiverseSumReps(int size, int min, int max, boolean checkOperatorDiversity, List<SumRep> sums) {
        if (size < 10) {
            throw new IllegalArgumentException("Minimum sample size = 10");
        }
        if (max - min < 3) {
            throw new IllegalArgumentException("Minimum difference between min and max = 3");
        }
        Assert.assertNotNull(sums);
        Assert.assertEquals(size, sums.size());

        Map<Integer, Set<Integer>> uniqueTerms = new TreeMap<>();
        Map<Integer, Set<String>> uniqueOperators = new TreeMap<>();
        Set<Integer> uniqueOutputs = new HashSet<>();

        for (SumRep sum : sums) {
            System.out.println(sum.toString());

            // Verify all terms are within limits
            for (int i=0; i < sum.getNrOfTerms(); i++) {
                int term = sum.getTerm(i);
                Assert.assertTrue("Term "+i+" of "+sum+" does not fit the bounds", term >= min && term <= max);

                Set<Integer> terms = uniqueTerms.get(i);
                if (terms == null) {
                    terms = new HashSet<>();
                    uniqueTerms.put(i, terms);
                }
                terms.add(term);
            }

            // Verify all operators are valid operators
            for (int i=0; i < sum.getNrOfTerms()-1; i++) {
                String operator = sum.getOperator(i);
                Assert.assertTrue("Operator "+i+" of "+sum+" is not a valid operator", VALID_OPERATORS.contains(operator));

                Set<String> operators = uniqueOperators.get(i);
                if (operators == null) {
                    operators = new HashSet<>();
                    uniqueOperators.put(i, operators);
                }
                operators.add(operator);
            }

            // Verify output within limits
            int output = sum.getOutput();
            Assert.assertTrue("Output of "+sum+" does not fit the bounds",output >= min && output <= max);
            uniqueOutputs.add(output);
        }

        Set<SumRep> uniqueSums = new HashSet<>(sums);
        Assert.assertTrue(uniqueSums.size()>1);

        for (int i=0; i < uniqueTerms.size(); i++) {
            Set<Integer> terms = uniqueTerms.get(i);
            Assert.assertTrue("Insufficient variation on term "+i, terms.size()>1);
        }

        if (checkOperatorDiversity) {
            for (int i = 0; i < uniqueOperators.size(); i++) {
                Set<String> operators = uniqueOperators.get(i);
                Assert.assertTrue("Insufficient variation on operator " + i, operators.size() > 1);
            }
        }

        Assert.assertTrue("Insufficient variation on output", uniqueOutputs.size()>1);
    }
}

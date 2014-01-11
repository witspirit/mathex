package be.witspirit.mathex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SumGenerator {
    private final int minValue;
    private final int maxValue;
    private final Random random = new Random();

    public SumGenerator(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public List<Sum> generate(int nrOfSums) {
        ArrayList<Sum> sums = new ArrayList<Sum>();
        for (int i = 0; i < 10; i++) {
            Sum sum = null;
            int output = minValue - 1;
            while (output < minValue || output > maxValue) {
                int input1 = generateInput(random);
                int input2 = generateInput(random);

                sum = new Sum(input1, random.nextBoolean() ? "+" : "-", input2);
                output = sum.getOutput();
            }
            sums.add(sum);
        }
        return sums;
    }

    private int generateInput(Random random) {
        return minValue + random.nextInt(maxValue + 1 - minValue);
    }
}

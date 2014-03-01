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
        ArrayList<Sum> sums = new ArrayList<>();
        for (int i = 0; i < nrOfSums; i++) {
            Sum sum = generateSum();
            sums.add(sum);
        }
        return sums;
    }

    public Sum generateSum() {
        Sum sum = null;
        int output = minValue - 1;
        while (output < minValue || output > maxValue) {
            int input1 = generateInput();
            String operator = random.nextBoolean() ? "+" : "-";
            int input2 = generateInput();

            sum = new Sum(input1, operator, input2);
            output = sum.getOutput();
        }
        return sum;
    }

    private int generateInput() {
        return minValue + random.nextInt(maxValue + 1 - minValue);
    }
}

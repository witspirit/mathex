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

    public Sum3 generateSum3() {
        Sum3 sum = null;
        int output = minValue - 1;
        while (output < minValue || output > maxValue) {
            int input1 = generateInput();
            int input2 = generateInput();
            int input3 = generateInput();

            sum = new Sum3(input1, input2, input3);
            output = sum.getOutput();
        }
        return sum;
    }

    public List<Sum3> generate3(int nrOfSums) {
        List<Sum3> sums = new ArrayList<>(nrOfSums);
        for (int i=0; i < nrOfSums; i++) {
            sums.add(generateSum3());
        }
        return sums;
    }
}

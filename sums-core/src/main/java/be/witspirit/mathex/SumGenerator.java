package be.witspirit.mathex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class SumGenerator {
    private final int minValue;
    private final int maxValue;
    private final Random random = new Random();

    public SumGenerator(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Sum sum() {
        return ensureOutputWithinBounds(() -> new Sum(generateInput(), generateOperator(), generateInput()));
    }

    public List<Sum> generateSum(int nrOfSums) {
        return generate(nrOfSums, this::sum);
    }


    public Sum3 sum3() {
        return ensureOutputWithinBounds(() -> new Sum3(generateInput(), generateInput(), generateInput()));
    }

    public List<Sum3> generateSum3(int nrOfSums) {
        return generate(nrOfSums, this::sum3);
    }


    private String generateOperator() {
        return random.nextBoolean() ? "+" : "-";
    }

    private int generateInput() {
        return minValue + random.nextInt(maxValue + 1 - minValue);
    }

    private <T extends HasOutput> T ensureOutputWithinBounds(Supplier<T> sumSupplier) {
        T sum = null;
        int output = minValue -1;
        while (output < minValue || output > maxValue) {
            sum = sumSupplier.get();
            output = sum.getOutput();
        }
        return sum;
    }

    private <T> List<T> generate(int nrOfSums, Supplier<T> supplier) {
        List<T> sums = new ArrayList<>(nrOfSums);
        for (int i=0; i < nrOfSums; i++) {
            sums.add(supplier.get());
        }
        return sums;
    }

    

}

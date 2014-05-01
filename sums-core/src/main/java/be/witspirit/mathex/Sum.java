package be.witspirit.mathex;

public class Sum implements HasOutput {
    private int input1;
    private int input2;
    private String operator="+";
    private int output;

    public Sum(int input1, String operator, int input2) {
        this.input1 = input1;
        this.operator = operator;
        this.input2 = input2;
        this.output = calculateOutput();
    }

    private int calculateOutput() {
        return operator.equals("+") ? input1 + input2 : input1 - input2;
    }

    public int getInput1() {
        return input1;
    }

    public String getOperator() {
        return operator;
    }

    public int getInput2() {
        return input2;
    }

    public int getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return input1 + " " + operator + " " + input2 + " = " + output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sum sum = (Sum) o;

        if (input1 != sum.input1) return false;
        if (input2 != sum.input2) return false;
        return operator.equals(sum.operator);
    }

    @Override
    public int hashCode() {
        int result = input1;
        result = 31 * result + input2;
        result = 31 * result + operator.hashCode();
        return result;
    }

}

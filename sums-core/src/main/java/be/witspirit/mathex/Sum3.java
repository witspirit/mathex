package be.witspirit.mathex;

/**
 * A Sum with 3 terms. The plus operator is assumed.
 */
public class Sum3 implements HasOutput {


    private final int t1;
    private final int t2;
    private final int t3;

    public Sum3(int t1, int t2, int t3) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    public int getT1() {
        return t1;
    }

    public int getT2() {
        return t2;
    }

    public int getT3() {
        return t3;
    }

    public int getOutput() {
        return t1+t2+t3;
    }

    @Override
    public String toString() {
        return t1 + " + " + t2 + " + " + t3 + " = " + getOutput();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sum3 sum3 = (Sum3) o;

        if (t1 != sum3.t1) return false;
        if (t2 != sum3.t2) return false;
        if (t3 != sum3.t3) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = t1;
        result = 31 * result + t2;
        result = 31 * result + t3;
        return result;
    }
}

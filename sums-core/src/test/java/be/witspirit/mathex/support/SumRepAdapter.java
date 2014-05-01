package be.witspirit.mathex.support;

import be.witspirit.mathex.Sum;

/**
 * Adapts a Sum to the SumRep interface
 */
public class SumRepAdapter implements SumRep {

    private Sum sum;

    public SumRepAdapter(Sum sum) {
        this.sum = sum;
    }


    @Override
    public int getNrOfTerms() {
        return 2;
    }

    @Override
    public int getTerm(int termIndex) {
        switch (termIndex) {
            case 0 : return sum.getInput1();
            case 1 : return sum.getInput2();
        }
        throw new IllegalArgumentException("No term with index "+termIndex+". Only indexes between 0 and "+getNrOfTerms()+" are allowed.");
    }

    @Override
    public String getOperator(int operatorIndex) {
        if (operatorIndex == 0) {
            return sum.getOperator();
        }
        throw new IllegalArgumentException("No operator with index "+operatorIndex+". Only indexes between 0 and "+(getNrOfTerms()-1)+" are allowed.");
    }

    @Override
    public int getOutput() {
        return sum.getOutput();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SumRepAdapter that = (SumRepAdapter) o;

        if (!sum.equals(that.sum)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sum.hashCode();
    }

    @Override
    public String toString() {
        return sum.toString();
    }
}

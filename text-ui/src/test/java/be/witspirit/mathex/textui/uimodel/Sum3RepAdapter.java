package be.witspirit.mathex.textui.uimodel;

import be.witspirit.mathex.Sum3;

/**
 * Adapts a Sum3 to the SumRep interface
 */
public class Sum3RepAdapter implements SumRep {

    private Sum3 sum;

    public Sum3RepAdapter(Sum3 sum) {
        this.sum = sum;
    }


    @Override
    public int getNrOfTerms() {
        return 3;
    }

    @Override
    public int getTerm(int termIndex) {
        switch (termIndex) {
            case 0 : return sum.getT1();
            case 1 : return sum.getT2();
            case 2 : return sum.getT3();
        }
        throw new IllegalArgumentException("No term with index "+termIndex+". Only indexes between 0 and "+getNrOfTerms()+" are allowed.");
    }

    @Override
    public String getOperator(int operatorIndex) {
        switch (operatorIndex) {
            case 0 : return "+";
            case 1 : return "+";
        }
        throw new IllegalArgumentException("No operator with index "+operatorIndex+". Only indexes between 0 and "+(getNrOfTerms()-1)+" are allowed.");
    }

    @Override
    public int getOutput() {
        return sum.getOutput();
    }
}

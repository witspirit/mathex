package be.witspirit.mathex.textui.uimodel;

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
}

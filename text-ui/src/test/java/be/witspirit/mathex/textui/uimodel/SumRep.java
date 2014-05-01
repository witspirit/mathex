package be.witspirit.mathex.textui.uimodel;

/**
 * Wrapper representation of a Sum with a unified interface
 */
public interface SumRep {

    int getNrOfTerms();

    int getTerm(int termIndex);

    String getOperator(int operatorIndex);

    int getOutput();
}

package be.witspirit.mathex.textui;

/**
 * Keeps track of statistics of an exercise session
 */
public class Stats {
    private int total = 0;
    private int correct = 0;
    private int fault = 0;

    /**
     * @return Nr of exercises presented to the user. Excludes repeated sums due to faults
     */
    public int getTotal() {
        return total;
    }

    /**
     * @return Nr of exercises answered correctly at the first try
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * @return Nr of exercises answered faulty. Counts each faulty attempt !
     */
    public int getFault() {
        return fault;
    }

    public Stats incTotal() {
        total++;
        return this;
    }

    public Stats incCorrect() {
        correct++;
        return this;
    }

    public Stats incFault() {
        fault++;
        return this;
    }
}

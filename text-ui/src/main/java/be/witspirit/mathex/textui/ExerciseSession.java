package be.witspirit.mathex.textui;

/**
 * Represents the state of an ExerciseSession and is made available to the CommandHandlers
 */
public class ExerciseSession {
    private boolean running = false;
    private Stats stats = new Stats();

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public Stats getStats() {
        return stats;
    }
}

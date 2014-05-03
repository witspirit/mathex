package be.witspirit.mathex.textui;


import be.witspirit.mathex.*;

import java.io.*;
import java.util.function.Supplier;

public class TextUi {

    private CommandHandler commandHandler = new StartHandler();
    private Stats stats = new Stats();
    private boolean run = false;

    public String start() {
        run = true;
        return commandHandler.instructUser();
    }

    public String command(String command) {
        String feedback = commandHandler.handleUserInput(command);
        String instruction = commandHandler.instructUser();
        return (feedback == null || feedback.trim().isEmpty() ? "" : feedback+"\n") + instruction;
    }

    public Stats getStats() {
        return stats;
    }

    private class CommonHandler implements CommandHandler {

        @Override
        public String instructUser() {
            return "";
        }

        @Override
        public String handleUserInput(String userInput) {
            userInput = userInput.toUpperCase();
            switch (userInput) {
                case "S":
                    commandHandler = new StopHandler();
                    return "Tot gauw !";
                case "I":
                    StringBuilder sb = new StringBuilder();
                    sb.append("Statistieken\n");
                    sb.append("Correct = ").append(stats.getCorrect()).append("\n");
                    sb.append("Fout = ").append(stats.getFault()).append("\n");
                    sb.append("Totaal = ").append(stats.getTotal()).append("\n");
                    double score = ((double) stats.getCorrect() / (double) stats.getTotal()) * 100;
                    sb.append("Score = ").append(String.format("%2.2f %%", score));
                    return sb.toString();
            }
            return "Antwoord niet begrepen";
        }
    }

    private int num(String value) {
        return Integer.parseInt(value);
    }


    private class StartHandler extends CommonHandler {

        @Override
        public String instructUser() {
            return "Welkom bij rekenoefeningen !\n" +
                   "Tot hoeveel mogen de oefeningen gaan ? ";
        }

        @Override
        public String handleUserInput(String userInput) {
            try {
                int max = num(userInput);

                commandHandler = new TypeSelectionHandler(max);
                return "";

            } catch (NumberFormatException nfe) {
                return super.handleUserInput(userInput);
            }
        }

    }

    private class TypeSelectionHandler extends CommonHandler {

        private int maxValue;

        public TypeSelectionHandler(int maxValue) {
            this.maxValue = maxValue;
        }

        @Override
        public String instructUser() {
            return "Welk soort oefeningen had je graag gemaakt ?\n" +
                    "A) 1 + 2 = 3\n" +
                    "B) 1 + 2 + 3 = 6\n" +
                    "Jouw keuze: ";
        }

        @Override
        public String handleUserInput(String userInput) {
            SumGenerator sumGenerator = new SumGenerator(0, maxValue);
            switch (userInput.toUpperCase()) {
                case "A" :
                    // Sum
                    commandHandler = new ExerciseHandler<>(sumGenerator::sum);
                    break;
                case "B" :
                    // Sum3
                    commandHandler = new ExerciseHandler<>(sumGenerator::sum3);
                    break;
                default:
                   return super.handleUserInput(userInput);
            }
            return "Ok, hier gaan we...";
        }

    }

    private class StopHandler extends CommonHandler {
        @Override
        public String instructUser() {
            run = false;
            return "";
        }

        @Override
        public String handleUserInput(String userInput) {
            throw new IllegalStateException("Ui has been stopped");
        }
    }

    private class ExerciseHandler<T extends Exercise> extends CommonHandler {

        private Supplier<T> exerciseSupplier;
        protected T currentExercise = null;
        private boolean repeated = false;

        public ExerciseHandler(Supplier<T> exerciseSupplier) {
            this.exerciseSupplier = exerciseSupplier;
            currentExercise = exerciseSupplier.get(); // We don't count the first exercise, as the last one will not be answered either
        }

        @Override
        public String instructUser() {
            return currentExercise.getExerciseRep();
        }

        @Override
        public String handleUserInput(String command) {
            try {
                int solution = num(command);

                if (solution == currentExercise.getOutput()) {
                    if (!repeated) {
                        stats.incCorrect();
                    }

                    repeated = false;
                    currentExercise = exerciseSupplier.get();
                    stats.incTotal();

                    return "Juist !";
                } else {
                    // On mistake we keep the current sum
                    stats.incFault();
                    repeated = true;
                    return "Fout !";
                }

            } catch (NumberFormatException nfe) {
                return super.handleUserInput(command);
            }
        }
    }

    public static void main(String... args) throws IOException {
        TextUi ui = new TextUi();
        ui.runConsole(System.out, System.in);
    }

    public void runConsole(PrintStream out, InputStream in) throws IOException {
        out.print(start());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while (run) {
            String userInput = reader.readLine();
            out.print(command(userInput));
        }
    }
}

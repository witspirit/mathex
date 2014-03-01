package be.witspirit.mathex.textui;


import be.witspirit.mathex.Sum;
import be.witspirit.mathex.SumGenerator;

import java.io.*;

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
        return feedback+"\n"+instruction;
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

                commandHandler = new ExerciseHandler(new SumGenerator(0, max));
                return "Ok, hier gaan we...";

            } catch (NumberFormatException nfe) {
                return super.handleUserInput(userInput);
            }
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

    private class ExerciseHandler extends CommonHandler {

        private SumGenerator sumGen;
        private Sum currentSum = null;
        private boolean repeated = false;

        public ExerciseHandler(SumGenerator sumGen) {
            this.sumGen = sumGen;
            currentSum = sumGen.generateSum(); // We don't count the first exercise, as the last one will not be answered either
        }

        @Override
        public String instructUser() {
            return String.format("%d %s %d = ", currentSum.getInput1(), currentSum.getOperator(), currentSum.getInput2());
        }

        @Override
        public String handleUserInput(String command) {
            try {
                int solution = num(command);

                if (solution == currentSum.getOutput()) {
                    if (!repeated) {
                        stats.incCorrect();
                    }

                    repeated = false;
                    currentSum = sumGen.generateSum();
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

package be.witspirit.mathex.textui;


import be.witspirit.mathex.Sum;
import be.witspirit.mathex.SumGenerator;

public class TextUi {

    private CommandHandler commandHandler = new StartHandler();

    public String start() {
        return commandHandler.instructUser();
    }

    public String command(String command) {
        String feedback = commandHandler.handleUserInput(command);
        String instruction = commandHandler.instructUser();
        return feedback+"\n"+instruction;
    }

    private class CommonHandler implements CommandHandler {

        @Override
        public String instructUser() {
            return "";
        }

        @Override
        public String handleUserInput(String userInput) {
            switch (userInput) {
                case "S":
                    commandHandler = new StopHandler();
                    return "Tot gauw !";
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
                   "Tot hoeveel mogen de oefeningen gaan ?";
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

        public ExerciseHandler(SumGenerator sumGen) {
            this.sumGen = sumGen;
        }

        @Override
        public String instructUser() {
            currentSum = sumGen.generateSum();
            return String.format("%d %s %d = ", currentSum.getInput1(), currentSum.getOperator(), currentSum.getInput2());
        }

        @Override
        public String handleUserInput(String command) {
            try {
                int solution = num(command);

                if (solution == currentSum.getOutput()) {
                    return "Juist !";
                } else {
                    return "Fout !";
                }

            } catch (NumberFormatException nfe) {
                return super.handleUserInput(command);
            }
        }
    }
}

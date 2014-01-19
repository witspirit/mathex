package be.witspirit.mathex.textui;


public class TextUi {

    private CommandHandler commandHandler = new StartHandler();

    public String start() {
        return "Welkom bij rekenoefeningen !\nTot hoeveel mogen de oefeningen gaan ?\n";
    }

    public String command(String command) {
        return commandHandler.handle(command);
    }

    private class CommonHandler implements CommandHandler {

        @Override
        public String handle(String command) {
            switch (command) {
                case "S" : return "Tot gauw !\n";
            }
            return "Antwoord niet begrepen\n";
        }
    }


    private class StartHandler extends CommonHandler {

        @Override
        public String handle(String command) {
            switch (command) {
                case "0":
                    commandHandler = new ExerciseHandler();
                    return "Ok, hier gaan we...\n0 + 0 = ";
            }
            return super.handle(command);
        }

    }

    private class ExerciseHandler extends CommonHandler {

       @Override
        public String handle(String command) {
            switch (command) {
                case "0" : return "Juist !\n0 - 0 = ";
                case "6" : return "Fout !\n0 - 0 = ";
            }
           return super.handle(command);
        }
    }
}

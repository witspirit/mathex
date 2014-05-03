package be.witspirit.mathex.textui;


import be.witspirit.mathex.textui.handlers.StartHandler;

import java.io.*;

public class TextUi {

    private CommandHandler commandHandler = new StartHandler();

    private ExerciseSession session = new ExerciseSession();

    public String start() {
        return commandHandler.instructUser(session);
    }

    public String command(String command) {
        FeedbackAndHandler response = commandHandler.handleUserInput(command, session);
        String feedback = response.getFeedback();
        commandHandler = response.getCommandHandler();
        String instruction = commandHandler.instructUser(session);
        return (feedback == null || feedback.trim().isEmpty() ? "" : feedback+"\n") + instruction;
    }

    public Stats getStats() {
        return session.getStats();
    }


    public static void main(String... args) throws IOException {
        TextUi ui = new TextUi();
        ui.runConsole(System.out, System.in);
    }

    public void runConsole(PrintStream out, InputStream in) throws IOException {
        out.print(start());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while (session.isRunning()) {
            String userInput = reader.readLine();
            out.print(command(userInput));
        }
    }
}

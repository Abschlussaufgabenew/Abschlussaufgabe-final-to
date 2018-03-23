package edu.kit.informatik;

public class Main {
    /**
     * The main method that is the entry point to the program.
     *
     * @param args An array of command line arguments.
     */
    public static void main(String[] args) {     
        OlympicGames olympicGames = new OlympicGames();
        Commands command = null;
        do {
            try {
                command = Commands.executeMatching(Terminal.readLine(), olympicGames);
            } catch (InputException e) {
                Terminal.printError(e.getMessage());
            }
        } while (command == null || command.isRunning());
    }
}
package zuul.io.userInterfaces;

import zuul.Game;
import zuul.GameInterface;

import java.util.Scanner;

/**
 * A command line {@link UserInterface} for the "World of Zuul" application.
 * <p>
 * This user interface uses the standard terminal command line to display messages and get input from the user.
 *
 * @author Timothy Shelton
 */
public class CommandLineInterface implements UserInterface {

    /**
     * The input scanner for all user input.
     */
    private Scanner reader = new Scanner(System.in);

    /**
     * Sets the {@link GameInterface} to an instance of this class and starts the {@link Game}.
     * <p>
     * This method should be called by {@link zuul.Main} to initialise this user interface.
     *
     * @param args  the program arguments, not used
     */
    public static void main(String[] args) {
        GameInterface.set(new CommandLineInterface());

        Game.getInstance().play();
    }

    /**
     * Does nothing. This interface does not change its behaviour based on {@link Game} events.
     *
     * @param event  a string that describes the event that immediately follows this update, not null
     */
    @Override
    public void update(String event) {
        //Do nothing, we don't want to change the standard behaviour.
    }

    /**
     * Prints the specified string to the standard output stream {@code System.out}.
     *
     * @param str  the string requested to be printed to the player, not null
     */
    @Override
    public void print(String str) {
        System.out.print(str);
    }

    /**
     * Prints a new line to the standard output stream {@code System.out}.
     */
    @Override
    public void printNextln() {
        System.out.println();
    }

    /**
     * Gets the next line of input from the standard input stream {@code System.in}.
     *
     * @return the next line of input from {@code System.in}.
     */
    @Override
    public String getNextLine() {
        return reader.nextLine();
    }
}

package zuul.commands;

import zuul.Command;

import java.util.ArrayList;

public class QuitCommand extends Command {

    /**
     * Constructor
     */
    public QuitCommand() {
        super("quit");
    }

    /**
     * Constructor
     */
    public QuitCommand(ArrayList<String> modifiers) {
        super("quit");
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return True, if this command quits the game, false otherwise.
     */
    @Override
    public boolean execute() {
        if (hasModifiers()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}

package zuul.commands;

import zuul.Command;
import zuul.Game;

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
     * Execute the command.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return execute(Game.getInstance());
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return True, if this command quits the game, false otherwise.
     */
    public boolean execute(Game game) {
        if (hasModifiers()) {
            System.out.println("Quit what?");
            return false;
        } else {
            game.finish(); // signal that we want to quit
            return true;
        }
    }
}

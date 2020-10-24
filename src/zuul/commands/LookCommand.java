package zuul.commands;

import zuul.Command;
import zuul.Game;

public class LookCommand extends Command {

    /**
     * Constructor
     */
    public LookCommand() {
        super("Look");
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
     * Execute the command
     * @return false, we do not want to quit.
     */
    public boolean execute(Game game) {
        game.getCurrentRoom().printInfo();
        return false;
    }
}

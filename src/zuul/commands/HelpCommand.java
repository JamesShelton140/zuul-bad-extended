package zuul.commands;

import zuul.*;

public class HelpCommand extends Command {

    /**
     * Constructor
     */
    public HelpCommand() {
        super("help");
    }

    /**
     * Execute the command.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return false;
    }
}

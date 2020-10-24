package zuul.commands;

import zuul.Command;

import java.util.ArrayList;

public class DropCommand extends Command {

    /**
     * Constructor
     */
    public DropCommand(ArrayList<String> modifiers) {
        super("drop", modifiers);
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

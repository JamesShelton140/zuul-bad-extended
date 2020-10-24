package zuul.commands;

import zuul.Command;

import java.util.ArrayList;

public class GiveCommand extends Command {

    /**
     * Constructor
     */
    public GiveCommand(ArrayList<String> modifiers) {
        super("give", modifiers);
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

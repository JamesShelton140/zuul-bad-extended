package zuul.commands;

import zuul.Character;
import zuul.Command;
import zuul.Game;

import java.util.ArrayList;

public class LookCommand extends Command {

    /**
     * Constructor
     */
    public LookCommand() {
        super("look");
    }

    /**
     * Constructor
     */
    public LookCommand(ArrayList<String> modifiers) {
        super("look");
    }

    /**
     * Execute the command.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return execute(Game.getInstance().getCharacter(0));
    }

    /**
     * Execute the command
     * @return True if command executes successfully, false otherwise.
     * @param character
     */
    public boolean execute(zuul.Character character) {
        character.getCurrentRoom().printInfo();
        return true;
    }
}

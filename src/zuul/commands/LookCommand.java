package zuul.commands;

import zuul.*;
import zuul.Character;

import java.util.ArrayList;

public class LookCommand extends Command {

    /**
     * Constructor
     */
    public LookCommand() {
        super(GameText.getString("CommandWordsBundle", "look"));
    }

    /**
     * Constructor
     */
    public LookCommand(ArrayList<String> modifiers) {
        super(GameText.getString("CommandWordsBundle", "look"));
    }

    /**
     * Execute the command
     * @return True if command executes successfully, false otherwise.
     * @param character
     */
    @Override
    public boolean commandLogic(zuul.Character character) {
        character.getCurrentRoom().printInfo();
        character.act(); //allow character to perform another action
        return true;
    }
}

package zuul.commands;

import zuul.Character;
import zuul.Command;
import zuul.Game;
import zuul.GameText;

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
    public boolean execute(zuul.Character character) {
        character.getCurrentRoom().printInfo();
        return true;
    }
}

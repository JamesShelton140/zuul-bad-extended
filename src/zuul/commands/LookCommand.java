package zuul.commands;

import zuul.*;
import zuul.Character;

import java.util.ArrayList;

/**
 * A Look {@link Command} for the "World of Zuul" application.
 * This command prints the state of the current {@link Room} of a {@link zuul.Character}.
 * <p>
 * This command takes no modifier words.
 *
 * @author Timothy Shelton
 */
public class LookCommand extends Command {

    /**
     * Constructor without modifiers.
     *
     * Initialises a {@link Command} with locale-dependent command word of the key "look".
     */
    public LookCommand() {
        super(GameText.getString("CommandWordsBundle", "look"));
    }

    /**
     * Constructor with modifiers.
     *
     * Initialises a {@link Command} with locale-dependent command word of the key "look".
     */
    public LookCommand(ArrayList<String> modifiers) {
        super(GameText.getString("CommandWordsBundle", "look"));
    }

    /**
     * Prints the state information of the current {@link Room} of the specified {@link Character}.
     * <p>
     *     Allows the {@link Character} looking around its room to act on another command before returning.
     * </p>
     *
     * @param character the Character looking around its current room
     * @return true always
     */
    @Override
    public boolean commandLogic(zuul.Character character) {
        character.getCurrentRoom().printInfo(); //look around the room
        character.act(); //allow character to perform another action
        return true;
    }
}

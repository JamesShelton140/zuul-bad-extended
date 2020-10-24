package zuul.commands;

import java.util.ArrayList;
import zuul.*;

public class TakeCommand extends Command {

    /**
     * Constructor
     */
    public TakeCommand(ArrayList<String> modifiers) {
        super("take", modifiers);
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
     * Execute the command. Try to take an item from the current room, otherwise print an error
     * message.
     * @param character The character that is executing the command.
     * @return false, do not quit;
     */
    public boolean execute(zuul.Character character) {
        if (!hasModifiers()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return false;
        }

        String item = getModifier(0);
        int w = character.getCurrentRoom().containsItem(item);
        if (w == 0) {
            // The item is not in the room
            System.out.println("No " + item + " in the room");
            return false;
        }
        if (character.getTotalWeight() + w > character.getMAX_WEIGHT()) {
            // The player is carrying too much
            System.out.println(item + " is too heavy");
            return false;
        }
        // OK we can pick it up
        character.getCurrentRoom().removeItem(item);
        character.addItem(item);
        character.addWeight(w);
        System.out.println("You take the " + item);

        return false;
    }
}

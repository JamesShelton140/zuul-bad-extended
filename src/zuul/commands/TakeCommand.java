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
        return execute(Game.getInstance());
    }

    /**
     * Execute the command. Try to take an item from the current room, otherwise print an error
     * message.
     * @param game The running game.
     * @return false, do not quit;
     */
    public boolean execute(Game game) {
        if (!hasModifiers()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return false;
        }

        String item = getModifier(0);
        int w = game.getCurrentRoom().containsItem(item);
        if (w == 0) {
            // The item is not in the room
            System.out.println("No " + item + " in the room");
            return false;
        }
        if (game.getTotalWeight() + w > game.getMAX_WEIGHT()) {
            // The player is carrying too much
            System.out.println(item + " is too heavy");
            return false;
        }
        // OK we can pick it up
        game.getCurrentRoom().removeItem(item);
        game.addItem(item);
        game.addWeight(w);

        return false;
    }
}

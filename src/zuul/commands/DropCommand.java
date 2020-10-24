package zuul.commands;

import zuul.*;

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
        return execute(Game.getInstance());
    }

    /**
     * Execute the command. Try to drop an item, otherwise print an error message.
     * @param game The game object to modify.
     * @return False, we do not want to quit the game.
     */
    public boolean execute(Game game){
        if (!hasModifiers()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return false;
        }

        String item = getModifier(0);
        int i = game.getItemIndex(item);
        if (i == -1) {
            System.out.println("You don't have the " + item);
            return false;
        }
        game.removeItem(i);
        int w = (Integer) game.removeWeight(i);
        game.getCurrentRoom().addItem(item, w);
        return false;
    }
}

package zuul.commands;

import zuul.*;
import zuul.Character;

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
        return execute(Game.getInstance().getCharacter(0));
    }

    /**
     * Execute the command. Try to drop an item, otherwise print an error message.
     * @param character The character object to modify.
     * @return False, we do not want to quit the game.
     */
    public boolean execute(zuul.Character character){
        if (!hasModifiers()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return false;
        }

        String item = getModifier(0);
        int i = character.getItemIndex(item);
        if (i == -1) {
            System.out.println("You don't have the " + item);
            return false;
        }
        character.removeItem(i);
        int w = (Integer) character.removeWeight(i);
        character.getCurrentRoom().addItem(item, w);
        return false;
    }
}

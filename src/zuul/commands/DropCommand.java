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
     * @return True if command executes successfully, false otherwise.
     */
    public boolean execute(zuul.Character character){
        if (!hasModifiers()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return false;
        }

        String item = getModifier(0);
        int i = character.getInventory().getItemIndex(item);
        if (i == -1) {
            System.out.println("You don't have the " + item);
            return false;
        }
        character.getCurrentRoom().getInventory().addItem(character.getInventory().removeItem(i));
        System.out.println("You drop the " + item);
        return true;
    }
}

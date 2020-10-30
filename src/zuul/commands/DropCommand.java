package zuul.commands;

import zuul.*;
import zuul.Character;

import java.util.ArrayList;

public class DropCommand extends Command {

    /**
     * Constructor
     */
    public DropCommand(ArrayList<String> modifiers) {
        super(GameText.getString("CommandWordsBundle","drop"), modifiers);
    }

    /**
     * Execute the command. Try to drop an item, otherwise print an error message.
     * @param character The character object to modify.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute(zuul.Character character){
        if (!hasModifiers()) {
            // if there is no second word, we don't know what to drop...
            System.out.println(GameText.getString("dropHasNoModifiersError"));
            return false;
        }

        String item = getModifier(0);
        int i = character.getInventory().getItemIndex(item);
        if (i == -1) {
            System.out.println(GameText.getString("dropItemNotHeldError", new Object[]{item}));
            return false;
        }
        character.getCurrentRoom().getInventory().addItem(character.getInventory().removeItem(i));
        System.out.println(GameText.getString("dropSuccessful", new Object[]{item}));
        return true;
    }
}

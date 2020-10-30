package zuul.commands;

import zuul.*;
import zuul.Character;
import zuul.characters.Player;

import java.util.ArrayList;
import java.util.Optional;

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

        Optional<String> opItemName = getModifier(0);

        if (opItemName.isEmpty()) {
            // if there is no second word, we don't know what to drop...
            System.out.println(GameText.getString("dropHasNoModifiersError"));
            return false;
        }

        //modifier exists so unwrap it
        String itemName = opItemName.get();

        Optional<Item> opItem = character.getInventory().getItem(itemName);

        if (opItem.isEmpty()) {
            //Item not held by character
            System.out.println(GameText.getString("dropItemNotHeldError", new Object[]{itemName}));
            return false;
        }

        //Item found so unwrap Optional
        Item item = opItem.get();;

        character.getInventory().removeItem(item); //remove item from player
        character.getCurrentRoom().getInventory().addItem(item); //add item to room

        //if (character instanceof Player) {
            //Tell player that the command was successful
        System.out.println(GameText.getString("dropSuccessful", new Object[]{item}));
        //}

        return true;
    }
}

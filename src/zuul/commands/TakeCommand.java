package zuul.commands;

import java.util.ArrayList;
import java.util.Optional;

import zuul.*;
import zuul.characters.Player;

public class TakeCommand extends Command {

    /**
     * Constructor
     */
    public TakeCommand(ArrayList<String> modifiers) {
        super(GameText.getString("CommandWordsBundle", "take"), modifiers);
    }

    /**
     * Execute the command. Try to take an item from the current room, otherwise print an error
     * message.
     * @param character The character that is executing the command.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean commandLogic(zuul.Character character) {

        Optional<String> opItemName = getModifier(0);

        if (opItemName.isEmpty()) {
            // if there is no second word, we don't know what to take...
            updateErr("noModifier");
            zuul.io.Out.println(GameText.getString("takeNoModifiersError"));
            return false;
        }

        String itemName = opItemName.get();

        Optional<Item> opItem = character.getCurrentRoom().getInventory().getItem(itemName);

        if (opItem.isEmpty()) {
            // The item is not in the room
            updateErr("itemNotFound");
            zuul.io.Out.println(GameText.getString("takeItemNotInRoomError", new Object[]{itemName}));
            return false;
        }

        //The item was found, unwrap it from Optional<Item>
        Item item = opItem.get();

        if (character.getInventory().getTotalWeight() + item.getWeight() > character.getInventory().getMAX_WEIGHT()) {
            // The player is carrying too much
            updateErr("itemTooHeavy");
            zuul.io.Out.println(GameText.getString("takeItemTooHeavyError", new Object[]{itemName}));
            return false;
        }

        // OK we can pick it up
        character.getCurrentRoom().getInventory().removeItem(item); //Remove item from room
        character.getInventory().addItem(item); //Give item to player

        //if (character instanceof Player) {
            //Tell player that the command was successful
        zuul.io.Out.println(GameText.getString("takeSuccessful", new Object[]{itemName}));
        //}

        return true;
    }
}

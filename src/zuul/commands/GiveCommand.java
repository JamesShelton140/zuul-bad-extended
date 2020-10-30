package zuul.commands;

import zuul.*;
import zuul.Character;

import java.util.ArrayList;
import java.util.Optional;

public class GiveCommand extends Command {

    /**
     * Constructor
     */
    public GiveCommand(ArrayList<String> modifiers) {
        super(GameText.getString("CommandWordsBundle", "give"), modifiers);
    }

    /**
     * Execute the command. Try to give an item, otherwise print an error message.
     * @param character The game object to modify.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute(zuul.Character character) {
        Optional<String> opItemName = getModifier(0);

        if (opItemName.isEmpty()) {
            // if there is no second word, we don't know what to give...
            System.out.println(GameText.getString("giveNoItemError"));
            return false;
        }

        Optional<String> opWhom = getModifier(1);

        if (opWhom.isEmpty()) {
            // if there is no third word, we don't to whom to give it...
            System.out.println(GameText.getString("giveNoCharacterError"));
            return false;
        }

        //modifiers exist so unwrap them
        String itemName = opItemName.get();
        String whom = opWhom.get();


        //get the new character by name
        Optional<Character> opRecipient = character.getCurrentRoom().getCharacter(whom);

        if (opRecipient.isEmpty()) {
            // cannot give it if the character is not here
            System.out.println(GameText.getString("giveCharacterNotInRoomError", new Object[]{whom}));
            return false;
        }

        //Character found so unwrap Optional
        Character recipient = opRecipient.get();

        //Check if the item is currently held
        Optional<Item> opItem = character.getInventory().getItem(itemName);

        if (opItem.isEmpty()) {
            //Item not held by character
            System.out.println(GameText.getString("giveItemNotHeldError", new Object[]{itemName}));
            return false;
        }

        //Item found so unwrap Optional
        Item item = opItem.get();


        character.getInventory().removeItem(item); //remove the item from the current character
        recipient.getInventory().addItem(item);  //give item to new character
        System.out.println(GameText.getString("giveSuccessful", new Object[]{recipient.getName(), item}));
        return true;
    }

}

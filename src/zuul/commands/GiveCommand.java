package zuul.commands;

import zuul.*;
import zuul.Character;

import java.util.ArrayList;

public class GiveCommand extends Command {

    /**
     * Constructor
     */
    public GiveCommand(ArrayList<String> modifiers) {
        super(GameText.getString("CommandWordsBundle", "give"), modifiers);
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
     * Execute the command. Try to give an item, otherwise print an error message.
     * @param character The game object to modify.
     * @return True if command executes successfully, false otherwise.
     */
    public boolean execute(zuul.Character character) {
        if (!hasModifier(0)) {
            // if there is no second word, we don't know what to give...
            System.out.println(GameText.getString("giveNoItemError"));
            return false;
        }
        if (!hasModifier(1)) {
            // if there is no third word, we don't to whom to give it...
            System.out.println(GameText.getString("giveNoCharacterError"));
            return false;
        }

        String item = getModifier(0);
        String whom = getModifier(1);

        if (character.getCurrentRoom().getCharacters().stream().noneMatch(character1 -> character1.getName().equals(whom))) {
            // cannot give it if the character is not here
            System.out.println(GameText.getString("giveCharacterNotInRoomError", new Object[]{whom}));
            return false;
        }
        //Check if the item is currently held
        int i = character.getInventory().getItemIndex(item);
        if (i == -1) {
            System.out.println(GameText.getString("giveItemNotHeldError", new Object[]{item}));
            return false;
        }

        //get the new character by name
        Character recipient = character.getCurrentRoom().getCharacter(whom);

        //remove the item from the current character and give it to the new one
        recipient.getInventory().addItem(character.getInventory().removeItem(i));
        System.out.println(GameText.getString("giveSuccessful", new Object[]{recipient.getName(), item}));
        return true;
    }

}

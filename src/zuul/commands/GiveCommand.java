package zuul.commands;

import zuul.*;
import zuul.Character;

import java.util.ArrayList;

public class GiveCommand extends Command {

    /**
     * Constructor
     */
    public GiveCommand(ArrayList<String> modifiers) {
        super("give", modifiers);
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
     * @return False, we do not want to quit the game.
     */
    public boolean execute(zuul.Character character) {
        if (!hasModifier(0)) {
            // if there is no second word, we don't know what to give...
            System.out.println("Give what?");
            return false;
        }
        if (!hasModifier(1)) {
            // if there is no third word, we don't to whom to give it...
            System.out.println("Give it to who?");
            return false;
        }

        String item = getModifier(0);
        String whom = getModifier(1);

        if (!character.getCurrentRoom().getCharacters().contains(new Player(whom))) {
            // cannot give it if the character is not here
            System.out.println(whom + " is not in the room");
            return false;
        }
        //Check if the item is currently held
        int i = character.getItemIndex(item);
        if (i == -1) {
            System.out.println("You don't have the " + item);
            return false;
        }
        character.removeItem(i);
        character.removeWeight(i);
        return false;
    }

}

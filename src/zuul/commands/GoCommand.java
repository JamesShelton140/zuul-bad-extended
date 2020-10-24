package zuul.commands;

import zuul.*;
import zuul.Character;

import java.util.ArrayList;

public class GoCommand extends Command {

    /**
     * Constructor
     */
    public GoCommand(ArrayList<String> modifiers) {
        super("go", modifiers);
    }

    /**
     * Execute the command. Try to go to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     * @param character The character object to execute the command on.
     * @return True if command executes successfully, false otherwise.
     */
    public boolean execute(Character character) {
        if (!hasModifiers()) {
            // if there is no modifier, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = getModifier(0);

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = character.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return false;
        } else {
            character.setCurrentRoom(nextRoom);
            character.getCurrentRoom().printInfo();
            return false;
        }
    }

    /**
     * Execute the command.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return execute(Game.getInstance().getCharacter(0));
    }
}

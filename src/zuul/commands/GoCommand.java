package zuul.commands;

import zuul.*;
import zuul.Character;

import java.util.ArrayList;

public class GoCommand extends Command {

    /**
     * Constructor
     */
    public GoCommand(ArrayList<String> modifiers) {
        super(GameText.getString("CommandWordsBundle", "go"), modifiers);
    }

    /**
     * Execute the command. Try to move the selected character to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     * @param character The character object to execute the command on.
     * @return True if command executes successfully, false otherwise.
     */
    public boolean execute(Character character) {
        if (!hasModifiers()) {
            // if there is no modifier, we don't know where to go...
            System.out.println(GameText.getString("goHasNoModifiersError"));
            return false;
        }

        String direction = getModifier(0);

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = character.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println(GameText.getString("goNoExitError"));
            return false;
        } else {
            character.getCurrentRoom().removeCharacter(character);  //leave current room
            character.setCurrentRoom(nextRoom); //enter next room
            nextRoom.addCharacter(character); //enter next room
            nextRoom.printInfo(); //look around next room
            return true;
        }
    }

    /**
     * Call execute(Character) to show the Game instance is being used despite not being passed in the method call.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return execute(Game.getInstance().getCharacter(0));
    }
}

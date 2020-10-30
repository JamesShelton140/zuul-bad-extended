package zuul.commands;

import zuul.*;
import zuul.Character;

import java.util.ArrayList;
import java.util.Optional;

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
    @Override
    public boolean execute(Character character) {

        Optional<String> opDirection = getModifier(0);

        if (opDirection.isEmpty()) {
            // if there is no modifier, we don't know where to go...
            System.out.println(GameText.getString("goHasNoModifiersError"));
            return false;
        }

        String direction = opDirection.get();

        // Try to leave current room.
        Optional<Room> opNextRoom = character.getCurrentRoom().getExit(direction);

        if (opNextRoom.isEmpty()) {
            System.out.println(GameText.getString("goNoExitError"));
            return false;
        } else {
            //Exit room exists so unwrap it
            Room nextRoom = opNextRoom.get();

            character.getCurrentRoom().removeCharacter(character);  //leave current room
            character.setCurrentRoom(nextRoom); //enter next room
            nextRoom.addCharacter(character); //enter next room
            //nextRoom.printInfo(); //look around next room
            System.out.println(GameText.getString("goSuccessful", new Object[]{direction}));
            return true;
        }
    }
}

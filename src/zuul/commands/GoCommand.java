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
     * Ties to move the specified character to a new {@link Room} in one direction.
     *
     * If there is an exit, enters the new room, otherwise prints an error message.
     *
     * @param character The character object to execute the command on.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean commandLogic(Character character) {

        Optional<String> opDirection = getModifier(0);

        if (opDirection.isEmpty()) {
            // if there is no modifier, we don't know where to go...
            updateErr("noModifier");
            zuul.io.Out.println(GameText.getString("goHasNoModifiersError"));
            return false;
        }

        String direction = opDirection.get();

        // Try to leave current room.
        Optional<Room> opNextRoom = character.getCurrentRoom().getExit(direction);

        if (opNextRoom.isEmpty()) {
            //Cannot go in a direction if there is no exit
            updateErr("noExit");
            zuul.io.Out.println(GameText.getString("goNoExitError"));
            return false;
        } else {
            //Exit room exists so unwrap it
            Room nextRoom = opNextRoom.get();

            character.moveRoom(nextRoom); //Move character to next room

            zuul.io.Out.println(GameText.getString("goSuccessful", new Object[]{direction}));
            zuul.io.Out.println();

            nextRoom.printInfo(); //look around next room
            return true;
        }
    }
}

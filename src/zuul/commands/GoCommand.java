package zuul.commands;

import zuul.*;

import java.util.ArrayList;

public class GoCommand extends Command {

    /**
     * Constructor
     */
    public GoCommand(ArrayList<String> modifiers) {
        super("go", modifiers);
    }

    /**
     * Execute the command.
     * @param game The game object to execute the command on.
     * @return True if command executes successfully, false otherwise.
     */
    public boolean execute(Game game) {
        if (!hasModifiers()) {
            // if there is no modifier, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = getModifier(0);

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = game.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return false;
        } else {
            game.setCurrentRoom(nextRoom);
            game.getCurrentRoom().printInfo();
            return true;
        }
    }

    /**
     * Execute the command.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return execute(Game.getInstance());
    }
}

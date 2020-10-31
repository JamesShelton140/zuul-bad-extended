package zuul.characters;

import zuul.Character;
import zuul.Command;
import zuul.Game;
import zuul.Room;

public class Player extends Character {
    /**
     * Constructor
     * @param name
     * @param startingRoom
     */
    public Player(String name, Room startingRoom) {
        super(name, startingRoom);
    }

    /**
     * Constructor
     * @param name
     */
    public Player(String name) {
        this(name, null);
    }

    /**
     * The Player performs an action by getting a command from the Game's Parser and processing it.
     * Method does not return until a command is successfully processed.
     */
    @Override
    public void act() {
        //For multiplayer
        //Look around the current room to remind us where we are
        //getCurrentRoom().printInfo();

        boolean commandProcessed = false;
        do {
            System.out.print(getName() + " ");
            Command command = Game.getInstance().getParser().getCommand();
            commandProcessed = processCommand(command);
        } while (!commandProcessed);
    }

}

package zuul.characters;

import zuul.Character;
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

}

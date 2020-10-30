package zuul.characters;

import zuul.Character;
import zuul.Command;
import zuul.Game;
import zuul.Room;

public class NPC extends Character {
    //private npcBehaviour = behaviour;

    public NPC(String name, Room startingRoom) {
        super(name, startingRoom);
    }

    /**
     * The NPC performs an action by getting a command from its npcBehaviour and processing it.
     * Method returns regardless of outcome.
     */
    @Override
    public void act() {
        //Command command = behaviour.getNext();
        //processCommand(command);
    }
}

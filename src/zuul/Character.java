package zuul;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public abstract class Character {

    //zuul.Character fields
    private String name;
    private Room currentRoom;
    private Inventory inventory;
    private Command runningCommand;


    /**
     * Constructor
     */
    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        startingRoom.addCharacter(this);
        this.inventory = new Inventory();
        this.runningCommand = null;
    }

    /**
     * @return The current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Set the current room.
     * @param nextRoom The room to set as current.
     */
    public void setCurrentRoom(Room nextRoom) {
        this.currentRoom = nextRoom;
    }

    /**
     * Check if this character is equal to the given object.
     * @param o The object to compare to this.
     * @return True if o is a Character and o.name == this.name.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return name.equals(character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * @return The name of the character.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the current command being executed
     */
    public Optional<Command> getRunningCommand() {
        if (this.runningCommand == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.runningCommand);
        }
    }

    /**
     * Create string from the character
     * @return The character's name.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Check if the object has an inventory
     * @return
     */
    public boolean hasInventory() {
        return inventory != null;
    }

    /**
     * @return The character's inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * The character performs an action
     */
    public abstract void act();

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command executed properly, false otherwise.
     */
    protected boolean processCommand(Command command) {
        this.runningCommand = command; //set the current running command
        boolean result = command.execute(this); //execute the command
        this.runningCommand = null;
        return result;
    }
}

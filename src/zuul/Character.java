package zuul;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Character implements CanHaveInventory {

    //zuul.Character fields
    private String name;
    private Room currentRoom;
    private Inventory inventory;


    /**
     * Constructor
     */
    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        startingRoom.addCharacter(this);
        this.inventory = new Inventory();
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
        if(inventory == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return The character's inventory.
     */
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}

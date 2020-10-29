package zuul;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Character {

    //zuul.Character fields
    private String name;
    private Room currentRoom;
    private ArrayList<Item> items;
    private final int MAX_WEIGHT = 10;


    /**
     * Constructor
     */
    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        startingRoom.addCharacter(this);
        this.items = new ArrayList<Item>();
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
     * Add an item.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Remove an item from the items array.
     * @param index Index of item to be removed.
     */
    public Item removeItem(int index) {
        return items.remove(index);
    }

    /**
     * Get the index of an item in the items array.
     * @param item the item to check for.
     * @return The index of item parameter if it exists in the array. -1 otherwise.
     */
    public int getItemIndex(Item item) {
        return items.indexOf(item);
    }

    /**
     * Get the index of an item in the items array.
     * @param itemName the item to check for.
     * @return The index of item parameter if it exists in the array. -1 otherwise.
     */
    public int getItemIndex(String itemName) {
        return items.indexOf(items.stream()
                .filter(item -> item.getName().equals(itemName))
                .findAny()
                .get());
    }

    /**
     * @return The total weight currently held.
     */
    public int getTotalWeight(){
        return items.stream().mapToInt(Item::getWeight).sum();
    }

    /**
     * @return The maximum carry weight.
     */
    public int getMAX_WEIGHT() {
        return MAX_WEIGHT;
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
}

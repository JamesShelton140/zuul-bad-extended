package zuul;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Character {

    //zuul.Character fields
    private String name;
    private Room currentRoom;
    private ArrayList<String> items;
    private ArrayList<Integer> weights;
    private int totalWeight;
    private final int MAX_WEIGHT = 10;


    /**
     * Constructor
     */
    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.items = new ArrayList<String>();
        this.weights = new ArrayList<Integer>();
        this.totalWeight = 0;
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
    public void addItem(String item) {
        items.add(item);
    }

    /**
     * Remove an item from the items array.
     * @param index Index of item to be removed.
     */
    public void removeItem(int index) {
        items.remove(index);
    }

    /**
     * Get the index of an item in the items array.
     * @param item the item to check for.
     * @return The index of item parameter if it exists in the array. -1 otherwise.
     */
    public int getItemIndex(String item) {
        return items.indexOf(item);
    }

    /**
     * Add a weight.
     * @param weight
     */
    public void addWeight(int weight) {
        weights.add(weight);
        totalWeight += weight;
    }

    /**
     * Remove a weight from the weights array.
     * @param index Index of weight to be removed.
     * @return The value of the weight that was removed.
     */
    public Integer removeWeight(int index) {
        totalWeight -= weights.get(index);
        return weights.remove(index);
    }

    /**
     * @return The total weight currently held.
     */
    public int getTotalWeight(){
        return totalWeight;
    }

    /**
     * @return The maximum carry weight.
     */
    public int getMAX_WEIGHT() {
        return MAX_WEIGHT;
    }

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
}

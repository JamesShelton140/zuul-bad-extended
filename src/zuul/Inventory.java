package zuul;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Inventory {
    private ArrayList<Item> items;
    private final int MAX_WEIGHT;

    private static final int DEFAULT_MAX_WEIGHT = 10;

    /**
     * Default constructor
     */
    public Inventory() {
        items = new ArrayList<Item>();
        MAX_WEIGHT = DEFAULT_MAX_WEIGHT;
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
    public void removeItem(int index) {
        removeItem(items.get(index));
    }

    /**
     * Remove an item from the items array.
     * @param item Index of item to be removed.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Remove an item from the items array.
     * @param name Index of item to be removed.
     */
    public void removeItem(String name) {
        removeItem(new Item(name, 0));
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
        return items.indexOf(new Item(itemName, 0));
    }

    /**
     * Get the specified item if it is in the inventory.
     * @param item The item to get
     * @return Optional<Item> containing the requested item if found, empty otherwise.
     */
    public Optional<Item> getItem(Item item) {
        int index = getItemIndex(item);

        if(index == -1) {
            //item is not in inventory, return empty
            return Optional.empty();
        } else {
            //item found, return it
            return Optional.of(items.get(index));
        }
    }

    /**
     * Get an item with the specified name if it is in the inventory.
     * @param name The name of the item to get.
     * @return Optional<Item> containing the requested item if found, empty otherwise.
     */
    public Optional<Item> getItem(String name) {
        return getItem(new Item(name, 0));
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
     * Does the room contain an item
     * @param item The item
     * @return True if the room contains the given item, false otherwise
     */
    public boolean containsItem(Item item) {
        if (this.items.contains(item)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the room contains an item with the given name.
     * @param name Name of the item to find.
     * @return True if the room contains the given item, false otherwise
     */
    public boolean containsItem(String name) {
        Optional<Item> item = items.stream()
                                    .filter(i -> i.getName().equals(name))
                                    .findAny();

        if (item.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return items.stream()
                .map(Item::toString)
                .collect(Collectors.joining(", "));
    }
}

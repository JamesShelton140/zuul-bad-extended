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
    public Item removeItem(int index) {
        return items.remove(index);
    }

    /**
     * Remove an item from the items array.
     * @param item Index of item to be removed.
     */
    public Item removeItem(Item item) {
        items.remove(item);
        return item;
    }

    /**
     * Remove an item from the items array.
     * @param name Index of item to be removed.
     */
    public Item removeItem(String name) {
        return items.remove(getItemIndex(name));
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
     * @return The item's weight or 0 if none
     */
    public int containsItem(Item item) {
        if (this.items.contains(item)) {
            return item.getWeight();
        } else {
            return 0;
        }
    }

    /**
     * Check if the room contains an item with the given name.
     * @param name Name of the item to find.
     * @return Weight of the item if it exists, false otherwise.
     */
    public int containsItem(String name) {
        Optional<Item> item = items.stream()
                                    .filter(i -> i.getName()
                                    .equals(name))
                                    .findAny();
        if (item.isPresent()) {
            return item.get().getWeight();
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return items.stream()
                .map(Item::toString)
                .collect(Collectors.joining(", "));
    }
}

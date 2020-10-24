package zuul;

import java.util.HashMap;

/**
 * Class zuul.Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "zuul.Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    private String description;
    
    // Exits from the room
    private HashMap<String, Room> exits;

    // An item in the room
    private String itemDescription;
    private int itemWeight;
    
    // Characters in the room
    private String character;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) {
        this.exits = new HashMap<String, Room>();
        this.description = description;
        this.itemDescription = null;
        this.itemWeight = 0;
        this.character = null;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param directions The set of exit directions.
     * @param rooms The set of rooms lead to by the exit directions.
     */
    public void setExits(String[] directions, Room[] rooms) {
        if (directions.length > rooms.length) {
            //not every exit leads to a room!
            System.out.println("Every exit must lead to a room!");
            return;
        }
        for(int i = 0; i < directions.length; i++) {
            addExit(directions[i], rooms[i]);
        }
    }

    /**
     * Add an exit to the room.
     * @param direction The direction of the exit.
     * @param room The room the exit leads to.
     */
    public void addExit(String direction, Room room) {
        this.exits.put(direction, room);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Add an item to the zuul.Room
     * @param description The description of the item
     * @param weight The item's weight
     */
    public void addItem(String description, int weight) {
        itemDescription = description;
        itemWeight = weight;
    }

    /**
     * @return The description of the item.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Set the description of the item.
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * @return The weight of the item.
     */
    public int getItemWeight() {
        return itemWeight;
    }

    /**
     * Set the weight of the item.
     */
    public void setItemWeight(int itemWeight) {
        this.itemWeight = itemWeight;
    }

    /**
     * @return The character currently in the room.
     */
    public String getCharacter() {
        return character;
    }
    
    /**
     * Does the room contain an item
     * @param description the item
     * @return the item's weight or 0 if none
     */
    public int containsItem(String description) {
        if (itemDescription != null && itemDescription.equals(description))
            return itemWeight;
        else return 0;
    }
    
    /**
     * Remove an item from the zuul.Room
     */
    public String removeItem(String description) {
        if (itemDescription.equals(description)) {
            String tmp = itemDescription;
            itemDescription = null;
            return tmp;
        }
        else {
            System.out.println("This room does not contain" + description);
            return null;
        }
    }

    /**
     * @return the room in direction exit or null if it doesn't exist
     */

    public Room getExit(String exit) {
        //Check if exit exists
        if (!this.exits.containsKey(exit)) {
            System.out.println("No such exit exists!");
            return null;
        }

        return this.exits.get(exit);
    }

    /**
     * Print the description, exits, and items contained within this room.
     */
    public void printInfo() {
        System.out.println("You are " + getDescription());
        System.out.print("Exits: ");
        for (String exit: exits.keySet()) {
            System.out.print(exit + " ");
        }
        System.out.println();
        System.out.print("Items: ");
        if (getItemDescription() != null) {
            System.out.print(getItemDescription()
                    + '(' + getItemWeight() + ')');
        }
        System.out.println();
    }

}

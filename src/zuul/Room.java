package zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

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
public class Room implements CanHaveInventory {
    private String name;
    private String description;
    
    // Exits from the room
    private HashMap<String, Room> exits;

    // An item in the room
    private Inventory inventory;
    
    // Characters in the room
    private ArrayList<Character> characters;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description) {
        this.exits = new HashMap<String, Room>();
        this.description = description;
        this.characters = new ArrayList<>();
        this.inventory = new Inventory();
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
    public String getDescription() {
        return description;
    }

    /**
     * @return The characters currently in the room.
     */
    public ArrayList<Character> getCharacters() {
        return (ArrayList<Character>) characters.clone();
    }

    public zuul.Character getCharacter(String name){
        return characters.stream()
                .filter(character -> character.getName().equals(name))
                .findAny()
                .get();
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
        if (inventory != null) {
            System.out.print(inventory);
        }
        System.out.println();
        System.out.print("Characters: ");
        if (characters != null && characters.size() != 0) {
            characters.stream().forEach(character -> System.out.println(character.getName()));
        }
        System.out.println();
    }

    /**
     * Add a character to the room.
     * @param character The character to add.
     */
    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    /**
     * Remove a character from the room.
     * @param character The character to remove.
     */
    public void removeCharacter(Character character) {
        this.characters.remove(character);
    }

    /**
     * @return The character's inventory.
     */
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}

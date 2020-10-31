package zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class Room {
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
        this.name = name;
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
            System.out.println(GameText.getString("exitNoRoomError"));
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

    public Optional<Character> getCharacter(String name){
        return characters.stream()
                .filter(character -> character.getName().equals(name))
                .findAny();
    }

    /**
     * @return the room in direction exit or null if it doesn't exist
     */

    public Optional<Room> getExit(String exit) {
        //Check if exit exists
        if (!this.exits.containsKey(exit)) {
            System.out.println(GameText.getString("noSuchExitError"));
            return Optional.empty();
        }

        return Optional.of(this.exits.get(exit));
    }

    /**
     * Print the description, exits, and items contained within this room.
     */
    public void printInfo() {
        System.out.println("You are " + getDescription());
        System.out.print(GameText.getString("exitsDisplay"));
        System.out.print(String.join(" ", exits.keySet()));
        System.out.println();
        System.out.print(GameText.getString("itemsDisplay"));
        if (inventory != null) {
            System.out.print(inventory);
        }
        System.out.println();
        System.out.print(GameText.getString("charactersDisplay"));
        if (characters != null && characters.size() != 0) {
            System.out.println(characters.stream().map(Character::toString).collect(Collectors.joining(", ")));
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
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Test if this room is equal to the given object.
     * Automatically generated by IntelliJ
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

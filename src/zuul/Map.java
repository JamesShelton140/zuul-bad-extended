package zuul;

import zuul.characters.Player;

import java.util.HashMap;
import java.util.function.Consumer;

public abstract class Map {
    private Room defaultStartingRoom;
    private java.util.Map<Room, Room> rooms; //use Map<T,T> so all elements are unique and can be retrieved
    private java.util.Map<Character, Character> characters; //use Map<T,T> so all elements are unique and can be retrieved

    public Map() {
        this.rooms = new HashMap<Room, Room>();
        this.characters = new HashMap<Character, Character>();
    }

    /**
     * Add a character to the Map
     * @param character
     */
    public void addCharacter(Character character) {
        characters.put(character, character);
    }

    /**
     * Return the character at index, 'index'.
     * @param character The character to retrieve.
     * @return Character in position 'index'.
     */
    public zuul.Character getCharacter(Character character) {
        return characters.get(character);
    }

    /**
     * Apply the given consumer function on each character in the map
     * @param consumer
     */
    public void forEachCharacter(Consumer<? super Character> consumer) {
        characters.keySet().forEach(consumer);
    }

    /**
     * Set the default starting room.
     * @param room
     */
    public void setDefaultStartingRoom(Room room) {
        this.defaultStartingRoom = room;
    }

    /**
     * @return The default starting room
     */
    public Room getDefaultStartingRoom() {
        return this.defaultStartingRoom;
    }

    /**
     * Add a room to the Map
     * @param room
     */
    public void addRoom(Room room) {
        rooms.put(room, room);
    }

    /**
     * Apply the given consumer function on each room in the map
     * @param consumer
     */
    public void forEachRoom(Consumer<? super Room> consumer) {
        rooms.keySet().forEach(consumer);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    protected abstract void createRooms();

    /**
     * Create all the characters
     */
    protected abstract void createCharacters();

    /**
     * Print a welcome message
     */
    public abstract void printWelcome();
}

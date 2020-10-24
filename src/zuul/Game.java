package zuul;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * zuul.commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game {

    //Static instance of zuul.Game class to ensure a single instance is active. (Singleton pattern)
    private static Game gameInstance;

    //zuul.Game fields
    private Parser parser;
    private Room currentRoom;
    private ArrayList<String> items;
    private ArrayList<Integer> weights;
    private int totalWeight;
    private final int MAX_WEIGHT = 10;

    /**
     * Create the game and initialise its internal map.
     */
    private Game() {
        createRooms();
        parser = new Parser();
        items = new ArrayList<String>();
        weights = new ArrayList<Integer>();
        totalWeight = 0;
    }

    /**
     * Get the static game instance
     */
    public static Game getInstance() {
        //Create new instance of zuul.Game if one does not yet exist
        if (gameInstance == null) {
            gameInstance = new Game();
        }

        //Return the static instance of zuul.Game
        return gameInstance;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, theatre, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits //zuul.Room north, zuul.Room east, zuul.Room south, zuul.Room west)
        outside.setExits(new String[]{"east", "south", "west"}, new Room[]{theatre, lab, pub});
        outside.addItem("notebook", 2);
        theatre.setExits(new String[]{"west"}, new Room[]{outside});
        pub.setExits(new String[]{"east"}, new Room[]{outside});
        lab.setExits(new String[]{"north", "east"}, new Room[]{outside, office});
        office.setExits(new String[]{"west"}, new Room[]{lab});

        currentRoom = outside;  // start game outside
    }

    /**
     * zuul.Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read zuul.commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        currentRoom.printInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command executed properly, false otherwise.
     */
    private boolean processCommand(Command command) {
        return command.execute();
    }

    // Getters and setters for class fields.

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
     * @return The game's parser.
     */
    public Parser getParser(){
        return parser;
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

    /**
     * Add an item.
     */
    public void addItem(String item) {
        items.add(item);
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
     * Get the index of an item in the items array.
     * @param item the item to check for.
     * @return The index of item parameter if it exists in the array. -1 otherwise.
     */
    public int getItemIndex(String item) {
        return items.indexOf(item);
    }

    /**
     * Remove an item from the items array.
     * @param index Index of item to be removed.
     */
    public void removeItem(int index) {
        items.remove(index);
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
}

package zuul;

import zuul.characters.Player;

import java.util.ArrayList;

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
    private Room startingRoom;
    private ArrayList<Character> characters = new ArrayList<>();
    private Boolean finished = false;

    /**
     * Create the game and initialise its internal map.
     */
    private Game() {
        createRooms();
        parser = new Parser();
        this.characters.add(new Player("Player_1", startingRoom));
    }

    /**
     * Get the static game instance
     * @return The static instance of Game.
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
        outside = new Room("outside", GameText.getString("outside"));
        theatre = new Room("theatre", GameText.getString("theatre"));
        pub = new Room("pub", GameText.getString("pub"));
        lab = new Room("lab", GameText.getString("lab"));
        office = new Room("office", GameText.getString("office"));

        // initialise room exits //zuul.Room north, zuul.Room east, zuul.Room south, zuul.Room west)
        outside.setExits(new String[]{"east", "south", "west"}, new Room[]{theatre, lab, pub});
        outside.getInventory().addItem(new Item("notebook", 2));
        theatre.setExits(new String[]{"west"}, new Room[]{outside});
        pub.setExits(new String[]{"east"}, new Room[]{outside});
        lab.setExits(new String[]{"north", "east"}, new Room[]{outside, office});
        office.setExits(new String[]{"west"}, new Room[]{lab});

        startingRoom = outside;  // start game outside
    }

    /**
     * zuul.Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read zuul.commands and
        // execute them until the game is over.

        while (!finished) {
            boolean commandSuccessful = false;
            do {
                Command command = parser.getCommand();
                commandSuccessful = processCommand(command);
            } while (!commandSuccessful);
        }
        System.out.println(GameText.getString("goodBye"));
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println(GameText.getString("welcome_ln1"));
        System.out.println(GameText.getString("welcome_ln2"));
        System.out.println(GameText.getString("welcome_ln3"));
        System.out.println();
        characters.get(0).getCurrentRoom().printInfo(); //Print info for starting room of player 1
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
     * @return The game's parser.
     */
    public Parser getParser(){
        return parser;
    }

    /**
     * Return the character at index, 'index'.
     * @param index The index of characters to retrieve.
     * @return Character in position 'index'.
     */
    public zuul.Character getCharacter(int index) {
        return characters.get(index);
    }

    public void finish() {
        this.finished = true;
    }
}

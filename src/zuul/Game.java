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
    private final Map map;

    //zuul.Game fields
    private Parser parser;
    private Room startingRoom;
    private ArrayList<Character> characters = new ArrayList<>();
    private Boolean finished = false;

    /**
     * Create the game and initialise its internal map.
     */
    private Game() {
        map = new ZuulMap();
        parser = new Parser();
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
     * zuul.Main play routine. Loops until end of play.
     */
    public void play() {
        map.printWelcome();

        // Enter the main command loop.
        // Here we ask every character in the map to act until a player quits the game.

        while (!finished) {
            map.forEachCharacter(Character::act);
        }

        System.out.println(GameText.getString("goodBye"));
    }

    // Getters and setters for class fields.

    /**
     * @return The game's parser.
     */
    public Parser getParser(){
        return parser;
    }

    public void finish() {
        this.finished = true;
    }
}

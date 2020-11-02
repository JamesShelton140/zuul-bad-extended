package zuul;


import java.util.Collection;

/**
 * The game engine. This represents the state of the game and handles overall flow of game logic.
 * <p>
 * The {@link zuul.io.userInterfaces.UserInterface UserInterface} should call {@code Game.getInstance().play()}
 * to start the game logic.
 * <p>
 *
 * <p>
 * @author Michael Kolling and David J. Barnes
 * @author Timothy Shelton
 * @version 2006.03.30
 */
///**
// * This class is the main class of the "World of Zuul" application. "World of
// * Zuul" is a very simple, text based adventure game. Users can walk around some
// * scenery. That's all. It should really be extended to make it more
// * interesting!
// * <p>
// * To play this game, create an instance of this class and call the "play"
// * method.
// * <p>
// * This main class creates and initialises all the others: it creates all rooms,
// * creates the parser and starts the game. It also evaluates and executes the
// * zuul.commands that the parser returns.
// * <p>
// * @author Michael Kolling and David J. Barnes
// * @author Timothy Shelton
// * @version 2006.03.30
// */
public class Game {

    /**
     * Static instance of zuul.Game class to ensure a single instance is active.
     */
    private static Game gameInstance;

    //zuul.Game fields
    /**
     * The map to be used for this Game.
     */
    private final Map map;
    /**
     * The Parser to be used for this Game.
     */
    private Parser parser;
    /**
     * Marker for if this Game should finish.
     */
    private Boolean finished = false;

    /**
     * Create the game and initialise its internal map.
     */
    private Game() {
        map = new ZuulMap();
        parser = new Parser();
    }

    /**
     * Gets the static
     *
     * @return the static instance of Game, not null
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
     * Starts main game flow. Loops until {@link #finish()} is called.
     * <p>
     * Each loop every character in the {@link #map} is told to {@link Character#act()}.
     */
    public void play() {
        map.printWelcome();

        // Enter the main command loop.
        // Here we ask every character in the map to act until a player quits the game.
        while (!finished) {
            GameInterface.get().update("game nextRound");
            map.forEachCharacter(Character::act);
        }

        zuul.io.Out.println(GameText.getString("goodBye"));
    }

    // Getters and setters for class fields.

    /**
     * Gets the {@link Parser} associated to this Game.
     *
     * @return the parser associated to this Game.
     */
    public Parser getParser(){
        return parser;
    }

    /**
     * Sets the finished marker to true.
     * <p>
     * This will cause game logic to stop executing.
     */
    public void finish() {
        this.finished = true;
    }
}

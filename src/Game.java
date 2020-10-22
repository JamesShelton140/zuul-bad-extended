
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
 * commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game {

    //Static instance of Game class to ensure a single instance is active. (Singleton pattern)
    private static Game gameInstance;

    //Game fields
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
        //Create new instance of Game if one does not yet exist
        if (gameInstance == null) {
            gameInstance = new Game();
        }

        //Return the static instance of Game
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

        // initialise room exits //Room north, Room east, Room south, Room west)
        outside.setExits(new String[]{"east", "south", "west"}, new Room[]{theatre, lab, pub});
        outside.addItem("notebook", 2);
        theatre.setExits(new String[]{"west"}, new Room[]{outside});
        pub.setExits(new String[]{"east"}, new Room[]{outside});
        lab.setExits(new String[]{"north", "east"}, new Room[]{outside, office});
        office.setExits(new String[]{"west"}, new Room[]{lab});

        currentRoom = outside;  // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
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
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("look")) {
            look();
        } else if (commandWord.equals("take")) {
            take(command);
        } else if (commandWord.equals("drop")) {
            drop(command);
        } else if (commandWord.equals("give")) {
            give(command);
        }
        return wantToQuit;
    }

// implementations of user commands:
    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            currentRoom.printInfo();
        }
    }

    /**
     * "Look" was entered. Report what the player can see in the room
     */
    private void look() {
        currentRoom.printInfo();
    }

    /**
     * Try to take an item from the current room, otherwise print an error
     * message.
     */
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }

        String item = command.getSecondWord();
        int w = currentRoom.containsItem(item);
        if (w == 0) {
            // The item is not in the room
            System.out.println("No " + item + " in the room");
            return;
        }
        if (totalWeight + w > MAX_WEIGHT) {
            // The player is carrying too much
            System.out.println(item + " is too heavy");
            return;
        }
        // OK we can pick it up
        currentRoom.removeItem(item);
        items.add(item);
        weights.add(w);
        totalWeight += w;
    }

    /**
     * Try to drop an item, otherwise print an error message.
     */
    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }

        String item = command.getSecondWord();
        int i = items.indexOf(item);
        if (i == -1) {
            System.out.println("You don't have the " + item);
            return;
        }
        items.remove(i);
        int w = (Integer) weights.remove(i);
        currentRoom.addItem(item, w);
        totalWeight -= w;
    }

    /**
     * Try to drop an item, otherwise print an error message.
     */
    private void give(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to give...
            System.out.println("Give what?");
            return;
        }
        if (!command.hasThirdWord()) {
            // if there is no third word, we don't to whom to give it...
            System.out.println("Give it to who?");
            return;
        }

        String item = command.getSecondWord();
        String whom = command.getThirdWord();

        if (!currentRoom.getCharacter().equals(whom)) {
            // cannot give it if the character is not here
            System.out.println(whom + " is not in the room");
            return;
        }
        int i = items.indexOf(item);
        if (i == -1) {
            System.out.println("You don't have the " + item);
            return;
        }
        items.remove(i);
        int w = (Integer) weights.remove(i);
        totalWeight -= w;
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}

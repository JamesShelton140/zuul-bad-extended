package zuul;

import java.util.Objects;
import java.util.Optional;

/**
 * A Character in the "World of Zuul" application.
 * This represents a generic character in the game including state and functionality common to all types of character.
 * Examples of concrete subclasses of this are {@link zuul.characters.Player} (a user-controlled character) and
 * {@link zuul.characters.NPC} (an AI-controlled character).
 * <p>
 * Characters must exist in a {@link Room} and are part of the {@link Map} state.
 *
 * @author Timothy Shelton
 */
public abstract class Character {

    /**
     * The name of this character.
     */
    private String name;

    /**
     * The {@link Room} this character is currently in.
     */
    private Room currentRoom;

    /**
     * An {@link Inventory} to store items held by this character
     */
    private Inventory inventory;

    /**
     * The {@link Command} currently being processed by this character, may be null.
     */
    private Command runningCommand;


    /**
     * Constructor creates a character with name and starting {@link Room} specified.
     *
     * @param name  name of this character
     * @param startingRoom  the room that this character should start in
     */
    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        startingRoom.addCharacter(this);
        this.inventory = new Inventory();
        this.runningCommand = null;
    }

    /**
     * Gets the {@link Room} this character is currently in.
     *
     * @return the current room this character is in
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the {@link Room} this character is currently in.
     *
     * @param nextRoom  the room to set this character to be in
     */
    public void setCurrentRoom(Room nextRoom) {
        this.currentRoom = nextRoom;
    }

    /**
     * Moves this Character into the specified {@link Room}.
     *
     * @param nextRoom  the Room to move this Character to
     */
    public void moveRoom(Room nextRoom) {
        //move out of current room
        this.currentRoom.removeCharacter(this);
        //move into next room
        setCurrentRoom(nextRoom);
        nextRoom.addCharacter(this);
    }

    /**
     * Indicates if the specified object reference is "equal to" this Character using {@link #name}.
     * <p>
     * The given object reference is considered "equal to" this if it:
     * <ul>
     *     <li>is a reference to the same object</li>
     * </ul>
     * or
     * <ul>
     *     <li>is an instance of {@code Character}, and
     *     <li>{@code o.name} {@link String#equals equals} {@code this.name}
     * </ul>
     * (Method automatically generated by IntelliJ Idea.)
     *
     * @param o  the object to compare to this
     * @return true if {@code o == this} or {@code o} is a Character and
     * {@code o.name} {@link String#equals equals} {@code this.name}, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return name.equals(character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Gets the name of this character.
     *
     * @return the name of this character
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the {@link Command} currently being processed by this character.
     *
     * @return an Optional containing the current command being processed if it is not null, an empty Optional otherwise
     */
    public Optional<Command> getRunningCommand() {
        if (this.runningCommand == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.runningCommand);
        }
    }

    /**
     * Creates and returns a String that contains the name of this Character.
     *
     * @return the name of this character
     */
    public String toString() {
        return this.name;
    }

    /**
     * Gets the {@link Inventory} of this character.
     * <p>
     * It is returned directly so that it can be modified without {@link Item} duplication.
     *
     * @return The character's inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Attempts to perform an action.
     * <p>
     * User-controlled characters should generate and process a {@link Command}.
     * <p>
     * AI-controlled characters should process the next step of their behaviour.
     */
    public abstract void act();

    /**
     * Processes (that is: executes) the specified {@link Command} on this character.
     * <p>
     * {@code this.runningCommand} is set to the specified command for the duration of its execution.
     *
     * @param command the command to be processed
     * @return true if the command executed properly, false otherwise
     */
    protected boolean processCommand(Command command) {
        this.runningCommand = command; //set the current running command
        boolean result = command.execute(this); //execute the command
        this.runningCommand = null; //reset the current running command
        return result;
    }
}

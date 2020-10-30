package zuul;

import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public abstract class Command
{
    private String commandWord;
    private ArrayList<String> modifiers;

    /**
     * Create a command object. zuul.Command word must be supplied but modifiers may be null.
     * @param commandWord The first word of the command. Null if the command
     *                    was not recognised.
     * @param modifiers The array of modifiers to the command word. Empty if no modifiers input.
     */
    public Command(String commandWord, ArrayList<String> modifiers) {
        commandWord = commandWord;
        this.modifiers = modifiers;

    }

    /**
     * Create a command object. zuul.Command word must be supplied. Sets modifiers == null.
     * @param commandWord  The first word of the command. Null if the command
     *                     was not recognised.
     */
    public Command(String commandWord) {
        this(commandWord, new ArrayList<String>());
    }

    /**
     * Execute the command.
     * @return whether the command executed successfully.
     */
    public abstract boolean execute(zuul.Character character);

    /**
     * @return true if command has at least one modifier word, false otherwise
     */
    public boolean hasModifiers() {
        return (modifiers != null) && (modifiers.size() != 0);
    }

    /**
     * @return Modifier number i
     */
    public String getModifier(int i) {
        return modifiers.get(i);
    }

    /**
     * Check if a non-null modifier exists in index i.
     * @param i The index to check.
     * @return True if modifiers has an index i and a non-null modifier exists in that index, false otherwise.
     */
    public boolean hasModifier(int i) {
        return (modifiers.size() > i) && (modifiers.get(i) != null);
    }
}


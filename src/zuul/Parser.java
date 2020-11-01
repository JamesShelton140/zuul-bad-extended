package zuul;

import zuul.io.Out;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a three word command. It returns the command
 * as an object of class zuul.Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known zuul.commands, and if the input is not one of the known zuul.commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    CommandFactory commandFactory;  // creates commands from input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commandFactory = new CommandFactory();
        commands = new CommandWords();
    }

    /**
     * Reads input from user and tries to parse it as a valid command.
     * Only returns if a valid zuul.commands is parsed. Else an error message is printed and getCommand() is called recursively.
     * @return The next valid command from the user.
     */
    public Command getCommand(String caller)
    {
        String commandWord = null; //Initialise command word to null so "no input" will result in a null command.
        ArrayList<String> modifiers = new ArrayList<>();

        Out.print(caller + " > ");     // print prompt

        String inputLine = zuul.io.In.nextLine();

        Scanner tokenizer = new Scanner(inputLine);

        // Find commandWord
        if (tokenizer.hasNext()) {
            commandWord = tokenizer.next();
        }

        // Find all modifier words on the line.
        while(tokenizer.hasNext()) {
            modifiers.add(tokenizer.next());
        }

        tokenizer.close();  // Scanner cleanup

        // Try to create a command using the command word and modifiers
        // "go", "quit", "help", "look", "take", "drop", "give"
        Optional<Command> command = commandFactory.getCommand(commandWord, modifiers);

        if (command.isPresent()) {
            return command.get();
        } else {
            Out.println(GameText.getString("unrecognisedCommandError"));
            return getCommand(caller);
        }
    }

    /**
     * getCommand to retrieve command without explicit calling character.
     * @return
     */
    public Command getCommand() {
        return getCommand("");
    }

    /**
     * @return The CommandsWords object that holds all valid zuul.commands for this parser.
     */
    public CommandWords getCommandWords() {
        return commands;
    }
}

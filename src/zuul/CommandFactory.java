package zuul;

import zuul.commands.*;

import java.util.ArrayList;

public class CommandFactory {

    /**
     * Constructor
     */
    public CommandFactory() {
        //do nothing
    }

    public Command getCommand(String commandWord, ArrayList<String> modifiers) {
        switch (commandWord) {
            case "go":
                return new GoCommand(modifiers);
            case "quit":
                return new QuitCommand();
            case "help":
                return new HelpCommand();
            case "look":
                return new LookCommand();
            case "take":
                return new TakeCommand(modifiers);
            case "drop":
                return new DropCommand(modifiers);
            case "give":
                return new GiveCommand(modifiers);
            default:
                System.out.println("I don't know what you mean...");
                return null;
        }
    }

}

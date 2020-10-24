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

    /**
     * Create Command from commandWord String.
     * @param commandWord The name of the command to create.
     * @param modifiers The command modifiers.
     * @return The created command.
     */
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

    public Command getCommandReflexive(String commandWord, ArrayList<String> modifiers) {
        try{
            return (zuul.Command) Class
                    .forName("zuul.commands." + commandWord.substring(0, 1).toUpperCase() + commandWord.substring(1) + "Command")
                    .getConstructor(new Class<?>[]{ArrayList.class})
                    .newInstance(modifiers);
        } catch (Exception e) {
            System.out.println("I don't know what you mean...");
            return null;
        }
    }

}

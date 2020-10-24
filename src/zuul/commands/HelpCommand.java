package zuul.commands;

import zuul.*;

import java.util.ArrayList;
import java.util.Arrays;

public class HelpCommand extends Command {

    /**
     * Constructor
     */
    public HelpCommand() {
        super("help");
    }

    /**
     * Constructor
     */
    public HelpCommand(ArrayList<String> modifiers) {
        super("help");
    }

    /**
     * Execute the command. Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
//        System.out.println("   go quit help");
        System.out.println(Arrays.toString(Game.getInstance().getParser().getCommandWords().getValidCommands()));
        return false;
    }
}

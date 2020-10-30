package zuul.commands;

import zuul.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class HelpCommand extends Command {

    /**
     * Constructor
     */
    public HelpCommand() {
        super(GameText.getString("CommandWordsBundle", "help"));
    }

    /**
     * Constructor
     */
    public HelpCommand(ArrayList<String> modifiers) {
        super(GameText.getString("CommandWordsBundle", "help"));
    }

    /**
     * Execute the command. Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        System.out.println(GameText.getString("help_ln1"));
        System.out.println(GameText.getString("help_ln2"));
        System.out.println();
        System.out.println(GameText.getString("helpCommandWordsDisplay"));
//        System.out.println("   go quit help");
//        System.out.println(Arrays.toString(Game.getInstance().getParser().getCommandWords().getValidCommands()));
        System.out.println(GameText.getCommandWords().stream()
                .map(key -> GameText.getString("CommandWordsBundle", key))
                .sorted(Comparator.comparing(String::toString, GameText.getCollator())) //sort the command words alphabetically by locale.
                .collect(Collectors.joining(", "))
        );
        return true;
    }
}

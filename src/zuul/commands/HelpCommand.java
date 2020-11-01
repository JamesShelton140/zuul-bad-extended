package zuul.commands;

import zuul.*;
import zuul.Character;

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
    public boolean commandLogic(Character character) {
        zuul.io.Out.println(GameText.getString("help_ln1"));
        zuul.io.Out.println(GameText.getString("help_ln2"));
        zuul.io.Out.println();
        zuul.io.Out.println(GameText.getString("helpCommandWordsDisplay"));
//        zuul.io.Out.println(Arrays.toString(Game.getInstance().getParser().getCommandWords().getValidCommands()));
        //Get the command words from the CommandWords resource bundle for the current locale
        zuul.io.Out.println(GameText.getCommandWords().stream()
                .map(key -> GameText.getString("CommandWordsBundle", key))
                .sorted(Comparator.comparing(String::toString, GameText.getCollator())) //sort the command words alphabetically by locale.
                .collect(Collectors.joining(", "))
        );
        zuul.io.Out.println();
        character.act(); //allow character to perform another action
        return true;
    }
}

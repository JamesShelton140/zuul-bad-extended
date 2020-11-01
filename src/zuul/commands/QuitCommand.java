package zuul.commands;

import zuul.Command;
import zuul.Game;
import zuul.GameText;
import zuul.characters.Player;

import java.util.ArrayList;

public class QuitCommand extends Command {

    /**
     * Constructor
     */
    public QuitCommand() {
        super(GameText.getString("CommandWordsBundle", "quit"));
    }

    /**
     * Constructor
     */
    public QuitCommand(ArrayList<String> modifiers) {
        super(GameText.getString("CommandWordsBundle", "quit"), modifiers);
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return True, if this command quits the game, false otherwise.
     */
    @Override
    public boolean execute(zuul.Character character) {
        if(!(character instanceof Player)) {
            //Only players can quit the game
            return false;
        }

        if (hasModifiers()) {
            //Only quit if we're really sure
            zuul.io.Out.println(GameText.getString("quitHasModifiersError"));
            return false;
        } else {
            Game.getInstance().finish(); // signal that we want to quit
            return true;
        }
    }
}

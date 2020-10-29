package zuul;

import zuul.commands.*;

import java.util.ArrayList;
import java.util.Optional;

public class CommandFactory {

    /**
     * Constructor
     */
    public CommandFactory() {
        //do nothing
    }

    /**
     * Create command from commandWord String by building the class name using reflection.
     * @param commandWord
     * @param modifiers
     * @return
     */
    public Optional<Command> getCommand(String commandWord, ArrayList<String> modifiers) {
        try{
            return Optional.of((zuul.Command) Class
                    .forName("zuul.commands." + commandWord.substring(0, 1).toUpperCase() + commandWord.substring(1).toLowerCase() + "Command")
                    .getConstructor(new Class<?>[]{ArrayList.class})
                    .newInstance(modifiers));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}

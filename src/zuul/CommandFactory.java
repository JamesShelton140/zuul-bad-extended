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
     * Create command from commandWord String by building the class name using reflection.
     * @param commandWord
     * @param modifiers
     * @return
     */
    public Command getCommand(String commandWord, ArrayList<String> modifiers) {
        try{
            return (zuul.Command) Class
                    .forName("zuul.commands." + commandWord.substring(0, 1).toUpperCase() + commandWord.substring(1).toLowerCase() + "Command")
                    .getConstructor(new Class<?>[]{ArrayList.class})
                    .newInstance(modifiers);
        } catch (Exception e) {
            System.out.println("I don't know what you mean...");
            return null;
        }
    }

}

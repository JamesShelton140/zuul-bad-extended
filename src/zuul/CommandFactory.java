package zuul;

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
            //Get the english name of the command class from the resource bundle for this locale
            //If it is not found throw a NoSuchElementException
            String commandClassName = getCommandClassName(commandWord).orElseThrow();

            //If the commandClassName is found then use it to create an instance of that command
            return Optional.of((zuul.Command) Class
                    .forName("zuul.commands." + commandClassName.substring(0, 1).toUpperCase() + commandClassName.substring(1).toLowerCase() + "Command")
                    .getConstructor(new Class<?>[]{ArrayList.class})
                    .newInstance(modifiers));
        } catch (Exception e) {
            //Catch all exceptions and return an "null command"
            return Optional.empty();
        }
    }

    private Optional<String> getCommandClassName(String commandWord) {
        return GameText.getCommandWords().stream()
                .filter(key -> GameText.getString("CommandWordsBundle", key).equalsIgnoreCase(commandWord))
                .findAny();
    }

}

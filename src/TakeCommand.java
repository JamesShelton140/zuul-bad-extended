import java.util.ArrayList;

public class TakeCommand extends Command{

    /**
     * Constructor
     */
    public TakeCommand(ArrayList<String> modifiers) {
        super("take", modifiers);
    }

    /**
     * Execute the command.
     * @return True if command executes successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return false;
    }
}

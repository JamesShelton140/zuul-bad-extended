import java.util.ArrayList;

public class QuitCommand extends Command {

    /**
     * Constructor
     */
    public QuitCommand() {
        super("quit");
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

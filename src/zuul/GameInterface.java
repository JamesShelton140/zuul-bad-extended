package zuul;

import zuul.io.userInterfaces.UserInterface;

/**
 * Enables static access to the active UserInterface.
 * For example: {@link zuul.io.userInterfaces.CommandLineInterface CommandLineInterface}
 *
 * @author Timothy Shelton
 * @version 02/11/2020
 */

public class GameInterface {
    /**
     * The currently active user interface.
     */
    private static UserInterface userInterface;

    public static void set(UserInterface userInterface) {
        GameInterface.userInterface = userInterface;
    }

    public static UserInterface get() {
        return userInterface;
    }
}

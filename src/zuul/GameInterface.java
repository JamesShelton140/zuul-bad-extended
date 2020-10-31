package zuul;

import zuul.io.userInterfaces.UserInterface;

/**
 * "zuul.GameInteface" stores the active UserInterface.
 * Such as: locale, user interface etc.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class GameInterface {
    /**
     * User interface fields
     */
    private static UserInterface userInterface;

    public static void set(UserInterface userInterface) {
        GameInterface.userInterface = userInterface;
    }

    public static UserInterface get() {
        return userInterface;
    }
}

package zuul.io;

import zuul.GameInterface;

public class In {
    public static String nextLine() {
        return GameInterface.get().getNextLine();
    }
}

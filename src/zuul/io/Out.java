package zuul.io;

public class Out {

    /**
     * Print the given string to the screen.
     * @param str
     */
    public static void print(String str) {
        System.out.println(str);
    }

    /**
     * Print the given string to the screen with a line break following.
     * @param str
     */
    public static void println(String str) {
        print(str);
        nextln();
    }

    /**
     * Moves to next line of output
     */
    private static void nextln() {
        System.out.println();
    }
}

package zuul.io;

import zuul.GameInterface;

public class Out {

    /**
     * Print the given string
     * @param str
     */
    public static void print(String str) {
        GameInterface.get().print(str);
    }

    /**
     * Print object.toString()
     * @param obj
     */
    public static void print(Object obj) {
        print(obj.toString());
    }

    /**
     * Print the given string with a line break following
     * @param str
     */
    public static void println(String str) {
        print(str);
        println();
    }

    /**
     * Print the given string with a line break following
     * @param obj
     */
    public static void println(Object obj) {
        print(obj);
        println();
    }

    /**
     * Print a line break by calling nextln()
     */
    public static void println() {
        nextln();
    }

    /**
     * Print a line break
     */
    public static void nextln() {
        GameInterface.get().printNextln();
    }
}

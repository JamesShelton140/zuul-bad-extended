package zuul;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import zuul.io.userInterfaces.CommandLineInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * This class handles initialising and running the game.
 *
 * @author rej
 */
public class Main {

    private static List<String> argsList;

    private static final String LOCALE_KEY_WORD = "locale";
    private static final String DEFAULT_LANGUAGE = "en";
    private static final String DEFAULT_COUNTRY = "GB";

    private static final String INTERFACE_KEY_WORD = "interface";
    private static final String DEFAULT_INTERFACE = "CommandLine";

    /**
     * Set the locale for this instance of Zuul.
     * Set default locale if one not specified.
     * Arg check adapted from java i18n trail: https://docs.oracle.com/javase/tutorial/i18n/intro/after.html
     * @return The args parameter with locale information removed.
     */
    private static String[] setLocale(String[] args) {

        argsList = new ArrayList<String>(Arrays.asList(args));

        String language;
        String country;

        if (argsList.contains(LOCALE_KEY_WORD) && (argsList.indexOf(LOCALE_KEY_WORD) + 2 <= argsList.size())) {
            //'locale' argument exists and at least 2 more arguments exist
            //So set locale to the 2 arguments following 'locale'
            int index = argsList.indexOf(LOCALE_KEY_WORD);
            country = new String(argsList.remove(index + 2));
            language = new String(argsList.remove(index + 1));
            argsList.remove(index);
        } else {
            //No locale specified so set default locale
            language = DEFAULT_LANGUAGE;
            country = DEFAULT_COUNTRY;
        }

        GameText.setLocale(new Locale(language, country));

        return argsList.toArray(new String[0]);
    }

    /**
     * Set the user interface for this instance of Zuul.
     * Set default interface if one not specified.
     */
//    private static void setUserInterface() {
//        String userInterface;
//
//        if (argsList.contains(INTERFACE_KEY_WORD) && (argsList.indexOf(INTERFACE_KEY_WORD) + 1 <= argsList.size())) {
//            //'interface' argument exists and at least 2 more arguments exist
//            //So set locale to the 2 arguments following 'locale'
//            int index = argsList.indexOf(INTERFACE_KEY_WORD);
//            userInterface = new String(argsList.get(index + 1));
//        } else {
//            //No locale specified so set default locale
//            userInterface = DEFAULT_INTERFACE;
//        }
//
//        GameInterface.set()
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        Set the locale for this instance of Zuul.
//        Arg check from java i18n trail: https://docs.oracle.com/javase/tutorial/i18n/intro/after.html
        args = setLocale(args);

//        Set the user interface for this instance of zuul
//        GameInterface.set(new CommandLineInterface());

        CommandLineInterface.main(args);


        //Game.getInstance().play();
    }
}

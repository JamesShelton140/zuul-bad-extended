package zuul;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Locale;

/**
 *
 * @author rej
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * Set the locale for this instance of Zuul. With default "en, GB" if no locale specified on program start.
         *
         * Arg check from java i18n trail: https://docs.oracle.com/javase/tutorial/i18n/intro/after.html
         */
        String language;
        String country;

        if (args.length < 2) {
            language = new String("en");
            country = new String("GB");
        } else {
            language = new String(args[0]);
            country = new String(args[1]);
        }

        GameText.setLocale(new Locale(language, country));

        Game.getInstance().play();
    }
}

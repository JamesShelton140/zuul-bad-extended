package zuul;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Structure for helper class from Utku Özdemir on stack overflow:
 * https://stackoverflow.com/questions/19686476/java-internationalization-do-i-have-to-load-a-resource-bundle-for-every-class
 */

public class GameText {
    private static Locale locale;

    public static void setLocale(Locale locale) {
        GameText.locale = locale;
    }

    public static String getString(String key) {
        return ResourceBundle.getBundle("zuul.TextBundle", locale).getString(key);
    }
}

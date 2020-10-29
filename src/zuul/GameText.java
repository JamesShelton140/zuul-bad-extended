package zuul;

import java.text.Collator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Structure for helper class from Utku Özdemir on stack overflow:
 * https://stackoverflow.com/questions/19686476/java-internationalization-do-i-have-to-load-a-resource-bundle-for-every-class
 */

public class GameText {
    private static Locale locale;
    private static Collator localeCollator;

    public static void setLocale(Locale locale) {
        GameText.locale = locale;
        GameText.localeCollator = Collator.getInstance(locale);
    }

    public static String getString(String key) {
        return ResourceBundle.getBundle("zuul.GameTextBundle", locale).getString(key);
    }

    public static String getString(String bundle, String key) {
        return ResourceBundle.getBundle("zuul."+bundle, locale).getString(key);
    }

    public static Set<String> getCommandWords() {
        return ResourceBundle.getBundle("zuul.CommandWordsBundle", locale).keySet();
    }

    public static Collator getCollator() {
        return GameText.localeCollator;
    }
}

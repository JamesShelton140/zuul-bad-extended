package zuul;

import java.text.Collator;
import java.text.MessageFormat;
import java.util.Arrays;
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
    private static MessageFormat formatter;

    public static void setLocale(Locale locale) {
        GameText.locale = locale;
        GameText.localeCollator = Collator.getInstance(locale); //locale specific collator for string comparison
        GameText.formatter = new MessageFormat("");
        formatter.setLocale(locale);
    }

    public static String getString(String key) {
        return ResourceBundle.getBundle("zuul.GameTextBundle", locale).getString(key);
    }

    public static String getString(String key, Object[] arguments) {
        formatter.applyPattern(GameText.getString(key));
        return formatter.format(arguments);
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

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
    private static MessageFormat formatter = new MessageFormat("");
    private static final String bundleDir = "zuul.resourceBundles.";

    public static void setLocale(Locale localeToSet) {
        locale = localeToSet;
        localeCollator = Collator.getInstance(locale); //locale specific collator for string comparison
        formatter.setLocale(locale);
    }

    public static String getString(String key) {
        return GameText.getString("GameTextBundle", key);
    }

    public static String getString(String key, Object[] arguments) {
        formatter.applyPattern(GameText.getString(key));
        return formatter.format(arguments);
    }

    public static String getString(String bundle, String key) {
        return ResourceBundle.getBundle(bundleDir+bundle, locale).getString(key);
    }

    public static Set<String> getCommandWords() {
        return ResourceBundle.getBundle("zuul.resourceBundles.CommandWordsBundle", locale).keySet();
    }

    public static Collator getCollator() {
        return GameText.localeCollator;
    }
}

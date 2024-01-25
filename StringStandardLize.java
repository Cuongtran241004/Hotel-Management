
package Tools;

import java.util.regex.Pattern;

/**
 * The StringStandardize class provides static methods for standardizing and manipulating strings.
 * It includes functionality to remove unnecessary spaces, remove all spaces, and standardize strings in name format.
 * @author Lenovo
 */
public class StringStandardLize {

    /**
     * Remove unnecessary spaces from the given string.
     *
     * @param str The input string.
     * @return The string with unnecessary spaces removed.
     */
    public static String removeUnnecessarySpace(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    /**
     * Remove all spaces from the given string.
     *
     * @param str The input string.
     * @return The string with all spaces removed.
     */
    public static String removeAllSpace(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", "");
        return str;
    }

    /**
     * Standardize the string in name format by capitalizing the first letter of each word.
     *
     * @param str The input string.
     * @return The standardized string.
     */
    public static String nameStandardize(String str) {
        str = removeUnnecessarySpace(str).toLowerCase();
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                str += " ";
            }
        }
        return str;
    }

}
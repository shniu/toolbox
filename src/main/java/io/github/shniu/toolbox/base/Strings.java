package io.github.shniu.toolbox.base;

/**
 * @author niushaohan
 * @date 2020/11/3 19
 */
public class Strings {
    public static final String EMPTY = "";
    public static final String BLANK = " ";
    public static final String DASH = "-";

    /**
     * Is null or empty ?
     * <p>
     * Strings.isNullOrEmpty(null)  == true
     * Strings.isNullOrEmpty("")  == true
     * Strings.isNullOrEmpty(" ")  == false
     * Strings.isNullOrEmpty(" aa")  == false
     *
     * @param str string
     * @return true or false
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * Is not empty ?
     *
     * @param str string
     * @return true or false
     */
    public static boolean isNotEmpty(String str) {
        return null != str && str.length() > 0;
    }

    /**
     * Is blank ?
     *
     * @param str string
     * @return true or false
     */
    public static boolean isBlank(String str) {
        return str != null && str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToEmpty(String str) {
        return str == null ? EMPTY : str.trim();
    }

    public static String trimToNull(String str) {
        String trimStr = trim(str);
        return isNullOrEmpty(trimStr) ? null : trimStr;
    }

    /// Pad

    public static String padStart(String string, int minLength, char padChar) {
        // TODO
        return EMPTY;
    }

    public static String padEnd(String string, int minLength, char padChar) {
        // TODO
        return EMPTY;
    }

    public static String repeat(String string, int count) {
        // TODO
        return EMPTY;
    }

    /// Replace

    public static String replaceDash(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }

        return str.replace(DASH, EMPTY);
    }
}

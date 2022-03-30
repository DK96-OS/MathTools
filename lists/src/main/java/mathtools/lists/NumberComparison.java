package mathtools.lists;

import javax.annotation.Nonnull;

/** Comparing Number Types
 * @author DK96-OS : 2022 */
public final class NumberComparison {

    private NumberComparison() {}

    /** Determine if a Number and a Byte are equivalent
     * @param number The Number to compare
     * @param primitive The byte to compare
     * @return Whether the number can be converted without loss */
    public static boolean isEquivalent(
        @Nonnull final Number number,
        final byte primitive
    ) {
        // Check whole number types
        if (number instanceof Long)
            return number.equals((long) primitive);
        else if (number instanceof Integer)
            return number.equals((int) primitive);
        else if (number instanceof Short)
            return number.equals((short) primitive);
        else if (number instanceof Byte)
            return number.equals(primitive);
        // Compare string representations
        final String nStr = number.toString();
        final int dotIndex = nStr.indexOf('.');
        // Check floating point numbers
        if (0 < dotIndex) {
            // Check length of number
            if ('-' == nStr.charAt(0)) {
                // Max size of a byte is 4 chars (-127)
                if (4 < dotIndex) return false;
                // If positive, then max size is 3 chars (127)
            } else if (3 < dotIndex) return false;
            // Check decimal place values are blank
            if (nStr.endsWith(".0"))
                // Check whole number place values equal
                return nStr.substring(0, dotIndex)
                    .equals(String.valueOf(primitive));
            else
                return false;
        } else
            return nStr.equals(String.valueOf(primitive));
    }

    /** Determine if a Number and a Short are equivalent
     * @param number The Number to compare
     * @param primitive The short to compare
     * @return Whether the number can be converted without loss */
    public static boolean isEquivalent(
        @Nonnull final Number number,
        final short primitive
    ) {
        // Check whole number types
        if (number instanceof Long)
            return number.equals((long) primitive);
        else if (number instanceof Integer)
            return number.equals((int) primitive);
        else if (number instanceof Short)
            return number.equals(primitive);
        else if (number instanceof Byte)
            return number.shortValue() == primitive;
        // Compare string representations
        final String nStr = number.toString();
        final int dotIndex = nStr.indexOf('.');
        // Check floating point numbers
        if (0 < dotIndex) {
            // Check length of number
            if ('-' == nStr.charAt(0)) {
                // Max size of a negative short is 6 chars (-32767)
                if (6 < dotIndex) return false;
                // If positive, then max size is 5 chars (32767)
            } else if (5 < dotIndex) return false;
            // Check decimal place values are blank
            if (nStr.endsWith(".0"))
                // Check whole number place values equal
                return nStr.substring(0, dotIndex)
                    .equals(String.valueOf(primitive));
            else
                return false;
        } else
            return nStr.equals(String.valueOf(primitive));
    }

    /** Determine if a Number and an Integer are equivalent
     * @param number The Number to compare
     * @param primitive The Integer to compare
     * @return Whether the number can be converted without loss */
    public static boolean isEquivalent(
        @Nonnull final Number number,
        final int primitive
    ) {
        // Check whole number types
        if (number instanceof Long)
            return number.equals((long) primitive);
        else if (number instanceof Integer)
            return number.equals(primitive);
        else if (number instanceof Short)
            return number.intValue() == primitive;
        else if (number instanceof Byte)
            return number.intValue() == primitive;
        // Compare string representations
        final String nStr = number.toString();
        final int dotIndex = nStr.indexOf('.');
        // Check floating point numbers
        if (0 < dotIndex) {
            // Check length of number
            if ('-' == nStr.charAt(0)) {
                // Max size of a negative short is 11 chars (-2147483647)
                if (11 < dotIndex) return false;
                // If positive, then max size is 10 chars (2147483647)
            } else if (10 < dotIndex) return false;
            // Check decimal place values are blank
            if (nStr.endsWith(".0"))
                // Check whole number place values equal
                return nStr.substring(0, dotIndex)
                    .equals(String.valueOf(primitive));
            else
                return false;
        } else
            return nStr.equals(String.valueOf(primitive));
    }

    /** Determine if a Number and a Long are equivalent
     * @param number The Number to compare
     * @param primitive The Long to compare
     * @return Whether the number can be converted without loss */
    public static boolean isEquivalent(
        @Nonnull final Number number,
        final long primitive
    ) {
        // Check whole number types
        if (number instanceof Long)
            return number.equals(primitive);
        else if (number instanceof Integer)
            return number.longValue() == primitive;
        else if (number instanceof Short)
            return number.longValue() == primitive;
        else if (number instanceof Byte)
            return number.longValue() == primitive;
        // Compare string representations
        final String nStr = number.toString();
        final int dotIndex = nStr.indexOf('.');
        // Check floating point numbers
        if (0 < dotIndex) {
            // Check length of number
            if ('-' == nStr.charAt(0)) {
                // Max size of a negative short is 20 chars
                if (20 < dotIndex) return false;
                // If positive, then max size is 19 chars
            } else if (19 < dotIndex) return false;
            // Check decimal place values are blank
            if (nStr.endsWith(".0"))
                // Check whole number place values equal
                return nStr.substring(0, dotIndex)
                    .equals(String.valueOf(primitive));
            else
                return false;
        } else
            return nStr.equals(String.valueOf(primitive));
    }

}
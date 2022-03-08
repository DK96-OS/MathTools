package mathtools.lists;

/** Comparing Number Types
 * @author DK96-OS : 2022 */
public final class NumberComparison {

    private NumberComparison() {}

    /** Determine if a Number and a Byte are equivalent
     * @param n The Number to compare
     * @param b The byte to compare
     * @return Whether the number can be converted without loss */
    public static boolean isEquivalent(
            final Number n,
            final byte b
    ) {
        if (n instanceof Byte)
            return n.equals(b);
        // Compare string representations
        final String nStr = n.toString();
        final int dotIndex = nStr.indexOf('.');
        // Check floating point numbers
        if (dotIndex > 0) {
            // Check length of number
            if (nStr.charAt(0) == '-') {
                // Max size of a byte is 4 chars (-127)
                if (dotIndex > 4) return false;
                // If positive, then max size is 3 chars (127)
            } else if (dotIndex > 3) return false;
            // Check decimal place values are blank
            if (nStr.endsWith(".0"))
                // Check whole number place values equal
                return nStr.substring(0, dotIndex)
                        .equals(String.valueOf(b));
            else
                return false;
        } else
            return nStr.equals(String.valueOf(b));
    }

    /** Determine if a Number and a Short are equivalent
     * @param n The Number to compare
     * @param s The short to compare
     * @return Whether the number can be converted without loss */
    public static boolean isEquivalent(
            final Number n,
            final short s
    ) {
        if (n instanceof Short)
            return n.equals(s);
        // Compare string representations
        final String nStr = n.toString();
        final int dotIndex = nStr.indexOf('.');
        // Check floating point numbers
        if (dotIndex > 0) {
            // Check length of number
            if (nStr.charAt(0) == '-') {
                // Max size of a negative short is 6 chars (-32767)
                if (dotIndex > 6) return false;
                // If positive, then max size is 5 chars (32767)
            } else if (dotIndex > 5) return false;
            // Check decimal place values are blank
            if (nStr.endsWith(".0"))
                // Check whole number place values equal
                return nStr.substring(0, dotIndex)
                        .equals(String.valueOf(s));
            else
                return false;
        } else
            return nStr.equals(String.valueOf(s));
    }

}
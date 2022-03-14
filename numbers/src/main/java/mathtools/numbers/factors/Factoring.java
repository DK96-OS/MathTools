package mathtools.numbers.factors;

/** Functions that do factoring
 * @author DK96-OS : 2022 */
public final class Factoring {

    private Factoring() {}

    /** Reduce by dividing with a factor until a remainder occurs
     * @param number The number to be reduced
     * @param factor The factor to try dividing with
     * @return The reduced number, or the number itself if no divisions succeeded */
    public static long divideOutFactor(
            long number,
            final int factor
    ) {
        // These factors are invalid, or won't do anything
        if (-1 <= factor && factor <= 1) return number;
        // Convert factor to Long once
        final long lFactor = factor < 0 ? -factor : factor;
        // Whether the input number was negative
        final boolean isNegativeNumber = number < 0;
        // Divide and check
        long reduced = number / lFactor;
        while (reduced * lFactor == number) {
            // Update and check again
            number = reduced;
            if (isNegativeNumber) {
                if (number > -2) break;
            } else
                if (number < lFactor) break;
            reduced /= lFactor;
        }
        return number;
    }

    /** Reduce by dividing with a factor until a remainder occurs
     * @param number The number to be reduced
     * @param factor The factor to try dividing with. Should be positive.
     * @return The reduced number, or the number itself if no divisions succeeded */
    public static long divideOutFactor(
            long number,
            long factor
    ) {
        // Whether the input number is negative
        final boolean isNegativeNumber = number < 0;
        // Invert negative factors
        if (factor < 0) {
            // Protect against Min Value
            if (factor == Long.MIN_VALUE) {
                if (number == Long.MIN_VALUE) return 1;
                return number;
            }
            factor = -factor;
        }
        // Check cases where nothing will happen
        if (factor < 2) return number;
        // Check if factor has greater magnitude than number
        if (isNegativeNumber) {
            if (number > -factor) return number;
        } else
            if (number < factor) return number;
        // Divide and check
        long reduced = number / factor;
        while (reduced * factor == number) {
            // Update and check again
            number = reduced;
            if (isNegativeNumber) {
                if (number > -2) break;
            } else
                if (number < factor) break;
            reduced /= factor;
        }
        return number;
    }

}
package mathtools.numbers.factors;

/** Common Factoring Operations
 * @author DK96-OS : 2022 */
public final class Factoring {

    /** Reduce by dividing with a factor until a non-zero remainder occurs.
     * @param number The number to be reduced.
     * @param factor The factor to try dividing with.
     * @return The reduced number, or the number itself if no divisions succeeded.
     */
    public static long divideOutFactor(
        long number,
        final int factor
    ) {
        // These factors are invalid, or won't do anything
        if (-1 <= factor && 1 >= factor) return number;
        // Convert factor to Long once
        final long lFactor = 0 > factor ? -factor : factor;
        // Whether the input number was negative
        final boolean isNegativeNumber = 0 > number;
        // Divide and check
        long reduced = number / lFactor;
        while (reduced * lFactor == number) {
            // Update and check again
            number = reduced;
            if (isNegativeNumber) {
                if (-2 < number) break;
            } else
                if (number < lFactor) break;
            reduced /= lFactor;
        }
        return number;
    }

    /** Reduce by dividing with a factor until a remainder occurs.
     * @param number The number to be reduced.
     * @param factor The factor to try dividing with. Should be positive.
     * @return The reduced number, or the number itself if no divisions succeeded.
     */
    public static long divideOutFactor(
        long number,
        long factor
    ) {
        // Whether the input number is negative
        final boolean isNegativeNumber = 0 > number;
        // Invert negative factors
        if (0 > factor) {
            // Protect against Min Value
            if (Long.MIN_VALUE == factor) {
                if (Long.MIN_VALUE == number) return 1;
                return number;
            }
            factor = -factor;
        }
        // Check cases where nothing will happen
        if (2 > factor) return number;
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
                if (-2 < number) break;
            } else
                if (number < factor) break;
            reduced /= factor;
        }
        return number;
    }

    private Factoring() {}

}
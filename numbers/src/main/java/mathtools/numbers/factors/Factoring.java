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
            final long number,
            final int factor
    ) {
        // These factors are invalid, or won't do anything
        if (-1 <= factor && factor <= 1) return number;
        // Convert factor to Long once
        final long lFactor = factor < 0 ? -factor : factor;
        // Divide and check
        long reduced = number / lFactor;
        if (reduced * lFactor == number) {
            // Try reducing by another factor
            long newReduced = reduced / lFactor;
            while (newReduced * lFactor == reduced) {
                reduced = newReduced;
                if (reduced < 2) break;
                newReduced = reduced / lFactor;
            }
            return reduced;
        } else
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
        // Invert negative factors
        if (factor < 0) {
            // Protect against Min Value
            if (factor == Long.MIN_VALUE) {
                if (number == Long.MIN_VALUE) return 1;
                return number;
            }
            factor = -factor;
        }
        // These factors won't do anything
        if (factor > number || factor < 2) return number;
        // Divide and check
        long reduced = number / factor;
        while (reduced * factor == number) {
            // Update and check again
            number = reduced;
            if (number < factor) break;
            reduced = number / factor;
        }
        return number;
    }

}
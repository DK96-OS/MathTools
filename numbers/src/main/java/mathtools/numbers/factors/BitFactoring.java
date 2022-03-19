package mathtools.numbers.factors;

/** Uses bit representation for efficient factoring
 * @author DK96-OS : 2022 */
public final class BitFactoring {

    /** Determines whether a number contains a factor of 2
     * @param number the Number to check
     * @return True if number is divisible by 2 */
    public static boolean isProductOf2(
        final long number
    ) {
        return 1 < (number & -number);
    }

    /** Determines whether a number contains a factor of 2
     * @param number the Number to check
     * @return True if number is divisible by 2 */
    public static boolean isProductOf2(
        final int number
    ) {
        return 1 < (number & -number);
    }

    private BitFactoring() {}

}
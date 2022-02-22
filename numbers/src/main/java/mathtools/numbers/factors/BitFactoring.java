package mathtools.numbers.factors;

/** Uses bit representation for efficient factoring
 * @author DK96-OS : 2022 */
public final class BitFactoring {

    private BitFactoring() {}

    /** Determines whether a number contains a factor of 2
     * @param number the Number to check
     * @return True if number is divisible by 2 */
    public static boolean isProductOf2(
            long number
    ) {
        return (number & -number) > 1;
    }

    /** Determines whether a number contains a factor of 2
     * @param number the Number to check
     * @return True if number is divisible by 2 */
    public static boolean isProductOf2(
            int number
    ) {
        return (number & -number) > 1;
    }

}
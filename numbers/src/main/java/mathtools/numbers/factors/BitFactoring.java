package mathtools.numbers.factors;

/** Uses bit representation and operations for efficient factoring.
 * @author DK96-OS : 2022 - 2023
 */
public final class BitFactoring {

    /** Determines whether a number contains a factor of 2.
     * @param number The number to validate.
     * @return True if number is divisible by 2.
     */
    public static boolean isProductOf2(
        final long number
    ) {
        return 1L < Long.lowestOneBit(number)
            || number == Long.MIN_VALUE;
    }

    /** Determines whether a number contains a factor of 2.
     * @param number The number to validate.
     * @return True if number is divisible by 2.
     */
    public static boolean isProductOf2(
        final int number
    ) {
        return 1 < Integer.lowestOneBit(number)
            || number == Integer.MIN_VALUE;
    }
    
    /** Divides by 2 as many times as possible.
     * @param number The number to be divided, if possible.
     * @return An integer that can no longer be divided by 2 evenly.
     */
    public static int divide2(
    	final int number
    ) {
    	final int trail = Integer.numberOfTrailingZeros(number);
        return trail == 0 ? number : number >> trail;
    }
    
    /** Divides by 2 as many times as possible.
     * @param number The number to be divided, if possible.
     * @return An integer that can no longer be divided by 2 evenly.
     */
    public static long divide2(
    	final long number
    ) {
    	final int trail = Long.numberOfTrailingZeros(number);
        return trail == 0 ? number : number >> trail;
    }

    private BitFactoring() {}

}
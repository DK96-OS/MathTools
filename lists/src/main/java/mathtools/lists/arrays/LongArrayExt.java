package mathtools.lists.arrays;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/** Methods for operating on a long array
 * @author DK96-OS : 2022 */
public final class LongArrayExt {

    private LongArrayExt() {}

    /** Calculate the sum.
     * Uses BigInteger when necessary, tries to use long.
     * @param array The elements to add in the sum
     * @return A long integer, the sum of the elements */
    public static BigInteger sum(
            @NotNull long[] array
    ) {
        long limit = Long.MAX_VALUE / 2;
        BigInteger sum = BigInteger.ZERO;
        long minorSum = 0;
        for (long next : array) {
            if (next >= limit)
                sum = sum.add(BigInteger.valueOf(next));
            else if (minorSum < limit)
                minorSum += next;
            else {
                long trySum = minorSum + next;
                // check for overflow
                if (trySum > minorSum)  // No overflow
                    minorSum = trySum;
                else {
                    sum = sum.add(BigInteger.valueOf(minorSum));
                    minorSum = next;
                }
            }
        }
        if (minorSum != 0L)
            sum = sum.add(BigInteger.valueOf(minorSum));
        return sum;
    }
    
}
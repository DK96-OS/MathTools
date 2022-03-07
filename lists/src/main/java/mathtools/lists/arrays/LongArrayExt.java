package mathtools.lists.arrays;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

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

    /** Create a List from the values in an array */
    @Nonnull
    public static List<Long> toList(
            @Nonnull final long[] array
    ) {
        if (array.length < 5) switch (array.length) {
            case 4: return List.of(array[0], array[1], array[2], array[3]);
            case 3: return List.of(array[0], array[1], array[2]);
            case 2: return List.of(array[0], array[1]);
            case 1: return List.of(array[0]);
            default: return Collections.emptyList();
        }
        final ArrayList<Long> list = new ArrayList<>(array.length);
        for (long i : array) list.add(i);
        return list;
    }

    /** Determines whether all Short values in this array are non-zero */
    public static boolean allNonZero(
            @Nonnull final long[] array
    ) {
        for (long l : array)
            if (0 == l) return false;
        return true;
    }

}
package mathtools.arrays;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mathtools.arrays.sum.BigIntSumBuffer;

/** Methods for operating on a long array
 * @author DK96-OS : 2022 */
public final class LongArrayExt {

    private LongArrayExt() {}

    /** Calculate the sum.
     * Uses BigInteger when necessary, tries to use long.
     * @param array The elements to add in the sum
     * @return A long integer, the sum of the elements */
    public static BigInteger sum(
            @Nonnull long[] array
    ) {
        if (array.length == 0) return BigInteger.ZERO;
        else if (array.length == 1) return BigInteger.valueOf(array[0]);
        //
        final BigIntSumBuffer buffer = new BigIntSumBuffer();
        for (long next : array) buffer.add(next);
        return buffer.getSum();
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


    /** Determine the min and max values in the array.
     * @param array The array of values to search.
     * @return An array containing the min and max values, or null if array is empty.
     */
    @Nullable
    public static long[] getMinAndMax(
            @Nonnull final long[] array
    ) {
        if (array.length == 0) return null;
        if (array.length == 1) return new long[]{array[0], array[0]};
        long min = array[0];
        long max = array[0];
        for (int i = 1; i < array.length; ++i) {
            final long value = array[i];
            if (value < min) min = value;
            else if (value > max) max = value;
        }
        return new long[]{min, max};
    }

}
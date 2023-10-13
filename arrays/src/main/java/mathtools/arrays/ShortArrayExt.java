package mathtools.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Methods for operating on a short array
 * @author DK96-OS : 2022 */
public final class ShortArrayExt {

    private ShortArrayExt() {}

    /** Calculate the sum
     *  Does not prevent 64-bit long overflow
     * @param array The elements to add in the sum
     * @return A long integer, the sum of the elements */
    public static long sum(
            @Nonnull short[] array
    ) {
        long sum = 0;
        for (short s : array) sum += s;
        return sum;
    }

    /** Create a List from the values in an array */
    @Nonnull
    public static List<Short> toList(
            @Nonnull final short[] array
    ) {
        if (array.length < 5) switch (array.length) {
            case 4: return List.of(array[0], array[1], array[2], array[3]);
            case 3: return List.of(array[0], array[1], array[2]);
            case 2: return List.of(array[0], array[1]);
            case 1: return List.of(array[0]);
            default: return Collections.emptyList();
        }
        final ArrayList<Short> list = new ArrayList<>(array.length);
        for (short i : array) list.add(i);
        return list;
    }

    /** Determines whether all Short values in this array are non-zero */
    public static boolean allNonZero(
            @Nonnull final short[] array
    ) {
        for (short value : array)
            if (0 == value) return false;
        return true;
    }

    /** Determine the min and max values in the array.
     * @param array The array of values to search.
     * @return An array containing the min and max values, or null if array is empty.
     */
    @Nullable
    public static short[] getMinAndMax(
            @Nonnull final short[] array
    ) {
        if (array.length == 0) return null;
        if (array.length == 1) return new short[]{array[0], array[0]};
        short min = array[0];
        short max = array[0];
        for (int i = 1; i < array.length; ++i) {
            final short value = array[i];
            if (value < min) min = value;
            else if (value > max) max = value;
        }
        return new short[]{min, max};
    }

}
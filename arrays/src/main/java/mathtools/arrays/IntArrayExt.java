package mathtools.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Methods for operating on an int array
 * @author DK96-OS : 2022 */
public final class IntArrayExt {

    private IntArrayExt() {}

    /** Calculate the sum
     *  Does not prevent 64-bit long overflow
     * @param array The elements to add in the sum
     * @return A long integer, the sum of the elements */
    public static long sum(
            @Nonnull int[] array
    ) {
        long sum = 0;
        for (int s : array) sum += s;
        return sum;
    }

    /** Create a List from the values in an array */
    @Nonnull
    public static List<Integer> toList(
            @Nonnull final int[] array
    ) {
        if (array.length < 5) switch (array.length) {
            case 4: return List.of(array[0], array[1], array[2], array[3]);
            case 3: return List.of(array[0], array[1], array[2]);
            case 2: return List.of(array[0], array[1]);
            case 1: return List.of(array[0]);
            default: return Collections.emptyList();
        }
        final ArrayList<Integer> list = new ArrayList<>(array.length);
        for (int i : array) list.add(i);
        return list;
    }

    /** Determines whether all Integer values in this array are non-zero */
    public static boolean allNonZero(
            @Nonnull final int[] array
    ) {
        for (int j : array)
            if (0 == j) return false;
        return true;
    }

    /** Determine the min and max values in the array.
     * @param array The array of values to search.
     * @return An array containing the min and max values, or null if array is empty.
     */
    @Nullable
    public static int[] getMinAndMax(
            @Nonnull final int[] array
    ) {
        if (array.length == 0) return null;
        if (array.length == 1) return new int[]{array[0], array[0]};
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; ++i) {
            final int value = array[i];
            if (value < min) min = value;
            else if (value > max) max = value;
        }
        return new int[]{min, max};
    }

}
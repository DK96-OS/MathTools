package mathtools.lists.arrays;

import com.google.common.primitives.Bytes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Methods for operating on a byte array
 * @author DK96-OS : 2022 */
public final class ByteArrayExt {

    /** Compute the sum of all elements in the array
     * @param array The array of elements
     * @return A Long value containing the sum of the elements */
    public static long sum(
        @Nonnull final byte[] array
    ) {
        long sum = 0;
        for (byte b : array) sum += b;
        return sum;
    }

    /** Clears any nonzero elements in the Array
     *  Uses java.util.Arrays.fill()
     * @param array The array to reset to all zero */
    public static void clear(
        @Nonnull final byte[] array
    ) {
        Arrays.fill(array, (byte) 0);
    }

    /** Create a List from the values in an array */
    @Nonnull
    public static List<Byte> toList(
        @Nonnull final byte[] array
    ) {
        if (5 > array.length) switch (array.length) {
            case 4: return List.of(array[0], array[1], array[2], array[3]);
            case 3: return List.of(array[0], array[1], array[2]);
            case 2: return List.of(array[0], array[1]);
            case 1: return List.of(array[0]);
            default: return Collections.emptyList();
        }
        final ArrayList<Byte> list = new ArrayList<>(array.length);
        for (byte i : array) list.add(i);
        return list;
    }

    /** Determines whether all Integer values in this array are non-zero */
    public static boolean allNonZero(
        @Nonnull final byte[] array
    ) {
        for (byte j : array)
            if (0 == j) return false;
        return true;
    }

    /** Search the array from start to end, for a specific repeating value.
     * @param array The array to search in.
     * @param target The target values to search for.
     * @param counter The number of target values to find.
     * @return The index of the n-th value, or -1 if this target was not found.
     */
    public static int findTargetValueN(
        @Nonnull final byte[] array,
        final byte target,
        int counter
    ) {
        final int firstIndex = Bytes.indexOf(array, target);
        //
        if (-1 == firstIndex) return -1;
        // If only 1 is required
        if (1 == counter--) return firstIndex;
        // Continue the search
        int searchIndex = firstIndex + 1;
        for (;
             searchIndex < array.length;
             ++searchIndex
        ) {
            if (target == array[searchIndex]) {
                // When counter is done, return the index
                if (0 == --counter) return searchIndex;
            }
        }
        // Done reading array, not enough target values found
        return -1;
    }

    /** Determine the min and max values in the array.
     * @param array The array of values to search.
     * @return An array containing the min and max values, or null if array is empty.
     */
    @Nullable
    public static byte[] getMinAndMax(
        @Nonnull final byte[] array
    ) {
        if (array.length == 0) return null;
        if (array.length == 1) return new byte[]{array[0], array[0]};
        byte min = array[0];
        byte max = array[0];
        for (int i = 1; i < array.length; ++i) {
            final byte value = array[i];
            if (value < min) min = value;
            else if (value > max) max = value;
        }
        return new byte[]{min, max};
    }

    private ByteArrayExt() {}

}
package mathtools.lists.arrays;

import com.google.common.primitives.Bytes;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

/** Methods for operating on a byte array
 * @author DK96-OS : 2022 */
public final class ByteArrayExt {

    /** Compute the sum of all elements in the array
     * @param array The array of elements
     * @return A Long value containing the sum of the elements */
    public static long sum(
        @NotNull final byte[] array
    ) {
        long sum = 0;
        for (byte b : array) sum += b;
        return sum;
    }

    /** Clears any nonzero elements in the Array
     *  Uses java.util.Arrays.fill()
     * @param array The array to reset to all zero */
    public static void clear(
        @NotNull final byte[] array
    ) {
        Arrays.fill(array, (byte) 0);
    }

    /** Create a List from the values in an array */
    @NonNull
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

    /** Search the array from start to end, for a specific repeating value
     * @param array The array to search in
     * @param target The target values to search for
     * @param counter The number of target values to find
     * @return The index of the n-th value, or -1 if  */
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

    private ByteArrayExt() {}

}
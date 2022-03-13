package mathtools.lists.arrays;

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

    private ByteArrayExt() {}

    /** Compute the sum of all elements in the array
     * @param array The array of elements
     * @return A Long value containing the sum of the elements */
    public static long sum(
            @NotNull byte[] array
    ) {
        long sum = 0;
        for (byte b : array) sum += b;
        return sum;
    }

    /** Clears any nonzero elements in the Array
     *  Uses java.util.Arrays.fill()
     * @param array The array to reset to all zero */
    public static void clear(
            @NotNull byte[] array
    ) {
        Arrays.fill(array, (byte) 0);
    }

    /** Create a List from the values in an array */
    @NonNull
    public static List<Byte> toList(
            @Nonnull final byte[] array
    ) {
        if (array.length < 5) switch (array.length) {
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

}
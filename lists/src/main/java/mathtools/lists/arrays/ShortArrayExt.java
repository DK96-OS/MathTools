package mathtools.lists.arrays;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

/** Methods for operating on a short array
 * @author DK96-OS : 2022 */
public final class ShortArrayExt {

    private ShortArrayExt() {}

    /** Calculate the sum
     *  Does not prevent 64-bit long overflow
     * @param array The elements to add in the sum
     * @return A long integer, the sum of the elements */
    public static long sum(
            @NotNull short[] array
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
            case 0: return Collections.emptyList();
            case 1: return List.of(array[0]);
            case 2: return List.of(array[0], array[1]);
            case 3: return List.of(array[0], array[1], array[2]);
            case 4: return List.of(array[0], array[1], array[2], array[3]);
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

}
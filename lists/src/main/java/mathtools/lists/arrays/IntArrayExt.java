package mathtools.lists.arrays;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

/** Methods for operating on an int array
 * @author DK96-OS : 2022 */
public final class IntArrayExt {

    private IntArrayExt() {}

    /** Calculate the sum
     *  Does not prevent 64-bit long overflow
     * @param array The elements to add in the sum
     * @return A long integer, the sum of the elements */
    public static long sum(
            @NotNull int[] array
    ) {
        long sum = 0;
        for (int s : array) sum += s;
        return sum;
    }

    /** Create a List from the values in an array */
    @NonNull
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

}
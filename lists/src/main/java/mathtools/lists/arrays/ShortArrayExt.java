package mathtools.lists.arrays;

import org.jetbrains.annotations.NotNull;

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

}
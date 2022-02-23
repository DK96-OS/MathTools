package mathtools.lists.arrays;

import org.jetbrains.annotations.NotNull;

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

}
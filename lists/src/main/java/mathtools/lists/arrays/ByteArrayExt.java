package mathtools.lists.arrays;

/** Methods for operating on a byte array
 * @author DK96-OS : 2022 */
public class ByteArrayExt {

    private ByteArrayExt() {}

    /** Compute the sum of all elements in the array
     * @param array The array of elements
     * @return A Long value containing the sum of the elements */
    public static long sum(byte[] array) {
        long sum = 0;
        for (byte b : array) sum += b;
        return sum;
    }

    /** Clears any nonzero elements in the Array
     * @param array The array to reset to all zero */
    public static void clear(
            byte[] array
    ) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) array[i] = 0;
        }
    }

}
package mathtools.statistics;

import mathtools.lists.arrays.ByteArrayExt;
import mathtools.lists.arrays.ShortArrayExt;

/** Statistics functions on Array types
 * @author DK96-OS : 2022 */
public class ArrayStatistics {

    private ArrayStatistics() {}

    /** Calculate the mean value of an 8 bit Byte Array
     * @param array The array of values to use in the calculation
     * @return A double representing the mean (average) value */
    public static double calculateMean(
            byte[] array
    ) {
        if (array.length < 1) return 0;
        double sum = (double) ByteArrayExt.sum(array);
        return sum / array.length;
    }

    /** Calculate the mean value of a 16 bit Short Array
     * @param array The array of values to use in the calculation
     * @return A double representing the mean (average) value */
    public static double calculateMean(
            short[] array
    ) {
        if (array.length < 1) return 0;
        double sum = (double) ShortArrayExt.sum(array);
        return sum / array.length;
    }

}
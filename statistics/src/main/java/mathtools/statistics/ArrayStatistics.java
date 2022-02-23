package mathtools.statistics;

import mathtools.lists.arrays.ByteArrayExt;
import mathtools.lists.arrays.IntArrayExt;
import mathtools.lists.arrays.LongArrayExt;
import mathtools.lists.arrays.ShortArrayExt;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/** Statistics functions on Array types
 * @author DK96-OS : 2022 */
public final class ArrayStatistics {

    private ArrayStatistics() {}

    /** Calculate the mean value of an 8 bit Byte Array
     * @param array The array of values to use in the calculation
     * @return A double representing the mean (average) value */
    public static double calculateMean(
            @NotNull byte[] array
    ) {
        if (array.length < 1) return 0;
        double sum = (double) ByteArrayExt.sum(array);
        return sum / array.length;
    }

    /** Calculate the mean value of a 16 bit Short Array
     * @param array The array of values to use in the calculation
     * @return A double representing the mean (average) value */
    public static double calculateMean(
            @NotNull short[] array
    ) {
        if (array.length < 1) return 0;
        double sum = (double) ShortArrayExt.sum(array);
        return sum / array.length;
    }

    /** Calculate the mean value of a 32 bit Int Array
     * @param array The array of values to use in the calculation
     * @return A double representing the mean (average) value */
    public static double calculateMean(
            @NotNull int[] array
    ) {
        if (array.length < 1) return 0;
        double sum = (double) IntArrayExt.sum(array);
        return sum / array.length;
    }
    
    /** Calculate the mean value of a 64 bit Long Array
     * @param array The array of values to use in the calculation
     * @return A double representing the mean (average) value */
    public static double calculateMean(
            @NotNull long[] array
    ) {
        if (array.length < 1) return 0;
        BigInteger sum = LongArrayExt.sum(array);
        return sum.divide(
                BigInteger.valueOf(array.length)
        ).doubleValue();
    }

}
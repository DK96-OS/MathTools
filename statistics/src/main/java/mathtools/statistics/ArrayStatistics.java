package mathtools.statistics;

import com.google.common.math.Stats;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import mathtools.lists.arrays.ByteArrayExt;
import mathtools.lists.arrays.IntArrayExt;
import mathtools.lists.arrays.LongArrayExt;
import mathtools.lists.arrays.ShortArrayExt;

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

    /** Calculate the mean value of a 32 bit Float Array
     *  Ignores Infinite and NaN values
     *  Does not check precision loss, outcome may depend on order of input
     * @param array The array of Float values
     * @return The mean of array values, or zero if empty */
    public static double calculateMean(
            @NotNull float[] array
    ) {
        if (array.length < 1)
            return 0.0;
        else {
            double sum = 0.0;
            for (float f : array) {
                if (Float.isFinite(f)) sum += f;
            }
            return sum / array.length;
        }
    }

    /** Calculate mean value of a 64 bit Double Array
     *  See [com.google.common.math.Stats] meanOf method for details.
     *  Prevents IllegalArgumentException on empty list
     * @param array The array of Double values
     * @return The mean of array values, or zero if empty */
    public static double calculateMean(
            @NotNull double[] array
    ) {
        return (array.length < 1) ? 0.0 : Stats.meanOf(array);
    }

}
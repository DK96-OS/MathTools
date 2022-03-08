package mathtools.statistics;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
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
            int infiniteNumbers = 0;    // Count numbers removed from sum
            for (float f : array) {
                if (Float.isFinite(f)) sum += f;
                else
                    infiniteNumbers++;
            }
            if (infiniteNumbers > 0) {
                final int finiteNumbers = array.length - infiniteNumbers;
                return (finiteNumbers < 2) ? sum : (sum / finiteNumbers);
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
        if (array.length < 1)
            return 0.0;
        else {
            BigDecimal sum = BigDecimal.ZERO;
            int infiniteNumbers = 0;
            for (double d : array) {
                if (Double.isFinite(d))
                    sum = sum.add(BigDecimal.valueOf(d));
                else
                    infiniteNumbers++;
            }
            if (infiniteNumbers > 0) {
                final int finiteNumbers = array.length - infiniteNumbers;
                if (finiteNumbers < 2)
                    return sum.doubleValue();
                else
                    return sum.divide(
                        BigDecimal.valueOf(finiteNumbers)
                    ).doubleValue();
            }
            return sum.divide(
                    BigDecimal.valueOf(array.length)
            ).doubleValue();
        }
    }

}
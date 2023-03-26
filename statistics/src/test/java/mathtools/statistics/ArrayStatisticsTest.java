package mathtools.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.statistics.ArrayStatistics.calculateMean;
import static mathtools.statistics.ArrayStatisticsKt.calculateSDev;

import org.junit.jupiter.api.Test;

/** Testing [ArrayStatistics] functions
 * @author DK96-OS : 2022 */
public final class ArrayStatisticsTest {

    @Test
    public void testCalculateMean_EmptyArray_ReturnsZero() {
        assertEquals(
            0.0, calculateMean(new byte[0]));
        assertEquals(
            0.0, calculateMean(new short[0]));
        assertEquals(
            0.0, calculateMean(new int[0]));
        assertEquals(
            0.0, calculateMean(new long[0]));
        assertEquals(
            0.0, calculateMean(new double[0]));
        assertEquals(
            0.0, calculateMean(new float[0]));
    }

    @Test
    public void testCalculateSDev_EmptyArray_ReturnsZero() {
        assertEquals(
            0.0, calculateSDev(new byte[0], 0));
        assertEquals(
            0.0, calculateSDev(new short[0], 0));
        assertEquals(
            0.0, calculateSDev(new int[0], 0));
        assertEquals(
            0.0, calculateSDev(new long[0], 0));
        assertEquals(
            0.0, calculateSDev(new float[0], 0));
        assertEquals(
            0.0, calculateSDev(new double[0], 0));
    }

    @Test
    public void testCalculateMean_InfiniteFloat_ReturnsZero() {
        float[] input = new float[]{ Float.NEGATIVE_INFINITY };
        assertEquals(0.0, calculateMean(input));
        //
        input[0] = Float.POSITIVE_INFINITY;
        assertEquals(0.0, calculateMean(input));
        //
        input[0] = Float.NaN;
        assertEquals(0.0, calculateMean(input));
    }

    @Test
    public void testCalculateMean_InfiniteDouble_ReturnsZero() {
        double[] input = new double[]{ Double.NEGATIVE_INFINITY };
        assertEquals(0.0, calculateMean(input));
        //
        input[0] = Double.POSITIVE_INFINITY;
        assertEquals(0.0, calculateMean(input));
        //
        input[0] = Double.NaN;
        assertEquals(0.0, calculateMean(input));
    }

    @Test
    public void testCalculateMean_InfiniteFloats_IgnoresInfiniteValues() {
        float[] input = new float[]{ 4.2f, 4.0f, Float.NEGATIVE_INFINITY };
        assertEquals(
            4.1f, calculateMean(input), 0.00001f
        );
        input[2] = Float.POSITIVE_INFINITY;
        assertEquals(
            4.1f, calculateMean(input), 0.00001f
        );
        input[2] = Float.NaN;
        assertEquals(
            4.1f, calculateMean(input), 0.00001f
        );
    }

    @Test
    public void testCalculateMean_InfiniteDoubles_IgnoresInfiniteValues() {
        double[] input = new double[] { 4.0, 4.2, Double.NEGATIVE_INFINITY };
        assertEquals(
            4.1, calculateMean(input), 0.00001f
        );
        input[2] = Double.POSITIVE_INFINITY;
        assertEquals(
            4.1, calculateMean(input), 0.00001f
        );
        input[2] = Double.NaN;
        assertEquals(
            4.1, calculateMean(input), 0.00001f
        );
    }

}
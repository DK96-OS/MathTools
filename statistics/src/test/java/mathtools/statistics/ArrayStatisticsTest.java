package mathtools.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static mathtools.statistics.ArrayStatistics.calculateMean;
import static mathtools.statistics.ArrayStatisticsKt.calculateSDev;

import org.junit.jupiter.api.Test;

/** Testing [ArrayStatistics] functions
 * @author DK96-OS : 2022 */
public class ArrayStatisticsTest {

    @Test
    void testMeanEmptyArray() {
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
    void testSDevEmptyArray() {
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
    void testMeanNonFiniteValues() {
        // Float
        assertEquals(
                8.2,
                calculateMean(new float[] { 8.2f, Float.NEGATIVE_INFINITY }),
                0.000001
        );
        assertEquals(
                8.2,
                calculateMean(new float[] { 8.2f, Float.POSITIVE_INFINITY }),
                0.000001
        );
        assertEquals(
                8.2,
                calculateMean(new float[] { 8.2f, Float.NaN }),
                0.000001
        );
        // Double
        assertEquals(
                8.2,
                calculateMean(new double[] { 8.2, Double.NEGATIVE_INFINITY })
        );
        assertEquals(
                8.2,
                calculateMean(new double[] { 8.2, Double.POSITIVE_INFINITY })
        );
        assertEquals(
                8.2,
                calculateMean(new double[] { 8.2, Double.NaN })
        );
    }

}
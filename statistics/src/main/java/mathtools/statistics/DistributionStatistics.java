package mathtools.statistics;

import static mathtools.statistics.ArrayStatisticsKt.calculateSDev;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;
import com.google.common.primitives.SignedBytes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Array Statistics Functions
 * @author DK96-OS : 2022 */
public final class DistributionStatistics {

    /** Process an Array to determine it's DistributionStats
     * @param array The Array to be processed
     * @return A DistributionStats representing the Array data
     */
    @Nullable
    public static DistributionStats process(
        @Nonnull final byte[] array
    ) {
        if (2 > array.length)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            SignedBytes.min(array),
            SignedBytes.max(array)
        );
    }

    /** Process an Array to determine it's DistributionStats
     * @param array The Array to be processed
     * @return A DistributionStats representing the Array data
     */
    @Nullable
    public static DistributionStats process(
        @Nonnull final short[] array
    ) {
        if (2 > array.length)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
                mean,
                calculateSDev(array, mean),
                Shorts.min(array),
                Shorts.max(array)
        );
    }

    /** Process an Array to determine it's DistributionStats
     * @param array The Array to be processed
     * @return A DistributionStats representing the Array data
     */
    @Nullable
    public static DistributionStats process(
        @Nonnull final int[] array
    ) {
        if (2 > array.length)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
                mean,
                calculateSDev(array, mean),
                Ints.min(array),
                Ints.max(array)
        );
    }

    /** Process an Array to determine it's DistributionStats
     * @param array The Array to be processed
     * @return A DistributionStats representing the Array data
     */
    @Nullable
    public static DistributionStats process(
        @Nonnull final long[] array
    ) {
        if (2 > array.length)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
                mean,
                calculateSDev(array, mean),
                Longs.min(array),
                Longs.max(array)
        );
    }

    /** Process an Array to determine it's DistributionStats
     * @param array The Array to be processed
     * @return A DistributionStats representing the Array data
     */
    @Nullable
    public static DistributionStats process(
        @Nonnull final float[] array
    ) {
        if (2 > array.length)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            Floats.min(array),
            Floats.max(array)
        );
    }

    /** Process an Array to determine it's DistributionStats
     * @param array The Array to be processed
     * @return A DistributionStats representing the Array data
     */
    @Nullable
    public static DistributionStats process(
        @Nonnull final double[] array
    ) {
        if (2 > array.length)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            Doubles.min(array),
            Doubles.max(array)
        );
    }

    private DistributionStatistics() {}

}
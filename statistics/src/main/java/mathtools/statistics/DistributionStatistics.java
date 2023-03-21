package mathtools.statistics;

import static mathtools.statistics.ArrayStatisticsKt.calculateSDev;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mathtools.lists.arrays.ByteArrayExt;
import mathtools.lists.arrays.IntArrayExt;
import mathtools.lists.arrays.LongArrayExt;
import mathtools.lists.arrays.ShortArrayExt;

/** Array Statistics Functions.
 * @author DK96-OS : 2022
 */
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
        final byte[] minAndMax = ByteArrayExt.getMinAndMax(array);
        if (minAndMax == null)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            minAndMax[0],
            minAndMax[1],
            array.length
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
        final short[] minAndMax = ShortArrayExt.getMinAndMax(array);
        if (minAndMax == null)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            minAndMax[0],
            minAndMax[1],
            array.length
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
        final int[] minAndMax = IntArrayExt.getMinAndMax(array);
        if (minAndMax == null)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            minAndMax[0],
            minAndMax[1],
            array.length
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
        final long[] minAndMax = LongArrayExt.getMinAndMax(array);
        if (minAndMax == null)
            return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            minAndMax[0],
            minAndMax[1],
            array.length
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
            Floats.max(array),
            array.length
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
            Doubles.max(array),
            array.length
        );
    }

    private DistributionStatistics() {}

}

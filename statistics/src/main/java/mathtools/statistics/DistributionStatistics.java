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
        final byte min = minAndMax[0];
        final byte max = minAndMax[1];
        if (min == max) {
            return new DistributionStats(
                min, 0, min, max, array.length
            );
        }
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            min,
            max,
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
        final short min = minAndMax[0];
        final short max = minAndMax[1];
        if (min == max) {
            return new DistributionStats(
                min, 0, min, max, array.length
            );
        }
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            min,
            max,
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
        final int min = minAndMax[0];
        final int max = minAndMax[1];
        if (min == max) {
            return new DistributionStats(
                min, 0, min, max, array.length
            );
        }
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            min,
            max,
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
        final long min = minAndMax[0];
        final long max = minAndMax[1];
        if (min == max) {
            return new DistributionStats(
                min, 0, min, max, array.length
            );
        }
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            min,
            max,
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
        final float min = Floats.min(array);
        final float max = Floats.max(array);
        if (min == max) {
            return new DistributionStats(
                min, 0, min, max, array.length
            );
        }
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            min,
            max,
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
        final double min = Doubles.min(array);
        final double max = Doubles.max(array);
        if (min == max) {
            return new DistributionStats(
                min, 0, min, max, array.length
            );
        }
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
            mean,
            calculateSDev(array, mean),
            min,
            max,
            array.length
        );
    }

    private DistributionStatistics() {}

}
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

/** Static functions for Statistics
 * @author DK96-OS : 2022 */
final class DistributionStatistics {

    private DistributionStatistics() {}

    @Nullable
    static DistributionStats process(
            @Nonnull final byte[] array
    ) {
        if (array.length < 2) return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
                mean,
                calculateSDev(array, mean),
                SignedBytes.min(array),
                SignedBytes.max(array)
        );
    }

    @Nullable
    static DistributionStats process(
            @Nonnull final short[] array
    ) {
        if (array.length < 2) return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
                mean,
                calculateSDev(array, mean),
                Shorts.min(array),
                Shorts.max(array)
        );
    }

    @Nullable
    static DistributionStats process(
            @Nonnull final int[] array
    ) {
        if (array.length < 2) return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
                mean,
                calculateSDev(array, mean),
                Ints.min(array),
                Ints.max(array)
        );
    }

    @Nullable
    static DistributionStats process(
            @Nonnull final long[] array
    ) {
        if (array.length < 2) return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
                mean,
                calculateSDev(array, mean),
                Longs.min(array),
                Longs.max(array)
        );
    }

    @Nullable
    static DistributionStats process(
            @Nonnull final float[] array
    ) {
        if (array.length < 2) return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
                mean,
                calculateSDev(array, mean),
                Floats.min(array),
                Floats.max(array)
        );
    }

    @Nullable
    static DistributionStats process(
            @Nonnull final double[] array
    ) {
        if (array.length < 2) return null;
        final double mean = ArrayStatistics.calculateMean(array);
        return new DistributionStats(
                mean,
                calculateSDev(array, mean),
                Doubles.min(array),
                Doubles.max(array)
        );
    }

}
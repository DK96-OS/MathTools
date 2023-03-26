package mathtools.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static mathtools.statistics.DistributionStatistics.process;

import org.junit.jupiter.api.Test;

import java.util.Collections;

/** Testing [DistributionStats] Object methods
 * @author DK96-OS : 2022
 */
public final class DistributionStatsObjectMethodsTest {

    private static final DistributionStats mStats1;
    private static final DistributionStats mStats2;
    private static final DistributionStats mStats3;

    static {
        DistributionStats stats = process(new byte[] { 30, 31, 32 });
        assert stats != null;
        mStats1 = stats;
        //
        stats = process(new byte[] { -30, -31, -32 });
        assert stats != null;
        mStats2 = stats;
        //
        stats = process(new byte[] { 29, 31, 33 });
        assert stats != null;
        mStats3 = stats;
    }

    @Test
    void testString() {
        final String s1 = "Mean: 31.0, SD: "
            + mStats1.getStandardDeviation()
            + ", Min: 30.0, Max: 32.0"
            + ", Count: 3";
        assertEquals(
            s1, mStats1.toString()
        );
    }

    @Test
    void testHashCode() {
        final int hash1 = mStats1.hashCode();
        final int hash2 = mStats2.hashCode();
        final int hash3 = mStats3.hashCode();
        assert hash1 != hash2;
        assert hash1 != hash3;
        assert hash2 != hash3;
    }

    @Test
    void testEqualsSelf() {
        assertEquals(mStats1, mStats1);
        assertEquals(mStats2, mStats2);
    }

    @Test
    void testEqualsNotEqual() {
        assertNotEquals(mStats1, mStats2);
        assertNotEquals(mStats1, mStats3);
        //
        assertNotEquals(mStats1, Collections.EMPTY_LIST);
        //
        assertNotEquals(mStats1, null);
    }

    @Test
    void testEquals_DifferentMinValues_ReturnsFalse() {
        DistributionStats stats = new DistributionStats(
            mStats1.getMean(),
            mStats1.getStandardDeviation(),
            mStats1.getMin() + 1,
            mStats1.getMax(),
            mStats1.getCount()
        );
        assertNotEquals(stats, mStats1);
    }

    @Test
    void testEquals_DifferentMaxValues_ReturnsFalse() {
        DistributionStats stats = new DistributionStats(
            mStats1.getMean(),
            mStats1.getStandardDeviation(),
            mStats1.getMin(),
            mStats1.getMax() + 1,
            mStats1.getCount()
        );
        assertNotEquals(stats, mStats1);
    }

    @Test
    void testEquals_DifferentCounts_ReturnsFalse() {
        DistributionStats stats = new DistributionStats(
            mStats1.getMean(),
            mStats1.getStandardDeviation(),
            mStats1.getMin(),
            mStats1.getMax(),
            mStats1.getCount() + 1
        );
        assertNotEquals(stats, mStats1);
    }

}
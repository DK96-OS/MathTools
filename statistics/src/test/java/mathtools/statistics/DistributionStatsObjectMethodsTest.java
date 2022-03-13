package mathtools.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static mathtools.statistics.DistributionStatistics.process;

import org.junit.jupiter.api.Test;

import java.util.List;

/** Testing [DistributionStats] Object methods
 * @author DK96-OS : 2022 */
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
                + ", Min: 30.0, Max: 32.0";
        assertEquals(
                s1, mStats1.toString());
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
        assertTrue(mStats1.equals(mStats1));
        assertTrue(mStats2.equals(mStats2));
    }

    @Test
    void testEqualsNotEqual() {
        assertFalse(mStats1.equals(mStats2));
        assertFalse(mStats1.equals(mStats3));
        //
        assertFalse(mStats1.equals(List.of()));
        //
        assertFalse(mStats1.equals(null));
    }

}
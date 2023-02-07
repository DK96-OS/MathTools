package mathtools.generators.elements.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import javax.annotation.Nonnull;

import mathtools.generators.RandomProvider;
import mathtools.generators.counters.ints.IntCounter127;
import mathtools.generators.counters.ints.IntCounter32000;
import mathtools.generators.counters.ints.IntGeneratorCounter;
import mathtools.statistics.DistributionStats;

/** Measuring the Generated Values of [IntRangeElement]
 * @author DK96-OS : 2022 */
public final class IntRangeElementGeneratedValuesTest {

    private IntGeneratorCounter runner;

    private static final int MIN = Integer.MIN_VALUE;
    private static final int MAX = Integer.MAX_VALUE;

    @AfterEach
    void testCleanup() { runner = null; }

    @ParameterizedTest
    @ValueSource(ints = {
            0, -16, MAX - 15, MIN
    })
    void testRangeSize16(
            final int startValue
    ) {
        // All ranges contain 16 values
        final int end = startValue + 15;  // add 15 because of inclusivity
        if (startValue > end)
            throw new IllegalArgumentException();
        // Initialize runner
        runner = new IntGeneratorCounter(
            new IntRangeElement(startValue, end),
            new IntCounter32000(startValue, end)
        );
        // Counts will depend on the number of generated values, and
        // the size of the range
        final int targetMeanCount = 100;
        final int nValuesGenerated = 16 * targetMeanCount;
        assertTrue(
            runner.countGeneratedValues(nValuesGenerated));
        final List<Integer> results = runner.getCounter().toList();
        assertEquals(
            16, results.size());
        final DistributionStats stats = assertStats(
            results, 50, 150
        );
        assertEquals(
            (double) targetMeanCount, stats.getMean());
        assertEquals(
            9.5, stats.getStandardDeviation(), 5);
    }

    @Test
    void testAllIntegersRange() {
        runner = new IntGeneratorCounter(
            new IntRangeElement(
                MIN, MAX, RandomProvider.fixedValue(MIN)
            ),
            new IntCounter127(MIN, MIN + 3)
        );
        assertTrue(
            runner.countGeneratedValues(10));
        List<Integer> results = runner.getCounter().toList();
        assertEquals(
            4, results.size());
        assertEquals(
            10, results.get(0));
        //
        runner = new IntGeneratorCounter(
            new IntRangeElement(
                MIN, MAX, RandomProvider.fixedValue(MAX)
            ),
            new IntCounter127(MAX - 3, MAX)
        );
        assertTrue(
            runner.countGeneratedValues(10));
        results = runner.getCounter().toList();
        assertEquals(
            4, results.size());
        assertEquals(
            10, results.get(3));
    }

    @Test
    void testOverMaxIntRangeSize() {
        runner = new IntGeneratorCounter(
            new IntRangeElement(
                -1, MAX, RandomProvider.fixedValue(9)
            ),
            new IntCounter127(-1, 20)
        );
        assertTrue(
            runner.countGeneratedValues(10));
        IntCounter127 counter = (IntCounter127) runner.getCounter();
        assertEquals(
            10, (byte) counter.getCountOf(9));
    }

    @Test
    void testOverMaxIntRangeSizeBadRandom() {
        IntRangeElement elem = new IntRangeElement(
            -1, MAX, RandomProvider.fixedValue(-3)
        );
        assertEquals(
            -3, elem.generate());
    }

    @Test
    void testOverMaxIntRangeSizeBadThenGoodRandom() {
        IntRangeElement elem = new IntRangeElement(
            -1, MAX, RandomProvider.firstThenSecondValue(-3, 8)
        );
        assertEquals(
            8, elem.generate());
        // Try with MIN
        elem = new IntRangeElement(
            MIN, 5, RandomProvider.firstThenSecondValue(8, 4)
        );
        assertEquals(
            4, elem.generate());
    }

    /** Verifies that all Integer in this list are non-zero */
    private static boolean allNonZero(
            @Nonnull final List<Integer> list
    ) {
        for (int i = 0; i < list.size(); i++) {
            if (0 == list.get(i)) return false;
        }
        return true;
    }

    /** Checks that the Min and Max values of a collection are within a given range
     * @param list The list to obtain the Statistics of
     * @param min The Minimum allowed value
     * @param max The Maximum allowed value
     * @return The DistributionStats instance that was created */
    private static DistributionStats assertStats(
            @Nonnull final List<Integer> list,
            final int min,
            final int max
    ) {
        final DistributionStats stats =
                DistributionStats.Companion.process(list);
        assert stats != null;
        assert min <= stats.getMin();
        assert max >= stats.getMax();
        return stats;
    }

}

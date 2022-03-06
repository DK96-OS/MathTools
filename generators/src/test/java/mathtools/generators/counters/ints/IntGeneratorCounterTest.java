package mathtools.generators.counters.ints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import mathtools.generators.elements.ints.IntRangeElement;

/** Testing [IntGeneratorCounter]
 * @author DK96-OS : 2022 */
public final class IntGeneratorCounterTest {

    private IntGeneratorCounter mGC;

    @AfterEach
    void testCleanup() { mGC = null; }

    @Test
    void testMismatchedCounter() {
        mGC = new IntGeneratorCounter(
                new IntRangeElement(1, 1),
                new IntCounter32000(10, 20)
        );
        assertFalse(
                mGC.countGeneratedValues(1));
        assertEquals(
                1, mGC.getRejectedValue());
        assertNull(
                mGC.getPreviousValue());
    }

    @Test
    void testSingleValuedCounter() {
        mGC = new IntGeneratorCounter(
                new IntRangeElement(10, 10),
                new IntCounterSingle(10)
        );
        assertTrue(
                mGC.countGeneratedValues(100));
        assertEquals(
                10, mGC.getPreviousValue());
        assertEquals(
                100, ((IntCounterSingle) mGC.getCounter()).getCount());
    }

    @Test
    void testTwoValuedCounter() {
        mGC = new IntGeneratorCounter(
                new IntRangeElement(10, 11),
                new IntCounter32000(10, 11)
        );
        assertTrue(
                mGC.countGeneratedValues(100));
        final IntCounter32000 counter =
                (IntCounter32000) mGC.getCounter();
        final Short tenCount = counter.getCountOf(10);
        final Short elevenCount = counter.getCountOf(11);
        assertNotNull(tenCount);
        assertNotNull(elevenCount);
        assertEquals(100, tenCount + elevenCount);
        assert 0 < tenCount;
        assert 0 < elevenCount;
    }

}